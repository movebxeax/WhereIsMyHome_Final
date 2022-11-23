<template>
  <!--

  Colors are picked from...
  https://colorhunt.co/palette/f9f7f7dbe2ef3f72af112d4e

  -->
  <v-row class="pa-4 ma-4" align="center" justify="center" dense>
    <v-card persistent justify-center max-width="600px" min-width="360px">
      <div class="d-flex align-center">
        <v-tabs v-model="active_tab" show-arrows background-color="#3F72AF" icons-and-text dark grow>
          <v-tabs-slider color="#DBE2EF"></v-tabs-slider>
          <v-tab v-for="tab in tabs" :key="tab.name">
            <v-icon large>{{ tab.icon }}</v-icon>
            <div class="caption py-1">{{ tab.name }}</div>
          </v-tab>
          <v-tab-item>
            <v-card class="px-4">
              <v-card-text>
                <v-form ref="loginForm" v-model="valid" lazy-validation>
                  <v-row>
                    <v-col cols="12">
                      <v-text-field v-model.lazy="loginId" :rules="loginIdRules" label="User ID" color="#112D4E"
                        required></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field v-model.lazy="loginPassword" :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                        :rules="[rules.required, rules.min]" :type="showPassword ? 'text' : 'password'"
                        name="input-10-1" label="Password" hint="At least 8 characters" color="#112D4E"
                        @click:append="showPassword = !showPassword"></v-text-field>
                    </v-col>
                    <v-col class="d-flex" cols="12" sm="6" xsm="12"> </v-col>
                    <v-spacer></v-spacer>
                    <v-col class="d-flex" cols="12" sm="3" xsm="12" align-end>
                      <v-btn x-large block :disabled="!valid" color="#DBE2EF" @click="login"> Login </v-btn>
                    </v-col>
                  </v-row>
                </v-form>
              </v-card-text>
            </v-card>
          </v-tab-item>
          <v-tab-item>
            <v-card class="px-4">
              <v-card-text>
                <v-form ref="registerForm" v-model="valid" lazy-validation>
                  <v-row>
                    <v-col cols="12" sm="6" md="6">
                      <v-text-field v-model="signupId" :rules="[rules.required]" label="아이디" maxlength="20" required>
                      </v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="6">
                      <v-text-field v-model="signupName" :rules="[rules.required]" label="이름" maxlength="20" required>
                      </v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="6">
                      <v-text-field v-model.lazy="signupPassword"
                        :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'" :rules="[rules.required, rules.min]"
                        :type="showPassword ? 'text' : 'password'" name="input-10-1" label="비밀번호"
                        hint="비밀번호는 8글자 이상이여야 합니다." @click:append="showPassword = !showPassword"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="6">
                      <v-text-field block v-model.lazy="signupPasswordVerify"
                        :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'" :rules="[rules.required, passwordMatch]"
                        :type="showPassword ? 'text' : 'password'" name="input-10-1" label="비밀번호 확인"
                        @click:append="showPassword = !showPassword"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="6">
                      <v-text-field v-model.lazy="signupEmailId" label="E-mail" required></v-text-field>
                    </v-col>
                    <v-col class="d-flex justify-center align-center" cols="12" sm="1" md="1">@</v-col>
                    <v-col cols="12" sm="5" md="5">
                      <v-text-field v-model.lazy="signupEmailDomain" label="Domain" required></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field v-model="signupBaseAddress" name="input-10-1" label="기본 주소 입력"></v-text-field>
                      <v-text-field v-model="signupDetailAddress" name="input-10-1" label="상세 주소 입력"></v-text-field>
                      <v-text-field v-model="signupContact" name="input-10-1" label="연락처"></v-text-field>
                    </v-col>
                    <v-col>
                      <v-checkbox v-model="checkbox">
                        <template v-slot:label>
                          <div>
                            <v-tooltip bottom>
                              <template v-slot:activator="{ on }">
                                <a target="_blank" @click.stop v-on="on"> 개인정보 처리 방침 </a>
                              </template>
                              이거 href 대신 모달로 변경해야해요!!!!
                            </v-tooltip>
                            에 동의합니다.
                          </div>
                        </template>
                      </v-checkbox>
                    </v-col>
                    <!-- <v-spacer></v-spacer> -->
                    <v-col class="mt-1 mr-6" cols="12" sm="3" xsm="12">
                      <v-btn class="d-flex align-center" x-large block :disabled="!valid" @click.stop="signup"
                        color="#DBE2EF">Register</v-btn>
                    </v-col>
                  </v-row>
                </v-form>
              </v-card-text>
            </v-card>
          </v-tab-item>
        </v-tabs>
      </div>
    </v-card>
  </v-row>
</template>

<script>
import { httpUser } from "@/utils/api.js";
import Constant from "@/utils/constant.js";

export default {
  computed: {
    passwordMatch() {
      return () => this.password === this.verify || "비밀번호가 일치하지 않습니다.";
    },
    selected() {
      console.log(this.$route.params.selected);
      return this.$route.params.selected;
    },
  },
  watch: {
    selected(path) {
      if (path === "signin") this.active_tab = 0;
      else if (path === "signup") this.active_tab = 1;
    },
  },
  created() {
    const path = this.$route.params.selected;
    if (path === "signin") this.active_tab = 0;
    else if (path === "signup") this.active_tab = 1;
  },
  methods: {
    login() {
      if (this.$refs.loginForm.validate()) {
        httpUser
          .post("/login", {
            userid: this.loginId,
            password: this.loginPassword,
          })
          .then(({ data }) => {
            this.$store.dispatch("userStore/" + [Constant.SAVE_TOKENS], {
              accessToken: data.accessToken,
              refreshToken: data.refreshToken,
            });
            this.$store.dispatch("userStore/" + [Constant.SET_LOGIN_STATE], true);
            this.$store.dispatch("userStore/" + [Constant.SET_USER_INFO], {
              userid: data.userid,
              username: data.username,
            });

            this.$router.push("/");
          })
          .catch(() => alert("로그인 실패! 아이디와 비밀번호를 확인해주세요."));
        // submit form to server/API here...
      }
    },
    signup() {
      if (this.$refs.registerForm.validate()) {
        httpUser
          .post("/signup", {
            userid: this.signupId,
            password: this.signupPassword,
            username: this.signupName,
            emailId: this.signupEmailId,
            emailDomain: this.signupEmailDomain,
            contact: this.signupContact,
            baseAddress: this.signupBaseAddress,
            specAddress: this.signupDetailAddress,
          })
          .then(({ data }) => console.log(data));
      }
    },
    reset() {
      this.$refs.form.reset();
    },
    resetValidation() {
      this.$refs.form.resetValidation();
    },
  },
  data: () => ({
    dialog: true,
    active_tab: -1,
    tabs: [
      { id: "signin", name: "Login", icon: "mdi-account" },
      { id: "signup", name: "Register", icon: "mdi-account-outline" },
    ],
    valid: true,

    signupId: "",
    signupName: "",
    signupPassword: "",
    signupPasswordVerify: "",
    signupEmailId: "",
    signupEmailDomain: "",
    signupBaseAddress: "",
    signupDetailAddress: "",
    signupContact: "",
    checkbox: "",

    loginId: "",
    loginPassword: "",

    loginIdRules: [(v) => !!v || "아이디를 입력해주세요"],
    rules: {
      required: (value) => !!value || "비밀번호를 입력해주세요",
      min: (v) => (v && v.length >= 8) || "8글자 이상 입력해주세요",
    },
    showPassword: false,
  }),
};
</script>

<style scoped lang="scss">

</style>
