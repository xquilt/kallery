package com.polendina.kallery.ui.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.polendina.kallery.R
import com.polendina.kallery.data.model.User
import com.polendina.kallery.ui.profile.ProfileScreenViewModelMock
import com.polendina.kallery.ui.theme.KalleryTheme

@Composable
fun UserWidget(
    user: User?,
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.preview_now),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
            )
            IconButton(
                onClick = onProfileClick,
                Modifier
                    .shadow(10.dp, clip = true, shape = CircleShape)
                    .clip(CircleShape)
                    .background(Color.White)
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                )
            }
        }
        Divider(color = MaterialTheme.colorScheme.surfaceVariant)
    }
}


//Column (
//Modifier
//.height(40.dp)
//) {
//    Text(text = user.name)
//    Text(text = user.address.toString())
//    Text(text = user.phone)
//}

@Preview(showBackground = true)
@Composable
fun UserWidgetPreview() {
    KalleryTheme {
        UserWidget(
            user = ProfileScreenViewModelMock().currentUser,
            onProfileClick = {}
        )
    }
}