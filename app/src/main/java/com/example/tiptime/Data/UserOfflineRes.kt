package com.example.tiptime.Data

abstract class UserOfflineRes(private val userDao: NormalUserDao) : UserRes {
    override fun getStaff(
        UserId : String,
        UserName : String,
    ): String = userDao.getUser(UserId, UserName)

}