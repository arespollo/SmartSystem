import React from "react";
import { Card, Switch, Button } from "antd";

interface FurCardData {
  name: string;
  type: string;
  status: number;
  address: string;
  id: number;
}
interface FurnitureCardProps {
  name: string;
  type: string;
  status: number;
  id: number;
  address: string;
  onEdit: () => void;
  onDelete: () => void;
  onUpdate: (updatedFurniture: FurCardData) => void;
}

const FurCard: React.FC<FurnitureCardProps> = ({
  name,
  type,
  status,
  address,
  id,
  onEdit,
  onDelete,
  onUpdate,
}) => {
  const handleSwitch = (checked: boolean) => {
    onUpdate({ name, type, status: checked ? 1 : 0, address, id: id });
  };

  return (
    <Card
      title={name}
      actions={[
        // Add any actions like Edit, Delete here
        <Button onClick={onEdit}>Edit</Button>,
        <Button onClick={onDelete}>Delete</Button>,

        <Switch checked={status === 1} onChange={handleSwitch} />,
      ]}
      // extra={
      //   <>
      //     <a href="#" onClick={handleDeleteClick}>
      //       Delete
      //     </a>
      //     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      //     <a href="#" onClick={handleEditClick}>
      //       Edit
      //     </a>
      //   </>
      // }
      style={{ width: 280 }}
    >
      <p>Type: {type}</p>
      <p>Status: {status === 1 ? "On" : "Off"}</p>
    </Card>
  );
};

export default FurCard;
