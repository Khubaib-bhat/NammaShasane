package com.example.testapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.Search
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "home"
            ) {

                composable("home") {
                    HomeScreen(navController)
                }

                composable(
                    route = "detail/{title}/{translation}/{description}/{location}/{imageId}",
                    arguments = listOf(
                        navArgument("title") { type = NavType.StringType },
                        navArgument("translation") { type = NavType.StringType },
                        navArgument("description") { type = NavType.StringType },
                        navArgument("location") { type = NavType.StringType },
                        navArgument("imageId") { type = NavType.IntType }
                    )
                ) { backStackEntry ->

                    DetailScreen(
                        navController = navController,
                        title = backStackEntry.arguments?.getString("title") ?: "",
                        translation = backStackEntry.arguments?.getString("translation") ?: "",
                        description = backStackEntry.arguments?.getString("description") ?: "",
                        location = backStackEntry.arguments?.getString("location") ?: "",
                        imageId = backStackEntry.arguments?.getInt("imageId")
                            ?: R.drawable.stone1
                    )
                }
            }
        }
    }
}

data class HeritageStone(
    val title: String,
    val translation: String,
    val description: String,
    val location: String,
    val image: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: androidx.navigation.NavController) {

    var searchText by remember {
        mutableStateOf("")
    }

    val stones = listOf(

        HeritageStone(
            "Rashtrakuta Inscription",
            "ಈ ಶಾಸನವು ಭೂದಾನವನ್ನು ದಾಖಲಿಸುತ್ತದೆ.",
            "This inscription belongs to the Rashtrakuta dynasty and records royal land grants and administrative details.",
            "Hampi Karnataka",
            R.drawable.stone1
        ),

        HeritageStone(
            "Hoysala Temple Stone",
            "ಈ ಶಾಸನವು ದೇವಾಲಯದ ದೇಣಿಗೆಗಳನ್ನು ವಿವರಿಸುತ್ತದೆ.",
            "This Hoysala inscription documents donations made to temples and highlights temple architecture.",
            "Belur Karnataka",
            R.drawable.stone2
        ),

        HeritageStone(
            "Chalukya Record",
            "ಈ ಶಾಸನವು ವಿಜಯವನ್ನು ದಾಖಲಿಸುತ್ತದೆ.",
            "This Chalukya inscription describes military victories and kingdom expansion.",
            "Badami Karnataka",
            R.drawable.stone3
        ),

        HeritageStone(
            "Vijayanagara Stone",
            "ಈ ಶಾಸನವು ಆಡಳಿತದ ವಿವರಗಳನ್ನು ನೀಡುತ್ತದೆ.",
            "This stone from Vijayanagara Empire contains royal administrative and trade details.",
            "Hampi Karnataka",
            R.drawable.stone4
        ),

        HeritageStone(
            "Ashoka Edict",
            "ಈ ಶಾಸನವು ಧರ್ಮ ಸಂದೇಶವನ್ನು ನೀಡುತ್ತದೆ.",
            "This ancient Ashokan edict spreads moral teachings and Buddhist values.",
            "Maski Karnataka",
            R.drawable.stone5
        ),

        HeritageStone(
            "Kadamba Record",
            "ಈ ಶಾಸನವು ರಾಜವಂಶದ ಇತಿಹಾಸವನ್ನು ಹೇಳುತ್ತದೆ.",
            "This Kadamba inscription explains the origin and rule of the Kadamba dynasty.",
            "Banavasi Karnataka",
            R.drawable.stone6
        ),

        HeritageStone(
            "Ganga Dynasty Stone",
            "ಈ ಶಾಸನವು ಜೈನ ಧರ್ಮದ ಪ್ರಭಾವವನ್ನು ತೋರಿಸುತ್ತದೆ.",
            "This inscription highlights Jain culture and contributions during Ganga dynasty.",
            "Shravanabelagola Karnataka",
            R.drawable.stone7
        ),

        HeritageStone(
            "Pallava Script Stone",
            "ಈ ಶಾಸನವು ಪ್ರಾಚೀನ ಲಿಪಿಯನ್ನು ವಿವರಿಸುತ್ತದೆ.",
            "This stone preserves early South Indian scripts and cultural practices.",
            "Kanchipuram Tamil Nadu",
            R.drawable.stone8
        ),

        HeritageStone(
            "Mysore Kingdom Stone",
            "ಈ ಶಾಸನವು ಆಡಳಿತದ ಆದೇಶಗಳನ್ನು ದಾಖಲಿಸುತ್ತದೆ.",
            "This inscription contains royal announcements from the Mysore kingdom.",
            "Mysore Karnataka",
            R.drawable.stone9
        ),

        HeritageStone(
            "Shravanabelagola Record",
            "ಈ ಶಾಸನವು ಜೈನ ಪರಂಪರೆಯನ್ನು ವಿವರಿಸುತ್ತದೆ.",
            "This historical Jain inscription highlights pilgrimage traditions and spirituality.",
            "Shravanabelagola Karnataka",
            R.drawable.stone10
        )
    )

    val filteredStones = stones.filter {

        it.title.contains(searchText, ignoreCase = true) ||
                it.location.contains(searchText, ignoreCase = true)
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text(
                        text = "Namma Shasane",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF5D4037)
                )
            )
        }

    ) { paddingValues ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFF5E6CC),
                            Color(0xFFE8D3B0)
                        )
                    )
                )
                .padding(paddingValues)

        ) {

            Text(
                text = "Explore Karnataka Heritage",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4E342E),
                modifier = Modifier.padding(16.dp)
            )



            OutlinedTextField(

                value = searchText,

                onValueChange = {
                    searchText = it
                },

                placeholder = {
                    Text("Search inscriptions...")
                },
                trailingIcon = {

                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color(0xFF6D4C41)
                    )
                },

                shape = RoundedCornerShape(18.dp),

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),

                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF6D4C41),
                    unfocusedBorderColor = Color(0xFFBCAAA4)
                )
            )

            Spacer(modifier = Modifier.height(18.dp))

            LazyColumn(

                verticalArrangement = Arrangement.spacedBy(16.dp),

                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 20.dp
                )

            ) {
                item {

                    Text(

                        text =
                            "Namma Shasane is a digital heritage application that helps users explore ancient inscriptions and historical records from Karnataka.",

                        fontSize = 16.sp,

                        lineHeight = 28.sp,

                        color = Color(0xFF5D4037),

                        modifier = Modifier.padding(
                            bottom = 20.dp
                        )
                    )
                }
                item {

                    Card(

                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF6D4C41)
                        ),

                        shape = RoundedCornerShape(22.dp),

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 18.dp)

                    ) {

                        Column(
                            modifier = Modifier.padding(18.dp)
                        ) {

                            Text(
                                text = "Heritage Fact",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text =
                                    "Karnataka has one of the richest inscription histories in India with more than 25,000 documented stone inscriptions.",

                                color = Color(0xFFFFF3E0),

                                fontSize = 16.sp,

                                lineHeight = 26.sp
                            )
                        }
                    }
                }

                item {

                    Text(
                        text = "Explore Dynasties",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = Color(0xFF4E342E),
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.padding(bottom = 22.dp)
                    ) {

                        AssistChip(
                            onClick = {
                                searchText = "Hoysala"
                            },
                            label = { Text("Hoysala") }
                        )

                        AssistChip(
                            onClick = {
                                searchText = "Chalukya"
                            },
                            label = { Text("Chalukya") }
                        )

                        AssistChip(
                            onClick = {
                                searchText = "Rashtrakuta"
                            },
                            label = { Text("Rashtrakuta") }
                        )
                    }
                }

                if (filteredStones.isEmpty()) {

                    item {

                        Column(

                            horizontalAlignment = Alignment.CenterHorizontally,

                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 50.dp)

                        ) {

                            Text(
                                text = "No Match Found",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF6D4C41)
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = "Try searching with another keyword.",
                                fontSize = 16.sp,
                                color = Color.Gray
                            )
                        }
                    }

                } else {

                    items(filteredStones) { stone ->

                        Card(

                            shape = RoundedCornerShape(24.dp),

                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFF3E5D8)
                            ),

                            elevation = CardDefaults.cardElevation(2.dp),

                            border = BorderStroke(
                                1.dp,
                                Color(0xFFE0E0E0)
                            ),

                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize()
                                .clickable {

                                    navController.navigate(
                                        "detail/${stone.title}/${stone.translation}/${stone.description}/${stone.location}/${stone.image}"
                                    )
                                }

                        ) {

                            Column {

                                Box {

                                    Image(
                                        painter = painterResource(id = stone.image),
                                        contentDescription = stone.title,
                                        contentScale = ContentScale.Crop,

                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(240.dp)
                                    )

                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(240.dp)
                                            .background(
                                                Brush.verticalGradient(
                                                    colors = listOf(
                                                        Color.Transparent,
                                                        Color(0xAA000000)
                                                    )
                                                )
                                            )
                                    )

                                    Text(
                                        text = stone.title,

                                        color = Color.White,

                                        fontWeight = FontWeight.Bold,

                                        fontSize = 24.sp,

                                        modifier = Modifier
                                            .align(Alignment.BottomStart)
                                            .padding(18.dp)
                                    )
                                }

                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {

                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {

                                        Icon(
                                            imageVector = Icons.Default.LocationOn,
                                            contentDescription = null,
                                            tint = Color(0xFF6D4C41)
                                        )

                                        Spacer(modifier = Modifier.width(4.dp))

                                        Text(
                                            text = stone.location,
                                            color = Color(0xFF6D4C41),
                                            fontSize = 14.sp
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(10.dp))

                                    Text(
                                        text = "Tap to explore inscription details",
                                        color = Color(0xFF6D4C41),
                                        fontSize = 13.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: androidx.navigation.NavController,
    title: String,
    translation: String,
    description: String,
    location: String,
    imageId: Int
) {

    val context = LocalContext.current

    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text(
                        text = "Namma Shasane",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },

                navigationIcon = {

                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {

                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF5D4037)
                )
            )
        }

    ) { paddingValues ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFF5E6CC),
                            Color(0xFFE8D3B0)
                        )
                    )
                )
                .padding(paddingValues)
                .padding(16.dp)

        ) {

            Image(
                painter = painterResource(id = imageId),
                contentDescription = title,
                contentScale = ContentScale.Crop,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = title,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3E2723)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Kannada Translation",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5D4037)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = translation,
                fontSize = 18.sp,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Description",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5D4037)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = description,
                fontSize = 17.sp,
                lineHeight = 28.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(

                onClick = {

                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(
                            "https://www.google.com/maps/search/?api=1&query=$location"
                        )
                    )

                    context.startActivity(intent)
                },

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6D4C41)
                ),

                shape = RoundedCornerShape(16.dp),

                modifier = Modifier.fillMaxWidth()

            ) {

                Text(
                    text = "Open in Google Maps",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}