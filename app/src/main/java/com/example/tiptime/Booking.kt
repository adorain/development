package com.example.tiptime



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Preview
@Composable
fun booking (
    onNextButtonClicked:() -> Unit = {},
    onCancelButtonClicked : () -> Unit = {},
    modifier: Modifier = Modifier){
    val text = " "
    Column {
        Row (
            ){
            Image(painter = painterResource(R.drawable.nitro_wallpaper_02_3840x2400), contentDescription = null )
        }
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 245.dp),

    ){
        Row (
            modifier = Modifier.fillMaxWidth()
        ){
            Text(text = " who" , color = Color.White, fontSize = 35.sp )

        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(

        ) {
            Text(text = " WWWWWWWWWWWWWWW", fontSize = 15.sp , color = Color.White,)
        }
    }

    Column (
        Modifier
            .fillMaxSize()
            .padding(top = 400.dp),

    ){

        Box(modifier = Modifier){
            Divider(modifier = Modifier.padding(start = 2.dp ),thickness = 3.dp)
            Text(text = "Single Room", color = Color.White , fontSize = 20.sp,modifier = Modifier.padding(top = 35.dp))
            Divider(modifier = Modifier.padding(start = 2.dp , top = 90.dp),thickness = 3.dp)
            Text(text = "Single Room", color = Color.White , fontSize = 20.sp,modifier = Modifier.padding(top = 130.dp))
            Divider(modifier = Modifier.padding(start = 2.dp , top = 190.dp),thickness = 3.dp)
            Text(text = "Single Room", color = Color.White , fontSize = 20.sp,modifier = Modifier.padding(top = 230.dp))
            Divider(modifier = Modifier.padding(start = 2.dp , top = 290.dp),thickness = 3.dp)



        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(modifier = Modifier
            .padding(start = 45.dp)
            .fillMaxWidth()) {
            OutlinedButton(
                onClick =  onCancelButtonClicked,modifier = Modifier.padding(end = 100.dp).size(width = 100.dp, height = 50.dp)) {
                Text(text = "Cancel")
            }
            OutlinedButton(onClick = onNextButtonClicked ,modifier = Modifier.size(width = 100.dp, height = 50.dp)) {
                Text(text = "Next")
            }

        }
        Spacer(modifier = Modifier.height(50.dp))

    }

}
