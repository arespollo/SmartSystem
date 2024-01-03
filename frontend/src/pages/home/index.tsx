import axios from "axios";
// import { Button } from "baseui/button";
import { useEffect } from "react";
import { HeadingXXLarge } from "baseui/typography";
import { useSignOut } from "react-auth-kit";
import { useNavigate } from "react-router-dom";
import { Container } from "../../components/commons";
import {
  LaptopOutlined,
  NotificationOutlined,
  UserOutlined,
} from "@ant-design/icons";
import type { MenuProps } from "antd";
import React, { useState } from "react";
import {
  Layout,
  Menu,
  theme,
  Input,
  Button,
  Modal,
  List,
  Select,
  message,
} from "antd";
import { SearchOutlined } from "@ant-design/icons";
import FurCard from "../../components/FurCard";
import SceneCard from "../../components/SceneCard";
import AddFurModal from "../../components/AddFurModal";
import AddSceneModal from "../../components/AddSceneModal";
import EditFurnitureModal from "../../components/EditFurnitureModal";
import HistoryTable from "../../components/HistoryTable";
import AddSceneFurnitureModal from "../../components/AddSceneFurnitureModal";
import {
  queryFurnitureByUserId,
  queryHistoryByUserId,
  queryScenesByUserId,
  addFurniture,
  addScene,
  delScene,
  getScene,
  deleteFurniture,
  updateFurniture,
  addSceneFurniture,
  deleteSceneFurniture,
  updateSceneFurniture,
} from "../../api/furnitureApi";
import { useAuthHeader } from "react-auth-kit";
const { Header, Content, Footer, Sider } = Layout;

const items1: MenuProps["items"] = [
  { icon: UserOutlined, label: "Main" },
  { icon: LaptopOutlined, label: "Scene" },
  { icon: NotificationOutlined, label: "Notification" },
].map((item, index) => {
  const key = String(index + 1);

  return {
    key: `sub${key}`,
    icon: React.createElement(item.icon),
    label: `${item.label}`,
  };
});
interface FurCardData {
  name: string;
  type: string;
  status: number;
  address: string;
  id: number;
}
interface HistoryRecordData {
  key: string;
  date: string;
  operation: string;
}

interface SceneFurniture {
  id: number;
  sceneId: number;
  furId: number;
  furStatus: number;
  furName: string;
  furType: string;
}

interface SceneData {
  id: number;
  name: string;
  userId: number;
  status: number;
  sceneFurnitureList: SceneFurniture[];
}

interface FurnitureData {
  key: React.Key;
  name: string;
  type: string;
  id: number;
}

