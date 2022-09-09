package toledo24.pro.data.repositoryimpl

import toledo24.pro.data.network.RetrofitService
import toledo24.pro.data.network.autorization.UserData
import toledo24.pro.data.network.profile.ResponseFavoriteCompareModel
import toledo24.pro.data.room.RoomService
import toledo24.pro.data.room.user.UserEntity
import toledo24.pro.domain.repository.ProfileRepository

class ProfileRepositoryImpl(retrofitService: RetrofitService): ProfileRepository {

    override suspend fun getFavoriteCompare(USER_ID: String)
            : ResponseFavoriteCompareModel = RetrofitService.retrofitService!!.getFavoriteCompare(USER_ID)


    override suspend fun getUserInfo(): UserEntity = RoomService.INSTANCE?.userDao()!!.getUserInfo()


    override suspend fun deleteUserInfo(userEntity: UserEntity){
        RoomService.INSTANCE?.userDao()!!.deleteUser(userEntity)
    }

}