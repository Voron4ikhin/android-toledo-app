package toledo24.pro.presentation.catalog

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import toledo24.pro.data.network.catalog.CatalogItemModel
import toledo24.pro.data.network.catalog.DetailProductModel
import toledo24.pro.data.room.catalog.CatalogEntity
import toledo24.pro.domain.usecase.catalog.GetAnalogsRelatedUseCase
import toledo24.pro.domain.usecase.catalog.GetDetailProductUseCase

class CatalogDetailFragmentViewModel(
    private val getDetailProductUseCase: GetDetailProductUseCase,
    private val getAnalogsRelatedUseCase: GetAnalogsRelatedUseCase
): ViewModel() {

    private val _detailProduct = MutableSharedFlow<DetailProductModel>()
    val detailProduct = _detailProduct.asSharedFlow()

    private val _relatedProduct = MutableSharedFlow<List<DetailProductModel>>()
    val relatedProduct = _relatedProduct.asSharedFlow()

    fun getDetailProduct(category : String, name: String) {
        viewModelScope.launch {
            val catalog = getDetailProductUseCase.execute(category, name);
            Log.d("tag", "${catalog.result}")
            _detailProduct.emit(catalog.result)
        }
    }

    fun getAnalogsRelated(relatedXml : String){
        viewModelScope.launch {
            val analogsRelated = getAnalogsRelatedUseCase.execute(relatedXml)
            _relatedProduct.emit(analogsRelated.ANALOG_PRODUCT)
        }
    }



}