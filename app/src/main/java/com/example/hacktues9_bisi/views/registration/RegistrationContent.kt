package com.example.hacktues9_bisi.views.registration

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.hacktues9_bisi.R
import com.example.hacktues9_bisi.viewmodels.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationContent(
    modifier: Modifier = Modifier,
    onClickDone: () -> Unit,
    viewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = R.string.register))
        Spacer(modifier = modifier.height(16.dp))
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = viewModel.email,
            onValueChange = { username -> viewModel.updateUsername(username) },
            shape = RoundedCornerShape(12.dp),
            label = { Text(text = stringResource(id = R.string.email)) },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "email icon") }
        )
        Spacer(modifier = modifier.height(16.dp))
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = viewModel.password,
            onValueChange = { password -> viewModel.updatePassword(password) },
            shape = RoundedCornerShape(12.dp),
            label = { Text(text = stringResource(id = R.string.password)) },
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "password icon") }
        )
        Spacer(modifier = modifier.height(16.dp))
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = onClickDone,
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = stringResource(id = R.string.done))
        }
    }
}