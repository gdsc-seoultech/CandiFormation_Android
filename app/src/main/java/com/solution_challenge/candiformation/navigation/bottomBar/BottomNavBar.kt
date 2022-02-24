package com.solution_challenge.candiformation.navigation.bottomBar

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.solution_challenge.candiformation.ui.SharedViewModel

@Composable
fun BottomNavBar(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    val items = listOf(
        BottomNavItem.InfoBottomIcon,
        BottomNavItem.CandidateBottomIcon,
        BottomNavItem.HomeBottomIcon,
        BottomNavItem.NewsBottomIcon,
        BottomNavItem.SettingBottomIcon
    )

    if (viewModel.bottomBarShown.value) {
        BottomNavigation(
            elevation = 8.dp,
            backgroundColor = Color.White
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            items.forEach { item ->
                BottomNavigationItem(
                    icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                    label = {
                        Text(
                            text = item.label,
                            fontSize = 9.2.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    selectedContentColor = Color.Black,
                    unselectedContentColor = Color.LightGray,
                    alwaysShowLabel = true,
                    selected = (checkingNavigation(item, currentRoute)),
                    onClick = {
                        navController.navigate(item.route[0]) {
                            popUpTo(item.route[0])
                        }
                    }
                )
            }
        }
    }
}

fun checkingNavigation(
    item: BottomNavItem,
    currentRoute: String?
): Boolean {
    for (i in 1..item.route.size) {
        if (currentRoute == item.route[i - 1]) {
            return true
        }
    }
    return false
}