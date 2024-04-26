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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiptime.Data.PaymentMethod
import com.example.tiptime.Model.Pmethod

@Preview
@Composable
fun PaymentLayout(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        Row (
            Modifier.padding(10.dp)
        ){

            Text(text = "Choose a payment option" , color = Color.White)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Column (modifier = Modifier.padding(start = 30.dp)){
                Image(painterResource(R.drawable.touch__n_go_ewallet_logo), contentDescription = null, modifier = Modifier.size(100.dp))
            }
            Column (modifier = Modifier.padding(start = 100.dp, top = 35.dp)){
                Text(text = "Touch n Go" , fontSize = 20.sp, color = Color.White)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Divider(modifier = Modifier.padding(end = 30.dp, start = 30.dp))
        Spacer( modifier = Modifier.height(20.dp))

        Row {
            Column (modifier = Modifier.padding(start = 30.dp)){
                Image(painterResource(R.drawable.grabpay_wallet), contentDescription = null, modifier = Modifier.size(100.dp))
            }
            Column (modifier = Modifier.padding(start = 100.dp, top = 35.dp)){
                Text(text = "GrabPay" , fontSize = 20.sp, color = Color.White)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Divider(modifier = Modifier.padding(end = 30.dp, start = 30.dp))
        Spacer( modifier = Modifier.height(20.dp))

        Row {
            Column (modifier = Modifier.padding(start = 30.dp)){
                Image(painterResource(R.drawable.boost), contentDescription = null, modifier = Modifier.size(100.dp))
            }
            Column (modifier = Modifier.padding(start = 100.dp, top = 35.dp)){
                Text(text = "Boost" , fontSize = 20.sp, color = Color.White)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Divider(modifier = Modifier.padding(end = 30.dp, start = 30.dp))
        Spacer( modifier = Modifier.height(20.dp))

        Row {
            ExpandList(sections = PaymentMethod().listPaymentMethod())
            Column (modifier = Modifier.padding(start = 30.dp)){
                Image(painterResource(R.drawable.logo_visa_transparent_png), contentDescription = null, modifier = Modifier.size(100.dp))
            }
            Column (modifier = Modifier.padding(start = 100.dp, top = 35.dp)){
                Text(text = "Visa" , fontSize = 20.sp, color = Color.White)
            }
        }
    }
}

@Composable
fun SectionHeader(
    text: String,
    isExpanded: Boolean,
    onHeaderClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onHeaderClicked() }
            .background(Color.LightGray)
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.weight(1.0f)
        )
        // Show different icon based on whether the section is expanded or collapsed
        val iconResource = if (isExpanded) {
            painterResource(id = R.drawable.up_icon_154668) // Change this to your up icon resource
        } else {
            painterResource(id = R.drawable.arrow_down_icon_11549436707mosicxsqad) // Change this to your down icon resource
        }
        Image(
            painter = iconResource,
            contentDescription = null,
            modifier = Modifier.size(24.dp) // Adjust the size of the icon as needed
        )
    }
}

@Composable
fun ExpandList(sections: List<Pmethod>) {
    val isExpandedMap = rememberSaveable {
        mutableStateMapOf<Int, Boolean>().apply {
            sections.forEachIndexed { index, _ ->
                this[index] = false // Initially set all sections to collapsed
            }
        }
    }

    LazyColumn {
        itemsIndexed(sections) { index, paymentMethod ->
            Section(
                sectionData = paymentMethod,
                isExpanded = isExpandedMap[index] ?: false,
                onHeaderClick = { isExpandedMap[index] = !(isExpandedMap[index] ?: false) }
            )
        }
    }
}

@Composable
fun Section(
    sectionData: Pmethod,
    isExpanded: Boolean,
    onHeaderClick: () -> Unit
) {
    Column {
        SectionHeader(
            text = sectionData.stringResourcesId.toString(), // Displaying string resource ID for now, you can modify this as needed
            isExpanded = isExpanded,
            onHeaderClicked = onHeaderClick
        )
        // You can add content specific to each section here, which will be displayed when expanded
        if (isExpanded) {
            // For example, you can add Text or other composables here
            Text(text = "Some content for ${sectionData.stringResourcesId}")
        }
    }
}



