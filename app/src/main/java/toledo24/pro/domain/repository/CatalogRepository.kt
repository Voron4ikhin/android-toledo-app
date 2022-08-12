package toledo24.pro.domain.repository

import toledo24.pro.data.network.CategoryListModel
import toledo24.pro.data.network.ResponseCatalogModel
import toledo24.pro.data.network.catalog.ResponseAnalogRelatedModel
import toledo24.pro.data.network.catalog.ResponseCatalogListModel
import toledo24.pro.data.network.catalog.ResponseDetailProductModel
import toledo24.pro.data.room.catalog.CatalogEntity

interface CatalogRepository {

    //Получаем все меню каталога с сервера
    suspend fun getCatalog(): ResponseCatalogModel

    //Получаем меню каталога по переданному ID из room
    suspend fun getCatalogRoom(IBLOCK_SECTION_ID : String?): List<CatalogEntity>

    //Получаем детальную страницу товара с сервера
    suspend fun getDetailProduct(name :String, category :String): ResponseDetailProductModel

    //Добавляем информацию о пользователе в room
    suspend fun insertCatalogRoom( catalogEntityList: List<CatalogEntity>)

    //Получаем список товаров
    suspend fun getProductList(category : String, page : String): ResponseCatalogListModel //что то надо вернуть

    //Получаем список либо аналогов либо сопутки
    suspend fun getAnalogsRelated(xmlId: String) : ResponseAnalogRelatedModel



}