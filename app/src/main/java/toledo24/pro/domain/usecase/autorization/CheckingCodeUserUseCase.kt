package toledo24.pro.domain.usecase.autorization

import toledo24.pro.data.network.autorization.ResponseUserInfoModel
import toledo24.pro.data.network.autorization.UserData
import toledo24.pro.data.room.user.UserEntity
import toledo24.pro.domain.repository.UserRepository

class CheckingCodeUserUseCase(private val userRepository: UserRepository) {

    //Получаем ответ от сервера
    suspend fun checkStateSmsCode(phone:String, code: String): ResponseUserInfoModel {
        return userRepository.checkCode(phone, code)
    }

    //запись в room данных пользователя
    suspend fun saveUserInfoRoom( userData: UserData){
        val userEntity = UserEntity(
            1,
            userData.userId,
            userData.authorized,
            userData.login,
            userData.email,
            userData.name,
            userData.firstName,
            userData.secondName,
            userData.lastName,
        )
        userRepository.insertUserRoom(userEntity)
    }

    suspend fun getUserFromRoom():UserEntity{
        return userRepository.getUserRoom()
    }

}