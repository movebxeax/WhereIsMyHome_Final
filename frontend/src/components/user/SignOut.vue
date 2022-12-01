<template>
  <div></div>
</template>

<script>
import Constant from "@/utils/constant"
export default {
  mounted() {
    this.$store.dispatch("userStore/" + [Constant.SAVE_TOKENS], { accessToken: "", refreshToken: "" });
    this.$store.dispatch("userStore/" + [Constant.SET_LOGIN_STATE], false);
    this.$store.dispatch("userStore/" + [Constant.SET_USER_INFO], { username: "" });
    console.log("signout!!!");
    this.$router.push("/");
  },
}
</script>

<style>

</style>