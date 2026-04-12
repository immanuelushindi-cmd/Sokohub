package com.immanuel.sokohub.ui.screens.intent

import android.content.Intent
import android.provider.MediaStore
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.immanuel.sokohub.R
import com.immanuel.sokohub.ui.theme.newblue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntentScreen(navController: NavController) {

    val mContext = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.background),
                contentScale = ContentScale.FillBounds
            )
    ) {

        TopAppBar(
            title = { Text(text = "Home") },
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "menu")
                }
            },
            actions = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "shoppingcart")
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

        // MPESA
        OutlinedButton(
            onClick = {
                val simToolKitLaunchIntent =
                    mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                simToolKitLaunchIntent?.let { mContext.startActivity(it) }
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Text(text = "Pay Via Mpesa")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // EMAIL
        OutlinedButton(
            onClick = {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("ushindiimanuel@gmail.com"))
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "subject")
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Hello, this is the email body")
                mContext.startActivity(shareIntent)
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Text(text = "Email")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // SMS
        OutlinedButton(
            onClick = {
                val smsIntent = Intent(Intent.ACTION_SENDTO)
                smsIntent.data = "smsto:0720245837".toUri()
                smsIntent.putExtra("sms_body", "Hello Immanuel, how was your day?")
                mContext.startActivity(smsIntent)
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Text(text = "Send Message")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // CALL
        OutlinedButton(
            onClick = {
                val callIntent = Intent(Intent.ACTION_DIAL)
                callIntent.data = "tel:0720245837".toUri()
                mContext.startActivity(callIntent)
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Text(text = "Call")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // SHARE
        OutlinedButton(
            onClick = {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "https://github.com/immanuelushindi-cmd"
                )
                mContext.startActivity(Intent.createChooser(shareIntent, "Share"))
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Text(text = "Share")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // CAMERA
        OutlinedButton(
            onClick = {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (cameraIntent.resolveActivity(mContext.packageManager) != null) {
                    mContext.startActivity(cameraIntent)
                } else {
                    println("Camera app is not available")
                }
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Text(text = "Camera")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IntentPreview() {
    IntentScreen(rememberNavController())
}