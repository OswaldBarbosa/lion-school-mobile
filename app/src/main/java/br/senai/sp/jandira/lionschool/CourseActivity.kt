package br.senai.sp.jandira.lionschool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme

class CourseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CourseScreen()
                }
            }
        }
    }
}

@Composable
fun CourseScreen() {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 12.dp),
    ) {

        Column() {

            Text(
                text = stringResource(id = R.string.courses),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color(51, 71, 176)
            )

            Divider(
                modifier = Modifier
                    .height(2.dp)
                    .width(142.dp)
                    .offset(x = 4.dp),
                color = Color(255, 194, 61)
            )

        }

        OutlinedTextField(
            modifier = Modifier
                .width(368.dp)
                .height(58.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.search_a_course),
                    color = Color(51, 71, 176, 180),
                )
            },
            shape = RoundedCornerShape(15.dp),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = "Icon Serach",
                    tint = Color(51,71,176)
                )
            },
            value = "",
            onValueChange = {}
        )

        LazyColumn() {

            items(2) {

                Surface(
                    modifier = Modifier
                        .width(368.dp)
                        .height(208.dp)
                        .background(color = Color(0,0,0))
                ) {

                }

            }

        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview2() {
    LionSchoolTheme {
        CourseScreen()
    }
}