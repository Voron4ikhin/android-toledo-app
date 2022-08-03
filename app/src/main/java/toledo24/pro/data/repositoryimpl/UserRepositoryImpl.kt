package toledo24.pro.data.repositoryimpl

import toledo24.pro.data.network.ResponseCheckTokenUser
import toledo24.pro.data.network.autorization.ResponseUserInfoModel
import toledo24.pro.data.room.user.UserEntity
import toledo24.pro.domain.repository.UserRepository
import toledo24.pro.data.network.RetrofitService
import toledo24.pro.data.network.RetrofitService.Companion.retrofitService
import toledo24.pro.data.network.autorization.ResponseGetSmsModel
import toledo24.pro.data.room.RoomService

/**
 * class for working repository User
 */
class UserRepositoryImpl(
    retrofitService: RetrofitService,
    roomService: RoomService
): UserRepository {

    override suspend fun deleteUserRoom(userEntity: UserEntity) {
        RoomService.INSTANCE?.userDao()!!.deleteUser(userEntity)
    }

    override suspend fun checkingTokenUserApi(userEntity: UserEntity): ResponseCheckTokenUser = retrofitService!!.checkTokenUser(userEntity)

    override suspend fun sendCode(phone: String): ResponseGetSmsModel = retrofitService!!.sendCode(phone)

    override suspend fun checkCode(phone:String, code: String): ResponseUserInfoModel = retrofitService!!.checkCode(phone, code)

    override suspend fun insertUserRoom(userEntity: UserEntity){
        RoomService.INSTANCE?.userDao()!!.insertUser(userEntity)
    }

    override suspend fun getUserRoom(): UserEntity{
        return RoomService.INSTANCE?.userDao()!!.getUserInfo()
    }

}