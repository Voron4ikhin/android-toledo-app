package toledo24.pro.domain.repository

import toledo24.pro.data.network.profile.ResponseFavoriteCompareModel
import toledo24.pro.data.room.user.UserEntity

interface ProfileRepository {

    //Получаем все избранные товары
    suspend fun getFavoriteCompare(USER_ID: String): ResponseFavoriteCompareModel

    //Получаем информацию о пользоватаеле
    suspend fun getUserInfo() : UserEntity

    //Удалить данные пользователя
    suspend fun deleteUserInfo(userEntity: UserEntity)

}