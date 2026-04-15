package com.immanuel.sokohub.ui.screens.about

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.immanuel.sokohub.navigation.ROUT_HOME
import com.immanuel.sokohub.navigation.ROUT_INTENT
import com.immanuel.sokohub.ui.theme.newblue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController) {

    var selectedIndex by remember { mutableStateOf(0) }
    var search by remember { mutableStateOf("") }

    Scaffold(

        // Top Bar
        topBar = {
            TopAppBar(
                title = { Text("Business Cards") },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = newblue,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },

        // Bottom Bar
        bottomBar = {
            NavigationBar(
                containerColor = newblue
            ) {

                NavigationBarItem(
                    selected = selectedIndex == 0,
                    onClick = {
                        selectedIndex = 0
                        navController.navigate(ROUT_HOME)
                    },
                    icon = {
                        Icon(Icons.Default.Home, contentDescription = "Home", tint = Color.White)
                    },
                    label = {
                        Text("Home", color = Color.White)
                    }
                )

                NavigationBarItem(
                    selected = selectedIndex == 1,
                    onClick = {
                        selectedIndex = 1
                        navController.navigate(ROUT_INTENT)
                    },
                    icon = {
                        Icon(Icons.Default.Favorite, contentDescription = "Intent", tint = Color.White)
                    },
                    label = {
                        Text("Intent", color = Color.White)
                    }
                )
            }
        },

        // FloatingActionButton
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(ROUT_INTENT) },
                containerColor = newblue
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },

        content = { paddingValues ->

            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {

                // Main Contents of the page
                Text(
                    text = "Welcome to Business Screen",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text("Tap any business card to open Intent Services.")

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = search,
                    onValueChange = { search = it },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    },
                    placeholder = {
                        Text("Search business card...")
                    },
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(20.dp))

                BusinessCard(
                    name = "Immanuel Ushindi",
                    title = "Business Consultant",
                    phone = "0758522839",
                    email = "immanuelushindi@email.com",
                    cardColor = Color(0xFF4FC3F7),
                    navController = navController
                )

                Spacer(modifier = Modifier.height(16.dp))

                BusinessCard(
                    name = "Grace Amani",
                    title = "Dentist",
                    phone = "0704759087",
                    email = "graceamani@email.com",
                    cardColor = Color(0xFFE57373),
                    navController = navController
                )

                Spacer(modifier = Modifier.height(16.dp))

                BusinessCard(
                    name = "Laura Busienei",
                    title = "Designer",
                    phone = "0798675423",
                    email = "laurabusienei@email.com",
                    cardColor = Color(0xFFFFC107),
                    navController = navController
                )
            }
        }
    )
}

@Composable
fun BusinessCard(
    name: String,
    title: String,
    phone: String,
    email: String,
    cardColor: Color,
    navController: NavController
) {

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(8.dp),
        onClick = {
            navController.navigate(ROUT_INTENT)
        }
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column {
                    Text(
                        text = name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Text(
                        text = title,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }

                Row {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = Color.White
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Icon(
                        Icons.Default.Info,
                        contentDescription = "Info",
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Call, contentDescription = "Call", tint = Color.White)
                Spacer(modifier = Modifier.width(12.dp))
                Text(phone, color = Color.White)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Email, contentDescription = "Email", tint = Color.White)
                Spacer(modifier = Modifier.width(12.dp))
                Text(email, color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    AboutScreen(rememberNavController())
}