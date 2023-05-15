function right_slide() {
  let downSlides = document.querySelector(".right-swipe-wrapper");
  let slideSpan = document.querySelectorAll(".right-swipe-wrapper li");
  let c_Index = 0;
  let count = slideSpan.length;

  up = document.querySelector(".up");
  down = document.querySelector(".down");

  let slideHeight = 20;
  let slideMargin_bottom = 5;

  rightMakeClone();
  rightInitFunction();

  function rightMakeClone() {
    let cloneSlide_first = slideSpan[0].cloneNode(true);
    let cloneSlide_second = slideSpan[1].cloneNode(true);
    let cloneSlide_third = slideSpan[2].cloneNode(true);
    let cloneSlide_last = downSlides.lastElementChild.cloneNode(true);
    downSlides.append(cloneSlide_first);
    downSlides.append(cloneSlide_second);
    downSlides.append(cloneSlide_third);
    downSlides.insertBefore(cloneSlide_last, downSlides.firstElementChild);
  }

  function rightInitFunction() {
    downSlides.style.height =
      (slideHeight + slideMargin_bottom) * (count + 1) + "px";
    downSlides.style.bottom = slideHeight + slideMargin_bottom + "px";
  }

  function rightMoveUp() {
    if (c_Index <= count - 1) {
      downSlides.style.top =
        -(c_Index + 2) * (slideHeight + slideMargin_bottom) + "px";
      downSlides.style.transition = `${0.5}s ease-out`;
    }

    if (c_Index === count) {
      setTimeout(function () {
        downSlides.style.top = -(slideHeight + slideMargin_bottom) + "px";
        downSlides.style.transition = `${0}s ease-out`;
      }, 500);
      c_Index = -1;
    }
    c_Index += 1;
  }

  function rightMoveDown() {
    if (c_Index >= 0) {
      downSlides.style.top =
        -c_Index * (slideHeight + slideMargin_bottom) + "px";
      downSlides.style.transition = `${0.5}s ease-out`;
    }
    if (c_Index === 0) {
      setTimeout(() => {
        downSlides.style.top =
          -count * (slideHeight + slideMargin_bottom) + "px";
        downSlides.style.transition = `${0}s ease-out`;
      }, 500);
      c_Index = count;
    }
    c_Index -= 1;
  }

  up.addEventListener("click", function () {
    rightMoveUp();
  });
  down.addEventListener("click", function () {
    rightMoveDown();
  });

  let downInterval = setInterval(() => {
    rightMoveUp();
  }, 1000);

  downSlides.addEventListener("mouseover", () => {
    clearInterval(downInterval);
    up.style.opacity = 1;
    down.style.opacity = 1;
  });
  downSlides.addEventListener("mouseout", () => {
    downInterval = setInterval(() => {
      rightMoveUp();
    }, 1000);
    up.style.opacity = 0;
    down.style.opacity = 0;
  });

  up.addEventListener("mouseover", () => {
    clearInterval(downInterval);
    up.style.opacity = 1;
    down.style.opacity = 1;
    up.style.backgroundColor = "rgba(104, 104, 104, 0.6)";
  });
  up.addEventListener("mouseout", () => {
    clearInterval(downInterval);
    up.style.opacity = 0;
    down.style.opacity = 0;
    up.style.backgroundColor = "rgba(104, 104, 104, 0.6)";
  });

  down.addEventListener("mouseover", () => {
    clearInterval(downInterval);
    up.style.opacity = 1;
    down.style.opacity = 1;
    down.style.backgroundColor = "rgba(104, 104, 104, 0.6)";
  });
  down.addEventListener("mouseout", () => {
    clearInterval(downInterval);
    up.style.opacity = 0;
    down.style.opacity = 0;
    down.style.backgroundColor = "rgba(104, 104, 104, 0.6)";
  });
}
right_slide();
