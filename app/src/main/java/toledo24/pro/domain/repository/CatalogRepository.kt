package toledo24.pro.domain.repository

import toledo24.pro.data.network.CategoryListModel
import toledo24.pro.data.network.ResponseCatalogModel
import toledo24.pro.data.room.catalog.CatalogEntity

interface CatalogRepository {

    //Получаем все меню каталога с сервера
    suspend fun getCatalog(): ResponseCatalogModel

    //Получаем меню каталога по переданному ID из room
    suspend fun getCatalogRoom(IBLOCK_SECTION_ID : String?): List<CatalogEntity>

    //Добавляем информацию о пользователе в room
    suspend fun insertCatalogRoom( catalogEntityList: List<CatalogEntity>)



}