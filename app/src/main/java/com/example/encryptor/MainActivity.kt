package com.example.encryptor

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.encryptor.ui.theme.EncryptorTheme
import encryptAES256
import decryptAES256


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EncryptorTheme {
                Text(text = "Hello")
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TextFieldo() {
    Column(
        modifier = Modifier
            .background(color = Color.LightGray)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        var text by remember { mutableStateOf("") }
        TextField(
            value = text, onValueChange = { newText -> text = newText },
            label = {
                Text(text = "Text to encrypt")
            },
        )

        var encryptedText by remember { mutableStateOf("") }
        TextField(
            value = encryptedText, onValueChange = { newText -> encryptedText = newText },
            readOnly = true,
            label = {
                Text(text = "Encrypted Message")
            },
        )

        var secretKeyText by remember { mutableStateOf("secretkey123456secretkey") }
        TextField(
            value = secretKeyText, onValueChange = { newText -> secretKeyText = newText },
            label = {
                Text(text = "Secret key")
            },
        )

        Button(onClick = {encryptAES256(text, secretKeyText)}) {
            Text(text = "Encrypt")
        }

        Button(onClick = {decryptAES256(encryptedText, secretKeyText)}) {
            Text(text = "Decrypt")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EncryptButton(){

}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EncryptorTheme {
        Column(
            modifier = Modifier
                .background(color = Color.LightGray)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            TextFieldo()
        }

    }
}
