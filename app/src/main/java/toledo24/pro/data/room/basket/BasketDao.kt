package toledo24.pro.data.room.basket

import androidx.room.*
import toledo24.pro.data.room.catalog.CatalogEntity
import toledo24.pro.data.room.user.UserEntity


@Dao
interface BasketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBasket(basketEntityList: List<BasketEntity>)

    @Query("SELECT * FROM BasketEntity")
    suspend fun selectBasket(): List<BasketEntity>

    @Query("DELETE FROM BasketEntity")
    suspend fun clearBasket()

}