package com.example.tiptime
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.widget.doOnTextChanged
import com.example.tiptime.ui.theme.TipTimeTheme

class CurrencyConverter : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var spinner1: Spinner
    private lateinit var spinner2: Spinner

    private lateinit var currency1: EditText
    private lateinit var currency2: EditText

    var currencies = arrayOf<String?>(
        "Malaysian Ringgit",
        "Thai Batt",
        "PRC Rmb",
        "Indo Rupiah",
        "Japanese Yen",
        "Philipino Peso",
        "Singapore Dollar",
        "Korean Won",
        "GB Pound",
        "US Dollar",
        "Brunei Dollar"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.currency_convert)

        spinner1 = findViewById(R.id.spinner1)
        spinner2 = findViewById(R.id.spinner2)

        currency1 = findViewById(R.id.currency1)
        currency2 = findViewById(R.id.currency2)

        spinner1.onItemSelectedListener = this
        spinner2.onItemSelectedListener = this

        val ad: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this,
            android.R.layout.simple_spinner_item,
            currencies
        )
        val ad2: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this,
            android.R.layout.simple_spinner_item,
            currencies
        )

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        ad2.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        spinner1.adapter = ad
        spinner2.adapter = ad2


        currency1.doOnTextChanged { _, _, _, _ ->

            if (currency1.isFocused) {
                val amt =
                    if (currency1.text.isEmpty()) 0.0 else currency1.text.toString().toDouble()
                val convertedCurrency = convertCurrency(
                    amt,
                    spinner1.selectedItem.toString(),
                    spinner2.selectedItem.toString()
                )

                currency2.setText(convertedCurrency.toString())
            }
        }

        currency2.doOnTextChanged { _, _, _, _ ->

            if (currency2.isFocused) {
                val amt =
                    if (currency2.text.isEmpty()) 0.0 else currency2.text.toString().toDouble()
                val convertedCurrency = convertCurrency(
                    amt,
                    spinner2.selectedItem.toString(),
                    spinner1.selectedItem.toString()
                )

                currency1.setText(convertedCurrency.toString())
            }
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent!!.id) {
            R.id.spinner1 -> {
                val amt =
                    if (currency1.text.isEmpty()) 0.0 else currency1.text.toString().toDouble()
                val convertedCurrency = convertCurrency(
                    amt,
                    spinner1.selectedItem.toString(),
                    spinner2.selectedItem.toString()
                )
                currency2.setText(convertedCurrency.toString())

            }

            R.id.spinner2 -> {
                val amt =
                    if (currency2.text.isEmpty()) 0.0 else currency2.text.toString().toDouble()
                val convertedCurrency = convertCurrency(
                    amt,
                    spinner2.selectedItem.toString(),
                    spinner1.selectedItem.toString()
                )
                currency1.setText(convertedCurrency.toString())

            }

        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    fun convertCurrency(amt: Double, firstCurrency: String, secondCurrency: String): Double {
        val myRinggit = convertOtherToRinggit(amt, firstCurrency)
        return convertRinggitToOtherCurrency(myRinggit, secondCurrency)
    }

    //exchange rate as 10/5/2024
    private fun convertRinggitToOtherCurrency(myRinggit: Double, secondCurrency: String): Double {
        return myRinggit * when (secondCurrency) {
            "Malaysian Ringgit" -> 1.0
            "Thai Batt" -> 7.75
            "PRC Rmb" -> 1.52
            "Indo Rupiah" -> 3392.22
            "Japanese Yen" -> 32.87
            "Philipino Peso" -> 12.13
            "Singapore Dollar" -> 0.29
            "Korean Won" -> 289.09
            "GB Pound" -> 0.17
            "US Dollar" -> 0.21
            "Brunei Dollar" -> 0.29
            else -> 0.0
        }
    }

    private fun convertOtherToRinggit(amt: Double, firstCurrency: String): Double {
        return amt * when (firstCurrency) {
            "Malaysian Ringgit" -> 1.0
            "Thai Batt" -> 0.1290
            "PRC Rmb" -> 0.6558
            "Indo Rupiah" -> 0.000295
            "Japanese Yen" -> 0.0304
            "Philipino Peso" -> 0.0825
            "Singapore Dollar" -> 3.50
            "Korean Won" -> 0.0035
            "GB Pound" -> 5.93
            "US Dollar" -> 4.74
            "Brunei Dollar" -> 3.50
            else -> 0.0
        }
    }

}

@Preview
@Composable
fun CurrencyConverterPreview() {
    TipTimeTheme {
        CurrencyConverter()
    }
}
