"use strict"
function openProductRegister() {
    let width = "500px";
    let height = "500px";
    let left = (screen.width - width) / 3;
    let top = (screen.height - height) / 3;
    window.open("/product/new", "_blank", "width=" + width + ", height=" + height + ", left=" + left + ", top=" + top);
}