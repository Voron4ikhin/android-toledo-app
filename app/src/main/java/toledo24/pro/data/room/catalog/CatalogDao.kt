package toledo24.pro.data.room.catalog

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import toledo24.pro.data.network.CategoryListModel
import toledo24.pro.data.room.user.UserEntity

@Dao
interface CatalogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCatalog(catalogEntityList: List<CatalogEntity>)

    @Query("SELECT * FROM CatalogEntity WHERE IBLOCK_SECTION_ID == :IBLOCK_SECTION_ID")
    suspend fun getCatalogRoom(IBLOCK_SECTION_ID: String?): List<CatalogEntity>

    @Query("SELECT * FROM CatalogEntity WHERE IBLOCK_SECTION_ID IS NULL")
    suspend fun getCatalogRoomNull(): List<CatalogEntity>

}