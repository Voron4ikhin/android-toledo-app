package toledo24.pro.domain.usecase.catalog

import android.util.Log
import toledo24.pro.data.network.CategoryListModel
import toledo24.pro.data.room.catalog.CatalogEntity
import toledo24.pro.domain.repository.CatalogRepository

class ShowCategoriesUseCase(
    private val catalogRepository: CatalogRepository)
{

    //Получаем ответ от сервера
    suspend fun getListCategories(IBLOCK_SECTION_ID : String?) : List<CatalogEntity>{
        return catalogRepository.getCatalogRoom(IBLOCK_SECTION_ID)
    }

}