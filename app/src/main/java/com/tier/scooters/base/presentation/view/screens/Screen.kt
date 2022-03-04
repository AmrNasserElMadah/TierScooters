package com.tier.scooters.base.presentation.view.screens

sealed class Screen(val route: String) {
    object ScootersMapScreen : Screen("scooters_map_screen")

    //To pass data between screen and prepare route automatically
    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            if (args.isNotEmpty()) {
                append("?${args.first()}={${args.first()}}")

                args.slice(1 until args.size).forEach { arg ->
                    append("&${arg}={${arg}}")
                }
            }
        }
    }

    fun prepareRouteWithArgs(vararg args: Pair<String, Any>): String {
        return buildString {
            append(route)
            if (args.isNotEmpty()) {
                append("?${args.first().first}=${args.first().second}")

                args.slice(1 until args.size).forEach { arg ->
                    append("&${arg.first}=${arg.second}")
                }
            }
        }
    }
}
