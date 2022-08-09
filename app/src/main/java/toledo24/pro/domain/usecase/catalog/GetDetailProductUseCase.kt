package toledo24.pro.domain.usecase.catalog

import toledo24.pro.data.network.CatalogModel
import toledo24.pro.data.network.catalog.ResponseDetailProductModel
import toledo24.pro.domain.repository.CatalogRepository

class GetDetailProductUseCase(private val catalogRepository: CatalogRepository) {

    suspend fun execute(category: String, name :String): ResponseDetailProductModel {
        val response = catalogRepository.getDetailProduct(name, category)
        return response
    }


}