package com.example.anchorbay.ui.editbay

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.anchorbay.R
import com.example.anchorbay.data.Label
import com.example.anchorbay.data.LabelCategory
import com.example.anchorbay.data.baseLabels

@Composable
fun LabelSelect(
    values: List<Label>,
    onValuesChange: (value: List<Label>) -> Unit,
    availableLabels: List<Label> = baseLabels
) {
    Row(
        Modifier.fillMaxWidth(),
    ) {
        val categories = availableLabels.groupBy { it.category }

        for ((category, labels) in categories) {
            key(category) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CategoryImage(category)
                    Column(Modifier.fillMaxWidth()) {
                        for (label in labels) {
                            val selected = label in values
                            Row(
                                Modifier
                                    .padding(8.dp)
                                    .clickable {
                                        onValuesChange(if (selected) values.filter { it != label } else values + label)
                                    },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(checked = selected, onCheckedChange = null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = label.text, style = MaterialTheme.typography.body2)
                            }
                        }
                    }
                }
            }
        }
    }

}

@Composable
private fun CategoryImage(category: LabelCategory) {
    val resource = when (category) {
        LabelCategory.Nature -> R.drawable.nature
        LabelCategory.Facilities -> R.drawable.facilities
    }

    Icon(
        painter = painterResource(resource),
        contentDescription = null,
        Modifier.size(100.dp),
        tint = Color.Black
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun LabelSelectPreview() {
    Column(
        Modifier
            .fillMaxWidth()
            .scrollable(rememberScrollState(), Orientation.Vertical)
    ) {
        var labels by remember { mutableStateOf(listOf<Label>()) }
        Spacer(modifier = Modifier.height(16.dp))
        LabelSelect(values = labels, { labels = it })
        Spacer(modifier = Modifier.height(16.dp))

    }
}