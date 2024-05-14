import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Room
import androidx.room.Update

@Dao
interface RoomDao {
    @Insert
    suspend fun addNewRoom(room: Room)

    @Query("SELECT status FROM room WHERE hotel_id = :hotelId AND roomType = :roomType LIMIT 1")
    fun checkRoomStatus(hotelId: String, roomType: String): String

    @Query("SELECT roomType FROM room WHERE hotel_id = :hotelId LIMIT 1")
    fun findRoomType(hotelId: String): String

    @Query("SELECT MIN(price) || ' - ' || MAX(price) FROM room WHERE hotel_id = :hotelId")
    fun getPriceRange(hotelId: String): String

    @Query("SELECT * FROM room WHERE hotel_id = :hotelId")
    fun getAllRooms(hotelId: String): List<Room>

    @Query("""
        SELECT * FROM room WHERE hotel_Id = :hotelId AND roomType = :roomType AND roomId IN (
            SELECT roomId FROM booking WHERE 
            (BookedStartDate BETWEEN :startDate AND :endDate) OR 
            (BookedEndDate BETWEEN :startDate AND :endDate)
        )
    """)
    fun getRoomsForDateRangeAndType(hotelId: String, roomType: String, startDate: Long, endDate: Long): List<Room>

    @Update
    suspend fun updateRoom(room: Room)
}
