package toledo24.pro.presentation.catalog

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import toledo24.pro.data.network.basket.BasketModel
import toledo24.pro.data.network.catalog.CatalogItemModel
import toledo24.pro.domain.usecase.basket.AddToBasketUseCase
import toledo24.pro.domain.usecase.catalog.GetProductListUseCase

class CatalogProductsViewModel(
    private val getProductListUseCase: GetProductListUseCase,
    private val addToBasketUseCase: AddToBasketUseCase
): ViewModel()  {

    private val _catalogProduct =  MutableSharedFlow<List<CatalogItemModel>>()
    val catalogProduct = _catalogProduct.asSharedFlow()

    private val _basket = MutableSharedFlow<Map<String, BasketModel>>()
    val basket = _basket.asSharedFlow()

    val basketCount = MutableLiveData<Int>()
    val elemCount = MutableLiveData<Int>()
    val toast = MutableLiveData<Boolean>()


    fun getCatalogList(category : String, page: String) {
        viewModelScope.launch {
            val catalog = getProductListUseCase.execute(category, page);
            _catalogProduct.emit(catalog)
        }
    }

    fun addToBasket(productId: String, quantity: String){
        viewModelScope.launch {
            val getBasketApi = addToBasketUseCase.setNewDataApi(productId, quantity.toInt())    // Корзина сервера
            val getBasketRoom = addToBasketUseCase.getRoomBasket()      // Корзина в Room
            var basketSize : Int = 0        // Размер корзины в Room

            addToBasketUseCase.clearBasket()    // Очищаем корзину
            addToBasketUseCase.setRoomBasket(getBasketApi)  // Записываем в Room новую корзину

            getBasketRoom.forEach{
                basketSize += it.QUANTITY.toInt()
            }

            basketCount.value = basketSize      // Переменная LiveData для показа кол-ва элементов в корзине
            toast.value = true      // Переменная LiveData для отображения toast при добавлении товара в корзину
        }

    }

}