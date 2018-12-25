import { stat } from "fs";

export default {
    namespaced: true,
    state: {
      id: null,
      name: null,
      menus:[]
    },
    mutations: {
      updateUser(state,user){
        state.id=user.id;
        state.menus=user.menus;
        state.name=user.name;
      }
    }
  }
  