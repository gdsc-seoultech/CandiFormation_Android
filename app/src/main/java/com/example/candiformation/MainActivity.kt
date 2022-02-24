package com.example.candiformation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.candiformation.navigation.SetupNavigation
import com.example.candiformation.navigation.bottomBar.BottomNavBar
import com.example.candiformation.ui.SharedViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val sharedViewModel: SharedViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    @ExperimentalPagerApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            navController = rememberNavController()
            sharedViewModel.loginRefresh() // 저장된 로그인 정보 불러오기

                Scaffold(
                    content = {
                        SetupNavigation(
                            navController = navController,
                            viewModel = sharedViewModel
                        )
                    },
                    bottomBar = {
                        BottomNavBar(
                            navController = navController,
                            viewModel = sharedViewModel
                        )
                    }
                )
        }
    }
}
