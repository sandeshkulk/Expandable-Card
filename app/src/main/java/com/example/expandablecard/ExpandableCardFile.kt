package com.example.expandablecard

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.expandablecard.ui.theme.Shapes


@ExperimentalMaterialApi
@Composable
fun ExpandableCardFun(){
    var expandedState by remember{ mutableStateOf(false)}
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            //for expanding / shrinking of card add animatecontentsize
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = Shapes.medium,
        onClick = {
            expandedState =! expandedState
        }
    ){

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)) {
            //Adding a row here
            Row(verticalAlignment=Alignment.CenterVertically){
                //Text
                Text(
                    modifier = Modifier
                        .weight(6f),
                    text = "My Title",
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis)
                //Iconbtn
                IconButton(modifier = Modifier
                    .alpha(ContentAlpha.medium)
                    .weight(1f)
                    .rotate(rotationState),
                    onClick = {
                        expandedState =! expandedState
                    }) {
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "DropDown Arrow")
                }
            }

            if (expandedState){
                Text(text = "Severe acute respiratory syndrome coronavirus 2 (SARS-CoV-2)" +
                        " is a novel severe acute respiratory syndrome coronavirus"+
                        " It was first isolated from three people with pneumonia", fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal,
                    maxLines = 4, overflow = TextOverflow.Ellipsis)
            }
        }

    }
}

@ExperimentalMaterialApi
@Composable
@Preview
fun ExpandableCardPreview (){
    ExpandableCardFun()
}