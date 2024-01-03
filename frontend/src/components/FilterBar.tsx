import React from "react";
import { Row, Col, Select, Input, Button } from "antd";

const { Option } = Select;

interface FilterBarProps {
  onTypeChange: (value: string) => void;
  onStatusChange: (value: string) => void;
  onSearch: (value: string) => void;
  onReset: () => void;
}

const FilterBar: React.FC<FilterBarProps> = ({
  onTypeChange,
  onStatusChange,
  onSearch,
  onReset,
}) => {
  return (
    <Row gutter={16} style={{ marginBottom: 16 }}>
      <Col>
        <Select
          placeholder="Select a type"
          style={{ width: 200 }}
          onChange={onTypeChange}
        >
          {/* Add options dynamically or statically */}
          <Option value="type1">Type 1</Option>
          <Option value="type2">Type 2</Option>
        </Select>
      </Col>
      <Col>
        <Select
          placeholder="Select a status"
          style={{ width: 200 }}
          onChange={onStatusChange}
        >
          <Option value="active">Active</Option>
          <Option value="inactive">Inactive</Option>
        </Select>
      </Col>
      <Col>
        <Input.Search
          placeholder="Search..."
          onSearch={onSearch}
          style={{ width: 200 }}
        />
      </Col>
      <Col>
        <Button onClick={onReset}>Reset Filters</Button>
      </Col>
    </Row>
  );
};

export default FilterBar;
