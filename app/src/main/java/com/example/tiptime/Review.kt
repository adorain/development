package com.example.tiptime

import android.content.Context
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.RatingBar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tiptime.ui.theme.red
import com.example.tiptime.ui.theme.white
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class Review : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lateinit var firebaseAuth: FirebaseAuth
        setContent {
            ReviewScreen(context = this)
        }
    }
}

@Composable
fun ReviewScreen(context: Context) {
    var comment by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf("") }
    var invalidComment by remember { mutableStateOf(false) }
    var invalidRating by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    var showSuccessDialog by remember { mutableStateOf(false) }
    var showFailureDialog by remember { mutableStateOf(false) }

    if (showSuccessDialog) {
        ShowSuccessUserDialog { showSuccessDialog = false }
    }
    if (showFailureDialog) {
        ShowFailureUserDialog { showFailureDialog = false }
    }


    Column {
        TextField(
            value = comment,
            onValueChange = { comment = it },
            label = { org.jdom.Text("Review (100 words)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            textStyle = TextStyle(color = white),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        if (invalidComment) {
            Text(text = "Please write the comment within 100 words", color = red)
        }
        Spacer(modifier = Modifier.height(20.dp))

        val linearLayout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        }

        // Create a RatingBar programmatically
        val ratingBar = RatingBar(context).apply {
            numStars = 5
            stepSize = 1.0f
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(16, 16, 16, 16)
            }
        }


        linearLayout.addView(ratingBar)


        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            if (comment.isBlank() ||comment.length > 100) {
                invalidComment = true
            }
            else if (rating.isBlank() ) {
                invalidRating = true
            }
            else {
                ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
                    var storedRating = rating
                }
            }

            val auth = Firebase.auth
            auth.createUserWithEmailAndPassword(comment, rating)
                .addOnCompleteListener(context as ComponentActivity) { task ->
                    if (task.isSuccessful) {
                        showSuccessDialog = true
                    } else {
                        showFailureDialog = true
                    }
                }
        }) {
            Text(text = "Submit")
        }

        if (showError) {
            Text(text = "Invalid comment", color = red)
        }
    }

}

@Composable
fun ReviewField(
    hint: String,
    keyboardType: KeyboardType,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = hint) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        textStyle = TextStyle(color = Color.White),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        isError = isError
    )
}
@Composable
fun NumberField(
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean
) {
    TextField(
        value = value,
        onValueChange = { newValue ->
            if (newValue.all { it.isDigit() }) {
                onValueChange(newValue)
            }
        },
        placeholder = { Text(text = hint) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        textStyle = TextStyle(color = Color.White),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        isError = isError
    )
}


@Preview
@Composable
fun ReviewPreview() {
    ReviewScreen(context = LocalContext.current)
}

