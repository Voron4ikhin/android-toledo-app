package toledo24.pro.data.room.basket

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class BasketEntity (
    @PrimaryKey(autoGenerate = true)
    val PRODUCT_ID: Int,
    val QUANTITY: String,
)