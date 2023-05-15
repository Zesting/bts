/* 각각의 input 값을 확인하는 함수를 호출하는 함수.  */

function formCheck() {
  let formId = document.getElementById("formId");
  if (!checkLogId()) {
    return false;
  }
  if (!checkLogPwd()) {
    return false;
  }
  if (!checkName()) {
    return false;
  }
  if (!checkAge()) {
    return false;
  }
  if (!checkEmail()) {
    return false;
  }
  if (!checkPhonNum()) {
    return false;
  }
  if (formId.checkValidity()) {
    alert("회원가입 완료!");
    formId.submit();
  }
}

/* ============= 공백 확인 함수 =============== */

function checkExistData(value, dataName) {
  if (value == "") {
    alert(dataName + " 입력해주세요.");
    return false;
  }
  return true;
}

/* ============= 아이디 유효성 검증 =============== */

function checkLogId() {
  let logId = document.getElementById("logId");
  if (!checkExistData(logId.value, "아이디를")) {
    return false;
  }
  var logIdRegExp = /^[A-Za-z]{1}[A-Za-z0-9_-]{3,19}$/;
  if (!logIdRegExp.test(logId.value)) {
    alert(
      "아이디의 첫 번째 글짜는 영문으로 시작해야하며, 영문 대소문자와 숫자 4~12자리로 입력해야합니다."
    );
    logId.value = "";
    logId.focus();
    return false;
  }
  return true;
}

/* ============= 비밀번호 유효성 검증 =============== */

function checkLogPwd() {
  let logId = document.getElementById("logId");
  let logPwd = document.getElementById("logPwd");

  if (!checkExistData(logPwd.value, "비밀번호를")) {
    return false;
  }

  var logPwdRegExp = /(?=.*\d)(?=.*[a-zA-ZS]).{8,}/;

  if (!logPwdRegExp.test(logPwd.value)) {
    alert(
      "비밀번호는 8자 이상의 영문 대소문자와 숫자의 조합으로 입력해야합니다."
    );
    logPwd.value = "";
    logPwd.focus();
    return false;
  }

  if (logId.value == logPwd.value) {
    alert("아이디와 비밀번호는 동일할 수 없습니다.");
    logPwd.value = "";
    logPwd.focus();
    return false;
  }
  return true;
}

/* ============= 이름 유효성 검증 =============== */
function checkName() {
  let name = document.getElementById("name");
  if (!checkExistData(name.value, "이름을")) {
    return false;
  }
  var nameRegExp = /^[가-힣]+$/;
  if (!nameRegExp.test(name.value)) {
    alert("이름은 한글로만 입력해야합니다.");
    name.value = "";
    name.focus();
    return false;
  }
  return true;
}

/* ============= 나이 유효성 검증 =============== */

function checkAge() {
  let age = parseInt(document.getElementById("age"));
  if (!checkExistData(age.value)) {
    return false;
  }
  if (1 > age.value && age.value < 124) {
    alert("올바르지 않는 나이입니다.");
    document.getElementById("age").value = "";
    document.getElementById("age").focus();
    return false;
  }
  return true;
}

/* ============= 이메일 유효성 검증 =============== */

function checkEmail() {
  let email = document.getElementById("email");

  if (!checkExistData(email.value, "이메일을")) {
    return false;
  }

  var emailRegExp =
    /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/;
  if (!emailRegExp.test(email.value)) {
    alert("이메일 형식이 올바르지 않습니다.");
    email.value = "";
    email.focus();
    return false;
  }
  return true;
}

/* ============= 전화번호 유효성 검증 =============== */

function checkPhonNum() {
  let phonNum = document.getElementById("phonNum");
  if (!checkExistData(phonNum.value, "전화번호를")) {
    return false;
  }
  var phonNumRegExp = /^\d{2,3}-\d{3,4}-\d{4}$/;
  if (!phonNumRegExp.test(phonNum.value)) {
    alert("전화번호를 다시 입력해주시길 바랍니다.(예:010-1234-5678)");
    phonNum.value = "";
    phonNum.focus();
    return false;
  }
  return true;
}
