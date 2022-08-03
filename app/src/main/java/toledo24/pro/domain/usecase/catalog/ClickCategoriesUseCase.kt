package toledo24.pro.domain.usecase.catalog

import android.util.Log
import toledo24.pro.domain.repository.CatalogRepository

class ClickCategoriesUseCase(private val catalogRepository: CatalogRepository) {

    fun execute(): Boolean {
        // определяем есть ли у элемента sub елементы еслм да то true иначе false
        Log.d("tag", "Просто нажади на элемент")
        return false
    }

}