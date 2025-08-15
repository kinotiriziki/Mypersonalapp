package com.example.mypersonalapp.ui.theme.screens.clients

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.mypersonalapp.data.ClientViewModel
import com.example.mypersonalapp.models.Client
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import com.example.mypersonalapp.navigation.ROUTE_DASHBOARD

@Composable
fun ClientListScreen(navController: NavController){
    val clientViewModel:ClientViewModel = viewModel()
    val clients = clientViewModel.clients
    val context = LocalContext.current
    LaunchedEffect(Unit) {
            clientViewModel.fetchClients(context)
        }
    LazyColumn {
        items(clients){ client ->
            ClientCard(
                client= client,
                onDelete = {clientId -> clientViewModel.deleteClient(clientId,context)},
                navController
            )

        }
    }

}
@Composable
fun ClientCard(client: Client,
               onDelete: (String)->Unit,
               navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }
    if (showDialog){
        AlertDialog(
            onDismissRequest = {showDialog = false},
            title = { Text("Confirm Delete") },
            text = { Text("Are you sure you want to delete this patient?")},
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    client.id?.let { onDelete(it) }
                }) {
                    Text("Yes", color = Color.Red )
                }
            },
            dismissButton = {
                TextButton(onClick = {showDialog = false}) {
                    Text("No")
                }
            }
        )
    }

    Card(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        )
    {
        Row(modifier = Modifier.padding(8.dp)) {
            client.imageUrl?.let { imageUrl ->
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Client Image",
                    modifier = Modifier.size(64.dp).clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = client.name ?: "No name",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(text = "AGE: ${client.age}", style = MaterialTheme.typography.bodySmall)
                Text(
                    text = "DETAILS: ${client.details}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
          }
        Spacer(modifier = Modifier.height(10.dp))
        Row (horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
            TextButton(onClick = {
                navController.navigate("updateclient/${client.id}")
            }) {
                Text("UPDATE", color = Color.Cyan)
            }
            TextButton(onClick = { showDialog= true}) {
                Text("DELETE", color = Color.Red)
            }
        }
    }
    Row (modifier = Modifier.fillMaxWidth(0.8f),
        horizontalArrangement = Arrangement.SpaceBetween){
        Button(onClick = {navController.navigate(ROUTE_DASHBOARD)
        },colors = ButtonDefaults.buttonColors( containerColor = Color(0xFF1976D2)),)
        { Text(text = "GO BACK") }
    }
}


