import React from "react";
import { Card, Table, Button, Switch } from "antd";
import { ColumnType } from "antd/es/table";

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

interface SceneCardProps {
  name: string;
  id: number;
  furnitureData: SceneFurniture[];
  onFurAdd: () => void;
  onFurDelete: (id: number, sceneId: number, furnitureId: number) => void;
  onSceneDelete: () => void;
  onFurStatusChange: (fur: SceneFurniture, status: boolean) => void;
  onSceneStatusChange: () => void;
}

const SceneCard: React.FC<SceneCardProps> = ({
  name,
  id,
  furnitureData,
  onFurAdd: onFurAdd,
  onSceneDelete: onSceneDelete,
  onFurStatusChange: onFurStatusChange,
  onFurDelete: onFurDelete,
  onSceneStatusChange: onSceneStatusChange,
}) => {
  const columns: ColumnType<SceneFurniture>[] = [
    {
      title: "Name",
      dataIndex: "furName",
      key: "name",
    },
    {
      title: "Type",
      dataIndex: "furType",
      key: "type",
    },
    {
      title: "Status",
      dataIndex: "furStatus",
      key: "status",
      render: (status: number) => (status === 1 ? "On" : "Off"),
    },
    {
      title: "Delete",
      key: "delete",
      render: (text: string, record: SceneFurniture) => (
        <Button onClick={() => handleFurnitureDelete(record)}>Delete</Button>
      ),
    },
    {
      title: "Switch",
      key: "switch",
      render: (_, record: SceneFurniture) => (
        <Switch
          checked={record.furStatus === 1}
          onChange={(checked) => onFurStatusChange(record, checked)}
        />
      ),
    },
  ];

  const handleFurnitureDelete = (furniture: SceneFurniture) => {
    onFurDelete(furniture.id, furniture.sceneId, furniture.furId);
  };

  return (
    <Card
      title={name}
      actions={[
        <Button onClick={onSceneDelete}>Del</Button>,
        <Button onClick={onFurAdd}>Add</Button>,
        <Button onClick={onSceneStatusChange}>Turn On</Button>,
      ]}
      style={{ width: 500 }}
    >
      <div style={{ overflowX: "auto" }}>
        <Table
          dataSource={furnitureData}
          columns={columns}
          pagination={false}
          style={{ maxWidth: "100%", minHeight: 220 }} // Set max width for the Table
        />
      </div>
    </Card>
  );
};

export default SceneCard;
