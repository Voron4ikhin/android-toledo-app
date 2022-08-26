package toledo24.pro.domain.usecase.profile

import toledo24.pro.domain.repository.CardRepository
import toledo24.pro.domain.repository.ProfileRepository

class GetFavoriteUseCase(
    private val profileRepository: ProfileRepository
) {

    suspend fun getCount(): List<String> {
        val response = profileRepository.getFavoriteCompare("13523")
        return response.result.favorite
    }

}