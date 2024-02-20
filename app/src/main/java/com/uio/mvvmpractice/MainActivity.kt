package com.uio.mvvmpractice

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.uio.mvvmpractice.ui.theme.MVVMpracticeTheme
import com.uio.mvvmpractice.ui.theme.MainViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMpracticeTheme {
                 val viewModel by viewModels<MainViewModel>()
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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Greeting(viewModel: MainViewModel) {
    var item by remember {
        mutableStateOf("")
    }

    val controller = LocalSoftwareKeyboardController.current;


    Column(modifier = Modifier.fillMaxSize()) {
//        Text(text = "${viewModel.list}")

        OutlinedTextField(

            modifier = Modifier.padding(top = 8.dp, start = 8.dp),
            value = item,
            onValueChange = { item = it})
        Button(modifier = Modifier.padding(start = 8.dp),onClick = {
            Log.i("MAIN", "Add: $item")
            viewModel.add(item)
            item=""
            controller?.hide()
        }) {
            Text(text = "ADD")
        }
        LazyColumn {

            items(viewModel.list.size) { index ->
                Card (shape = RoundedCornerShape(15), modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)){
                    Row (modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
                        Text(
                            text = viewModel.list[index].capitalize(),
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier.padding( 8.dp)
                        )  
                        IconButton(onClick = {
                            viewModel.delete(viewModel.list[index])
                        },content = {
                            Icon(painter = painterResource(id = R.drawable.baseline_delete_24), contentDescription = "delete")
                        })
                    }
                
                }

            }
        }
    }
}

