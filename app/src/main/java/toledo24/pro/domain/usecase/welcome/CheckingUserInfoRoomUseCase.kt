package toledo24.pro.domain.usecase.welcome

import android.util.Log
import toledo24.pro.data.network.autorization.ResponseGetSmsModel
import toledo24.pro.data.room.user.UserEntity
import toledo24.pro.domain.repository.UserRepository

class CheckingUserInfoRoomUseCase(private val userRepository: UserRepository) {

    //Получаем ответ от сервера
    suspend fun checkUserData(): Boolean {
        if(userRepository.getUserRoom() != null) return true
        return false
    }

}