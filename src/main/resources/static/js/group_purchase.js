"use strict"

function openProductRegister() {
    let width = "500px";
    let height = "500px";
    let left = "200px";
    let top = "200px";
    const productWindow = window.open("/product/new", "_blank", "width=" + width + ", height=" + height + ", left=" + left + ", top=" + top);
    //생성창이 닫혔을경우 판매글을 새로고침하기위함
    productWindow.opener = window;
}
const controlOptionForm = (() => {
    if(productId == ""){
        console.log("product is not fetching");
        return;
    }
    const container = document.getElementById("option-form-container");
    let optionRow = 0;
    let colors = productColors;
    let sizes = productSizes;
    function createNewForm() {
        let form;
        form = `
            <form id="group-purchase-form-option-${++optionRow}">
                <select id="product-option-color-${optionRow}">
                    <option value = "-1">색상</option>`;
        for(let color of colors){
                    form += `<option value="${color}">${color}</option>`;
                }
        form += `</select>
                <select id="product-option-size-${optionRow}">
                    <option value="-1">사이즈</option>`;
        for(let size of sizes){
            form += `<option value="${size}">${size}</option>`;
        }
        form += `</select>
                <input id="product-option-quantity-${optionRow}" name="quantity" placeholder="재고수량">
                <input id="product-option-price-${optionRow}" name="price" placeholder="판매가">
            </form>
        `;
        container.innerHTML += form;
    }
    function removeForm(){
        let target = document.getElementById(`group-purchase-form-option-${optionRow--}`);
        container.removeChild(target);
    }

    return ((command) => {
        switch(command){
            case 'new':{
                createNewForm();
                break;
            }
            case 'remove':{
                removeForm();
                break;
            }
        }
    });
})();


