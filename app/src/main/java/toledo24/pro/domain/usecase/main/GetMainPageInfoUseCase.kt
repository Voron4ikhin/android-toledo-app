package toledo24.pro.domain.usecase.main

import toledo24.pro.data.network.basket.BasketModel
import toledo24.pro.data.network.mainPage.BannerAndPopular
import toledo24.pro.data.room.basket.BasketEntity
import toledo24.pro.domain.repository.CardRepository
import toledo24.pro.domain.repository.MainPageRepository

class GetMainPageInfoUseCase(
    private val mainPageRepository: MainPageRepository,
    private val cardRepository: CardRepository
) {

    suspend fun execute(): BannerAndPopular {
        val response = mainPageRepository.getMainPage()
        return response.result.body
    }

    suspend fun clearBasket(){
        cardRepository.clearBasket()
    }

    suspend fun getBasketApi(USER_ID : String) : Map<String, BasketModel>{
        return cardRepository.getBasket(USER_ID).result.BASKET_LIST
    }

    suspend fun setBasketRoom(basket : Map<String, BasketModel>){
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

    suspend fun getBasketRoom():  List<BasketEntity>{
        return cardRepository.getBasketRoom()
    }

}