function Home() {
  const authHeader = useAuthHeader();
  const [messageApi, contextHolder] = message.useMessage();

  const [furTypeFilter, setFurTypeFilter] = useState("");
  const [furStatusFilter, setFurStatusFilter] = useState("");
  const [furNameSearch, setFurNameSearch] = useState("");
  const [sceneNameSearch, setSceneNameSearch] = useState("");

  const [scenes, setScenes] = useState<SceneData[]>([]);
  const [historyData, setHistoryData] = useState<HistoryRecordData[]>([]);
  const [selectedKey, setSelectedKey] = useState("sub1");
  const [furCards, setFurCards] = useState<FurCardData[]>([]);
  const [selectedFurniture, setSelectedFurniture] =
    useState<FurCardData | null>(null);
  const [isEditModalVisible, setIsEditModalVisible] = useState(false);

  const [isAddSceneFurnitureModalVisible, setIsAddSceneFurnitureModalVisible] =
    useState(false);
  const [selectedSceneId, setSelectedSceneId] = useState<number | null>(null);
  const [availableFurCards, setAvailableFurCards] = useState<FurnitureData[]>(
    []
  );

  const singOut = useSignOut();
  const navigate = useNavigate();
  const logout = () => {
    singOut();
    navigate("/login");
  };

  const {
    token: { colorBgContainer },
  } = theme.useToken();

  const handleMenuSelect = (menuItem: any) => {
    setSelectedKey(menuItem.key);
    console.log(menuItem.key);
  };
  const handleAddFurniture = async (newFurniture: FurCardData) => {
    try {
      const token = authHeader();
      newFurniture.status = 0;
      const response = await addFurniture(newFurniture, token); // Make the API call

      // Check if the response is successful and add the new furniture to state
      if (response && response.code === "0") {
        setFurCards([...furCards, response.data]); // Update the state with the new furniture
      } else {
        // Handle the case where the API call was not successful
        console.error("Failed to add furniture:", response);
      }
    } catch (error) {
      // Handle any errors during the API call
      console.error("Error adding furniture:", error);
    }
  };

  const showEditModal = (furniture: FurCardData) => {
    setSelectedFurniture(furniture);
    setIsEditModalVisible(true);
  };

  const handleDeleteFurniture = async (id: number) => {
    Modal.confirm({
      title: "Are you sure you want to delete this furniture?",
      onOk: async () => {
        const token = authHeader(); // Retrieve the token
        const furnitureToDelete = furCards.find((furCard) => furCard.id === id);

        if (furnitureToDelete) {
          try {
            const response = await deleteFurniture(furnitureToDelete, token);
            if (response && response.code === "0") {
              // Update the state to remove the deleted furniture
              const updatedFurCards = furCards.filter(
                (furCard) => furCard.id !== id
              );
              setFurCards(updatedFurCards);
            } else {
              // Handle the case where the API call was not successful
              console.error("Failed to delete furniture:", response);
            }
          } catch (error) {
            // Handle any errors during the API call
            console.error("Error deleting furniture:", error);
          }
        }
      },
    });
  };

  const handleFurnitureUpdate = async (updatedFurniture: FurCardData) => {
    try {
      const token = authHeader(); // Retrieve the token
      console.log(updatedFurniture);
      const response = await updateFurniture(updatedFurniture, token); // Make the API call

      // Check if the response is successful and update the state
      if (response && response.code === "0") {
        const updatedFurCards = furCards.map((furCard) => {
          if (furCard.id === updatedFurniture.id) {
            return response.data; // Use the updated data from the response
          }
          return furCard;
        });
        setFurCards(updatedFurCards); // Update the state with the updated furniture
        setIsEditModalVisible(false); // Close the modal
      } else {
        // Handle the case where the API call was not successful
        console.error("Failed to update furniture:", response);
      }
    } catch (error) {
      // Handle any errors during the API call
      console.error("Error updating furniture:", error);
    }
  };

  const handleFurStatusUpdate = async (updatedFurniture: FurCardData) => {
    try {
      const token = authHeader(); // Retrieve the token
      console.log(updatedFurniture);
      const response = await updateFurniture(updatedFurniture, token); // Make the API call

      // Check if the response is successful and update the state
      if (response && response.code === "0") {
        const updatedFurCards = furCards.map((furCard) => {
          if (furCard.id === updatedFurniture.id) {
            return response.data; // Use the updated data from the response
          }
          return furCard;
        });
        setFurCards(updatedFurCards); // Update the state with the updated furniture
      } else {
        // Handle the case where the API call was not successful
        console.error("Failed to update furniture:", response);
      }
    } catch (error) {
      // Handle any errors during the API call
      console.error("Error updating furniture:", error);
    }
  };

  useEffect(() => {
    const fetchFurniture = async () => {
      try {
        const token = authHeader();
        const response = await queryFurnitureByUserId(token);
        console.log(response.data);
        if (response && Array.isArray(response.data)) {
          setFurCards(response.data); // Use response.data which is the actual array
        } else {
          // Handle the case where response.data is not an array
          console.error(
            "Expected an array of furniture, but received:",
            response
          );
          setFurCards([]); // You can set it to an empty array or a default value
        }
      } catch (error) {
        console.error("Error fetching furniture:", error);
      }
    };

    if (selectedKey === "sub1") {
      fetchFurniture();
    }

    const fetchScenes = async () => {
      const token = authHeader(); // Replace with your method of getting the auth token
      try {
        const response = await queryScenesByUserId(token);
        if (response.code === "0") {
          setScenes(response.data);
        } else {
          console.error("Failed to fetch scenes:", response.msg);
        }
      } catch (error) {
        console.error("Error fetching scenes:", error);
      }
    };

    if (selectedKey === "sub2") {
      fetchScenes();
    }

    const fetchHistory = async () => {
      const token = authHeader();
      try {
        const response = await queryHistoryByUserId(token);
        if (response && response.code === "0" && Array.isArray(response.data)) {
          console.log(response.data);
          const modifiedHistoryData = response.data.map(
            (record: HistoryRecordData, index: number) => ({
              ...record,
              key: index + 1, // Use the index (plus 1) as the key
            })
          );
          setHistoryData(modifiedHistoryData);
        } else {
          // Handle the case where response is not as expected
          console.error("Unexpected response format:", response);
        }
      } catch (error) {
        console.error("Error fetching history:", error);
      }
    };

    if (selectedKey === "sub3") {
      fetchHistory();
    }
  }, [selectedKey]); // Empty dependency array to ensure this runs once on component mount

  const handleSceneStatusChange = async (sceneId: number) => {
    const token = authHeader();
    const currentScene = scenes.find((scene) => scene.id === sceneId);

    if (!currentScene) {
      console.error("Scene not found");
      return;
    }

    const hideLoading = message.loading("Action in progress..", 0);

    for (const furniture of currentScene.sceneFurnitureList) {
      try {
        const updatedFurniture = {
          id: furniture.furId,
          name: furniture.furName,
          type: furniture.furType,
          status: furniture.furStatus,
        };
        const response = await updateFurniture(updatedFurniture, token);
        if (response.code !== "0") {
          console.error("Failed to update furniture status:", response);
        }
      } catch (error) {
        console.error("Error updating furniture status:", error);
      }
    }
    hideLoading();
    messageApi.open({
      type: "success",
      content: "Turn scene on successfully!",
    });
  };

  const handleUpdateSceneFurniture = async (
    furniture: SceneFurniture,
    checked: boolean
  ) => {
    const updatedStatus = checked ? 1 : 0;
    const updatedFurniture = { ...furniture, furStatus: updatedStatus };
    const token = authHeader();

    try {
      const response = await updateSceneFurniture(updatedFurniture, token);
      if (response && response.code === "0") {
        const updatedScenes = scenes.map((scene) => {
          if (scene.id === updatedFurniture.sceneId) {
            return {
              ...scene,
              sceneFurnitureList: scene.sceneFurnitureList.map((sf) =>
                sf.id === updatedFurniture.id ? updatedFurniture : sf
              ),
            };
          }
          return scene;
        });
        setScenes(updatedScenes); // Update
      } else {
        console.error("Failed to update scene furniture status:", response);
      }
    } catch (error) {
      console.error("Error updating scene furniture status:", error);
    }
  };

  const handleDeleteSceneFurniture = async (
    id: number,
    sceneId: number,
    furnitureId: number
  ) => {
    Modal.confirm({
      title: "Are you sure you want to delete this furniture in the scene?",
      onOk: async () => {
        try {
          const token = authHeader();
          const sceneFurnitureToDelete = {
            sceneId: sceneId,
            furId: furnitureId,
            id: id,
            furStatus: 1,
            furName: "",
            furType: "",
          };

          const response = await deleteSceneFurniture(
            sceneFurnitureToDelete,
            token
          );

          if (response && response.code === "0") {
            // Successfully deleted scene furniture
            // Now, update the scenes state to reflect this change
            const updatedScenes = scenes.map((scene) => {
              if (scene.id === sceneId) {
                return {
                  ...scene,
                  sceneFurnitureList: scene.sceneFurnitureList.filter(
                    (sf) => sf.furId !== furnitureId
                  ),
                };
              }
              return scene;
            });
            setScenes(updatedScenes);
          } else {
            console.error("Failed to delete scene furniture:", response);
          }
        } catch (error) {
          console.error("Error deleting scene furniture:", error);
        }
      },
    });
  };

  const handleAddSceneFurniture = (sceneId: number) => {
    const currentScene = scenes.find((scene) => scene.id === sceneId);
    if (currentScene) {
      setSelectedSceneId(sceneId);
      // Map the available furniture to include a 'key' property
      const mappedAvailableFurCards = furCards
        .filter(
          (furCard) =>
            !currentScene.sceneFurnitureList.some(
              (sceneFur) => sceneFur.furId === furCard.id
            )
        )
        .map((furCard) => ({ ...furCard, key: furCard.id }));

      setAvailableFurCards(mappedAvailableFurCards);
      setIsAddSceneFurnitureModalVisible(true);
    }
  };

  const handleCloseAddSceneFurnitureModal = () => {
    setIsAddSceneFurnitureModalVisible(false);
    setSelectedSceneId(null);
  };

  const handleAddFurnitureToScene = async (selectedFurnitureIds: number[]) => {
    const token = authHeader();
    const currentSceneId = selectedSceneId || 0;
    let allFurnitureAddedSuccessfully = true;

    // Loop through each selected furniture ID and add it to the scene
    for (const furId of selectedFurnitureIds) {
      let sceneFurnitureData = {
        id: 0,
        sceneId: currentSceneId,
        furId: furId,
        furStatus: 1,
        furName: "",
        furType: "",
      };
      try {
        const response = await addSceneFurniture(sceneFurnitureData, token);
        if (response.code !== "0") {
          allFurnitureAddedSuccessfully = false;
          console.error("Failed to add furniture to scene:", response);
          // Optionally, handle the failure case (e.g., show a message to the user)
        }
      } catch (error) {
        allFurnitureAddedSuccessfully = false;
        console.error("Error adding furniture to scene:", error);
      }
    }

    if (allFurnitureAddedSuccessfully) {
      try {
        const updatedSceneResponse = await getScene(currentSceneId, token);
        if (updatedSceneResponse.code === "0") {
          setScenes(
            scenes.map((scene) =>
              scene.id === currentSceneId ? updatedSceneResponse.data : scene
            )
          );
        }
      } catch (error) {
        console.error("Error fetching updated scene details:", error);
      }
    }
  };

  const handleDeleteScene = async (id: number) => {
    Modal.confirm({
      title: "Are you sure you want to delete this scene?",
      onOk: async () => {
        const token = authHeader(); // Retrieve the token
        const sceneToDelete = scenes.find((scene) => scene.id === id);

        if (sceneToDelete) {
          try {
            const response = await delScene(sceneToDelete, token);
            if (response && response.code === "0") {
              // Update the state to remove the deleted scene
              const updatedScenes = scenes.filter((scene) => scene.id !== id);
              setScenes(updatedScenes);
            } else {
              // Handle the case where the API call was not successful
              console.error("Failed to delete scene:", response);
            }
          } catch (error) {
            // Handle any errors during the API call
            console.error("Error deleting scene:", error);
          }
        }
      },
    });
  };

  const handleAddScene = async (sceneName: string) => {
    try {
      const token = authHeader();
      let newScene = {
        name: sceneName,
        id: 0,
        userId: 0,
        status: 0,
        sceneFurnitureList: [],
      };
      const response = await addScene(newScene, token);

      if (response && response.code === "0") {
        setScenes([...scenes, response.data]);
      } else {
        console.error("Failed to add scene:", response);
      }
    } catch (error) {
      console.error("Error adding scene:", error);
    }
  };

  const filteredFurCards = furCards.filter((card) => {
    const matchesType = furTypeFilter === "" || card.type === furTypeFilter;
    const matchesStatus =
      furStatusFilter === "" || card.status.toString() === furStatusFilter;
    const matchesName = card.name
      .toLowerCase()
      .includes(furNameSearch.toLowerCase());

    return matchesType && matchesStatus && matchesName;
  });

  const filteredScenes = scenes.filter((scene) =>
    scene.name.toLowerCase().includes(sceneNameSearch.toLowerCase())
  );

  return (
    <Layout style={{ minHeight: "100vh" }}>
      {contextHolder}
      <Layout style={{ padding: "15px 0", background: colorBgContainer }}>
        <Sider style={{ background: colorBgContainer }} width={200}>
          <div
            style={{ display: "flex", flexDirection: "column", height: "100%" }}
          >
            <Menu
              mode="inline"
              defaultSelectedKeys={["sub1"]}
              defaultOpenKeys={["sub1"]}
              style={{ flex: 1 }} // Menu takes the available space
              items={items1}
              onSelect={handleMenuSelect}
            />
            <Button
              onClick={logout}
              style={{ margin: "10px", alignSelf: "center", width: "75%" }} // Center the button
            >
              Logout
            </Button>
          </div>
        </Sider>
        <Content style={{ padding: "0 24px" }}>
          {selectedKey === "sub1" && (
            <div>
              <AddFurModal addFurniture={handleAddFurniture} />
              <div style={{ display: "flex", gap: "10px", margin: "20px 0px" }}>
                <Select
                  placeholder="Filter by Type"
                  style={{ width: 200 }}
                  onChange={setFurTypeFilter}
                  allowClear
                >
                  <Select.Option value="Television">Television</Select.Option>
                  <Select.Option value="AirConditioner">
                    Air Conditioner
                  </Select.Option>
                  <Select.Option value="Lamp">Lamp</Select.Option>
                  <Select.Option value="Router">Router</Select.Option>
                  <Select.Option value="">All</Select.Option>
                </Select>
                <Select
                  placeholder="Filter by Status"
                  style={{ width: 200 }}
                  onChange={setFurStatusFilter}
                  allowClear
                >
                  <Select.Option value="1">On</Select.Option>
                  <Select.Option value="0">Off</Select.Option>
                  <Select.Option value="">All</Select.Option>
                </Select>
                <Input
                  placeholder="Search by Name"
                  style={{ width: 200 }}
                  onChange={(e) => setFurNameSearch(e.target.value)}
                />
              </div>
              <div style={{ display: "flex", flexWrap: "wrap", gap: "30px" }}>
                <List
                  grid={{
                    gutter: 16,
                    column:
                      filteredFurCards.length < 4 ? filteredFurCards.length : 4,
                  }}
                  dataSource={filteredFurCards}
                  renderItem={(item) => (
                    <List.Item>
                      <FurCard
                        name={item.name}
                        type={item.type}
                        status={item.status}
                        address={item.address}
                        id={item.id}
                        onEdit={() => showEditModal(item)}
                        onDelete={() => handleDeleteFurniture(item.id)}
                        onUpdate={handleFurStatusUpdate}
                      />
                    </List.Item>
                  )}
                />
              </div>
            </div>
          )}
          {selectedKey === "sub2" && (
            <div>
              <AddSceneModal addScene={handleAddScene} />
              <div
                style={{
                  padding: "10px",
                  display: "flex",
                  justifyContent: "flex-start",
                }}
              >
                <Input
                  placeholder="Search by Scene Name"
                  style={{ width: 300 }}
                  onChange={(e) => setSceneNameSearch(e.target.value)}
                />
              </div>
              <div style={{ display: "flex", flexWrap: "wrap", gap: "30px" }}>
                <List
                  grid={{ gutter: 16, column: 2 }} // Customize as needed
                  dataSource={filteredScenes}
                  renderItem={(scene) => (
                    <List.Item key={scene.id}>
                      <SceneCard
                        name={scene.name}
                        id={scene.id}
                        furnitureData={scene.sceneFurnitureList}
                        onFurAdd={() => handleAddSceneFurniture(scene.id)}
                        onFurDelete={handleDeleteSceneFurniture}
                        onSceneDelete={() => handleDeleteScene(scene.id)}
                        onFurStatusChange={handleUpdateSceneFurniture}
                        onSceneStatusChange={() =>
                          handleSceneStatusChange(scene.id)
                        }
                      />
                    </List.Item>
                  )}
                />
              </div>
            </div>
          )}
          {selectedKey === "sub3" && (
            <div>
              <p>History Record</p>

              <HistoryTable data={historyData} />
            </div>
          )}
        </Content>
      </Layout>

      {isAddSceneFurnitureModalVisible && selectedSceneId && (
        <AddSceneFurnitureModal
          visible={isAddSceneFurnitureModalVisible}
          furCards={availableFurCards}
          sceneFurnitureList={
            scenes
              .find((scene) => scene.id === selectedSceneId)
              ?.sceneFurnitureList.map((sceneFur) => ({
                key: sceneFur.furId,
                name: sceneFur.furName,
                type: sceneFur.furType,
                id: sceneFur.furId,
              })) || []
          }
          onClose={handleCloseAddSceneFurnitureModal}
          onAddFurnitureToScene={handleAddFurnitureToScene}
        />
      )}

      {selectedFurniture && (
        <EditFurnitureModal
          furniture={selectedFurniture}
          visible={isEditModalVisible}
          onUpdate={handleFurnitureUpdate}
          onCancel={() => setIsEditModalVisible(false)}
        />
      )}
    </Layout>
  );
}

export { Home };
