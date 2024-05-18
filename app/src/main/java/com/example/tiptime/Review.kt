package com.example.tiptime

import android.content.Context
import android.widget.RatingBar
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore



/*
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
        ShowSuccessReivewDialog { showSuccessDialog = false }
    }
    if (showFailureDialog) {
        ShowFailureReviewDialog { showFailureDialog = false }
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
fun ShowSuccessReivewDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { org.jdom.Text("Success") },
        text = { org.jdom.Text("Information saved successfully.") },
        confirmButton = {
            Button(onClick = onDismiss) {
                org.jdom.Text("OK")
            }
        }
    )
}

@Composable
fun ShowFailureReviewDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { org.jdom.Text("Failure") },
        text = { org.jdom.Text("Failed to save information. Please try again.") },
        confirmButton = {
            Button(onClick = onDismiss) {
                org.jdom.Text("OK")
            }
        }
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
*/
@Composable
fun ReviewScreen(context: Context) {
    var comment by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf(0f) }
    var invalidComment by remember { mutableStateOf(false) }
    var invalidRating by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    var showSuccessDialog by remember { mutableStateOf(false) }
    var showFailureDialog by remember { mutableStateOf(false) }

    if (showSuccessDialog) {
        ShowSuccessReviewDialog { showSuccessDialog = false }
    }
    if (showFailureDialog) {
        ShowFailureReviewDialog { showFailureDialog = false }
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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = comment,
                onValueChange = { comment = it },
                label = { Text("Review ") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = TextStyle(color = Color.Black),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            if (invalidComment) {
                Text(text = "Please write the comment within 100 words", color = Color.Red)
            }
            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Rating")
            val ratingBar = remember { RatingBar(context).apply { numStars = 5; stepSize = 1.0f } }

            AndroidView({ ratingBar }) { view ->
                view.setOnRatingBarChangeListener { _, ratingValue, _ ->
                    rating = ratingValue
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                if (comment.isBlank() || comment.length > 100) {
                    invalidComment = true
                } else if (rating <= 0) {
                    invalidRating = true
                } else {
                    saveReviewData(context, comment, rating) { success ->
                        if (success) {
                            showSuccessDialog = true
                        } else {
                            showFailureDialog = true
                        }
                    }
                }
            }) {
                Text(text = "Submit")
            }

            if (showError) {
                Text(text = "Invalid comment", color = Color.Red)
            }
        }
    }
}

fun saveReviewData(context: Context, comment: String, rating: Float, callback: (Boolean) -> Unit) {
    val auth = FirebaseAuth.getInstance()
    auth.signInAnonymously()
        .addOnCompleteListener(context as ComponentActivity) { task ->
            if (task.isSuccessful) {

                val review = hashMapOf(
                    "comment" to comment,
                    "rating" to rating
                )
                // Assuming you're using Firestore
                val db = FirebaseFirestore.getInstance()
                db.collection("reviews")
                    .add(review)
                    .addOnSuccessListener {
                        callback(true)
                    }
                    .addOnFailureListener {
                        callback(false)
                    }
            } else {
                callback(false)
            }
        }
}

@Composable
fun ShowSuccessReviewDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Success") },
        text = { Text("Information saved successfully.") },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("OK")
            }
        }
    )
}

@Composable
fun ShowFailureReviewDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Failure") },
        text = { Text("Failed to save information. Please try again.") },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("OK")
            }
        }
    )
}

@Preview
@Composable
fun ReviewPreview() {
    ReviewScreen(context = LocalContext.current)
}

