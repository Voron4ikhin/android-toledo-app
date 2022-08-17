package toledo24.pro.domain.usecase.basket

import android.util.Log
import toledo24.pro.data.network.basket.BasketModel
import toledo24.pro.data.network.catalog.CatalogItemModel
import toledo24.pro.data.room.basket.BasketEntity
import toledo24.pro.domain.repository.CardRepository
import toledo24.pro.domain.repository.UserRepository

class AddToBasketUseCase(
    private val cardRepository: CardRepository,
    private val userRepository: UserRepository
    )
{
    //Обновляем корзину на сервере и в Room +
    suspend fun setNewDataApi(productId: String, quantity: Int): Map<String, BasketModel>{
        val USER_ID : String = userRepository.getUserRoom().userId     //
        val basketRoom = getRoomBasket()          //получаем корзину из Room
        var quantityInt : Int = quantity          //пересоздаем quantity для изменения

        basketRoom.forEach{
            if(it.PRODUCT_ID == productId.toInt()) {
                quantityInt += it.QUANTITY.toInt()
            }
        }
        //Возвращаем обновленную корзину с сервера
        val newBasket = cardRepository.updateBasket(USER_ID, productId, quantityInt).result.BASKET_LIST
        return newBasket
    }

    //Обновляем корзину на сервере и в Room -
    suspend fun setNewDataApiMinus(productId: String, quantity: Int): Map<String, BasketModel>{
        val USER_ID : String = userRepository.getUserRoom().userId     //
        val basketRoom = getRoomBasket()          //получаем корзину из Room
        var quantityInt : Int = quantity          //пересоздаем quantity для изменения

        basketRoom.forEach{
            if(it.PRODUCT_ID == productId.toInt()) {
                quantityInt = it.QUANTITY.toInt() - quantityInt
            }
        }
        //Возвращаем обновленную корзину с сервера
        val newBasket = cardRepository.updateBasket(USER_ID, productId, quantityInt).result.BASKET_LIST
        return newBasket
    }

    //Функция записываем корзину в Room
    suspend fun setRoomBasket(basket : Map<String, BasketModel>) {
        val gapArray = mutableListOf<BasketEntity>()
        basket.forEach{
            val basketEntity = BasketEntity(
                it.key.toInt(),
                it.value.QUANTITY,
                it.value.QUANTITY_INSTOCK,
                it.value.QUANTITY_UNDER_ORDER
            )
            gapArray.add(basketEntity)
        }
        cardRepository.updateBasketRoom(gapArray)
    }

    //Получаем корзину из Room
    suspend fun getRoomBasket() : List<BasketEntity>{
        return cardRepository.getBasketRoom()
    }

    //Очищаем корзину в Room
    suspend fun clearBasket(){
        cardRepository.clearBasket()
    }


}