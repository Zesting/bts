/* 내 ul 태그 class = "swipe-wrapper" */

/*var ul = document.querySelector(".swipe-wrapper");

function slideMove() {
  var curIdx = 0;

  setInterval(function () {
    ul.style.transition = "0.2s";
    ul.style.transform = "translate3d(-" + 200 * (curIdx + 1) + "px, 0px, 0px)";

    curIdx++;

    if (curIdx === 3) {
      curIdx = -1;
    }
  }, 1000);
}
document.addEventListener("DOMContentLoaded", function () {
  slideMove();
});
 */

$(() => {
  setInterval(() => {
    $(".swipe-wrapper")
      .delay(2000)
      .animate({ marginLeft: -210 })
      .delay(2000)
      .animate({ marginLeft: -420 })
      .delay(2000)
      .animate({ marginLeft: 0 });
  });
});
