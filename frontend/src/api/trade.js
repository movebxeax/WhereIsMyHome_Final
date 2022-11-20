import { apiAddress, apiTrade } from "@/api/index.js";

// const api = apiTrade();
const addressApi = apiAddress();
const tradeApi = apiTrade();

function searchOptionList(param, success, fail) {
  return addressApi.get("", { params: param }).then(success).catch(fail);
}

function aptList(dongcode, success, fail) {
  return tradeApi.get(`/list/${dongcode}`).then(success).catch(fail);
}

function aptListWithCds(params, success, fail) {
  return tradeApi.get(`/apt`, { params: params }).then(success).catch(fail);
}

export { searchOptionList, aptList, aptListWithCds };
