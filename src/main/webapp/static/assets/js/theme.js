// Custom theme code

if ($('.clean-gallery').length > 0) {
    baguetteBox.run('.clean-gallery', {animation: 'slideIn'});
}

if ($('.clean-product').length > 0) {
    $(window).on("load", function () {
        $('.sp-wrap').smoothproducts();
    });
}

// STUDENT CODE
const url_api = 'http://localhost:8888/';
const url_shopping_cart = url_api + 'cart.html';

async function updateQuantity() {
    let prodQuantity = this.value;
    let prodId = this.getAttribute("data-prod-id");

    //prepare data
    let dataToBePosted = new FormData();
    dataToBePosted.append('quantity', prodQuantity);
    dataToBePosted.append( 'productId', prodId);

    //send data
    await fetch(url_shopping_cart, {
        method: 'POST',
        body: dataToBePosted
    });

    //reload page
    window.location.reload();
}

let quantityInputs = document.getElementsByClassName("form-control quantity-input");
for (let quantityInput of quantityInputs) {
    quantityInput.addEventListener("click", updateQuantity);
}

