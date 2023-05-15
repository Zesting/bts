"use strict"

let colorRowNumber = 0;
let sizeRowNumber = 0;

//상품옵션 등록에서 상품을 고르는 셀렉트를 채우는 기능
function fillSeletProducts(){
    const select = document.getElementById("productIdInput");
    
    let tmpOption;
    if(products == ""){
        console.error("제품 로딩 실패");
        return;
    }
    for(let product of products){
        tmpOption = document.createElement("option");
        tmpOption.text = `${product.name} / ${product.brand} / ${product.category}`;
        tmpOption.value = product.id;
        select.appendChild(tmpOption);
    }
}
fillSeletProducts();

function productSubmit() {
    const form = document.getElementById("product-form");
    if (form.checkValidity()) {
        alert("작성 완료");
        form.submit();
    } else {
        alert("다시 확인해주세요");
    }
}
const createSetOption = (() => {
    const idWhereCreate = "option-form";
    const option = document.getElementById(idWhereCreate);
    const colorContainer = document.createElement("div");
    colorContainer.id = "option-container-color";
    const sizeContainer = document.createElement("div");
    sizeContainer.id = "option-container-size";
    option.appendChild(colorContainer);
    option.appendChild(sizeContainer);
    const optionMenu = document.createElement("div");
    optionMenu.setAttribute("id", "option-menu");
    let tmpRow = document.createElement("div");
    tmpRow.classList.add("option-head");
    tmpRow.innerHTML = `<div class="option-col-0">번호</div><div class="option-col-1">색상</div>`;
    colorContainer.appendChild(tmpRow);
    tmpRow = document.createElement("div");
    tmpRow.classList.add("option-head");
    tmpRow.innerHTML = `<div class="option-col-0">번호</div><div class="option-col-1">사이즈</div>`;
    sizeContainer.appendChild(tmpRow);


    let tmpDiv;
    let tmpInput;
    let tmpDatalist;
    let tmpOption;
    function plusRow(option) {
        switch (option) {
            case 'color': {
                tmpRow = document.createElement("div");
                colorContainer.appendChild(tmpRow);
                tmpRow.setAttribute("id", "option-color-row-" + (++colorRowNumber));
                tmpRow.classList.add("option-row");
                tmpDiv = document.createElement("div");
                tmpDiv.innerHTML = colorRowNumber;
                tmpDiv.classList.add("option-col-0");
                tmpRow.appendChild(tmpDiv);
                tmpInput = document.createElement("input");
                tmpInput.classList.add("option-col-1");
                tmpInput.setAttribute("list", "option_color_sample");
                tmpInput.id = "option-color-input-" + colorRowNumber;
                tmpRow.appendChild(tmpInput);
                tmpDatalist = document.createElement("datalist");
                tmpDatalist.id = "option_color_sample";
                tmpRow.appendChild(tmpDatalist);
                tmpOption = document.createElement("option");
                tmpOption.value = "black";
                tmpDatalist.appendChild(tmpOption);
                tmpOption = document.createElement("option");
                tmpOption.value = "white";
                tmpDatalist.appendChild(tmpOption);
                tmpOption = document.createElement("option");
                tmpOption.value = "red";
                tmpDatalist.appendChild(tmpOption);
                tmpOption = document.createElement("option");
                tmpOption.value = "navy";
                tmpDatalist.appendChild(tmpOption);
                tmpOption = document.createElement("option");
                tmpOption.value = "brown";
                tmpDatalist.appendChild(tmpOption);

                break;
            }
            case 'size': {
                tmpRow = document.createElement("div");
                sizeContainer.appendChild(tmpRow);
                tmpRow.setAttribute("id", "option-size-row-" + (++sizeRowNumber));
                tmpRow.classList.add("option-row");
                tmpDiv = document.createElement("div");
                tmpDiv.innerHTML = sizeRowNumber;
                tmpDiv.classList.add("option-col-0");
                tmpRow.appendChild(tmpDiv);
                tmpInput = document.createElement("input");
                tmpInput.classList.add("option-col-1");
                tmpInput.setAttribute("list", "option_size_sample");
                tmpInput.id = "option-size-input-" + sizeRowNumber;
                tmpRow.appendChild(tmpInput);
                tmpDatalist = document.createElement("datalist");
                tmpDatalist.id = "option_size_sample";
                tmpRow.appendChild(tmpDatalist);
                tmpOption = document.createElement("option");
                tmpOption.value = "230";
                tmpDatalist.appendChild(tmpOption);
                tmpOption = document.createElement("option");
                tmpOption.value = "235";
                tmpDatalist.appendChild(tmpOption);
                tmpOption = document.createElement("option");
                tmpOption.value = "240";
                tmpDatalist.appendChild(tmpOption);
                tmpOption = document.createElement("option");
                tmpOption.value = "245";
                tmpDatalist.appendChild(tmpOption);
                tmpOption = document.createElement("option");
                tmpOption.value = "250";
                tmpDatalist.appendChild(tmpOption);
                tmpOption = document.createElement("option");
                tmpOption.value = "255";
                tmpDatalist.appendChild(tmpOption);
                tmpOption = document.createElement("option");
                tmpOption.value = "260";
                tmpDatalist.appendChild(tmpOption);
                tmpOption = document.createElement("option");
                tmpOption.value = "265";
                tmpDatalist.appendChild(tmpOption);
                tmpOption = document.createElement("option");
                tmpOption.value = "270";
                tmpDatalist.appendChild(tmpOption);
                tmpOption = document.createElement("option");
                tmpOption.value = "275";
                tmpDatalist.appendChild(tmpOption);
                tmpOption = document.createElement("option");
                tmpOption.value = "280";
                tmpDatalist.appendChild(tmpOption);
                break;
            }
        }
    }
    function deleteRow(option) {
        switch (option) {
            case 'd-color': {
                let target = document.getElementById("option-color-row-" + (colorRowNumber--));
                colorContainer.removeChild(target);
                break;
            }
            case 'd-size': {
                let target = document.getElementById("option-size-row-" + (sizeRowNumber--));
                sizeContainer.removeChild(target);
                break;
            }
        }
    }
    plusRow('color');
    plusRow('size');
    return ((option) => {
        if (option[0] != 'd') {
            plusRow(option);
        }
        else {
            deleteRow(option);
        }
    });
})();
const switchFormPage = (() => {
    const productPage = document.getElementById("product-container-product");
    const optionPage = document.getElementById("product-container-option");
    optionPage.style.display = "none";
    return ((option) => {
        switch (option) {
            case 'product': {
                optionPage.style.display = "none";
                productPage.style.display = "flex";
                break;
            }
            case 'option': {
                productPage.style.display = "none";
                optionPage.style.display = "flex";
                break;
            }
        }
    });
})();
const submitOptionForm = (() => {

    let tmpSize;
    let tmpColor;

    function submitOne(productId, color, size) {
        let form = document.createElement("form");
        form.style.display = "none";
        document.body.appendChild(form);
        form.innerHTML = `
                    <input name="productId" value="${productId}">
                    <input name="color" value="${color}">
                    <input name="size" value="${size}">
                `;
        if (form.checkValidity()) {
            $.ajax({
                url: '/product/option/new',
                type: 'post',
                data: $(form).serialize(),
                async: false,
                success: (data) => { console.log(`an option saved(id: ${productId}, color: ${color}. size: ${size})`); },
                error: (xhr, status, error) => { console.error("옵션 저장 실패") }
            });
        } else {
            console.error("옵션 저장 실패 productId: " + productId + " color: " + color + " size: " + size);
        }
        window.location.href = `/product/new`;
    }

    return (() => {
        let productIdValue = document.getElementById("productIdInput").value;
        for (let colorNum = colorRowNumber; colorNum >= 1; colorNum--) {
            tmpColor = document.getElementById("option-color-input-" + colorNum).value;
            for (let sizeNum = sizeRowNumber; sizeNum >= 1; sizeNum--) {
                tmpSize = document.getElementById("option-size-input-" + sizeNum).value;

                submitOne(productIdValue, tmpColor, tmpSize);
            }
        }
        alert("서브밋 완료?");
    });
})();

//윈도우가 닫힐때 오프너 페이지를 새로고침하기;
window.onbeforeunload = function () {
    opener.location.reload();
};