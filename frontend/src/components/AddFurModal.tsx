import React, { useState } from "react";
import { Modal, Input, Select, Button } from "antd";
import { PlusOutlined } from "@ant-design/icons";

const { Option } = Select;

interface FurCardData {
  name: string;
  type: string;
  status: number;
  address: string;
  id: number;
}

interface AddFurnitureModalProps {
  addFurniture: (furniture: FurCardData) => void;
}

const AddFurModal: React.FC<AddFurnitureModalProps> = ({ addFurniture }) => {
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [formData, setFormData] = useState<FurCardData>({
    name: "",
    type: "",
    status: 0,
    address: "",
    id: 0,
  });

  const showModal = () => {
    setIsModalVisible(true);
  };

  const handleOk = () => {
    addFurniture(formData);
    setIsModalVisible(false);
    setFormData({ name: "", type: "", status: 0, address: "", id: 0 });
  };

  const handleCancel = () => {
    setIsModalVisible(false);
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSelectChange = (name: keyof FurCardData) => (value: string) => {
    setFormData({ ...formData, [name]: value });
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
        title="Add New Furniture"
        open={isModalVisible}
        onOk={handleOk}
        onCancel={handleCancel}
      >
        <Input
          placeholder="Enter Furniture Name"
          name="name"
          value={formData.name}
          onChange={handleInputChange}
        />
        <Select
          placeholder="Select Type"
          onChange={handleSelectChange("type")}
          style={{ width: "100%", marginTop: "10px" }}
        >
          <Option value="Television">Television</Option>
          <Option value="AirConditioner">Air Conditioner</Option>
          <Option value="Lamp">Lamp</Option>
          <Option value="Router">Router</Option>
        </Select>
        {/* <Select
          placeholder="Select Room"
          onChange={handleSelectChange("address")}
          style={{ width: "100%", marginTop: "10px" }}
        >
          <Option value="Bedroom">Bedroom</Option>
          <Option value="LivingRoom">Living Room</Option>
          <Option value="Kitchen">Kitchen</Option>
          <Option value="Bathroom">Bathroom</Option>
        </Select> */}
        {/* Similar inputs for status and address */}
      </Modal>
    </>
  );
};

export default AddFurModal;
