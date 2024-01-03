import React, { useState, useEffect } from "react";
import { Modal, Input, Select } from "antd";

const { Option } = Select;

interface FurCardData {
  name: string;
  type: string;
  status: number;
  address: string;
  id: number;
}

interface EditFurnitureModalProps {
  furniture: FurCardData;
  visible: boolean;
  onUpdate: (updatedFurniture: FurCardData) => void;
  onCancel: () => void;
}

const EditFurnitureModal: React.FC<EditFurnitureModalProps> = ({
  furniture,
  visible,
  onUpdate,
  onCancel,
}) => {
  const [formData, setFormData] = useState<FurCardData>(furniture);

  useEffect(() => {
    setFormData(furniture);
  }, [furniture]);

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSelectChange = (name: keyof FurCardData) => (value: string) => {
    setFormData({ ...formData, [name]: value });
    console.log(name, value);
  };

  const handleUpdate = () => {
    onUpdate(formData);
  };

  return (
    <Modal
      title="Edit Furniture"
      open={visible}
      onOk={handleUpdate}
      onCancel={onCancel}
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
      <Select
        placeholder="Select Status"
        onChange={handleSelectChange("status")}
        style={{ width: "100%", marginTop: "10px" }}
      >
        <Option value="1">On</Option>
        <Option value="0">Off</Option>
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
    </Modal>
  );
};

export default EditFurnitureModal;
