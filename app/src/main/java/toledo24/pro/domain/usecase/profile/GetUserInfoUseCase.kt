package toledo24.pro.domain.usecase.profile

import toledo24.pro.data.room.user.UserEntity
import toledo24.pro.domain.repository.ProfileRepository

class GetUserInfoUseCase(
    private val profileRepository: ProfileRepository
) {

    suspend fun getUserInfo(): UserEntity {
        val userInfo = profileRepository.getUserInfo()
        return userInfo
    }

    suspend fun deleteUserInfo(userEntity: UserEntity){
        profileRepository.deleteUserInfo(userEntity)
    }


}