package com.example.tiptime.Data

import java.util.Date

class StaffOffineRes(private val staffDao: HotelUserDao) : StaffRes {
    override fun getStaff(
        StaffId : String,
        StaffName : String,
    ): List<Staff> = staffDao.getStaff(StaffId, StaffName)

}