package toledo24.pro.domain.repository

import toledo24.pro.data.network.profile.ResponseFavoriteCompareModel

interface ProfileRepository {

    //Получаем все избранные товары
    suspend fun getFavoriteCompare(USER_ID: String): ResponseFavoriteCompareModel

}