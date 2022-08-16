package toledo24.pro.presentation.cart

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import toledo24.pro.data.network.basket.BasketModel
import toledo24.pro.data.room.catalog.CatalogEntity
import toledo24.pro.domain.usecase.basket.GetCardUseCase

class CardViewModel(
    private val getCardUseCase: GetCardUseCase,
): ViewModel() {

    private val _cardList = MutableSharedFlow<List<BasketModel>>()
    val cardList = _cardList.asSharedFlow()

    private val _cardListInOrder = MutableSharedFlow<List<BasketModel>>()
    val cardListInOrder = _cardListInOrder.asSharedFlow()

    val fragmentName = MutableLiveData<String>()


    init {
        viewModelScope.launch {
            val card = getCardUseCase.execute();
            var cardInStock : List<BasketModel> = emptyList()
            var cardUnderOrder :  List<BasketModel> = emptyList()
            card.forEach{
                //Log.d("tag", "${it.value.NAME} - ${it.value.QUANTITY} - ${it.value.QUANTITY_INSTOCK} - ${it.value.QUANTITY_UNDER_ORDER} ")
                if(it.value.QUANTITY_INSTOCK != 0) cardInStock = cardInStock + it.value
                if(it.value.QUANTITY_UNDER_ORDER != 0) cardUnderOrder = cardUnderOrder + it.value
            }
//            Log.d("tag", "cardInStock - ${cardInStock}")
//            Log.d("tag", "cardUnderOrder - ${cardUnderOrder}")
            fragmentName.value = "card"
            _cardList.emit(cardInStock)
            _cardListInOrder.emit(cardUnderOrder)
//            getCategoriesUseCase.saveCatalogInRoom(catalog)
//            val res = showCategoriesUseCase.getListCategories(_stateClickItem.value)
//            _categoriesList.emit(res)
        }
    }

//    fun updateName(){
//        viewModelScope.launch {
//            fragmentName.value = "КорзинаCart"
//        }
//    }
}