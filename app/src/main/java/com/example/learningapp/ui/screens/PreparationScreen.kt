package com.example.learningapp.ui.screens

import StepProgressBar
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.rounded.CheckCircleOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.learningapp.data.local.AppDatabase
import com.example.learningapp.data.local.KeywordDatabase
import com.example.learningapp.ui.components.Step1
import com.example.learningapp.ui.components.Step2
import com.example.learningapp.ui.components.Step3
import com.example.learningapp.viewmodel.KeywordViewModel
import com.example.learningapp.viewmodel.Step3ViewModel
import com.example.learningapp.viewmodel.StepViewModel

const val lessonTitleText = "第2回　予習"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreparationScreen(
    mainNavController: NavHostController,
    navController: NavHostController = rememberNavController(),
    stepViewModel: StepViewModel = viewModel(),
) {
    val currentStep by stepViewModel.currentStep.collectAsState()
    // ViewModelを取得
    val keywordViewModel: KeywordViewModel = viewModel()
    val step3ViewModel: Step3ViewModel = viewModel()
    // step3Daoの取得
    val context = LocalContext.current
    val keywordDao = KeywordDatabase.getDatabase(context).keywordDao()
    val step3Dao = AppDatabase.getDatabase(context).step3Dao()

    // 現在のステップを更新→プログレッションバーに反映
    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            when (backStackEntry.destination.route) {
                "step1" -> stepViewModel.updateStep(1)
                "step2" -> stepViewModel.updateStep(2)
                "step3" -> stepViewModel.updateStep(3)
            }
        }
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = lessonTitleText)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        if (currentRoute == "step1") {
                            mainNavController.navigate("home") {
                                popUpTo(mainNavController.graph.startDestinationId) {
                                    inclusive = true
                                }
                            }
                        } else {
                            navController.popBackStack()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (currentRoute == "completion") {
                    // データをDatabaseに保存
                    keywordViewModel.saveAllKeywords(keywordDao)
                    step3ViewModel.saveContentToDatabase(step3Dao)
                    mainNavController.navigate("home") {
                        popUpTo(mainNavController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                } else {
                    val nextRoute = when (currentStep) {
                        1 -> "step2"
                        2 -> "step3"
                        else -> "completion"
                    }
                    navController.navigate(nextRoute)
                }
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Next Step"
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            if (currentRoute != "completion") {
                StepProgressBar(currentStep = currentStep)
            }
            LearningNavHost(
                navController = navController,
                modifier = Modifier.fillMaxSize(),
                keywordViewModel = keywordViewModel,
                step3ViewModel = step3ViewModel
            )
        }
    }
}

// 完了画面
@Composable
fun CompletionScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "予習完了！", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(60.dp))
        Icon(
            imageVector = Icons.Rounded.CheckCircleOutline,
            contentDescription = "Completion",
            tint = Color.Green,
            modifier = Modifier.size(250.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "素晴らしいです！", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "授業に出席して、学びを深めましょう。",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun LearningNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    keywordViewModel: KeywordViewModel,
    step3ViewModel: Step3ViewModel
) {
    NavHost(navController, startDestination = "step1", modifier = modifier) {
        composable(route = "step1",
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(700)
                )
            }
        ) { Step1() }
        composable(route = "step2",
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(700)
                )
            }) { Step2(keywordViewModel = keywordViewModel) }
        composable(route = "step3", enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                animationSpec = tween(700)
            )
        },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(700)
                )
            }) { Step3(step3ViewModel = step3ViewModel) }
        composable(route = "completion", enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                animationSpec = tween(700)
            )
        },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(700)
                )
            }) { CompletionScreen() }
    }
}