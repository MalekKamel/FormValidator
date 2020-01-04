package com.sha.formvalidatorsample.compose

import androidx.compose.Composable
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.material.DrawerState
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ModalDrawerLayout

@Composable
fun ComposeApp() {
    MaterialTheme { ComposeFieldsScreen() }
}
