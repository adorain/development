package com.example.tiptime

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.tiptime.Data.PaymentMethod
import com.example.tiptime.Model.Pmethod
import com.example.tiptime.Data.Booking
import com.example.tiptime.SqlliteManagement.BookingDb
import com.example.tiptime.ui.theme.TipTimeTheme
import kotlinx.coroutines.delay

@Composable
fun PaymentLayout(


    onClickedButton :() -> Unit,

){


    var showDialog by remember{ mutableStateOf(false) }
    var isVisaExpanded by remember { mutableStateOf(false) }
    var iconResId by remember { mutableStateOf(R.drawable.down_icon) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            Modifier.padding(40.dp)
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
                Text(text = "Touch n Go", fontSize = 20.sp, color = Color.Black, modifier = Modifier.clickable { showDialog =true  })
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Divider(modifier = Modifier.padding(end = 30.dp, start = 30.dp),color = Color.Black)
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
                Text(text = "GrabPay", fontSize = 20.sp, color = Color.Black, modifier = Modifier.clickable { showDialog =true  })
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Divider(modifier = Modifier.padding(end = 30.dp, start = 30.dp),color = Color.Black)
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
                Text(text = "Boost", fontSize = 20.sp, color = Color.Black, modifier = Modifier.clickable { showDialog =true  })
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Divider(modifier = Modifier.padding(end = 30.dp, start = 30.dp),color = Color.Black)
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
                modifier = Modifier.padding(start = 50.dp, top = 35.dp)
            ) {
                Text(text = "Online Banking", fontSize = 20.sp, color = Color.Black)
            }
            Column(modifier = Modifier.padding(start = 10.dp,top=30.dp)) {
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
            ExpandList(sections = PaymentMethod().listPaymentMethod(), onNavigation = onClickedButton)
        }


        if(showDialog){
            paymentSuccessful(onClickedButton)
        }
    }
}

@Composable
fun ExpandList(sections: List<Pmethod>,onNavigation: () -> Unit) {
    LazyColumn {
        itemsIndexed(sections) { index, item ->
            ListItem(item = item, onNavigation = onNavigation)
            if (index < sections.size - 1) {
                Divider(modifier = Modifier.padding(horizontal = 30.dp))
            }
        }
    }
}


@Composable
fun ListItem(item: Pmethod , onNavigation: () -> Unit) {
    var showDialog by remember{ mutableStateOf(false) }
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
            color = Color.Black,
            modifier = Modifier.clickable { showDialog = true }
        )
        if(showDialog){
            paymentSuccessful(
                onNavigation
            )
        }

    }
}

@Composable
fun paymentSuccessful(
    onNavigation : () -> Unit
){
    var countdown by remember{mutableStateOf(5)}
    LaunchedEffect(Unit) {
        while (countdown > 0) {
            delay(5000)
            countdown--
        }
        onNavigation()
    }
    Dialog(
        onDismissRequest = onNavigation,
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Card(
            modifier = Modifier
                .width(500.dp)
                .height(500.dp)
                .background(Color.White)
        ) {
            Column {
                Image(painterResource(R.drawable.tick),contentDescription = null)
            }
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(text = "Payment Successful", fontSize = 30.sp)
            }
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(text = "Return to home $countdown in seconds", fontSize = 20.sp)
            }
        }
    }
}

@Preview
@Composable
fun paymentPreview(){
    TipTimeTheme {
        PaymentLayout {
            
        }
    }
}
