package com.example.tiptime.Data

import com.example.tiptime.Model.Pmethod
import com.example.tiptime.R

class PaymentMethod {
    fun listPaymentMethod(): List<Pmethod> {
        return listOf<Pmethod>(
            Pmethod(R.drawable._560px_rhb_logo,R.string.Rhb),
            Pmethod(R.drawable.maybank,R.string.MayBank),
            Pmethod(R.drawable.public_bank_logo_vector,R.string.Publicbank),
            Pmethod(R.drawable.hongleong,R.string.HongLeongBank),
            Pmethod(R.drawable.cimb001_cimb_logo_png,R.string.CIMB),
            Pmethod(R.drawable.ambank_group,R.string.Ambank)
        )
    }


}