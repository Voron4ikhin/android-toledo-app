package toledo24.pro.data.network.mainPage

import com.google.gson.annotations.SerializedName

data class ResponseBrandList(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("status_code")
    val status_code: Int,
    @SerializedName("result")
    val result: BrandResult,
)

data class BrandResult(
    @SerializedName("brandsList")
    val brandsList: List<BrandsListModel>,
)

data class BrandsListModel(
    @SerializedName("ID")
    val ID: String,
    @SerializedName("NAME")
    val NAME: String,
    @SerializedName("CODE")
    val CODE: String,
    @SerializedName("IMAGE")
    val IMAGE: String,
    @SerializedName("DETAIL_PAGE_URL")
    val DETAIL_PAGE_URL: String,
)