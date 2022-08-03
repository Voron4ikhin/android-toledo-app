package toledo24.pro.domain.usecase.autorization

import toledo24.pro.data.network.autorization.ResponseGetSmsModel
import toledo24.pro.domain.repository.UserRepository

class CheckingPhoneUserUseCase(private val userRepository: UserRepository) {

    //Получаем ответ от сервера
    suspend fun getStateSmsCode(phone: String):ResponseGetSmsModel{
        return userRepository.sendCode(phone)
    }

}