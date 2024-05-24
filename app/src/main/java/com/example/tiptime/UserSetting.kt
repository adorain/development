package com.example.tiptime
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tiptime.ui.theme.TipTimeTheme
import com.example.tiptime.ui.theme.white

@Composable
fun UserSettingContent(navController: NavController){
    var showLogoutDialog by remember { mutableStateOf(false) }
    var showAboutDialog by remember { mutableStateOf(false) }
    if (showLogoutDialog) {
        LogoutContent(
            onLogout = {
                showLogoutDialog = false
                navController.navigate(screen.home.name)
            },
            onCancel = { showLogoutDialog = false }
        )
    }

    if (showAboutDialog){
        AboutContent {
            var onDismiss = { showAboutDialog = false }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        Image(
            painter = painterResource(id = R.drawable.normal_user_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                /*.background(color = lavender)*/
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Setting",
                color = white,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                modifier = Modifier
                    .padding(
                        horizontal = 35.dp,
                        vertical = 63.dp
                    )
            )

            Column(
                modifier = Modifier
                    /*.fillMaxSize()*/
                    /*.background(color = navy_blue)*/
                    .padding(horizontal = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    "Account",
                    color = white,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .padding(
                            horizontal = 35.dp
                        )
                )

                /* Text(
                "Edit Profile",
                color = white,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                modifier = Modifier
                    .padding(
                        horizontal = 35.dp,
                        vertical = 30.dp
                    )
            )*/

                Button(onClick = { navController.navigate(screen.userSelection.name) }) {
                    Text(
                        "Log Out",
                        color = white,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        modifier = Modifier
                            .padding(
                                horizontal = 35.dp,
                                vertical = 30.dp
                            )
                    )
                }


            }
            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    /*  .fillMaxSize()*/
                    /*  .background(color = navy_blue)*/
                    .padding(horizontal = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    "Preferences",
                    color = white,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .padding(
                            horizontal = 60.dp
                        )
                )

                /*Text(
                "Language",
                color = white,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                modifier = Modifier
                    .padding(
                        horizontal = 35.dp,
                        vertical = 30.dp
                    )
            )*/

//                Button(onClick = { onCurrency }) {
//                    Text(
//                        "Currency",
//                        color = white,
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 30.sp,
//                        modifier = Modifier
//                            .padding(
//                                horizontal = 35.dp,
//                                vertical = 30.dp
//                            )
//                    )
//                }
//            }
//            Spacer(modifier = Modifier.height(20.dp))
//
//            Column(
//                modifier = Modifier
//                    /*.fillMaxSize()
//                .background(color = navy_blue)*/
//                    .padding(horizontal = 30.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//
//                Text(
//                    "App Information",
//                    color = white,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 24.sp,
//                    modifier = Modifier
//                        .padding(
//                            horizontal = 35.dp
//                        )
//                )
//
//                Button(onClick = onAbout) {
//                    Text(
//                        "About",
//                        color = white,
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 30.sp,
//                        modifier = Modifier
//                            .padding(
//                                horizontal = 35.dp,
//                                vertical = 30.dp
//                            )
//                    )
//                }
            }
        }
    }

}
//@Preview
//@Composable
//fun UserSettingPreview() {
//    TipTimeTheme {
//        UserSettingContent(onLogout={}, onCurrency = {}, onAbout={})
//    }
//}
//

