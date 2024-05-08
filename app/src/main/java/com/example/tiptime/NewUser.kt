package com.example.tiptime

class NewUser : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NewUserContent()
                }
            }
        }
    }
}

@Composable
fun NewUserContent(){
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.normal_user_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                /*                .background(color = navy_blue)*/
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sign-up",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = white,
                modifier = Modifier
                    .padding(
                        vertical = 120.dp,
                        horizontal = 70.dp
                    )
            )
            NewUserTextField(
                hint = "Name",
                keyboardType = KeyboardType.Text
            )
            Spacer(modifier = Modifier.height(40.dp))
            NewUserTextField(
                hint = "Phone Number",
                keyboardType = KeyboardType.Phone
            )
            Spacer(modifier = Modifier.height(40.dp))
            NewUserTextField(
                hint = "Gender (Male/Female)",
                keyboardType = KeyboardType.Text
            )
            Spacer(modifier = Modifier.height(40.dp))
            NewUserTextField(
                hint = "E-mail Address",
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(40.dp))
            NewUserTextField(
                hint = "Password",
                keyboardType = KeyboardType.Password
            )
            Spacer(modifier = Modifier.height(40.dp))

            Button(onClick = { /*TODO*/ }) {
                Text(text = "Submit")
            }


        }
    }

}




@Composable
fun NewUserTextField(hint: String, keyboardType: KeyboardType) {
    TextField(
        value = "",
        onValueChange = { },
        placeholder = { Text(text = hint) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        textStyle = TextStyle(color = white),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}

@Preview
@Composable
fun NewUserPreview() {
    TipTimeTheme {
        NewUserContent()
    }

}
