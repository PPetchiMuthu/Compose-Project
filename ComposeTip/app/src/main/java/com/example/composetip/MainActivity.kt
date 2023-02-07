package com.example.composetip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetip.ui.theme.ComposeTipTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTipTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    TipTimeScreen()
                }
            }
        }
    }
}

@Composable
fun TipTimeScreen() {
    var amountInput by remember { mutableStateOf("") }
    var tipPercentage by remember {
        mutableStateOf("")
    }
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tipPercent = tipPercentage.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(amount, tipPercent)

    Column(
        modifier = Modifier.padding(32.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Tip Calculate",
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(16.dp))
        EditNumberField(value = amountInput,"Amount", onValueChange = { amountInput = it })
        Spacer(Modifier.height(24.dp))
        EditNumberField(value = tipPercentage,"Tip Percentage", onValueChange = { tipPercentage = it })
        Spacer(Modifier.height(24.dp))
        Text(
            text = "Tip Amount : $tip",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNumberField(
    value: String,string: String, onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = string) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

private fun calculateTip(
    amount: Double, tipPercent: Double
): String {
    val tip = tipPercent / 100 * amount
    return NumberFormat.getCurrencyInstance().format(tip)
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTipTheme {
        TipTimeScreen()
    }
}