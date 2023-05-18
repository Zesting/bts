function logout_confirm() {
  if (confirm("로그아웃하시겠습니까?")) {
    window.location.href = "/logout";
  } else {
    window.location.href = "/";
  }
}

function order_confirm() {
  alert("로그인이 필요한 항목입니다. 로그인을 수행해주세요.");
  window.location.href = "/members/logIn";
}

function memberInfo() {
  alert("로그인이 필요한 항목입니다. 로그인을 수행해주세요.");
  window.location.href = "/members/logIn";
}
