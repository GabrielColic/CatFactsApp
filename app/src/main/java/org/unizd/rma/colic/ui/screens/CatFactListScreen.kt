package org.unizd.rma.colic.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.unizd.rma.colic.viewmodel.CatFactViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatFactListScreen(viewModel: CatFactViewModel, navController: NavController) {
    val facts = viewModel.facts.observeAsState(emptyList())
    val isLoading = viewModel.isLoading.observeAsState(true)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cat Facts") },
                navigationIcon = {
                    Icon(imageVector = Icons.Default.Pets, contentDescription = "Cat Icon")
                }
            )
        }
    ) { padding ->
        if (isLoading.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                items(facts.value) { fact ->
                    val preview = if (fact.fact.length > 20) fact.fact.take(20) + "..." else fact.fact

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                val encodedFact = fact.fact.replace("/", "%2F")
                                navController.navigate("detail/$encodedFact/${fact.length}")
                            }
                            .padding(16.dp)
                    ) {
                        Text(text = preview, style = MaterialTheme.typography.bodyLarge)
                    }
                    HorizontalDivider()
                }
            }
        }
    }
}
