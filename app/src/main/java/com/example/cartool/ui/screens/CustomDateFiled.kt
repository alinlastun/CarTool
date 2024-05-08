package com.example.cartool.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun customDateField(myStringDate: String? = null, nameInput: String): String {

    var pickedDate by remember {
        mutableStateOf(LocalDate.now())
    }

    val simpleState = rememberMaterialDialogState()
    val textState = remember { mutableStateOf(TextFieldValue()) }
    myStringDate?.let {
        textState.value = TextFieldValue(it)
    }
    MaterialDialog(
        dialogState = simpleState,
        buttons = {
            positiveButton(text = "Ok")
            negativeButton(text = "Cancel")
        }
    ) {
        datepicker(
            initialDate = LocalDate.now(),
            title = "Pick a date",
        ) {
            pickedDate = it
        }
    }


    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("dd-MMM-yyyy")
                .format(pickedDate)
        }
    }


    val formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy")
    val current = LocalDateTime.now().format(formatter)
    val textValue = if (myStringDate != null && current.equals(formattedDate)) {
        myStringDate
    } else {
        formattedDate
    }
    textState.value = TextFieldValue(textValue ?: "")

    Column(modifier = Modifier.padding(16.dp)) {
        ReadonlyTextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            onClick = {
                simpleState.show()
            },
            label = {
                Text(text = nameInput)
            }
        )
    }
    return textState.value.text
}

@Composable
fun ReadonlyTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: @Composable () -> Unit
) {
    Box {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            label = label
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .alpha(0f)
                .clickable(onClick = onClick),
        )
    }
}


