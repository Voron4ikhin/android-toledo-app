package toledo24.pro.data.repositoryimpl

import toledo24.pro.data.network.RetrofitService
import toledo24.pro.data.network.mainPage.ResponseBrandList
import toledo24.pro.data.network.mainPage.ResponseMainPage
import toledo24.pro.domain.repository.MainPageRepository


class MainPageRepositoryImpl(retrofitService: RetrofitService) : MainPageRepository {

    override suspend fun getMainPage(): ResponseMainPage = RetrofitService.retrofitService!!.getMainPage()


    override suspend fun getBrandList(): ResponseBrandList = RetrofitService.retrofitService!!.getBrandList()


}