package toledo24.pro.domain.usecase.basket

import android.util.Log
import toledo24.pro.data.network.basket.BasketModel
import toledo24.pro.data.room.catalog.CatalogEntity
import toledo24.pro.domain.repository.CardRepository
import toledo24.pro.domain.repository.UserRepository

class GetCardUseCase(
    private val cardRepository: CardRepository,
    private val userRepository: UserRepository,
) {

    suspend fun execute(): Map<String, BasketModel> {
        val USER_ID : String = userRepository.getUserRoom().userId     //
        return cardRepository.getBasket(USER_ID).result.BASKET_LIST
    }
}