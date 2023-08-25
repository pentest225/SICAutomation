package ci.orange.presentation.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView() {
    Scaffold { padding ->
        val modifier = Modifier.padding(padding)
        Text(text = "Home Screen")
    }
}