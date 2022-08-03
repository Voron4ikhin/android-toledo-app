package toledo24.pro.data.room.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userId: String,
    val authorized: String,
    val login: String,
    val email: String? = null,
    val name: String? = null,
    val firstName: String? = null,
    val secondName: String? = null,
    val thirdName: String? = null,
)
