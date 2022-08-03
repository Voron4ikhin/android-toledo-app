package toledo24.pro.data.room.catalog

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class CatalogEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?=null,
    val PRODUCT_ID: String,
    val IBLOCK_SECTION_ID: String? = null,
    val NAME: String,
    val SECTION_PAGE_URL: String,
    val CODE: String,
    val FLAG_ACTIVE: Boolean,
)