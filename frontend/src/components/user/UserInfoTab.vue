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
                      <v-text-field v-model="userData.userid" :rules="[rules.required]" label="아이디" maxlength="20" required disabled></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="6">
                      <v-text-field v-model="userData.username" :rules="[rules.required]" label="이름" maxlength="20" required></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="6">
                      <v-text-field v-model.lazy="userData.userPassword" :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'" :rules="[rules.required, rules.min]" :type="showPassword ? 'text' : 'password'" name="input-10-1" label="비밀번호" hint="비밀번호는 8글자 이상이여야 합니다." @click:append="showPassword = !showPassword"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="6">
                      <v-text-field block v-model.lazy="userData.userPasswordVerify" :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'" :rules="[rules.required, passwordMatch]" :type="showPassword ? 'text' : 'password'" name="input-10-1" label="비밀번호 확인" @click:append="showPassword = !showPassword"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="6">
                      <v-text-field v-model.lazy="userData.emailId" label="E-mail" required></v-text-field>
                    </v-col>
                    <v-col class="d-flex justify-center align-center" cols="12" sm="1" md="1">@</v-col>
                    <v-col cols="12" sm="5" md="5">
                      <v-text-field v-model.lazy="userData.emailDomain" label="Domain" required></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field v-model="userData.baseAddress" name="input-10-1" label="기본 주소 입력"></v-text-field>
                      <v-text-field v-model="userData.specAddress" name="input-10-1" label="상세 주소 입력"></v-text-field>
                      <v-text-field v-model="userData.contact" name="input-10-1" label="연락처"></v-text-field>
                    </v-col>
                    <v-col> </v-col>
                    <v-col class="mt-1 mr-6" cols="12" sm="3">
                      <v-btn class="d-flex align-center" x-large block :disabled="!valid" @click.stop="modify" color="#DBE2EF">정보 수정</v-btn>
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
import { apiUser } from "@/api/index.js"

const apiUserFunc = apiUser();

export default {
  computed: {
    passwordMatch() {
      return () => this.password === this.verify || "비밀번호가 일치하지 않습니다.";
    },
  },
  created () {
    apiUserFunc.get("/info")
    .then(({data}) => {
      this.userData = data;
      console.log(this.userData);
    });
  },
  data: () => ({
    dialog: true,
    valid: true,

    userData: {},
    checkbox: "",

    rules: {
      required: (value) => !!value || "비밀번호를 입력해주세요",
      min: (v) => (v && v.length >= 8) || "8글자 이상 입력해주세요",
    },
    showPassword: false,
  }),
  methods: {
    modify() {
      apiUserFunc.post("/modify", this.userData)
    .then(() => {
      alert("수정 성공!");
      this.$router.push('/');
    })
    .catch(() => alert("수정 실패!"));
    }
  },
};
</script>

<style scoped lang="scss"></style>
