package com.example.tiptime.Data

abstract class StaffOffineRes(private val hotelUserDao: HotelUserDao) : UserRes {
    /*override fun getStaff(
        StaffId : String,
        StaffName : String,
    ): String = staffDao.getStaff(StaffId, StaffName).toString()*/
    fun updateStaff(newStaffName: String, newStaffPhoneNumber: String, newStaffEmail: String,
                             newStaffPassword: String)
            = hotelUserDao.updateStaff(newStaffName, newStaffPhoneNumber, newStaffEmail, newStaffPassword)

}