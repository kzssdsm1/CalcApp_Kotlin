package com.example.calcapp_kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calcapp_kotlin.ui.theme.CalcApp_KotlinTheme

class MainActivity : ComponentActivity() {
    private val viewModel = CountViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountUp(viewModel = viewModel)
        }
    }
}

@Composable
fun CountUp(viewModel: CountViewModel) {
    val count: Int by viewModel.count.collectAsState()
    
    Column {
        Text(
            text = "$count"
        )
        
        Button(onClick = {
            viewModel.onCountUpTapped()
        }) {
            Text(
                text = "Count Up"
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalcApp_KotlinTheme {
        Greeting("Android")
    }
}