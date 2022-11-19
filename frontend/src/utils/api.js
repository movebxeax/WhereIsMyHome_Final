import axios from "axios";

const base = "http://localhost:8080";

const httpQna = axios.create({
  baseURL: base + "/api/qna",
});
const httpUser = axios.create({
  baseURL: base + "/api/user",
});

export { httpQna, httpUser };
