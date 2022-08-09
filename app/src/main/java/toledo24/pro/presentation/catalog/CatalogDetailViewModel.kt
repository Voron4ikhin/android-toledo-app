package toledo24.pro.presentation.catalog

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import toledo24.pro.data.network.catalog.DetailProductModel
import toledo24.pro.data.room.catalog.CatalogEntity
import toledo24.pro.domain.usecase.catalog.GetDetailProductUseCase

class CatalogDetailFragmentViewModel(
    private val getDetailProductUseCase: GetDetailProductUseCase,
): ViewModel() {

    private val _detailProduct = MutableSharedFlow<DetailProductModel>()
    val detailProduct = _detailProduct.asSharedFlow()

    fun getDetailProduct(category : String, name: String) {
        viewModelScope.launch {
             Log.d("tag", "${category} + ${name}")
            val catalog = getDetailProductUseCase.execute(category, name);
            _detailProduct.emit(catalog.result)
        }
    }

}