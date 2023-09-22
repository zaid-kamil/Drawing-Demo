@file:OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)

package com.example.drawingdemo.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.drawingdemo.ui.screen.ExampleOneScreen
import com.example.drawingdemo.ui.screen.ExampleThreeScreen
import com.example.drawingdemo.ui.screen.ExampleTwoScreen
import com.example.drawingdemo.ui.screen.HomeScreen

enum class Screen {
    Home, Example1, Example2, Example3
}

@Composable
fun AppStartScreen() {
    val navController = rememberNavController()
    Scaffold(topBar = { Appbar() }, bottomBar = {
        BottomBar(
            onSelect = {
                when (it) {
                    0 -> navController.navigate(Screen.Home.name)
                    1 -> navController.navigate(Screen.Example1.name)
                    2 -> navController.navigate(Screen.Example2.name)
                    3 -> navController.navigate(Screen.Example3.name)
                }
            }
        )
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            NavGraph(navController)
        }
    }
}

@Composable
private fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Example3.name) {
        composable(Screen.Home.name) {
            HomeScreen()
        }
        composable(Screen.Example1.name) {
            ExampleOneScreen()
        }
        composable(Screen.Example2.name) {
            ExampleTwoScreen()
        }
        composable(Screen.Example3.name) {
            ExampleThreeScreen()
        }
    }
}


@Composable
fun Appbar() {
    CenterAlignedTopAppBar(title = {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = null,
            )
            Text(text = "Canvas Drawing Demo", style = MaterialTheme.typography.titleMedium)
        }
    })
}

data class NavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

@Composable
fun BottomBar(onSelect: (Int) -> Unit = {}) {
    val navigationItems = listOf(
        NavItem(
            title = "Home",
            selectedIcon = Icons.Default.Home,
            unselectedIcon = Icons.Outlined.Home,
        ),
        NavItem(
            title = "Example 1",
            selectedIcon = Icons.Default.Face,
            unselectedIcon = Icons.Outlined.Face,
        ),
        NavItem(
            title = "Example 2",
            selectedIcon = Icons.Default.CheckCircle,
            unselectedIcon = Icons.Outlined.CheckCircle,
        ),
        NavItem(
            title = "Example 3",
            selectedIcon = Icons.Default.Favorite,
            unselectedIcon = Icons.Outlined.FavoriteBorder,
        ),
    )

    var selectedScreen by rememberSaveable {
        mutableIntStateOf(3)
    }
    NavigationBar {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(selected = selectedScreen == index,
                onClick = {
                    selectedScreen = index
                    onSelect(index)
                },
                icon = {
                    Icon(
                        imageVector = if (selectedScreen == index) item.selectedIcon else item.unselectedIcon,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(text = item.title)
                })

        }
    }
}