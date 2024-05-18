package com.example.tiptime
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
/*import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

 */
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.example.tiptime.ui.theme.TipTimeTheme
/*
class CurrencyConverter : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var spinner1: Spinner
    private lateinit var spinner2: Spinner

    private lateinit var currency1: EditText
    private lateinit var currency2: EditText

    var currencies = arrayOf(
        "Malaysian Ringgit",
        "Thai Baht",
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

        // Create root ConstraintLayout
        val rootLayout = ConstraintLayout(this).apply {
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )
        }

        // Create first LinearLayout
        val linearLayout1 = LinearLayout(this).apply {
            id = View.generateViewId()
            orientation = LinearLayout.HORIZONTAL
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(16, 16, 16, 0)
            }
            background = ContextCompat.getDrawable(this@CurrencyConverter, R.drawable.textframe)
            setPadding(4,4,4,4)
            weightSum = 10f
        }

        currency1 = EditText(this).apply {
            id = View.generateViewId()
            layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT,
                4.5f
            ).apply {
                marginEnd = 10
            }
            inputType = android.text.InputType.TYPE_CLASS_NUMBER or android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL
        }

        val separator1 = TextView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                2,
                LinearLayout.LayoutParams.MATCH_PARENT
            ).apply {
                setMargins(4, 4, 4, 4)
            }
            setBackgroundColor(Color.parseColor("#454545"))
        }

        spinner1 = Spinner(this).apply {
            id = View.generateViewId()
            layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT,
                5.5f
            )
        }

        linearLayout1.addView(currency1)
        linearLayout1.addView(separator1)
        linearLayout1.addView(spinner1)

        // Create second LinearLayout
        val linearLayout2 = LinearLayout(this).apply {
            id = View.generateViewId()
            orientation = LinearLayout.HORIZONTAL
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(16, 16, 16, 0)
            }
            background = ContextCompat.getDrawable(this@CurrencyConverter, R.drawable.textframe)
            setPadding(4,4,4,4)
            weightSum = 10f
        }

        currency2 = EditText(this).apply {
            id = View.generateViewId()
            layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT,
                4.5f
            ).apply {
                marginEnd = 10
            }
            inputType = android.text.InputType.TYPE_CLASS_NUMBER or android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL
        }

        val separator2 = TextView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                2,
                LinearLayout.LayoutParams.MATCH_PARENT
            ).apply {
                setMargins(4, 4, 4, 4)
            }
            setBackgroundColor(Color.parseColor("#454545"))
        }

        spinner2 = Spinner(this).apply {
            id = View.generateViewId()
            layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT,
                5.5f
            )
        }

        linearLayout2.addView(currency2)
        linearLayout2.addView(separator2)
        linearLayout2.addView(spinner2)

        // Add views to root layout
        rootLayout.addView(linearLayout1)
        rootLayout.addView(linearLayout2)

        setContentView(rootLayout)

        val constraintSet = ConstraintSet().apply {
            clone(rootLayout)

            connect(linearLayout1.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
            connect(linearLayout1.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
            connect(linearLayout1.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)

            connect(linearLayout2.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
            connect(linearLayout2.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
            connect(linearLayout2.id, ConstraintSet.TOP, linearLayout1.id, ConstraintSet.BOTTOM)
        }

        constraintSet.applyTo(rootLayout)

        // Set up the spinners and edit texts
        setUpSpinnersAndEditTexts()
    }

    private fun setUpSpinnersAndEditTexts() {
        val adapter: ArrayAdapter<String> = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            currencies
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        spinner1.adapter = adapter
        spinner2.adapter = adapter

        spinner1.onItemSelectedListener = this
        spinner2.onItemSelectedListener = this

        currency1.doOnTextChanged { _, _, _, _ ->
            if (currency1.isFocused) {
                val amt = if (currency1.text.isEmpty()) 0.0 else currency1.text.toString().toDouble()
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
                val amt = if (currency2.text.isEmpty()) 0.0 else currency2.text.toString().toDouble()
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
        val amt = if (currency1.text.isEmpty()) 0.0 else currency1.text.toString().toDouble()
        val convertedCurrency = convertCurrency(
            amt,
            spinner1.selectedItem.toString(),
            spinner2.selectedItem.toString()
        )
        currency2.setText(convertedCurrency.toString())
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        // Do nothing
    }

    fun convertCurrency(amt: Double, firstCurrency: String, secondCurrency: String): Double {
        val myRinggit = convertOtherToRinggit(amt, firstCurrency)
        return convertRinggitToOtherCurrency(myRinggit, secondCurrency)
    }

    private fun convertRinggitToOtherCurrency(myRinggit: Double, secondCurrency: String): Double {
        return myRinggit * when (secondCurrency) {
            "Malaysian Ringgit" -> 1.0
            "Thai Baht" -> 7.75
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
            "Thai Baht" -> 0.1290
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

 */
