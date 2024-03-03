package com.example.calculator

import android.os.Bundle
import android.util.StringBuilderPrinter
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.calculator.ui.theme.CalculatorTheme
val ViewModel = CalculateViewModel()
class MainActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        println("onCREATE")
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = Modifier.fillMaxSize()){
                Output(Modifier.weight(3f).fillMaxSize().align(Alignment.End),
                    string = ViewModel.answer,
                    )
                Btn(Modifier.weight(5f).fillMaxSize())
            }

        }
    }

    override fun onStart() {
        super.onStart()
        println("Started")
    }

    override fun onResume() {
        super.onResume()
        println("OnResume is called")
    }

    override fun onPause() {
        super.onPause()
        println("On Pause")
    }

    override fun onStop() {
        super.onStop()
        println("On Stopp")
    }

    override fun onRestart() {
        super.onRestart()
        println("onrestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("Destroyed activity")
    }
}

@Composable
fun InputButton(
    modifier: Modifier= Modifier,
    symbol:String,
    color:Color,
    isCircle:Boolean = true,

){

    Box(
        modifier = modifier
            .width(if (isCircle) 90.dp else 180.dp)
            .height(90.dp)
            .clip(if (isCircle) CircleShape else RoundedCornerShape(50.dp))
            .background(color)
            .clickable {
                if(symbol=="="){
                    ViewModel.evaluateExpression(ViewModel.answer)
                }
                else if(symbol == "AC"){
                    ViewModel.ac()
                }
                else if(symbol == "DEL"){
                    ViewModel.del()
                }
                else {
                    when(symbol){
                        "X"-> ViewModel.inputText("*")
                        "÷"-> ViewModel.inputText("/")
                        "−"-> ViewModel.inputText("-")
                        else ->ViewModel.inputText(symbol)
                    }
                }
            }
            .padding(10.dp),

    ){
        Text(text = symbol,
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 22.sp,
            modifier= Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun Btn(modifier: Modifier= Modifier){
    Column(modifier = modifier
        .padding(15.dp)

        ) {
        Row(modifier = Modifier
            , horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            InputButton(
                symbol = "AC", color = Color.Black, isCircle = false,)
            InputButton(
                symbol = "DEL", color = Color.Black)
            InputButton(
                symbol = "÷", color = Color(255, 190, 11))
        }
        Row(modifier = Modifier
            , horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            InputButton(
                symbol = "7", color = Color.Black, )
            InputButton(
                symbol = "8", color = Color.Black)
            InputButton(
                symbol = "9", color = Color.Black)
            InputButton(
                symbol = "X", color = Color(255, 190, 11))
        }
        Row(modifier = Modifier
            , horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            InputButton(
                symbol = "4", color = Color.Black)
            InputButton(
                symbol = "5", color = Color.Black)
            InputButton(
                symbol = "6", color = Color.Black)
            InputButton(
                symbol = "−", color = Color(255, 190, 11))
        }
        Row(modifier = Modifier
            , horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            InputButton(
                symbol = "1", color = Color.Black)
            InputButton(
                symbol = "2", color = Color.Black)
            InputButton(
                symbol = "3", color = Color.Black)
            InputButton(
                symbol = "+", color = Color(255, 190, 11))
        }
        Row(modifier = Modifier
            , horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            InputButton(
                symbol = "0", color = Color.Black, isCircle = false)
            InputButton(
                symbol = ".", color = Color.Black)
            InputButton(
                symbol = "=", color = Color(255, 190, 11))
        }

    }
}

@Composable
fun Output(modifier: Modifier= Modifier,string: String){
    Box(modifier = modifier
        .fillMaxWidth()
        .background(Color.Black)
        .padding(10.dp)){
        Text(text = string,
            textAlign = TextAlign.End,
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
            , modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}








