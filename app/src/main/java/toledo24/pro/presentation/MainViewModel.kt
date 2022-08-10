package toledo24.pro.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import toledo24.pro.data.network.mainPage.BannerListModel
import toledo24.pro.data.room.basket.BasketEntity
import toledo24.pro.domain.repository.UserRepository
import toledo24.pro.domain.usecase.main.GetMainPageInfoUseCase

class MainViewModel(
    private val getMainPageInfoUseCase: GetMainPageInfoUseCase,
    private val userRepository: UserRepository
): ViewModel() {

    val badgeCount = MutableLiveData<Int>()

    fun updateBadgeCount(){
        viewModelScope.launch {
            val USER_ID : String = userRepository.getUserRoom().userId
            val basketApi = getMainPageInfoUseCase.getBasketApi(USER_ID)
            var basketCount: Int = 0

            getMainPageInfoUseCase.clearBasket()
            getMainPageInfoUseCase.setBasketRoom(basketApi)
            val basketRoom : List<BasketEntity> = getMainPageInfoUseCase.getBasketRoom()
            basketRoom.forEach{
                basketCount += it.QUANTITY.toInt()
            }
            badgeCount.value = basketCount
        }
    }

}