import React from "react";
import { Modal, Table } from "antd";
import type { ColumnsType } from "antd/es/table";
import { useState } from "react";
interface FurnitureData {
  key: React.Key;
  name: string;
  type: string;
  id: number;
}

interface AddSceneFurnitureModalProps {
  visible: boolean;
  furCards: FurnitureData[];
  sceneFurnitureList: FurnitureData[];
  onClose: () => void;
  onAddFurnitureToScene: (selectedFurniture: number[]) => void;
}

const AddSceneFurnitureModal: React.FC<AddSceneFurnitureModalProps> = ({
  visible,
  furCards,
  sceneFurnitureList,
  onClose,
  onAddFurnitureToScene,
}) => {
  // Filter out furniture that is already in the scene
  const availableFurniture = furCards.filter(
    (furCard) =>
      !sceneFurnitureList.some((sceneFur) => sceneFur.id === furCard.id)
  );

  const [selectedFurnitureIds, setSelectedFurnitureIds] = useState<number[]>(
    []
  );

  const columns: ColumnsType<FurnitureData> = [
    {
      title: "Name",
      dataIndex: "name",
    },
    {
      title: "Type",
      dataIndex: "type",
    },
  ];

  const rowSelection = {
    onChange: (selectedRowKeys: React.Key[]) => {
      setSelectedFurnitureIds(selectedRowKeys as number[]);
    },
  };

  const handleOk = () => {
    onAddFurnitureToScene(selectedFurnitureIds);
    onClose(); // Close the modal after adding furniture to the scene
  };

  return (
    <Modal
      title="Add Furniture to Scene"
      open={visible}
      onOk={handleOk}
      onCancel={onClose}
    >
      <Table
        rowSelection={{
          type: "checkbox",
          ...rowSelection,
        }}
        columns={columns}
        dataSource={availableFurniture}
      />
    </Modal>
  );
};

export default AddSceneFurnitureModal;
