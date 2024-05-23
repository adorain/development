package com.example.tiptime.Data

class UserOfflineRes(private val userDao: NormalUserDao) : UserRes {


    override suspend fun updateUser(
        newUserName: String,
        newUserPhoneNumber: String,
        newUserEmail: String,
        newUserPassword: String,
        userId: String
    ) {
        //userDao.updateUserDetails(userId, newUserName, newUserPhoneNumber, newUserEmail, newUserPassword)
    }


}




