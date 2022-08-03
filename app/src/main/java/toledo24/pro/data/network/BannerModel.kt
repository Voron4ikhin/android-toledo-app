package toledo24.pro.data.network

import com.google.gson.annotations.SerializedName

data class BannerModel(
    @SerializedName("ID")
    val id: Int,
    @SerializedName("NAME")
    val name: String,
    @SerializedName("PICTURE")
    val picture: String,
)
