import React from "react";
import { Card, Switch } from "antd";

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
  const handleEditClick = (
    e: React.MouseEvent<HTMLAnchorElement, MouseEvent>
  ) => {
    e.preventDefault(); // Prevent default anchor behavior
    onEdit();
  };

  const handleDeleteClick = (
    e: React.MouseEvent<HTMLAnchorElement, MouseEvent>
  ) => {
    e.preventDefault(); // Prevent default anchor behavior
    onDelete();
  };

  const handleSwitch = (checked: boolean) => {
    onUpdate({ name, type, status: checked ? 1 : 0, address, id: id });
  };

  return (
    <Card
      title={name}
      extra={
        <>
          <a href="#" onClick={handleDeleteClick}>
            Delete
          </a>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <a href="#" onClick={handleEditClick}>
            Edit
          </a>
        </>
      }
      style={{ width: 300 }}
    >
      <p>Type: {type}</p>
      <p>Status: {status === 1 ? "On" : "Off"}</p>
      <Switch defaultChecked={status === 1} onChange={handleSwitch} />
    </Card>
  );
};

export default FurCard;
