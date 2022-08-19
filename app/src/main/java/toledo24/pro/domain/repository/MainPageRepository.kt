package toledo24.pro.domain.repository

import toledo24.pro.data.network.mainPage.ResponseBrandList
import toledo24.pro.data.network.mainPage.ResponseMainPage

interface MainPageRepository {

    //Получаем все меню каталога с сервера
    suspend fun getMainPage(): ResponseMainPage

    //Получаем бренды с сервера
    suspend fun getBrandList(): ResponseBrandList

}