package toledo24.pro.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import toledo24.pro.data.room.catalog.CatalogDao
import toledo24.pro.data.room.catalog.CatalogEntity
import toledo24.pro.data.room.user.UserDao
import toledo24.pro.data.room.user.UserEntity

@Database(entities = [UserEntity::class, CatalogEntity::class], version = 1)
abstract class RoomService: RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun catalogDao(): CatalogDao

    companion object {
        var INSTANCE: RoomService? = null

        fun getInstance(context: Context): RoomService {
            if (INSTANCE == null){
                synchronized(RoomService::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, RoomService::class.java, "toledoDB")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE!!
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }

}
