package toledo24.pro.domain.repository

import toledo24.pro.data.network.basket.ResponseCardModel
import toledo24.pro.data.room.basket.BasketEntity

interface CardRepository {

    //Обноволяем корзину и возвращаем ее по api
    suspend fun updateBasket(USER_ID: String, productId: String, quantity: Int) : ResponseCardModel

    suspend fun updateBasketRoom(basketEntityList: List<BasketEntity>)

    suspend fun getBasketRoom() : List<BasketEntity>

    suspend fun clearBasket()

}