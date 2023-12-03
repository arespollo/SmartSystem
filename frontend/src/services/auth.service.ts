import axios from "axios";

const API_URL = "http://localhost:8080/api/user/";

class AuthService {
  login(userName: string, password: string) {
    return axios
      .post(API_URL + "login", {
        username: userName,
        password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem("user");
  }

  register(userName: string, password: string) {
    return axios.post(API_URL + "register", {
      userName,
      password
    });
  }

  getCurrentUser() {
    const userStr = localStorage.getItem("user");
    if (userStr) return JSON.parse(userStr);

    return null;
  }
}

export default new AuthService();