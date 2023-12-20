import React from "react";
import styled from "styled-components";
import { useNavigate } from "react-router-dom";

const TriangleButton = styled.button`
  width: 50px;
  height: 50px;
  background-color: white;
  color: black;
  clip-path: polygon(0% 0%, 0% 100%, 100% 100%);
  border: none;
  cursor: pointer;
`;

export default TriangleButton;
