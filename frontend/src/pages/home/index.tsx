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
  Breadcrumb,
  Layout,
  Menu,
  theme,
  Card,
  Space,
  Button,
  Modal,
} from "antd";
import { PlusOutlined } from "@ant-design/icons";
import FurCard from "../../components/FurCard";
import AddFurModal from "../../components/AddFurModal";
import EditFurnitureModal from "../../components/EditFurnitureModal";
import {
  queryFurnitureByUserId,
  addFurniture,
  deleteFurniture,
  updateFurniture,
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
function Home() {
  const authHeader = useAuthHeader();
  const [selectedKey, setSelectedKey] = useState("sub1");
  const [furCards, setFurCards] = useState<FurCardData[]>([]);
  const [selectedFurniture, setSelectedFurniture] =
    useState<FurCardData | null>(null);
  const [isEditModalVisible, setIsEditModalVisible] = useState(false);

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

    fetchFurniture();
  }, []); // Empty dependency array to ensure this runs once on component mount

  return (
    <Layout style={{ minHeight: "100vh" }}>
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
            <div style={{ display: "flex", flexWrap: "wrap", gap: "30px" }}>
              <AddFurModal addFurniture={handleAddFurniture} />
              {furCards.map((furCard, index) => (
                <FurCard
                  key={index}
                  name={furCard.name}
                  type={furCard.type}
                  status={furCard.status}
                  address={furCard.address}
                  id={furCard.id}
                  onEdit={() => showEditModal(furCard)}
                  onDelete={() => handleDeleteFurniture(furCard.id)}
                  onUpdate={handleFurStatusUpdate}
                />
              ))}
            </div>
          )}
          {selectedKey === "sub2" && <div>Content for Menu Item 2</div>}
          {selectedKey === "sub3" && <div>Content for Menu Item 3</div>}
        </Content>
      </Layout>
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
