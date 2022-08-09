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

    val toast = MutableLiveData<Boolean>()


    fun getCatalogList(category : String, page: String) {
        viewModelScope.launch {
            val catalog = getProductListUseCase.execute(category, page);
            _catalogProduct.emit(catalog)
        }
    }

    fun addToBasket(productId: String, quantity: String){
        viewModelScope.launch {
            val getBasketApi = addToBasketUseCase.setNewDataApi(productId, quantity.toInt())
            //Очищаем корзину
            addToBasketUseCase.clearBasket()
            //Записываем в Room новую корзину
            addToBasketUseCase.setRoomBasket(getBasketApi)
            val getBasketRoom = addToBasketUseCase.getRoomBasket()
            getBasketRoom.forEach{
                Log.d("tag", "${it.PRODUCT_ID} - ${it.QUANTITY}")
            }

            toast.value = true
            //addToBasketUseCase.setRoomBasket(getBasketApi)
            //val getBasketRoom =
        }

    }

}