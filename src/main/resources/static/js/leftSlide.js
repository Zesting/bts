/* 내 ul 태그 class = "swipe-wrapper" */

function left_slide() {
  let slides = document.querySelector(".left-swipe-wrapper");
  let slideImg = document.querySelectorAll(".left-swipe-wrapper li");
  let currentIndex = 0;
  let slideCount = slideImg.length;

  prev = document.querySelector(".prev");
  next = document.querySelector(".next");

  let slideWidth = 210;
  let slideMargin = 0;

  makeClone();
  initFunction();

  function makeClone() {
    let cloneSlide_first = slideImg[0].cloneNode(true);
    let cloneSlide_last = slides.lastElementChild.cloneNode(true);
    slides.append(cloneSlide_first);
    slides.insertBefore(cloneSlide_last, slides.firstElementChild);
  }
  function initFunction() {
    slides.style.width = (slideWidth + slideMargin) * (slideCount + 2) + "px";
    slides.style.left = -(slideWidth + slideMargin) + "px";
  }

  function moveNext() {
    if (currentIndex <= slideCount - 1) {
      slides.style.left =
        -(currentIndex + 2) * (slideWidth + slideMargin) + "px";
      slides.style.transition = `${0.5}s ease-out`;
    }
    if (currentIndex === slideCount - 1) {
      setTimeout(function () {
        slides.style.left = -(slideWidth + slideMargin) + "px";
        slides.style.transition = `${0}s ease-out`;
      }, 500);
      currentIndex = -1;
    }
    currentIndex += 1;
  }

  function movePrev() {
    if (currentIndex >= 0) {
      slides.style.left = -currentIndex * (slideWidth + slideMargin) + "px";
      slides.style.transition = `${0.5}s ease-out`;
    }
    if (currentIndex === 0) {
      setTimeout(() => {
        slides.style.left = -slideCount * (slideWidth + slideMargin) + "px";
        slides.style.transition = `${0}s ease-out`;
      }, 500);
      currentIndex = slideCount;
    }
    currentIndex -= 1;
  }

  next.addEventListener("click", function () {
    moveNext();
  });
  prev.addEventListener("click", function () {
    movePrev();
  });

  let loopInterval = setInterval(() => {
    moveNext();
  }, 2000);

  slides.addEventListener("mouseover", () => {
    clearInterval(loopInterval);
    prev.style.opacity = 1;
    next.style.opacity = 1;
  });
  slides.addEventListener("mouseout", () => {
    loopInterval = setInterval(() => {
      moveNext();
    }, 2000);
    prev.style.opacity = 0;
    next.style.opacity = 0;
  });

  prev.addEventListener("mouseover", () => {
    clearInterval(loopInterval);
    prev.style.opacity = 1;
    next.style.opacity = 1;
    prev.style.backgroundColor = "rgba(104, 104, 104, 0.6)";
  });
  prev.addEventListener("mouseout", () => {
    clearInterval(loopInterval);
    prev.style.backgroundColor = "rgba(104, 104, 104, 0.2)";
    prev.style.opacity = 0;
    next.style.opacity = 0;
  });

  next.addEventListener("mouseover", () => {
    clearInterval(loopInterval);
    prev.style.opacity = 1;
    next.style.opacity = 1;
    next.style.backgroundColor = "rgba(104, 104, 104, 0.6)";
  });
  next.addEventListener("mouseout", () => {
    clearInterval(loopInterval);
    next.style.backgroundColor = "rgba(104, 104, 104, 0.2)";
    prev.style.opacity = 0;
    next.style.opacity = 0;
  });
}

left_slide();
