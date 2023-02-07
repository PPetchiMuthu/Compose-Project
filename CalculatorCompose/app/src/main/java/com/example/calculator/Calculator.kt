package com.example.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.ui.theme.CalculatorTheme

@Composable
fun Calculator(
    state: CalculatorData, modifier: Modifier = Modifier, onAction: (CalculatorAction) -> Unit
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                text = state.string,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                fontWeight = FontWeight.Light,
                fontSize = 60.sp,
                color = Color.White,
                maxLines = 2
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalculatorButton(text = "AC",
                    modifier = Modifier
                        .background(Color.Black)
                        .aspectRatio(2f)
                        .weight(2f),
                    onClick = { onAction(CalculatorAction.Clear) })
                CalculatorButton(text = "Del",
                    modifier = Modifier
                        .background(Color.Red)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { onAction(CalculatorAction.Delete) })
                CalculatorButton(text = "/",
                    modifier = Modifier
                        .background(Color.Green)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { onAction(CalculatorAction.Expression("/")) })
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalculatorButton(text = "7",
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { onAction(CalculatorAction.Expression("7")) })
                CalculatorButton(text = "8",
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { onAction(CalculatorAction.Expression("8")) })
                CalculatorButton(text = "9",
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { onAction(CalculatorAction.Expression("9")) })
                CalculatorButton(text = "x",
                    modifier = Modifier
                        .background(Color.Green)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { onAction(CalculatorAction.Expression("*")) })
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalculatorButton(text = "4",
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { onAction(CalculatorAction.Expression("4")) })
                CalculatorButton(text = "5",
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { onAction(CalculatorAction.Expression("5")) })
                CalculatorButton(text = "6",
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { onAction(CalculatorAction.Expression("6")) })
                CalculatorButton(text = "-",
                    modifier = Modifier
                        .background(Color.Green)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { onAction(CalculatorAction.Expression("-")) })
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalculatorButton(text = "1",
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { onAction(CalculatorAction.Expression("1")) })
                CalculatorButton(text = "2",
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { onAction(CalculatorAction.Expression("2")) })
                CalculatorButton(text = "3",
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { onAction(CalculatorAction.Expression("3")) })
                CalculatorButton(text = "+",
                    modifier = Modifier
                        .background(Color.Green)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { onAction(CalculatorAction.Expression("+")) })
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalculatorButton(text = "0",
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(2f)
                        .weight(2f),
                    onClick = { onAction(CalculatorAction.Expression("0")) })
                CalculatorButton(text = ".",
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { onAction(CalculatorAction.Expression(".")) })
                CalculatorButton(text = "=",
                    modifier = Modifier
                        .background(Color.Red)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { onAction(CalculatorAction.Calculation) })
            }
        }
    }
}

@Composable
fun CalculatorButton(
    text: String, modifier: Modifier = Modifier, onClick: () -> Unit
) {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(CircleShape)
            .clickable { onClick() }
            .then(modifier)) {
        Text(
            text = text, fontSize = 36.sp, color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPrevie() {
    CalculatorTheme {
        // A surface container using the 'background' color from the theme
        val viewModel = viewModel<CalculatorViewModel>()
        val state = viewModel.state
        Surface(
            modifier = Modifier.fillMaxSize(), color = Color.Gray
        ) {
            Calculator(state = state, onAction = {})
        }
    }
}
