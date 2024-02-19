package com.uio.mvvmpractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uio.mvvmpractice.ui.theme.MVVMpracticeTheme
import com.uio.mvvmpractice.ui.theme.MainViewModel

class MainActivity : ComponentActivity() {
    val viewModel:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMpracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) { Greeting(viewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(viewModel: MainViewModel) {
    var item by remember {
        mutableStateOf("")
    }
    val listing = remember {
        mutableListOf<String>()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "${listing}")

        OutlinedTextField(
            value = item,
            onValueChange = { item = it})
        Button(onClick = { listing.add(item) }) {
            Text(text = "ADD")
        }
        LazyColumn {
            val shoppingList by viewModel.list.observeAsState(listOf())
            items(shoppingList.size) { index ->
                Text(
                    text = shoppingList[index],
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    }
}

