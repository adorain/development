package com.example.tiptime.Data

interface StaffRes {
    fun getStaffName(staffName: String, staffId: String): List<Staff>

    fun getStaff(staffId: String, staffName: String): List<Staff>
}


