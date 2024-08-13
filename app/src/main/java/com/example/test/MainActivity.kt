package com.example.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.test.ui.navigation.AppNavHost
import com.example.test.ui.theme.TestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestTheme {
                val navController = rememberNavController()
                AppNavHost(navController)
            }
        }
    }
}

// @Composable
// fun MainScreen() {
//    Column(
//        modifier = Modifier
//            .background(MaterialTheme.colorScheme.background)
//            .fillMaxSize()
//            .padding(start = 16.dp, end = 16.dp)
//            .statusBarsPadding(),
//        verticalArrangement = Arrangement.Center
//    ) {
//        ListOfTypeDish()
//        Spacer(modifier = Modifier.height(32.dp))
//        FavouriteDishGrid()
//    }
// }
//
// @Composable
// fun ListOfTypeDish() {
//    Column {
//        Text(
//            text = "Выберите вид блюда, которое хотите съесть",
//            fontSize = 17.sp,
//            color = MaterialTheme.colorScheme.tertiary,
//            modifier = Modifier
//                .fillMaxWidth()
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Column {
//                FilterButton(type = "Завтрак")
//                Spacer(modifier = Modifier.height(8.dp))
//                FilterButton(type = "Гарнир")
//                Spacer(modifier = Modifier.height(8.dp))
//                FilterButton(type = "Десерты")
//            }
//            Spacer(modifier = Modifier.width(16.dp))
//            Column {
//                FilterButton(type = "Супы")
//                Spacer(modifier = Modifier.height(8.dp))
//                FilterButton(type = "Салаты")
//                Spacer(modifier = Modifier.height(8.dp))
//                FilterButton(type = "Мясо/Рыба")
//            }
//        }
//
//    }
// }
//
// @Composable
// fun FilterButton(type: String) {
//    Button(
//        onClick = {},
//        modifier = Modifier
//            .height(50.dp)
//            .width(155.dp),
//        colors = ButtonDefaults.buttonColors(
//            containerColor = MaterialTheme.colorScheme.primary
//        ),
//
//    ) {
//        Text(text = type, color = MaterialTheme.colorScheme.onPrimary)
//    }
// }
//
// @Composable
// fun FavouriteDishGrid() {
//    val dishes = remember { listOf("Блюдо 1", "Блюдо 2", "Блюдо 3", "Блюдо 4") }
//    Column {
//        Text(
//            text = "Вот ваши любимые блюда",
//            fontSize = 17.sp,
//            color = MaterialTheme.colorScheme.tertiary,
//            modifier = Modifier
//                .fillMaxWidth()
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        LazyHorizontalGrid(
//            rows = GridCells.Fixed(1),
//            contentPadding = PaddingValues(horizontal = 16.dp),
//            horizontalArrangement = Arrangement.spacedBy(16.dp),
//            verticalArrangement = Arrangement.spacedBy(16.dp),
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(220.dp)
//        ) {
//            items(dishes) { dish ->
//                DishCard(name = dish)
//            }
//        }
//    }
//
// }
//
// @OptIn(ExperimentalMaterial3Api::class)
// @Composable
// fun DishCard(name: String) {
//    Card(
//        onClick = { /*TODO*/ },
//        modifier = Modifier
//            .size(150.dp, 190.dp)
//            .background(MaterialTheme.colorScheme.background),
//        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary),
//        elevation = CardDefaults.cardElevation(8.dp)
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(8.dp),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            val image: Painter = painterResource(R.drawable.ic_launcher_background)
//            Image(
//                painter = image, contentDescription = name,
//                modifier = Modifier
//                    .size(95.dp)
//                    .padding(bottom = 8.dp)
//            )
//            Text(
//                text = name,
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Bold,
//                color = MaterialTheme.colorScheme.onSecondary
//            )
//        }
//    }
// }
//
// @Preview(showSystemUi = true,
//    showBackground = true)
// @Composable
// fun MainPreview(){
//    TestTheme{
//        MainScreen()
//    }
// }
