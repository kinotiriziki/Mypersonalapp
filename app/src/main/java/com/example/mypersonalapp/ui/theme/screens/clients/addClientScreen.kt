package com.example.mypersonalapp.ui.theme.screens.clients

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun AddClientScreen(navController: NavController){
    var name by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var nationality by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var details by remember { mutableStateOf("") }
    var imageUri = rememberSaveable() { mutableStateOf<Uri?>(null) }
    var launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        uri: Uri? ->  uri?.let { imageUri.value=it }
    }
    val clientViewModel:ClientViewModel = viewModel()
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize().padding(15.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "ADD NEW CLIENT",
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            textAlign = TextAlign.Center,
            color = Color.Blue,
            modifier = Modifier.fillMaxWidth()
        )
        Card (shape = CircleShape,
            modifier = Modifier.padding(10.dp).size(200.dp)) {
            AsyncImage(model = imageUri.value ?: R.drawable.ic_person,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(200.dp).clickable {
                    launcher.launch("image/*")
                })

        }
        Text(text = "Upload Picture Here")
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Enter Client Name") },
            textStyle = TextStyle(Color.Blue),
            placeholder = { Text(text = "Please enter client name") },
             modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = gender,
            onValueChange = { gender = it },
            label = { Text(text = "Enter Client Gender") },
            textStyle = TextStyle(Color.Blue),
            placeholder = { Text(text = "Please enter client gender") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = nationality,
            onValueChange = { nationality = it },
            label = { Text(text = "Enter Client Nationality") },
            textStyle = TextStyle(Color.Blue),
            placeholder = { Text(text = "Please enter client nationality") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text(text = "Enter Client Age") },
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
            Button(onClick = {

            },colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD81B60)),) { Text(text = "GO BACK") }
            Button(onClick = {
                clientViewModel.uploadClient(imageUri.value,
                    name,
                    gender,
                    nationality,
                    age,
                    details,
                    context)
            }) { Text(text = "SAVE CLIENT") }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddClientScreenPreview(){
    AddClientScreen(rememberNavController())
}