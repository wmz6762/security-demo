<template>
  <!-- <main class="aui-page-main">
    <div class="aui-main__bd">
      <div class="aui-page page-index">
        asdasd
      </div>
    </div>
  </main>-->
  <div>
    <button @click="login">跳转到登录</button>
    <button @click="unauthorize">跳转到403</button>
  </div>
</template>

<script>
export default {
  name: "index",
  data() {
    return {};
  },
  methods: {
    login() {
      this.$router.push({ name: "login" });
    },
    unauthorize() {
      this.$router.push({ name: "unauthorize" });
    }
  },
  mounted() {}
};
</script>

<style scoped>
</style>  
