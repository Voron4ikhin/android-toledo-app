package toledo24.pro.presentation.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import toledo24.pro.data.network.mainPage.BannerListModel
import toledo24.pro.data.network.mainPage.PopularProductsModel
import toledo24.pro.data.room.catalog.CatalogEntity
import toledo24.pro.domain.usecase.main.GetMainPageInfoUseCase

class NavigationViewModel(
    private val getMainPageInfoUseCase: GetMainPageInfoUseCase
): ViewModel() {

    private val _bannersList = MutableSharedFlow<List<BannerListModel>>()
    val bannersList = _bannersList.asSharedFlow()

    private val _popularList = MutableSharedFlow<List<PopularProductsModel>>()
    val popularList = _popularList.asSharedFlow()

    val fragmentName = MutableLiveData<String>()

init {
    viewModelScope.launch {
        val catalog = getMainPageInfoUseCase.execute();
        fragmentName.value = "main"
        _bannersList.emit(catalog.bannersList)
        _popularList.emit(catalog.popular_products)
    }

}


}