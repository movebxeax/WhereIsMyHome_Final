<template>
  <!--

  Colors are picked from...
  https://colorhunt.co/palette/f9f7f7dbe2ef3f72af112d4e

  -->
  <v-row class="pa-4 ma-4" align="center" justify="center" dense>
    <v-card persistent justify-center max-width="600px" min-width="360px">
      <div class="d-flex align-center">
        <v-tabs show-arrows background-color="#3F72AF" icons-and-text dark grow>
          <v-tabs-slider color="#DBE2EF"></v-tabs-slider>
          <v-tab>
            <v-icon large>mdi-account</v-icon>
            <div class="caption py-1">USER INFORMATION</div>
          </v-tab>
          <v-tab-item>
            <v-card class="px-4">
              <v-card-text>
                <v-form ref="registerForm" v-model="valid" lazy-validation>
                  <v-row>
                    <v-col cols="12" sm="6" md="6">
                      <v-text-field v-model="signupId" :rules="[rules.required]" label="아이디" maxlength="20" required></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="6">
                      <v-text-field v-model="signupName" :rules="[rules.required]" label="이름" maxlength="20" required></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="6">
                      <v-text-field v-model.lazy="signupPassword" :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'" :rules="[rules.required, rules.min]" :type="showPassword ? 'text' : 'password'" name="input-10-1" label="비밀번호" hint="비밀번호는 8글자 이상이여야 합니다." @click:append="showPassword = !showPassword"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="6">
                      <v-text-field block v-model.lazy="signupPasswordVerify" :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'" :rules="[rules.required, passwordMatch]" :type="showPassword ? 'text' : 'password'" name="input-10-1" label="비밀번호 확인" @click:append="showPassword = !showPassword"></v-text-field>
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
                    <v-col> </v-col>
                    <v-col class="mt-1 mr-6" cols="12" sm="3">
                      <v-btn class="d-flex align-center" x-large block :disabled="!valid" @click.stop="signup" color="#DBE2EF">Register</v-btn>
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
export default {
  computed: {
    passwordMatch() {
      return () => this.password === this.verify || "비밀번호가 일치하지 않습니다.";
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

<style scoped lang="scss"></style>
