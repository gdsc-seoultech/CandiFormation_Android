package com.example.candiformation.navigation.bottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: List<String>
) {
    object InfoBottomIcon: BottomNavItem(
        label = "정보",
        icon = Icons.Filled.Info,
        route = listOf("info")
    )
    object CandidateBottomIcon: BottomNavItem(
        label = "후보",
        icon = Icons.Filled.Attribution,
        route = listOf("candidate")
    )
    object HomeBottomIcon: BottomNavItem(
        label = "홈",
        icon = Icons.Filled.Home,
        route = listOf("home")
    )
    object NewsBottomIcon: BottomNavItem(
        label = "뉴스",
        icon = Icons.Filled.Article,
        route = listOf("news", "news/articles/selectedArticle")
    )
    object SettingBottomIcon: BottomNavItem(
        label = "설정",
        icon = Icons.Filled.Settings,
        route = listOf(
            "setting",
            "setting/appInfo",
            "setting/login",
            "setting/login/signup",
            "setting/login/signup/auth",
            "setting/login/signup/setnickname"
        )
    )
}