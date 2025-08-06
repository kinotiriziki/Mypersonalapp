package com.example.mypersonalapp.ui.theme.screens.clients


import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.mypersonalapp.R
import com.example.mypersonalapp.data.ClientViewModel
import com.example.mypersonalapp.models.Client
import com.example.mypersonalapp.navigation.ROUTE_DASHBOARD
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

@Composable
fun UpdateClientScreen(navController: NavController,clientId:String){
    val clientViewModel:ClientViewModel = viewModel()
    var client by remember { mutableStateOf<Client?>(null) }
    LaunchedEffect(clientId) {
        val ref = FirebaseDatabase.getInstance()
            .getReference("Clients").child(clientId)
        val snapshot = ref.get().await()
        client = snapshot.getValue(Client::class.java)?.apply {
            id= clientId
        }
    }
    if (client==null){
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            CircularProgressIndicator()
        }
        return
    }

    var name by remember { mutableStateOf(client!!.name?:"") }
    var gender by remember { mutableStateOf(client!!.gender?:"") }
    var nationality by remember { mutableStateOf(client!!.nationality?:"") }
    var age by remember { mutableStateOf(client!!.age?:"") }
    var details by remember { mutableStateOf(client!!.details?:"") }
    var contact by remember { mutableStateOf(client!!.contact ?:"") }

    val imageUri = remember() { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
          it?. let { uri -> imageUri.value = uri }
    }

    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize().padding(15.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "UPDATE CLIENT",
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            textAlign = TextAlign.Center,
            color = Color.Blue,
            modifier = Modifier.fillMaxWidth()
        )
        Card (shape = CircleShape,
            modifier = Modifier.padding(10.dp).size(200.dp)) {
            AsyncImage(model = imageUri.value ?:client!!.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(200.dp).clickable {
                    launcher.launch("image/*")
                })

        }
        Text(text = "Change Picture Here")
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Full Name") },
            textStyle = TextStyle(Color.Blue),
            placeholder = { Text(text = "Please enter client name") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = gender,
            onValueChange = { gender = it },
            label = { Text(text = "Gender") },
            textStyle = TextStyle(Color.Blue),
            placeholder = { Text(text = "Please enter client gender") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = nationality,
            onValueChange = { nationality = it },
            label = { Text(text = "Nationality") },
            textStyle = TextStyle(Color.Blue),
            placeholder = { Text(text = "Please enter client nationality") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = contact,
            onValueChange = { contact = it },
            label = { Text(text = "Phone Number") },
            textStyle = TextStyle(Color.Blue),
            placeholder = { Text(text = "Please enter client phone number") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text(text = "Age") },
            textStyle = TextStyle(Color.Blue),
            placeholder = { Text(text = "Please enter client age") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = details,
            onValueChange = { details = it },
            label = { Text(text = "Details") },
            textStyle = TextStyle(Color.Blue),
            placeholder = { Text(text = "Details") },
            modifier = Modifier.fillMaxWidth().height(150.dp),
            singleLine = false
        )
        Row (modifier = Modifier.fillMaxWidth(0.8f),
            horizontalArrangement = Arrangement.SpaceBetween){
            Button(onClick = {navController.navigate(ROUTE_DASHBOARD)
            },colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD81B60)),) { Text(text = "GO BACK") }
            Button(onClick = {

            }) { Text(text = "UPDATE") }
        }
    }
}

