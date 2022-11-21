import { aptList, aptListWithCds, searchOptionList, aptInfo } from "@/api/trade";

const tradeStore = {
  namespaced: true,
  state: {
    apt: {},
    apts: [],
    dong: {},
    searchOptions: [],
    filterOptions: {
      area: { min: 0, max: 200, range: [0, 200] },
      price: { min: 0, max: 3000000000, range: [0, 3000000000] },
      buildyear: 1922,
    },
  },
  getters: {
    apt(state) {
      return state.apt;
    },
    apts(state) {
      let limitBuildyear = new Date().getFullYear() - state.filterOptions.buildyear;
      console.log(limitBuildyear);
      const filters = state.apts.filter((apt) => apt.buildYear >= limitBuildyear);
      // console.log(filters);
      // console.log(state.apts);
      return filters;

      // // console.log(state.apts);
      // // console.log(limitBuildyear);
      // // return state.apts;
      // // console.log(state.apts.length);

      // });
    },
    dong(state) {
      return state.dong;
    },
    searchOptions(state) {
      return state.searchOptions;
    },
    filterOptions(state) {
      return state.filterOptions;
    },
  },
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
    CLEAR_APT(state) {
      state.apt = {};
    },
    SET_FILTER_OPTIONS(state, filterOptions) {
      state.filterOptions = filterOptions;
    },
  },
  actions: {
    setFilterOptions: ({ commit }, filterOptions) => {
      return commit("SET_FILTER_OPTIONS", filterOptions);
    },
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
    clearApt: ({ commit }) => {
      commit("CLEAR_APT");
    },
  },
};

export default tradeStore;
