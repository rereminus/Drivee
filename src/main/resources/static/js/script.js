ymaps.ready(function () {
    var location = ymaps.geolocation;
var myMap = new ymaps.Map('map', {
    center: [53.346785, 83.776856],
    zoom: 10
}, {
    searchControlProvider: 'yandex#search'
});


location.get({
        mapStateAutoApply: true
    })
.then(
    function(result) {
        
        var userAddress = result.geoObjects.get(0).properties.get('text');
        var userCoodinates = result.geoObjects.get(0).geometry.getCoordinates();
        
        result.geoObjects.get(0).properties.set({
            balloonContentBody: 'Адрес: ' + userAddress +
                                '<br/>Координаты:' + userCoodinates
    });
        myMap.geoObjects.add(result.geoObjects)
    },
    function(err) {
        console.log('Ошибка: ' + err)
    }
);


    var confirmOrderBtn = document.getElementById('confirmOrder');
    var orderDetailsBtn = document.getElementById('orderDetails');
    var orderDetailsForm = document.getElementById('orderDetailsForm');

    confirmOrderBtn.addEventListener('click', function() {
        
        alert('Заказ подтверждён!');
    });

    orderDetailsBtn.addEventListener('click', function() {
        orderDetailsForm.style.display = 'block';
    });
    
    
    var tariffSelect = document.getElementById('tariff');
    var priceInput = document.getElementById('price');
    tariffSelect.addEventListener('change', function() {
        var selectedTariff = tariffSelect.value;
        if (selectedTariff === 'small') {
            priceInput.value = 'Минимальная цена 500р (Изменить)'; 
        } else if (selectedTariff === 'standard') {
            priceInput.value = 'Минимальная цена 700р (Изменить)'; 
        } else if (selectedTariff === 'premium') {
            priceInput.value = 'Минимальная цена 700р (Изменить)'; 
        } else if (selectedTariff === 'luxury') {
            priceInput.value = 'Минимальная цена 900р (Изменить)';
        } else if (selectedTariff === 'large') {
            priceInput.value = 'Минимальная цена 1500р (Изменить)'; 
        } 
    });
	
    // var paySelect = document.getElementById('pay');
    // var paymentInput = document.getElementById('payment');
    // paySelect.addEventListener('change', function() {
    //     var selectedPay = paySelect.value;
    //     if (selectedPay === 'cash') {
    //         paymentInput.value = 'Оплата водителю лично';
    //     } else if (selectedPay === 'card') {
    //         paymentInput.value = 'Введите номер телефона';
    //     }
    // });

});