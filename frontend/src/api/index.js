import axios from "axios";

// local vue api axios instance
const BASE_URL = "http://localhost:8080";

function apiQna() {
  const instance = axios.create({
    baseURL: BASE_URL + "/api/qna",
  });
  return instance;
}

function apiTrade() {
  const instance = axios.create({
    baseURL: BASE_URL + "/api/trade",
  });
  return instance;
}

function apiAddress() {
  const instance = axios.create({
    baseURL: BASE_URL + "/api/address",
  });
  return instance;
}

export { apiQna, apiTrade, apiAddress };
