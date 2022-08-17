package toledo24.pro.presentation.cart

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import toledo24.pro.data.network.basket.BasketModel
import toledo24.pro.domain.usecase.basket.AddToBasketUseCase
import toledo24.pro.domain.usecase.basket.GetCardUseCase

class CardViewModel(
    private val getCardUseCase: GetCardUseCase,
    private val addToBasketUseCase: AddToBasketUseCase
): ViewModel() {

    private val _cardList = MutableSharedFlow<List<BasketModel>>()
    val cardList = _cardList.asSharedFlow()

    private val _cardListInOrder = MutableSharedFlow<List<BasketModel>>()
    val cardListInOrder = _cardListInOrder.asSharedFlow()

    val fragmentName = MutableLiveData<String>()
    val basketCount = MutableLiveData<Int>()
    val basketInStockCount =  MutableLiveData<Int>()
    val basketUnderOrderCount =  MutableLiveData<Int>()
    val toast = MutableLiveData<Boolean>()
    val currentItem = MutableLiveData<String>()


    init {
        viewModelScope.launch {
            val card = getCardUseCase.execute();
            var cardInStock : List<BasketModel> = emptyList()
            var cardUnderOrder :  List<BasketModel> = emptyList()
            card.forEach{
                if(it.value.QUANTITY_INSTOCK != 0) cardInStock = cardInStock + it.value
                if(it.value.QUANTITY_UNDER_ORDER != 0) cardUnderOrder = cardUnderOrder + it.value
            }
            fragmentName.value = "card"
            _cardList.emit(cardInStock)
            _cardListInOrder.emit(cardUnderOrder)
        }
    }

    fun addToBasket(productId: String, quantity: String){
        viewModelScope.launch {
            val getBasketApi = addToBasketUseCase.setNewDataApi(productId, quantity.toInt())    // Корзина сервера
            var basketSize : Int = 0        // Размер корзины в Room
            var basketInStockSize: Int = 0
            var basketUnderOrderSize: Int = 0

            addToBasketUseCase.clearBasket()    // Очищаем корзину
            addToBasketUseCase.setRoomBasket(getBasketApi)  // Записываем в Room новую корзину

            val getBasketRoom = addToBasketUseCase.getRoomBasket()      // Корзина в Room

            getBasketRoom.forEach{
                basketSize += it.QUANTITY.toInt()
                basketInStockSize += it.QUANTITY_IN_STOCK
                basketUnderOrderSize += it.QUANTITY_UNDER_ORDER
            }

            basketCount.value = basketSize      // Переменная LiveData для показа кол-ва элементов в корзине
            basketInStockCount.value = basketInStockSize
            basketUnderOrderCount.value = basketUnderOrderSize


            toast.value = true      // Переменная LiveData для отображения toast при добавлении товара в корзину
        }
    }


    fun subtractFromBasket(productId: String, quantity: String){
        viewModelScope.launch {
            val getBasketApi = addToBasketUseCase.setNewDataApiMinus(productId, quantity.toInt())    // Корзина сервера
            var basketSize : Int = 0        // Размер корзины в Room
            var basketInStockSize: Int = 0
            var basketUnderOrderSize: Int = 0

            addToBasketUseCase.clearBasket()    // Очищаем корзину
            addToBasketUseCase.setRoomBasket(getBasketApi)  // Записываем в Room новую корзину

            val getBasketRoom = addToBasketUseCase.getRoomBasket()      // Корзина в Room

            getBasketRoom.forEach{
                basketSize += it.QUANTITY.toInt()
                basketInStockSize += it.QUANTITY_IN_STOCK
                basketUnderOrderSize += it.QUANTITY_UNDER_ORDER
            }

            basketCount.value = basketSize      // Переменная LiveData для показа кол-ва элементов в корзине
            basketInStockCount.value = basketInStockSize
            basketUnderOrderCount.value = basketUnderOrderSize


            toast.value = true      // Переменная LiveData для отображения toast при добавлении товара в корзину
        }
    }



}