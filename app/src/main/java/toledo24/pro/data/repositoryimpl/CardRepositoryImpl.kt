package toledo24.pro.data.repositoryimpl

import toledo24.pro.data.network.RetrofitService
import toledo24.pro.data.network.RetrofitService.Companion.retrofitService
import toledo24.pro.data.network.basket.ResponseCardModel
import toledo24.pro.data.room.RoomService
import toledo24.pro.data.room.basket.BasketEntity
import toledo24.pro.domain.repository.CardRepository

class CardRepositoryImpl(retrofitService: RetrofitService): CardRepository {

    override suspend fun updateBasket(USER_ID: String, productId: String, quantity: Int)
    : ResponseCardModel = retrofitService!!.updateCard(USER_ID, productId, quantity)

    override suspend fun updateBasketRoom(basketEntityList: List<BasketEntity>) {
        RoomService.INSTANCE?.basketDao()!!.insertBasket(basketEntityList)
    }

    override suspend fun getBasketRoom() : List<BasketEntity>{
        return RoomService.INSTANCE?.basketDao()!!.selectBasket()
    }

    override suspend fun clearBasket(){
        RoomService.INSTANCE?.basketDao()!!.clearBasket()
    }

}