import React, { useState } from "react";
import { Modal, Input, Button } from "antd";
import { PlusOutlined } from "@ant-design/icons";

interface AddSceneModalProps {
  addScene: (sceneName: string) => void;
}

const AddSceneModal: React.FC<AddSceneModalProps> = ({ addScene }) => {
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [sceneName, setSceneName] = useState("");

  const showModal = () => {
    setIsModalVisible(true);
  };

  const handleOk = () => {
    addScene(sceneName);
    setIsModalVisible(false);
    setSceneName("");
  };

  const handleCancel = () => {
    setIsModalVisible(false);
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSceneName(e.target.value);
  };

  return (
    <>
      <div style={{ position: "fixed", bottom: "125px", right: "40px" }}>
        <Button
          shape="circle"
          icon={<PlusOutlined style={{ fontSize: "60px" }} />}
          style={{ width: "80px", height: "80px" }}
          onClick={showModal}
        />
      </div>
      <Modal
        title="Add New Scene"
        open={isModalVisible}
        onOk={handleOk}
        onCancel={handleCancel}
      >
        <Input
          placeholder="Enter Scene Name"
          value={sceneName}
          onChange={handleInputChange}
        />
      </Modal>
    </>
  );
};

export default AddSceneModal;
