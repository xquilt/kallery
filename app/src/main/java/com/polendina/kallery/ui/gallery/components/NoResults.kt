package com.polendina.kallery.ui.gallery.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.polendina.kallery.R

@Composable
internal fun NoResults(
    modifier: Modifier = Modifier
) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.no_results),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.LightGray)
        )
        Text(
            text = stringResource(id = R.string.no_results),
            style = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 30.sp,
                color = Color.LightGray
            )
        )
        Text(
            text = stringResource(id = R.string.no_results_description),
            style = TextStyle(
                fontWeight = FontWeight.Medium,
                color = Color.LightGray
            )
        )
    }
}

