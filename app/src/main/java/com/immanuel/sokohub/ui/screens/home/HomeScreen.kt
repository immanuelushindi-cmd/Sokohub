package com.immanuel.sokohub.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.immanuel.sokohub.R
import com.immanuel.sokohub.ui.theme.newblue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.background),
                contentScale = ContentScale.FillBounds
            )
    ) {

        // TOP APP BAR
        TopAppBar(
            title = { Text(text = "Home") },
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "menu")
                }
            },
            actions = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "cart")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = newblue,
                navigationIconContentColor = androidx.compose.ui.graphics.Color.White,
                actionIconContentColor = androidx.compose.ui.graphics.Color.White,
                titleContentColor = androidx.compose.ui.graphics.Color.White
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        // SEARCH BAR
        var search by remember { mutableStateOf("") }

        OutlinedTextField(
            value = search,
            onValueChange = { search = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "search")
            },
            placeholder = { Text(text = "Search products, Category...") }
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Featured Products",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 20.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // ROW (PRODUCTS)
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.width(20.dp))

            repeat(5) {
                Column {

                    Image(
                        painter = painterResource(R.drawable.grocery),
                        contentDescription = "grocery",
                        modifier = Modifier
                            .size(200.dp)
                            .clip(RoundedCornerShape(20.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = "Groceries",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 20.dp)
                    )

                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(newblue),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.padding(start = 20.dp)
                    ) {
                        Text(text = "Shop Now")
                    }
                }

                Spacer(modifier = Modifier.width(20.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}