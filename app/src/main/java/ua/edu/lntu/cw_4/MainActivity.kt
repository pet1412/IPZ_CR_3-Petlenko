package ua.edu.lntu.cw_4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ua.edu.lntu.cw_4.ui.theme.IPZ_CW_4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IPZ_CW_4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = "screen1"
    ){
        composable("screen1"){
            Screen1(navController)
        }
        composable("screen2"){
            Screen2(navController)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    IPZ_CW_4Theme {
        Greeting()
    }
}

@Composable
fun Screen1(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                contentColor = Color.Yellow,
                backgroundColor = Color.Yellow,
                title = {
                    Text(text = "Tasks")
            })
        }
    ) {
        paddingValues ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)
        ) {
            LazyColumn {
                items(5){
                    ItemsTask(it+1, navController)
                }
            }
        }
    }

}

@Composable
fun ItemsTask(id: Int, navController: NavController){
    var checkBoxState by remember{ mutableStateOf(true) }
    Box(
        modifier = Modifier
            .background(
                when(checkBoxState){
                    true -> Color.Cyan
                    false -> Color.Red
                }
            )
            .clickable {
                navController.navigate("screen2")
            }
            .fillMaxWidth()
    ){
       Row(
           verticalAlignment = Alignment.CenterVertically,
           horizontalArrangement = Arrangement.Center,
           modifier =  Modifier
               .padding(start = 12.dp)
       ) {
           Text(text = "$id")
           Spacer(modifier = Modifier.width(12.dp))
           Checkbox(checked = checkBoxState, onCheckedChange = {
               checkBoxState = !checkBoxState
           })
       }
    }
}

@Composable
fun Screen2(navController: NavController){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Disc")
        Text("12/12/2004")
        Button(onClick = { navController.navigate("screen1") }) {
            Text(text = "Done")
        }
    }
}
