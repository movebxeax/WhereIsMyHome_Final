import { aptList, aptListWithCds, searchOptionList, aptInfo } from "@/api/trade";

const tradeStore = {
  namespaced: true,
  state: {
    dong: {},
    searchOptions: [],
    apts: [],
    apt: {},
  },
  getters: {},
  mutations: {
    SET_SEARCH_OPTION_LIST(state, searchOptions) {
      state.searchOptions = searchOptions;
    },
    SET_APT_LIST(state, apts) {
      state.apts = apts;
    },
    CLEAT_APT_LIST(state) {
      state.apts = [];
    },
    SET_DONG(state, dong) {
      state.dong = dong;
    },
    SET_APT(state, apt) {
      state.apt = apt;
    },
  },
  actions: {
    setDong: ({ commit }, dong) => {
      commit("SET_DONG", dong);
    },
    getApt: ({ commit }, aptcode) => {
      return aptInfo(
        aptcode,
        ({ data }) => {
          commit("SET_APT", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getSearchOptionList: ({ commit }, keyword) => {
      const params = {
        keyword: keyword,
      };
      return searchOptionList(
        params,
        ({ data }) => {
          commit("SET_SEARCH_OPTION_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getAptList: ({ commit }, dongcode) => {
      return aptList(
        dongcode,
        ({ data }) => {
          commit("SET_APT_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getAptListWithCds: ({ commit }, params) => {
      return aptListWithCds(
        params,
        ({ data }) => {
          commit("SET_APT_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    clearAptList: ({ commit }) => {
      commit("CLEAT_APT_LIST");
    },
  },
};

export default tradeStore;
