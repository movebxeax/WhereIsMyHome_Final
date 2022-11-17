///////////////////////// select box 설정 (지역, 매매기간) /////////////////////////
let date = new Date();
function getContextPath() {
  return window.location.pathname.substring(
    0,
    window.location.pathname.indexOf("/", 2)
  );
}

document.querySelector("#year").addEventListener("change", function () {
  let month = date.getMonth() + 1;
  let monthEl = document.querySelector("#month");
  let monthOpt = `<option value="">매매월선택</option>`;
  let yearSel = document.querySelector("#year");
  let m =
    yearSel[yearSel.selectedIndex].value == date.getFullYear() ? month : 13;
  for (let i = 1; i < m; i++) {
    monthOpt += `<option value="${i < 10 ? "0" + i : i}">${i}월</option>`;
  }
  monthEl.innerHTML = monthOpt;
});

// https://juso.dev/docs/reg-code-api/
// let url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
// let regcode = "*00000000";
// 전국 특별/광역시, 도
// https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=*00000000

// 시도가 바뀌면 구군정보 얻기.
document.querySelector("#sido").addEventListener("change", function () {
  if (this[this.selectedIndex].value) {
    let regcode = this[this.selectedIndex].value.substr(0, 2);
    sendRequest("gugun", `${getContextPath()}/api/address/gugun/${regcode}`);
  } else {
    initOption("gugun");
    initOption("dong");
  }
});

// 구군이 바뀌면 동정보 얻기.
document.querySelector("#gugun").addEventListener("change", function () {
  if (this[this.selectedIndex].value) {
    let regcode = this[this.selectedIndex].value.substr(0, 5);
    sendRequest("dong", `${getContextPath()}/api/address/dong/${regcode}`);
  } else {
    initOption("dong");
  }
});

function sendRequest(selid, url, code) {
  if (code != null) {
    url = url + "?code=" + code;
  }

  let res = fetch(url)
    .then((response) => response.json())
    .then((data) => addOption(selid, data));
}

function getListAndSetInterestValue(selid, url, code, value) {
  if (code != null) {
    url = url + "?code=" + code;
  }

  let res = fetch(url)
    .then((response) => response.json())
    .then((data) => addOption(selid, data))
    .then(() => {
      document.querySelector(`#${selid}`).value = value;
    });
}

function addOption(selid, data) {
  return new Promise((resolve, reject) => {
    let opt = ``;
    initOption(selid);
    switch (selid) {
      case "sido":
        opt += `<option value="">시도선택</option>`;
        data.forEach(function (regcode) {
          opt += `
        <option value="${regcode.code}">${regcode.name}</option>
        `;
        });
        break;
      case "gugun":
        opt += `<option value="">구군선택</option>`;
        data.forEach(function (regcode) {
          opt += `
        <option value="${regcode.code}">${regcode.name}</option>
        `;
        });
        break;
      case "dong":
        opt += `<option value="">동선택</option>`;
        data.forEach(function (regcode) {
          opt += `
        <option value="${regcode.code}">${regcode.name}</option>
        `;
        });
    }
    document.querySelector(`#${selid}`).innerHTML = opt;

    resolve();
  });
}

function initOption(selid) {
  let options = document.querySelector(`#${selid}`);
  options.length = 0;
}

window.addEventListener("load", function () {
  let yearEl = document.querySelector("#year");
  let yearOpt = `<option value="">매매년도선택</option>`;
  let year = date.getFullYear();
  for (let i = year; i > year - 20; i--) {
    yearOpt += `<option value="${i}">${i}년</option>`;
  }
  yearEl.innerHTML = yearOpt;

  // 브라우저가 열리면 시도정보 얻기.
  sendRequest("sido", `${getContextPath()}/api/address/sido`, null);
});
