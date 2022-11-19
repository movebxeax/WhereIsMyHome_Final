import axios from "axios";
import store from "@/store/index.js";
import Constant from "@/utils/constant";

const base = "http://localhost:8080";

const httpQna = axios.create({
  baseURL: base + "/api/qna",
});
const httpUser = axios.create({
  baseURL: base + "/api/user",
});

function parseJwt(token) {
  var base64Url = token.split(".")[1];
  var base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
  var jsonPayload = decodeURIComponent(
    window
      .atob(base64)
      .split("")
      .map(function (c) {
        return "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2);
      })
      .join("")
  );

  return JSON.parse(jsonPayload);
}

function setSignOutStatus() {
  store.dispatch("userStore/" + [Constant.SAVE_TOKENS], { accessToken: "", refreshToken: "" });
  store.dispatch("userStore/" + [Constant.SET_LOGIN_STATE], false);
  store.dispatch("userStore/" + [Constant.SET_USER_INFO], { username: "" });
}

httpQna.interceptors.request.use(
  (config) => {
    if (!config.headers.Authorization) {
      const accessToken = store.getters["userStore/accessToken"];
      if (!accessToken && accessToken.length > 0) {
        const accessTokenJwtPayload = parseJwt(accessToken);

        if (accessTokenJwtPayload.exp >= Date.now() / 1000) {
          config.headers.Authorization = `Bearer ${accessToken}`;
        } else {
          console.log("JwtToken Expired. Refresh with RefreshToken");
          const refreshToken = store.getters["userStore/refreshToken"];
          if (!refreshToken || refreshToken.length > 0) {
            const refreshTokenJwtPayload = parseJwt(refreshToken);

            if (refreshTokenJwtPayload.exp >= Date.now() / 1000) {
              const refreshData = {
                refreshToken: refreshToken,
                userid: store.getters["userStore/userInfo"].username,
              };
              console.log(refreshData);
              config.headers.Authorization = `Bearer ${refreshToken}`;
              httpUser
                .post("/refresh", refreshData)
                .then(({ data }) => {
                  console.log("JwtToken refreshed");
                  store.dispatch("userStore/" + [Constant.SAVE_TOKENS], { accessToken: data.accessToken, refreshToken: data.refreshToken });
                  store.dispatch("userStore/" + [Constant.SET_LOGIN_STATE], true);
                  store.dispatch("userStore/" + [Constant.SET_USER_INFO], { username: data.userid });
                })
                .catch(() => {
                  console.log("Refresh Token Rejected");
                  setSignOutStatus();
                });
            } else {
              console.log("Invalid Refresh Token");
              setSignOutStatus();
            }
          } else {
            console.log("Refresh Token Doesn't Exist");
            setSignOutStatus();
          }
        }
      }
    }
    return config;
  },
  function (error) {
    console.log("error, rejected");
    setSignOutStatus();
    return Promise.reject(error);
  }
);

export { httpQna, httpUser };
