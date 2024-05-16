package com.example.tiptime
/*
import android.content.Context
import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.TextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiptime.ui.theme.TipTimeTheme


@Composable
fun CurrencyContent(context: Context) {
    var baseCurrency by remember { mutableStateOf("MYR") }
    var convertedToCurrency by remember { mutableStateOf("USD") }
    var conversionRate by remember { mutableStateOf(0f) }

    var amount by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.user_selection_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Currency Converter",
                fontSize = 35.sp,
                color = Color.Black,
                modifier = Modifier.padding(16.dp)
            )
            SpinnerLayout(baseCurrency, convertedToCurrency) { newBaseCurrency, newConvertedCurrency ->
                baseCurrency = newBaseCurrency
                convertedToCurrency = newConvertedCurrency
                getApiResult(baseCurrency, convertedToCurrency) { rate ->
                    conversionRate = rate
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Enter Amount") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                val convertedAmount = amount.toFloatOrNull()?.times(conversionRate)
                val message = convertedAmount?.let { "Converted Amount: $it" } ?: "Invalid input"
                displayMessage(context, message)
            }) {
                Text("Convert")
            }
        }
    }
}

@Composable
fun SpinnerLayout(
    baseCurrency: String,
    convertedToCurrency: String,
    onCurrencyChange: (String, String) -> Unit
) {
    Column {
        CurrencyDropdown(
            title = "Base Currency",
            selectedCurrency = baseCurrency,
            onCurrencySelected = { currency ->
                onCurrencyChange(currency, convertedToCurrency)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        CurrencyDropdown(
            title = "Converted Currency",
            selectedCurrency = convertedToCurrency,
            onCurrencySelected = { currency ->
                onCurrencyChange(baseCurrency, currency)
            }
        )
    }
}

@Composable
fun CurrencyDropdown(
    title: String,
    selectedCurrency: String,
    onCurrencySelected: (String) -> Unit
) {
    // Dummy data for currencies
    val currencies = listOf("USD", "EUR", "GBP", "JPY")

    DropdownMenu(
        expanded = false,
        onDismissRequest = { /* Do nothing */ }
    ) {
        currencies.forEach { currency ->
            DropdownMenuItem(onClick = { onCurrencySelected(currency) }) {
                CurrencyItem(
                    currency = currency,
                    isSelected = currency == selectedCurrency
                )
            }
        }
    }
}

@Composable
fun CurrencyItem(currency: String, isSelected: Boolean) {
    // Here you can customize how each currency item is displayed
    // For simplicity, just display the currency as text
    // You can add icons, flags, etc. based on your design
    Text(
        text = "currency",
        style = MaterialTheme.typography.body1,
        color = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
    )
}

fun displayMessage(context: Context, message: String) {
    // Show the message using appropriate UI component (e.g., Snackbar, Toast)
}

// Other composable functions (SpinnerLayout, getApiResult, etc.) remain the same

class CurrencyConverter : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CurrencyContent(context = this@CurrencyConverter)
                }
            }
        }
    }
}

@Composable
fun CurrencyItem(
    currency: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    shape: Shape? = null
) {
    Surface(
        color = if (isSelected) Color.LightGray else Color.Transparent,
        shape = shape,
        modifier = modifier
    ) {
        Text(
            text = currency,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview
@Composable
fun CurrencyItemPreview() {
    CurrencyItem(currency = "USD", isSelected = true)
}
*/
