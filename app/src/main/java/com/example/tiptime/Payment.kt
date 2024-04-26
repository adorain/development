package com.example.tiptime

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiptime.Data.PaymentMethod
import com.example.tiptime.Model.Pmethod

@Preview
@Composable
fun PaymentLayout(){
    var isVisaExpanded by remember { mutableStateOf(false) }
    var iconResId by remember { mutableStateOf(R.drawable.down_icon) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            Modifier.padding(30.dp)
        ) {

            Text(text = "Choose a payment option", color = Color.Black)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Column(modifier = Modifier.padding(start = 30.dp)) {
                Image(
                    painterResource(R.drawable.touch__n_go_ewallet_logo),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )
            }
            Column(modifier = Modifier.padding(start = 100.dp, top = 35.dp)) {
                Text(text = "Touch n Go", fontSize = 20.sp, color = Color.Black)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalDivider(modifier = Modifier.padding(end = 30.dp, start = 30.dp),color = Color.Black)
        Spacer(modifier = Modifier.height(20.dp))

        Row {
            Column(modifier = Modifier.padding(start = 30.dp)) {
                Image(
                    painterResource(R.drawable.grabpay_wallet),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )
            }
            Column(modifier = Modifier.padding(start = 100.dp, top = 35.dp)) {
                Text(text = "GrabPay", fontSize = 20.sp, color = Color.Black)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalDivider(modifier = Modifier.padding(end = 30.dp, start = 30.dp),color = Color.Black)
        Spacer(modifier = Modifier.height(20.dp))

        Row {
            Column(modifier = Modifier.padding(start = 30.dp)) {
                Image(
                    painterResource(R.drawable.boost),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )
            }
            Column(modifier = Modifier.padding(start = 100.dp, top = 35.dp)) {
                Text(text = "Boost", fontSize = 20.sp, color = Color.Black)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalDivider(modifier = Modifier.padding(end = 30.dp, start = 30.dp),color = Color.Black)
        Spacer(modifier = Modifier.height(20.dp))

        Row {
            Column(modifier = Modifier.padding(start = 30.dp)) {
                Image(
                    painterResource(R.drawable.logo_visa_transparent_png),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )
            }
            Column(
                modifier = Modifier.padding(start = 100.dp, top = 35.dp)
            ) {
                Text(text = "Visa", fontSize = 20.sp, color = Color.Black)
            }
            Column(modifier = Modifier.padding(start = 30.dp,top=30.dp)) {
                Image(
                    painterResource(iconResId),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            // Toggle the state of Visa section expansion
                            isVisaExpanded = !isVisaExpanded
                            iconResId = if (isVisaExpanded) {
                                R.drawable.up_icon_154668
                            } else {
                                R.drawable.down_icon
                            }

                        }

                )
            }
        }

        // If Visa section is expanded, show the list
        if (isVisaExpanded) {
            ExpandList(sections = PaymentMethod().listPaymentMethod())
        }



    }
}

@Composable
fun ExpandList(sections: List<Pmethod>) {
    LazyColumn {
        itemsIndexed(sections) { index, item ->
            ListItem(item = item)
            if (index < sections.size - 1) {
                HorizontalDivider(modifier = Modifier.padding(horizontal = 30.dp))
            }
        }
    }
}

@Composable
fun ListItem(item: Pmethod) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 30.dp, vertical = 12.dp)
    ) {
        Image(
            painterResource(item.imageResourceID),
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = stringResource(item.stringResourcesId),
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}
