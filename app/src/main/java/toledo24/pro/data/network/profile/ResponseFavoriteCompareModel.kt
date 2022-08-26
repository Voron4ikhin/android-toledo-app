package toledo24.pro.data.network.profile

import com.google.gson.annotations.SerializedName

data class ResponseFavoriteCompareModel (
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("status_code")
    val status_code: Int,
    @SerializedName("result")
    val result: FavoriteCompareModel,
)

data class FavoriteCompareModel(
    @SerializedName("Compare")
    val compare: List<String>,
    @SerializedName("Favorite")
    val favorite: List<String>,
)


