package com.example.anchorbay.ui.editbay

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.anchorbay.R
import com.example.anchorbay.data.Localisation
import com.example.anchorbay.data.Direction

val imageSize = 60.dp

@Composable
fun LocationAndDirection(
    localisation: Localisation,
    onLocalisationChange: (localisation: Localisation) -> Unit
) {
    var showDirectionDialog by remember { mutableStateOf(false) }

    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        IconButton(onClick = {}) {
            Icon(Icons.Rounded.LocationOn, contentDescription = "", Modifier.size(imageSize))
        }
        IconButton(onClick = { showDirectionDialog = true }) {
            if (localisation.direction == null) {
                Icon(
                    painter = painterResource(id = R.drawable.compass),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier
                        .size(imageSize)
                )
            } else {
                DirectionComp(localisation.direction)
            }
        }
    }

    if (showDirectionDialog) {
        Dialog(
            onDismissRequest = { showDirectionDialog = false },
        ) {
            SelectDirection {
                onLocalisationChange(localisation.copy(direction = it))
                showDirectionDialog = false
            }
        }
    }
}

@Composable
private fun SelectDirection(
    onDirectionSelected: (direction: Direction) -> Unit,
) {
    Card {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Direction", style = MaterialTheme.typography.h3)
            Direction.values().forEach {
                IconButton({ onDirectionSelected(it) }) {
                    DirectionComp(it)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun DirectionComp(it: Direction) {
    Box(
        modifier = Modifier
            .size(imageSize)
            .clip(CircleShape)
            .border(2.dp, Color.Black, CircleShape)
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = it.name,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
@Preview
fun SelectDirectionPreview() {
    SelectDirection({})
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun LocationAndDirectionPreview() {
    var local1 by remember { mutableStateOf(Localisation("here", null)) }
    Column {
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .background(Color.Gray)
                .fillMaxWidth()
        )
        LocationAndDirection(local1, { local1 = it })
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .background(Color.Gray)
                .fillMaxWidth()
        )
        LocationAndDirection(Localisation("here", Direction.NW), {})
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .background(Color.Gray)
                .fillMaxWidth()
        )
        LocationAndDirection(Localisation("here", Direction.S), {})
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .background(Color.Gray)
                .fillMaxWidth()
        )

    }
}