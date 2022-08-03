package toledo24.pro.data.network


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import toledo24.pro.data.network.autorization.ResponseGetSmsModel
import toledo24.pro.data.network.autorization.ResponseUserInfoModel
import toledo24.pro.data.network.mainPage.ResponseMainPage
import toledo24.pro.data.room.user.UserEntity


interface RetrofitService {

    @Headers("x-api-auth-token: 4e29b4b06a67d740-c66f8314afb9eb01-506f385e1fc5e2dc")
    @GET("multi/page/index")
    suspend fun getMainPage(): ResponseMainPage

    @Headers("x-api-auth-token: 4e29b4b06a67d740-c66f8314afb9eb01-506f385e1fc5e2dc")
    @GET("multi/get_menu_list")
    suspend fun getCatalog(): ResponseCatalogModel

    @Headers("x-api-auth-token: 4e29b4b06a67d740-c66f8314afb9eb01-506f385e1fc5e2dc")
    @FormUrlEncoded
    @POST("multi/get-sms-code")
    suspend fun sendCode(
        @Field("PHONE") phone: String
    ): ResponseGetSmsModel

    @Headers("x-api-auth-token: 4e29b4b06a67d740-c66f8314afb9eb01-506f385e1fc5e2dc")
    @FormUrlEncoded
    @POST("multi/check-sms-code")
    suspend fun checkCode(
        @Field("PHONE") phone: String,
        @Field("CODE") code: String
    ): ResponseUserInfoModel

    @Headers("x-api-auth-token: 4e29b4b06a67d740-c66f8314afb9eb01-506f385e1fc5e2dc")
    @POST("checkTokenUser")
    suspend fun checkTokenUser(@Body userEntity: UserEntity): ResponseCheckTokenUser

    companion object {
        var retrofitService: RetrofitService? = null
        var httpClient = OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()

        fun getInstance() : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://test.toledo24.pro/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }

}