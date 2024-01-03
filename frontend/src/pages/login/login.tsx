import { Button } from "baseui/button";
import { Input } from "baseui/input";
import styled from "styled-components";
import TriangleButton from "../../components/TriangleButton";
import * as Yup from "yup";
import { Modal } from "antd";
import {
  HeadingXXLarge,
  HeadingXLarge,
  HeadingLarge,
  HeadingMedium,
  HeadingSmall,
  HeadingXSmall,
} from "baseui/typography";
import {
  Container,
  ErrorText,
  InnerContainer,
  InputWrapper,
  StyledInput,
} from "../../components/commons";

import { useSignIn } from "react-auth-kit";
import { useFormik } from "formik";
import axios, { AxiosError } from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const validationSchema = Yup.object({
  userName: Yup.string().required("Required"),
  password: Yup.string().required("Required"),
  confirmPassword: Yup.string()
    .oneOf([Yup.ref("password")], "Passwords must match")
    .required("Required"),
});

const URL = "http://localhost:8080/api";

function Login(props: any) {
  const navigate = useNavigate();
  const [error, setError] = useState("");
  const [showLogin, setShowLogin] = useState(true);
  const signIn = useSignIn();
  const [isModalOpen, setIsModalOpen] = useState(false);

  const showModal = () => {
    setIsModalOpen(true);
  };

  const handleOk = () => {
    setIsModalOpen(false);
  };

  const handleCancel = () => {
    setIsModalOpen(false);
  };

  const toggleForm = () => {
    setShowLogin(!showLogin);
  };

  // 登录表单逻辑
  const onSubmitLogin = async (values: any) => {
    setError("");
    try {
      const response = await axios.post(URL + "/user/login", values);
      signIn({
        token: response.data.data.token,
        expiresIn: 3600,
        tokenType: "Bearer",
        authState: { userName: values.userName },
      });
      navigate("/");
    } catch (err) {
      if (err && err instanceof AxiosError)
        setError(err.response?.data.message);
      else if (err && err instanceof Error) setError(err.message);
      console.log("Error: ", err);
    }
  };

  const formikLogin = useFormik({
    initialValues: {
      userName: "",
      password: "",
    },
    onSubmit: onSubmitLogin,
  });

  // 注册表单逻辑
  const onSubmitRegister = async (values: any) => {
    setError("");
    try {
      const response = await axios.post(URL + "/user/register", values);
      if (response.data.code === "0") {
        showModal();
        setShowLogin(true);
      } else {
      }
    } catch (err) {
      if (err && err instanceof AxiosError)
        setError(err.response?.data.message);
      else if (err && err instanceof Error) setError(err.message);
      console.log("Error: ", err);
    }
  };

  const formikRegister = useFormik({
    initialValues: {
      userName: "",
      password: "",
      confirmPassword: "",
    },
    validationSchema,
    onSubmit: onSubmitRegister,
  });

  // 登录表单 JSX
  const LoginForm = (
    <form onSubmit={formikLogin.handleSubmit}>
      <HeadingXXLarge>Welcome Back!</HeadingXXLarge>
      <ErrorText>{error}</ErrorText>
      <InputWrapper>
        <StyledInput
          name="userName"
          value={formikLogin.values.userName}
          onChange={formikLogin.handleChange}
          placeholder="userName"
          clearOnEscape
          size="large"
          type="userName"
        />
      </InputWrapper>
      <InputWrapper>
        <StyledInput
          name="password"
          value={formikLogin.values.password}
          onChange={formikLogin.handleChange}
          placeholder="Password"
          clearOnEscape
          size="large"
          type="password"
        />
      </InputWrapper>
      <InputWrapper>
        <Button
          size="large"
          kind="primary"
          isLoading={formikLogin.isSubmitting}
        >
          Login
        </Button>
      </InputWrapper>
    </form>
  );

  // 注册表单 JSX
  const RegisterForm = (
    <form onSubmit={formikRegister.handleSubmit}>
      <HeadingXXLarge>Welcome Back!</HeadingXXLarge>
      <ErrorText>{error}</ErrorText>
      <InputWrapper>
        <StyledInput
          name="userName"
          value={formikRegister.values.userName}
          onChange={formikRegister.handleChange}
          placeholder="userName"
          clearOnEscape
          size="large"
          type="userName"
        />
      </InputWrapper>
      <InputWrapper>
        <StyledInput
          name="password"
          value={formikRegister.values.password}
          onChange={formikRegister.handleChange}
          placeholder="Password"
          clearOnEscape
          size="large"
          type="password"
        />
      </InputWrapper>
      <InputWrapper>
        <StyledInput
          name="confirmPassword"
          value={formikRegister.values.confirmPassword}
          onChange={formikRegister.handleChange}
          onBlur={formikRegister.handleBlur}
          placeholder="Confirm Password"
          clearOnEscape
          size="large"
          type="password"
        />
      </InputWrapper>
      <div>
        {formikRegister.touched.confirmPassword &&
        formikRegister.errors.confirmPassword ? (
          <div style={{ color: "white", marginTop: "5px" }}>
            {formikRegister.errors.confirmPassword}
          </div>
        ) : null}
      </div>
      <InputWrapper>
        <Button
          size="large"
          kind="primary"
          isLoading={formikRegister.isSubmitting}
        >
          Register
        </Button>
      </InputWrapper>
    </form>
  );

  return (
    <Container>
      <InnerContainer>
        <TriangleButton onClick={toggleForm}></TriangleButton>
        <div>{showLogin ? LoginForm : RegisterForm}</div>
      </InnerContainer>
      <Modal
        title="注册成功！"
        open={isModalOpen}
        onOk={handleOk}
        onCancel={handleCancel}
      >
        <p>注册成功！</p>
      </Modal>
    </Container>
  );
}

export { Login };
