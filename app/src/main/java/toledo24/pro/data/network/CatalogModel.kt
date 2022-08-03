package toledo24.pro.data.network

import com.google.gson.annotations.SerializedName

data class CatalogModel(
    @SerializedName("CHILDS")
    val CHILDS: Map<String, CategoryListModel>,
)

data class CategoryListModel(
    @SerializedName("ID")
    val ID: String,
    @SerializedName("IBLOCK_SECTION_ID")
    val IBLOCK_SECTION_ID: String,
    @SerializedName("NAME")
    val NAME: String,
    @SerializedName("SECTION_PAGE_URL")
    val SECTION_PAGE_URL: String,
    @SerializedName("CODE")
    val CODE: String,
    @SerializedName("FLAG_ACTIVE")
    val FLAG_ACTIVE: Boolean,
    @SerializedName("CHILDS")
    val CHILDS: Map<String, CategoryListModel>,
)
