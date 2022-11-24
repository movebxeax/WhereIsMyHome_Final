import { aptList, aptListWithCds, searchOptionList, aptInfo } from "@/api/trade";

const tradeStore = {
  namespaced: true,
  state: {
    apt: null,
    apts: [],
    dong: {},
    inputDongcode: "",
    searchOptions: [],
    filterOptions: {
      area: { min: 0, max: 200, range: [0, 200] },
      price: { min: 0, max: 5000000000, range: [0, 5000000000] },
      buildYear: 100,
    },
  },
  getters: {
    apt(state) {
      return state.apt;
    },
    inputDongcode(state) {
      return state.inputDongcode;
    },
    apts(state) {
      let limitBuildyear = new Date().getFullYear() - state.filterOptions.buildYear;

      let results = [];
      state.apts.forEach((apt) => {
        let isBuildYearOkay = false;
        let isAreaOkay = false;
        let isPriceOkay = false;

        if (apt.buildYear >= limitBuildyear) {
          isBuildYearOkay = true;
        }

        apt.details.forEach((detail) => {
          if (!isAreaOkay) {
            if (
              (detail.area >= state.filterOptions.area.range[0] && detail.area <= state.filterOptions.area.range[1]) ||
              detail.area >= 200
            ) {
              isAreaOkay = true;
            }
          }

          if (!isPriceOkay) {
            if (detail.priceInfoList.length > 0) {
              detail.priceInfoList.forEach((deal) => {
                if (
                  (deal.price >= state.filterOptions.price.range[0] / 10000 &&
                    deal.price <= state.filterOptions.price.range[1] / 10000) ||
                  deal.price >= 5000000000
                ) {
                  // console.log(deal.price);
                  isPriceOkay = true;
                }
              });
            }
          }
        });
        if (isAreaOkay && isPriceOkay && isBuildYearOkay) {
          results.push(apt);
        }
      });
      return results;
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
    SET_DONG(state, dong) {
      state.dong = dong;
    },
    SET_APT(state, apt) {
      state.apt = apt;
    },
    SET_FILTER_OPTIONS(state, filterOptions) {
      state.filterOptions = filterOptions;
    },
    SET_INPUT_DONGCODE(state, dongcode) {
      state.inputDongcode = dongcode;
    },
    CLEAR_APT(state) {
      console.log("CLEAR APT");
      state.apt = null;
    },
    CLEAR_INPUT_DONGCODE(state) {
      state.inputDongcode = "";
    },
    CLEAR_DONG(state) {
      state.dong = null;
    },
    CLEAR_APTS(state) {
      console.log("CLEART APTS");
      state.apts = [];
    },
  },
  actions: {
    setFilterOptions: ({ commit }, filterOptions) => {
      return commit("SET_FILTER_OPTIONS", filterOptions);
    },
    setDong: ({ commit }, dong) => {
      commit("SET_DONG", dong);
    },
    setApt: ({ commit }, apt) => {
      commit("SET_APT", apt);
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
    setInputDongcode: ({ commit }, dongcode) => {
      commit("SET_INPUT_DONGCODE", dongcode);
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
    clearApt: ({ commit }) => {
      commit("CLEAR_APT");
    },
    clearInputDongcode: ({ commit }) => {
      commit("CLEAR_INPUT_DONGCODE");
    },
    clearDong: ({ commit }) => {
      commit("CLEAR_DONG");
    },
    clearApts: ({ commit }) => {
      commit("CLEAR_APTS");
    },
  },
};

export default tradeStore;
