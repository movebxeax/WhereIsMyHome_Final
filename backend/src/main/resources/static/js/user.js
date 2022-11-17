async function login() {
  let userid = document.querySelector("#userid_login").value;
  let password = document.querySelector("#password_login").value;
  let res = await fetch(getContextPath() + "/api/user/login", {
    method: "POST",
    cache: "no-cache",
    body: new URLSearchParams({
      userid,
      password,
    }),
  });

  if (res.status == 200) {
    let loginFailMessage = document.querySelector("#loginFailMessage");
    if (loginFailMessage != undefined)
      loginFailMessage.parentNode.removeChild(loginFailMessage);
    $("#loginModalForm").modal("hide");
    showLogoutBtn();
    showEditInfoBtn();
  } else {
    let message = document.querySelector("#loginFailMessageDiv");
    if (message != undefined) {
      let loginFailMessage = document.querySelector("#loginFailMessage");
      if (loginFailMessage == undefined) {
        let span = document.createElement("span");
        span.className = "me-2 text-danger";
        span.id = "loginFailMessage";
        span.textContent = "로그인 실패";
        message.appendChild(span);
      }
    }
  }
}

async function logout() {
  let res = await fetch(getContextPath() + "/user/rest/logout.do", {
    method: "POST",
    cache: "no-cache",
  });

  if (res.status == 200) {
    showLogInBtn();
    showSignUpBtn();
  }
}

async function signup() {
  let userid = document.querySelector("#userid_signup").value;
  let password = document.querySelector("#password_signup").value;
  let username = document.querySelector("#username_signup").value;

  let res = await fetch(getContextPath() + "/user/rest/signup.do", {
    method: "POST",
    cache: "no-cache",
    body: new URLSearchParams({
      userid,
      password,
      username,
    }),
  });

  if (res.status == 200) {
    $("#signupModalForm").modal("hide");
  } else {
    let message = document.querySelector("#signupFailMessageDiv");
    if (message != undefined) {
      let span = document.createElement("span");
      span.className = "me-2 text-danger";
      span.id = "signupFailMessage";
      span.textContent = "회원가입 실패";
      message.appendChild(span);
    }
  }
}

async function getUserData() {
  let res = await fetch(getContextPath() + "/user/rest/getUserInfo.do", {
    method: "POST",
    cache: "no-cache",
  })
    .then((response) => response.json())
    .then((datas) => {
      let key = Object.keys(datas);
      let key_count = Object.keys(datas).length;
      for (let i = 0; i < key_count; i++) {
        if (datas[key[i]] != "") {
          let elem = document.querySelector("#" + key[i] + "_infoedit");
          elem.value = datas[key[i]];
        }
      }
    });
}

async function modifyUserInfo() {
  let userid = document.querySelector("#userid_infoedit").value;
  let password = document.querySelector("#password_infoedit").value;
  let username = document.querySelector("#username_infoedit").value;
  let res = await fetch(getContextPath() + "/user/rest/modifyUserInfo.do", {
    method: "POST",
    cache: "no-cache",
    body: new URLSearchParams({
      userid,
      password,
      username,
    }),
  });

  if (res.status == 200) {
    let loginFailMessage = document.querySelector("#infoEditFailMessageDiv");
    if (loginFailMessage != undefined)
      loginFailMessage.parentNode.removeChild(loginFailMessage);
    $("#infoEditModalForm").modal("hide");
  } else {
    let message = document.querySelector("#infoEditFailMessageDiv");
    if (message != undefined) {
      let span = document.createElement("span");
      span.className = "me-2 text-danger";
      span.id = "infoEditFailMessage";
      span.textContent = "정보 수정 실패";
      message.appendChild(span);
    }
  }
}

async function deleteUserInfo() {
  let userid = document.querySelector("#userid_infoedit").value;
  let res = await fetch(getContextPath() + "/user/rest/deleteUserInfo.do", {
    method: "POST",
    cache: "no-cache",
    body: new URLSearchParams({
      userid,
    }),
  });

  if (res.status == 200) {
    let loginFailMessage = document.querySelector("#infoEditFailMessageDiv");
    if (loginFailMessage != undefined)
      loginFailMessage.parentNode.removeChild(loginFailMessage);
    $("#infoEditModalForm").modal("hide");
  } else {
    let message = document.querySelector("#infoEditFailMessageDiv");
    if (message != undefined) {
      let span = document.createElement("span");
      span.className = "me-2 text-danger";
      span.id = "infoEditFailMessage";
      span.textContent = "계정 삭제 실패";
      message.appendChild(span);
    }
  }
}

