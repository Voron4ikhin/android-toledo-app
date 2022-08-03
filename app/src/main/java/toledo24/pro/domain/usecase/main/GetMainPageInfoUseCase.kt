package toledo24.pro.domain.usecase.main

import toledo24.pro.data.network.mainPage.BannerAndPopular
import toledo24.pro.domain.repository.MainPageRepository

class GetMainPageInfoUseCase(private val mainPageRepository: MainPageRepository) {

    suspend fun execute(): BannerAndPopular {
        val response = mainPageRepository.getMainPage()
        return response.result.body
    }

}