package com.example.tiptime.Data

abstract class UserOfflineRes(private val userDao: NormalUserDao) : UserRes {
  /*  override fun getStaff(
        UserId : String,
        UserName : String,
    ): String = userDao.getUser(UserId, UserName)*/
  override fun updateUser(newUserName: String, newUserPhoneNumber: String, newUserEmail: String,
                          newUserPassword: String)
          = userDao.updateUser(newUserName, newUserPhoneNumber, newUserEmail, newUserPassword)

}