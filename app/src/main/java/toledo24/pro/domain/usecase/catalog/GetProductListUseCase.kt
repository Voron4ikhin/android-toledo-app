package toledo24.pro.domain.usecase.catalog

import android.util.Log
import toledo24.pro.data.network.CatalogModel
import toledo24.pro.data.network.catalog.CatalogItemModel
import toledo24.pro.domain.repository.CatalogRepository


class GetProductListUseCase(private val catalogRepository: CatalogRepository) {

    suspend fun execute(category : String, page : String): List<CatalogItemModel>{
        val response = catalogRepository.getProductList(category, page).result.body.result
        Log.d("tag", "${response}")
        return response
    }

}