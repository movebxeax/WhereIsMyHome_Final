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
  return tradeApi.get(`/list`, { params: params }).then(success).catch(fail);
}

function aptInfo(aptcode, success, fail) {
  return tradeApi.get(`/info/${aptcode}`).then(success).catch(fail);
}

function dongMarkerInfo(params, success, fail) {
  return tradeApi.get(`/dong`, { params: params }).then(success).catch(fail);
}

function gugunMarkerInfo(params, success, fail) {
  return tradeApi.get(`/gugun`, { params: params }).then(success).catch(fail);
}

export { searchOptionList, aptList, aptListWithCds, aptInfo, dongMarkerInfo, gugunMarkerInfo };
