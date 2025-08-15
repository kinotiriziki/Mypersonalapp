package com.example.mypersonalapp.ui.theme.screens.dashboard

import android.content.Intent
import android.net.Uri
import androidx.compose.material3.AlertDialog
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mypersonalapp.R
import com.example.mypersonalapp.navigation.ROUTE_ADDCLIENT
import com.example.mypersonalapp.navigation.ROUTE_REGISTER
import com.example.mypersonalapp.navigation.ROUTE_VIEWCLIENT
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.ui.platform.LocalContext



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController) {
    val selectedItem = remember { mutableStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val userEmail = FirebaseAuth.getInstance().currentUser?.email ?: ""
   Scaffold (bottomBar = {
       NavigationBar (containerColor = Color.Cyan){
          NavigationBarItem(
              selected = selectedItem.value == 0,
              onClick = {},
              icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
              label = { Text(text = "Home") },
              alwaysShowLabel = true)

           NavigationBarItem(
               selected = selectedItem.value == 1,
               onClick = {selectedItem.value =1
                         val intent = Intent(Intent.ACTION_DIAL).apply {
                             data = Uri.parse("tel:0700000000")
                         }
                         context.startActivity(intent)
                         },
               icon = {Icon(Icons.Filled.Phone, contentDescription = "Phone") },
               label = { Text(text ="Phone") },
               alwaysShowLabel = true
           )

           NavigationBarItem(
               selected = selectedItem.value == 2,
               onClick = {
                   if (userEmail.isNotEmpty()) {
                       val intent = Intent(Intent.ACTION_SENDTO).apply {
                           data = Uri.parse("mailto:$userEmail")
                       }
                       context.startActivity(intent)
                   } else {
                       Toast.makeText(context, "No email found", Toast.LENGTH_SHORT).show()
                   }
               },
               icon = { Icon(Icons.Filled.Email, contentDescription = "Email") },
               label = { Text(text = "Email") },
               alwaysShowLabel = true
           )

       }
   })
   {innerPadding ->
       Box(modifier = Modifier.padding(innerPadding)){
           Image(painter = painterResource(id = R.drawable.willianjusten),
               contentDescription = "Background image",
               contentScale = ContentScale.FillBounds)
       }
       Column( modifier = Modifier.fillMaxWidth(),
           horizontalAlignment = Alignment.CenterHorizontally) {
           TopAppBar(
               title = { Text(text = "I&M Bank") },
               navigationIcon = {
                   IconButton(onClick = {}) {
                       Icon(Icons.Filled.Menu, contentDescription = "Menu")
                   }

               },
               actions = {
                   IconButton(onClick = {}) {
                       Icon(Icons.Filled.Search, contentDescription = "Search")
                   }
                   IconButton(onClick = {}) {
                       Icon(Icons.Filled.Person, contentDescription = "Person")
                   }


                   IconButton(onClick = { showDialog = true }) {
                       Icon(Icons.Filled.AccountCircle, contentDescription = "Logout")
                   }

                   if (showDialog) {
                       AlertDialog(
                           onDismissRequest = { showDialog = false },
                           title = { Text("Confirm Logout") },
                           text = { Text("Are you sure you want to log out?") },
                           confirmButton = {
                               TextButton(onClick = {
                                   FirebaseAuth.getInstance().signOut()
                                   Toast.makeText(context, "Logged out successfully",Toast.LENGTH_SHORT).show()
                                   navController.navigate("login") {
                                       popUpTo("home") { inclusive = true }
                                   }
                                   showDialog = false
                               }) {
                                   Text("Yes")
                               }
                           },
                           dismissButton = {
                               TextButton(onClick = { showDialog = false }) {
                                   Text("No")
                               }
                           }
                       )
                   }


               },
               colors = TopAppBarDefaults.topAppBarColors(
                   containerColor = Color.Cyan,
                   titleContentColor = Color.Black,
                   navigationIconContentColor = Color.Black,
                   actionIconContentColor = Color.Black

               )
           )
           Row(modifier = Modifier.wrapContentWidth()) {
               Card(
                   modifier = Modifier.padding(10.dp).clickable {navController.navigate(
                       ROUTE_ADDCLIENT) },
                   shape = RoundedCornerShape(20.dp),
                   elevation = CardDefaults.cardElevation(10.dp),
                   colors = CardDefaults.cardColors(Color.White)
               ) {
                   Box(
                       modifier = Modifier.height(100.dp).padding(20.dp),
                       contentAlignment = Alignment.Center
                   ) {
                       Text(text = "Add Client",
                           color = Color.Blue,
                           fontWeight = FontWeight.Bold)
                   }
               }
               Spacer(modifier = Modifier.width(30.dp))
               Card(
                   modifier = Modifier.padding(10.dp).clickable {navController.navigate(
                       ROUTE_VIEWCLIENT) },
                   shape = RoundedCornerShape(20.dp),
                   elevation = CardDefaults.cardElevation(10.dp),
                   colors = CardDefaults.cardColors(Color.White)
               ) {
                   Box(
                       modifier = Modifier.height(100.dp).padding(20.dp),
                       contentAlignment = Alignment.Center
                   ) {
                       Text(text = "View Client",
                           color = Color.Blue,
                           fontWeight = FontWeight.Bold)
                   }
               }


           }

           Row(modifier = Modifier.wrapContentWidth()) {
               Card(
                   modifier = Modifier.padding(10.dp).clickable { },
                   shape = RoundedCornerShape(20.dp),
                   elevation = CardDefaults.cardElevation(10.dp),
                   colors = CardDefaults.cardColors(Color.White)
               ) {
                   Box(
                       modifier = Modifier.height(100.dp).padding(20.dp),
                       contentAlignment = Alignment.Center
                   ) {
                       Text(text = "Client Details",
                           color = Color.Blue,
                           fontWeight = FontWeight.Bold)
                   }
               }
               Spacer(modifier = Modifier.width(30.dp))
               Card(
                   modifier = Modifier.padding(10.dp).clickable { },
                   shape = RoundedCornerShape(20.dp),
                   elevation = CardDefaults.cardElevation(10.dp),
                   colors = CardDefaults.cardColors(Color.White)
               ) {
                   Box(
                       modifier = Modifier.height(100.dp).padding(20.dp),
                       contentAlignment = Alignment.Center
                   ) {
                       Text(text = "Client Feedback",
                           color = Color.Blue,
                           fontWeight = FontWeight.Bold)
                   }
               }


           }
       }
   }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview(){
    DashboardScreen(rememberNavController())
}