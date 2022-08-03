package toledo24.pro.domain.usecase.catalog

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import toledo24.pro.data.network.CatalogModel
import toledo24.pro.data.network.CategoryListModel
import toledo24.pro.data.network.ResponseCatalogModel
import toledo24.pro.data.room.catalog.CatalogEntity
import toledo24.pro.data.room.user.UserEntity
import toledo24.pro.domain.repository.CatalogRepository

class GetCategoriesUseCase(private val catalogRepository: CatalogRepository) {
//
//    private val _categoriesList = MutableSharedFlow<List<CatalogEntity>>()
//    val categoriesList = _categoriesList.asSharedFlow()

    suspend fun execute(): CatalogModel {
        val response = catalogRepository.getCatalog()
        return response.result
    }

    suspend fun saveCatalogInRoom(catalogModel : CatalogModel){
        val finalArray : List<CatalogEntity> = findAllCategories(catalogModel.CHILDS)
        catalogRepository.insertCatalogRoom(finalArray)
    }
//
    suspend fun findAllCategories(catalogModel : Map<String, CategoryListModel>) : List<CatalogEntity>{
        val gapArray =  mutableListOf<CatalogEntity>()
        catalogModel.forEach{
            val catalogEntity = CatalogEntity(
                it.value.ID.toInt(),
                it.value.ID,
                it.value.IBLOCK_SECTION_ID,
                it.value.NAME,
                it.value.SECTION_PAGE_URL,
                it.value.CODE,
                it.value.FLAG_ACTIVE,
            )


            gapArray.add(catalogEntity)

            if (it.value.CHILDS !== null) {
                val gap2:  List<CatalogEntity> = findAllCategories(it.value.CHILDS!!)
                gapArray.addAll(gap2)
            }
        }
        return  gapArray
    }

}