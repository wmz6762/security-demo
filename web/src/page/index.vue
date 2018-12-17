<template>
  <div>{{user}}</div>
</template>

<script>
export default {
  name: "index",
  data() {
    return {
      user: ""
    };
  },
  methods: {
    getCurrentUser() {
      this.$http({
        url: this.$http.adornUrl("/account"),
        method: "get"
      }).then(res => {
        this.user = res;
      });
    }
  },
  created() {
    this.getCurrentUser();
  }
};
</script>

<style scoped>
</style>  
