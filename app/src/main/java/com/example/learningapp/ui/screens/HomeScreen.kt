package com.example.learningapp.ui.screens

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "ネットワーク概論")
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.List,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                actions = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.School, contentDescription = "Home") },
                    label = { Text("授業") },
                    selected = navController.currentDestination?.route == "home",
                    onClick = {
//                        navController.navigate("home") {
//                            popUpTo(navController.graph.startDestinationId) { saveState = true }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.People, contentDescription = "Profile") },
                    label = { Text("掲示板") },
                    selected = navController.currentDestination?.route == "profile",
                    onClick = {
//                        navController.navigate("profile") {
//                            popUpTo(navController.graph.startDestinationId) { saveState = true }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
                    label = { Text("設定") },
                    selected = navController.currentDestination?.route == "settings",
                    onClick = {
//                        navController.navigate("settings") {
//                            popUpTo(navController.graph.startDestinationId) { saveState = true }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
                    }
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            LessonCard(navController,1, "ガイダンス", 3)
            LessonCard(navController,2, "インターネットのプロトコル", 1)
            LessonCard(navController,3, "DNSサーバ", 0)
            LessonCard(navController,4, "暗号化の仕組み", 0)
            LessonCard(navController,5, "誤り検出", 0)
            LessonCard(navController,6, "インターネットの〜", 0)
            LessonCard(navController,7, "インターネットの〜", 0)
            LessonCard(navController,8, "インターネットの〜", 0)
            LessonCard(navController,9, "インターネットの〜", 0)
            LessonCard(navController,10, "インターネットの〜", 0)
            LessonCard(navController,11, "インターネットの〜", 0)
            LessonCard(navController,12, "インターネットの〜", 0)
            LessonCard(navController,13, "インターネットの〜", 0)
            LessonCard(navController,14, "インターネットの〜", 0)
            LessonCard(navController,15, "インターネットの〜", 0)
        }
    }
}


@Composable
fun LessonCard(
    navController: NavHostController,
    number: Int,
    title: String,
    star: Int,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(90.dp)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(onClick = {navController.navigate("preparation")})
            .padding(16.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
        ) {
            Text(
                text = number.toString(),
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 20.sp,
            modifier = Modifier
                .width(200.dp)
        )
        Spacer(modifier = Modifier.weight(1f))

        Text(text = "★", fontSize = 26.sp, color = if (star >0) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onPrimary)
        Text(text = "★", fontSize = 26.sp, color = if (star >1) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onPrimary)
        Text(text = "★", fontSize = 26.sp, color = if (star >2) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onPrimary)
    }
}