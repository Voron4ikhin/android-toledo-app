package toledo24.pro.data.network

import com.google.gson.annotations.SerializedName


data class ResponseCatalogModel(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("status_code")
    val status_code: Int,
    @SerializedName("result")
    val result: CatalogModel
)