function showLogInBtn() {
  let helloUser = document.querySelector("#helloUser");
  if (helloUser != undefined) helloUser.parentNode.removeChild(helloUser);

  hideLogoutBtn();
  hideEditInfoBtn();

  let navRight = document.querySelector("#nav-right");
  let loginBtn = document.createElement("button");
  loginBtn.setAttribute("type", "button");
  loginBtn.setAttribute("class", "btn btn-outline-primary me-2");
  loginBtn.setAttribute("id", "loginBtn");
  loginBtn.setAttribute("data-bs-toggle", "modal");
  loginBtn.setAttribute("data-bs-target", "#loginModalForm");
  loginBtn.textContent = "Login";

  navRight.appendChild(loginBtn);
}

function showLogoutBtn() {
  hideLoginBtn();
  hideSignupBtn();

  let navRight = document.querySelector("#nav-right");

  let helloUser = document.querySelector("#helloUser");
  if (helloUser == undefined) {
    let span = document.createElement("span");
    span.className = "me-2";
    span.id = "helloUser";
    span.textContent = getCookie("username") + " 님 환영합니다.";
    navRight.prepend(span);
  }

  let loginBtn = document.createElement("button");
  loginBtn.setAttribute("type", "button");
  loginBtn.setAttribute("class", "btn btn-outline-primary me-2");
  loginBtn.setAttribute("id", "logoutBtn");
  loginBtn.setAttribute("onclick", "javascript:logout()");
  loginBtn.textContent = "Logout";

  navRight.appendChild(loginBtn);
}

function showSignUpBtn() {
  let navRight = document.querySelector("#nav-right");
  let infoEditBtn = document.createElement("button");
  infoEditBtn.setAttribute("type", "button");
  infoEditBtn.setAttribute("class", "btn btn-warning me-2");
  infoEditBtn.setAttribute("id", "signupBtn");
  infoEditBtn.setAttribute("data-bs-toggle", "modal");
  infoEditBtn.setAttribute("data-bs-target", "#signupModalForm");
  infoEditBtn.textContent = "회원가입";

  navRight.appendChild(infoEditBtn);
}

function showEditInfoBtn() {
  let navRight = document.querySelector("#nav-right");
  let infoEditBtn = document.createElement("button");
  infoEditBtn.setAttribute("type", "button");
  infoEditBtn.setAttribute("class", "btn btn-info me-2");
  infoEditBtn.setAttribute("id", "infoEditBtn");
  infoEditBtn.setAttribute("data-bs-toggle", "modal");
  infoEditBtn.setAttribute("data-bs-target", "#infoEditModalForm");
  infoEditBtn.setAttribute("onclick", "javascript:getUserData()");
  infoEditBtn.textContent = "정보수정";

  navRight.appendChild(infoEditBtn);
}

function hideLoginBtn() {
  let loginBtn = document.querySelector("#loginBtn");
  if (loginBtn != undefined) loginBtn.parentNode.removeChild(loginBtn);
}

function hideLogoutBtn() {
  let logoutBtn = document.querySelector("#logoutBtn");
  if (logoutBtn != undefined) logoutBtn.parentNode.removeChild(logoutBtn);
}

function hideSignupBtn() {
  let signupBtn = document.querySelector("#signupBtn");
  if (signupBtn != undefined) signupBtn.parentNode.removeChild(signupBtn);
}

function hideEditInfoBtn() {
  let editInfoBtn = document.querySelector("#infoEditBtn");
  if (editInfoBtn != undefined) editInfoBtn.parentNode.removeChild(editInfoBtn);
}

window.onload = function () {
  let username = getCookie("username");
  if (username == undefined) {
    // not logged in
    showLogInBtn();
    showSignUpBtn();
  } else {
    showLogoutBtn();
    showEditInfoBtn();
  }
};

function getCookie(cookieName) {
  var cookieValue = null;
  if (document.cookie) {
    var array = document.cookie.split(escape(cookieName) + "=");
    if (array.length >= 2) {
      var arraySub = array[1].split(";");
      cookieValue = unescape(arraySub[0]);
    }
  }
  return cookieValue;
}

function getContextPath() {
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(
    hostIndex,
    location.href.indexOf("/", hostIndex + 1)
  );
  return contextPath;
}
