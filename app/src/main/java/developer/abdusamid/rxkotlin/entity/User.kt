package developer.abdusamid.rxkotlin.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
class User {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var userName: String? = null
    var password: String? = null
}