//게시물 삭제
function deleteBtn() {
  location.href = "/userBoard/Delete/${id}";
}

//댓글 삭제
function commentDeleteBtn(boardId, commentId) {
  location.href = "/userBoard/Delete/" + boardId + "/comment/" + commentId;
}

//댓글 수정
let modifyBtn = -1;
const modifyComment = ((commentId) => {
  const btn = document.getElementById("modifyBtn" + commentId);
  const textarea = document.querySelector('#content' + commentId);
  const updateBtn = document.querySelector("#modifyBtn" + commentId);
  if (modifyBtn == -1) {
    //textarea의 속성을 disabled로 해서 데이터가 안넘어갔었다...
    updateBtn.setAttribute("type", "button");
    //textarea 활성화
    textarea.disabled = false;
    //버튼 글씨 바꾸기
    const html = '수정완료';
    btn.innerText = html;
    $(document).ready(function () {
      //클릭한 버튼제외하고 나머지 버튼 비활성화
      //댓글 textarea 비활성화
      $('button:not("#modifyBtn' + commentId + '")').attr("disabled", true);
      $('#userBoardComment').attr("disabled", true);
    });
    //버튼 토글
    modifyBtn = 1;
  } else if (modifyBtn == 1) {
    //버튼 속성을 버튼에서 > 서밋으로 바꿔서 포스트 >>종민
    updateBtn.setAttribute("type", "submit");
    const html = '수정btn ' + commentId;
    btn.innerText = html;
    //버튼 다시 활성화
    $(document).ready(function () {
      $('button:not("#modifyBtn' + commentId + '")').attr("disabled", false);
      $('#userBoardComment').attr("disabled", false);
    });
    //버튼 토글
    modifyBtn = -1;
  }
});