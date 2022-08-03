package toledo24.pro.data.network.mainPage

import com.google.gson.annotations.SerializedName

data class ResponseMainPage (
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("status_code")
    val status_code: Int,
    @SerializedName("result")
    val result: BodyMainPage,
)

data class BodyMainPage(
    @SerializedName("body")
    val body: BannerAndPopular,
)

data class BannerAndPopular(
    @SerializedName("bannersList")
    val bannersList: List<BannerListModel>,
    @SerializedName("popular_products")
    val popular_products: List<PopularProductsModel>,
)

data class BannerListModel(
    @SerializedName("ID")
    val ID: String,
    @SerializedName("NAME")
    val NAME: String,
    @SerializedName("PICTURE")
    val PICTURE: String,
    @SerializedName("HREF_BANNER")
    val HREF_BANNER: String,

)

data class PopularProductsModel(
    @SerializedName("ID")
    val ID: String,
    @SerializedName("CODE")
    val CODE: String,
    @SerializedName("NAME")
    val NAME: String,
    @SerializedName("DETAIL_PAGE_URL")
    val DETAIL_PAGE_URL: String,
    @SerializedName("DETAIL_PICTURE")
    val DETAIL_PICTURE: String,
    @SerializedName("PRODUCT_ID")
    val PRODUCT_ID: String,
    @SerializedName("PRICE")
    val PRICE: String,
    @SerializedName("NORMAL_PRICE")
    val NORMAL_PRICE: String,
    @SerializedName("PICTURE")
    val PICTURE: String,
    @SerializedName("QUANTITY")
    val QUANTITY: String,
    @SerializedName("WEIGHT")
    val WEIGHT: String,
    @SerializedName("CATEGORY_NAME")
    val CATEGORY_NAME: String,
    @SerializedName("RATE")
    val RATE: String,
    @SerializedName("CODE_PRODUCT")
    val CODE_PRODUCT: String,
    @SerializedName("BASIC_UNIT")
    val BASIC_UNIT: String,
    @SerializedName("AMOUNT")
    val AMOUNT: String,
    @SerializedName("PREPAIR_QUANTITY")
    val PREPAIR_QUANTITY: String,
    )