package toledo24.pro.presentation.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import toledo24.pro.data.network.catalog.CatalogItemModel
import toledo24.pro.domain.usecase.catalog.GetProductListUseCase

class CatalogProductsViewModel(
    private val getProductListUseCase: GetProductListUseCase,
): ViewModel()  {

    private val _catalogProduct =  MutableSharedFlow<List<CatalogItemModel>>()
    val catalogProduct = _catalogProduct.asSharedFlow()


    fun getCatalogList(category : String, page: String) {
        viewModelScope.launch {
            val catalog = getProductListUseCase.execute(category, page);
            _catalogProduct.emit(catalog)
        }
    }

}