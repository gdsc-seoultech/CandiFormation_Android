package com.solution_challenge.candiformation.navigation.bottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: List<String>
) {
    object InfoBottomIcon: BottomNavItem(
        label = "Information",
        icon = Icons.Filled.Info,
        route = listOf("info", "info/presidentArchive")
    )
    object CandidateBottomIcon: BottomNavItem(
        label = "Candidates",
        icon = Icons.Filled.Attribution,
        route = listOf("candidate")
    )
    object HomeBottomIcon: BottomNavItem(
        label = "Home",
        icon = Icons.Filled.Home,
        route = listOf("home")
    )
    object NewsBottomIcon: BottomNavItem(
        label = "Articles",
        icon = Icons.Filled.Article,
        route = listOf("news", "news/articles/selectedArticle")
    )
    object SettingBottomIcon: BottomNavItem(
        label = "Profile",
        icon = Icons.Filled.AccountCircle,
        route = listOf(
            "profile",
            "profile/appInfo",
            "profile/login",
            "profile/login/signup",
            "profile/login/signup/auth",
            "profile/login/signup/setnickname",
            "profile/like",
            "profile/comments",
            "profile/login/signup/service_usage",
            "profile/login/signup/private_info",
            "profile/version"
        )
    )
}