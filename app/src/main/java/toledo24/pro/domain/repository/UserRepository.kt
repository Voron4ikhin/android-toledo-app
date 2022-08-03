package toledo24.pro.domain.repository

import toledo24.pro.data.network.ResponseCheckTokenUser
import toledo24.pro.data.network.autorization.ResponseUserInfoModel
import toledo24.pro.data.network.autorization.ResponseGetSmsModel
import toledo24.pro.data.room.user.UserEntity

interface UserRepository {

    suspend fun deleteUserRoom(userEntity: UserEntity)

    suspend fun checkingTokenUserApi(userEntity: UserEntity): ResponseCheckTokenUser

    //Отправляем номер телефона для получения кода по sms
    suspend fun sendCode(phone: String): ResponseGetSmsModel

    //Отправляем sms код и получаем объект пользователя
    suspend fun checkCode(phone: String, code: String): ResponseUserInfoModel

    //Добавляем информацию о пользователе в room
    suspend fun insertUserRoom(userEntity: UserEntity)

    //Достаем данные о пользователе из room
    suspend fun getUserRoom():UserEntity

}