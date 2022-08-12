package toledo24.pro.domain.usecase.catalog

import toledo24.pro.data.network.catalog.AnalogRelatedModel
import toledo24.pro.data.network.catalog.CatalogListModel
import toledo24.pro.domain.repository.CatalogRepository

class GetAnalogsRelatedUseCase(private val catalogRepository: CatalogRepository) {

    suspend fun execute(relatedXml : String): AnalogRelatedModel {
        val response = catalogRepository.getAnalogsRelated(relatedXml)
        return response.result
    }

}