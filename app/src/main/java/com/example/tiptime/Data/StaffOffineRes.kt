package com.example.tiptime.Data
import java.util.Date

abstract class StaffOffineRes(private val staffDao: HotelUserDao) : UserRes {
    override fun getStaff(
        StaffId : String,
        StaffName : String,
    ): String = staffDao.getStaff(StaffId, StaffName).toString()

}



