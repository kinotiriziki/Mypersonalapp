package com.example.mypersonalapp.ui.theme.screens.register

import android.text.InputType
import android.widget.EditText
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mypersonalapp.R
import com.example.mypersonalapp.data.AuthViewModel
import com.example.mypersonalapp.navigation.ROUTE_LOGIN

@Composable
fun registerScreen(navController: NavController){
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmpassword by remember { mutableStateOf("") }
    var fullname by remember { mutableStateOf("") }
    val authViewModel: AuthViewModel = viewModel()

    Box(){
        Image(painter = painterResource(id = R.drawable.peximg),
            contentDescription = "Image background",
            contentScale = ContentScale.FillBounds)
    }
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Register Here",
            fontSize = 30.sp,
            fontFamily = FontFamily.SansSerif,
            fontStyle = FontStyle.Normal,
            color = Color.Blue,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(5.dp))

        Spacer(modifier = Modifier.height(30.dp))
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Image logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            contentScale = ContentScale.Fit)

        OutlinedTextField(value = username, onValueChange = {username=it},
            label = { Text("Enter Username", color = Color.White, fontWeight = FontWeight.Bold)},
            placeholder = {Text("Please enter your username",color = Color.Blue, fontWeight = FontWeight.Bold)},
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Person icon",) },
            modifier = Modifier.fillMaxWidth(0.8f))

        OutlinedTextField(value = fullname, onValueChange = {fullname=it},
            label = { Text("Enter Full name", color = Color.White, fontWeight = FontWeight.Bold)},
            placeholder = {Text("Please enter your full name",color = Color.Blue, fontWeight = FontWeight.Bold)},
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Person icon",) },
            modifier = Modifier.fillMaxWidth(0.8f))

        OutlinedTextField(value = email, onValueChange = {email=it},
            label = { Text("Enter Email",color = Color.White, fontWeight = FontWeight.Bold) },
            placeholder = { Text("Please enter email",color = Color.Blue, fontWeight = FontWeight.Bold) },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email icon") },
            modifier = Modifier.fillMaxWidth(0.8f),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email))


        OutlinedTextField(value = password, onValueChange = {password=it},
            label = { Text("Enter Your Password",color = Color.Blue, fontWeight = FontWeight.Bold)},
            placeholder = {Text("Please enter your password",color = Color.Blue, fontWeight = FontWeight.Bold)},
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Lock icon") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(0.8f),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password))


        OutlinedTextField(value = confirmpassword, onValueChange = {confirmpassword=it},
            label = { Text("Confirm Your Password",color = Color.White, fontWeight = FontWeight.Bold)},
            placeholder = {Text("Please confirm your password",color = Color.White, fontWeight = FontWeight.Bold)},
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Lock icon") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(0.8f),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password))

        Spacer(modifier = Modifier.height(10.dp))
        val context = LocalContext.current
        Button(onClick = {
            authViewModel.signup(username = username,
                email = email,
                password = password,
                confirmpassword = confirmpassword,
                navController = navController,
                context = context)},

            colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier.fillMaxWidth(0.8f))
        { Text(text = "Register",
            color = Color.Cyan,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp) }

        Text(text = "If already registered, Login here",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable { navController.navigate(ROUTE_LOGIN)  })
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun registerScreenPreview(){
    registerScreen(rememberNavController())
}