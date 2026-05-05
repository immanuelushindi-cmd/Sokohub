package com.immanuel.sokohub.ui.screens.auth



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.immanuel.sokohub.R
import com.immanuel.sokohub.data.AuthViewModel
import com.immanuel.sokohub.navigation.ROUT_LOGIN
import com.immanuel.sokohub.ui.theme.newblue

@Composable
fun RegisterScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = painterResource(R.drawable.background),
            contentScale = ContentScale.FillBounds),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

        ) {
        Image(
            painter = painterResource(R.drawable.product),
            contentDescription = "",
            modifier = Modifier.size(200.dp),
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Join us and start your journey today!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Variables
        var username by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmpassword by remember { mutableStateOf("") }

        //username
        OutlinedTextField(
            value = username,
            onValueChange = {username = it},
            modifier = Modifier.width(350.dp),
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "") },
            label = { Text("Username") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = newblue,
                focusedBorderColor = Color.Black,
                unfocusedLeadingIconColor = newblue,
                focusedLabelColor = newblue
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        //Email
        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            modifier = Modifier.width(350.dp),
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "") },
            label = { Text("Email Address") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = newblue,
                focusedBorderColor = Color.Black,
                unfocusedLeadingIconColor = newblue,
                focusedLabelColor = newblue
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        //Password
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            modifier = Modifier.width(350.dp),
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "") },
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = newblue,
                focusedBorderColor = Color.Black,
                unfocusedLeadingIconColor = newblue,
                focusedLabelColor = newblue
            ),
            visualTransformation = PasswordVisualTransformation(),
        )

        Spacer(modifier = Modifier.height(10.dp))

        //ConfirmPassword
        OutlinedTextField(
            value = confirmpassword,
            onValueChange = {confirmpassword = it},
            modifier = Modifier.width(350.dp),
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "") },
            label = { Text("Confirm Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = newblue,
                focusedBorderColor = Color.Black,
                unfocusedLeadingIconColor = newblue,
                focusedLabelColor = newblue
            ),
            visualTransformation = PasswordVisualTransformation(),
        )

        Spacer(modifier = Modifier.height(10.dp))

        //Sign Up Button
        val context = LocalContext.current
        val authViewModel = AuthViewModel(navController, context)
        Button(
            onClick = {
                authViewModel.signup(username, email, password,confirmpassword)
            },
            colors = ButtonDefaults.buttonColors(newblue),
            shape = RoundedCornerShape(size = 10.dp),
            modifier = Modifier.width(160.dp)

        ) {
            Text(
                text = "Create Account"
            )

        }



        Spacer(modifier = Modifier.height(10.dp))

        TextButton(onClick = { navController.navigate(ROUT_LOGIN) }) {
            Text(
                text = "Already have an Account? Login",
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp
            )
        }






















    }
}
@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview(){
    RegisterScreen(rememberNavController())
}