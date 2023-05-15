"use strict"

//-----------------------------------------페이지 로드시 실행----------------------------------------------------

//---------상품 변경시 상품에 해당하는 옵션 데이터를 불러오기----------------------------------

//상품을 고르는 input 객체를 변수화
const productSelect = document.getElementById('productId');
//상품을 선택하면 상품id를 get방식으로 request해서 옵션의 사이즈와 색상들을 가져온다.
productSelect.addEventListener('change', (event) => {
    const selectedId = event.target.value;
    window.location.href = `/grouppurchase/new/${selectedId}`
    console.log(`Selected option: ${selectedOption}`);
});
//상품을 선택하여 새로고침 되었을 때 원래 선택을 유지시킨다.
if (productId != null) {
    productSelect.value = productId;
}

//---------전역변수---------------------------------------------------------------------------------
//구매상품 옵션의 수
let optionRow = 0;
//--------------------------------------------------------------
function openProductRegister() {
    window.open("/product/new", "_blank", "width=500px, height=500px, left=200px, top=200px");
}

const controlOptionForm = (() => {
    if (productId == "") {
        console.log("product is not fetching");
        return;
    }
    const container = document.getElementById("option-form-container");

    let colors = productColors;
    let sizes = productSizes;
    //판매글옵션을 추가하는 폼을 1개 추가하는 기능
    function createNewForm() {
        if (colors == null) {
            container.innerHTML = "<p>상품을 먼저 선택해주세요</p>"
            return;
        }
        let form;
        form = `
            <form id="group-purchase-form-option-${++optionRow}">
                <select id="product-option-color-${optionRow}" name="optionColor">
                    <option value = "-1">색상</option>`;
        for (let color of colors) {
            form += `<option value="${color}">${color}</option>`;
        }
        form += `</select>
                <select id="product-option-size-${optionRow}" name="optionSize">
                    <option value="-1">사이즈</option>`;
        for (let size of sizes) {
            form += `<option value="${size}">${size}</option>`;
        }
        form += `</select>
                <input id="product-option-quantity-${optionRow}" name="quantity" placeholder="재고수량">
                <input id="product-option-price-${optionRow}" name="price" placeholder="판매가">
            </form>
        `;
        container.innerHTML += form;
    }
    //마지막 생성된 옵션폼을 없애는 기능
    function removeForm() {
        let target = document.getElementById(`group-purchase-form-option-${optionRow--}`);
        container.removeChild(target);
    }
    //최초 한개 옵션 생성
    createNewForm();
    return ((command) => {
        switch (command) {
            case 'new': {
                createNewForm();
                break;
            }
            case 'remove': {
                removeForm();
                break;
            }
        }
    });
})();
//최종 포스트 서브밋
function submitGroupPurchase() {
    //무결성 체크

    //같은 색상과 사이즈를 가진 옵션이 있는지 확인
    for (let i = 1; i <= optionRow; i++) {
        if (i < optionRow) {
            for (let j = i + 1; j <= optionRow; j++) {
                if ($(`#product-option-color-${i}`).val() == $(`#product-option-color-${j}`).val()
                    && $(`#product-option-size-${i}`).val() == $(`#product-option-size-${j}`).val()) {
                    alert(`중복된 옵션이 있습니다. 색상: ${$(`#product-option-color-${i}`).val()}, 사이즈: ${$(`#product-option-size-${i}`).val()}`);
                    return;
                }
            }
        }
    }
    //groupPurchase post
    let form = document.createElement("form");
    $("body").append(form);
    form.style.display = "none";
    $(form)
        .append($("#productId").clone().val($("#productId").val()))
        .append($("#saleStart").clone())
        .append($("#saleEnd").clone())
        .append($("#minQuantity").clone())
        .append($("#information").clone());
    //공동구매상품옵션에 저장할 공동구매id 추출
    $.ajax({
        url: "/grouppurchase/new",
        type: "post",
        data: $(form).serialize(),
        success: function (data) {
            //options post
            let optionNumber = 0;
            let optionForm;
            while (true) {
                //사용할 폼 초기화
                optionForm = document.createElement("form");
                optionForm.style.display = "none";
                //더이상 옵션을 찾을 수 없을때 break
                if (document.getElementById(`group-purchase-form-option-${++optionNumber}`) == null) {
                    break;
                }
                $(optionForm).append(`
                    <input name="groupPurchaseId" value="${data}">
                    <input name="productId" value="${$("#productId").val()}">`)
                    .append($(`#product-option-color-${optionNumber}`).clone().val($(`#product-option-color-${optionNumber}`).val()))
                    .append($(`#product-option-size-${optionNumber}`).clone().val($(`#product-option-size-${optionNumber}`).val()))
                    .append($(`#product-option-quantity-${optionNumber}`).clone())
                    .append($(`#product-option-price-${optionNumber}`).clone());
                $("body").append(optionForm);
                $.ajax({
                    url: "/groupurchase/option/new",
                    type: "post",
                    data: $(optionForm).serialize(),
                    success: (data => { }),
                    error: ((xhr, status, error) => { console.log("option error"); })
                });
            }
        },
        error: function (xhr, status, error) {
            console.log(xhr, status, error);
        }
    });

}



