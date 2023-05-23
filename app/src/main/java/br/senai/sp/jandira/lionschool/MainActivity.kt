package br.senai.sp.jandira.lionschool

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import br.senai.sp.jandira.lionschool.ui.theme.Shapes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainActivityScreen()
                }
            }
        }
    }
}

@Composable
fun MainActivityScreen() {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 32.dp, top = 100.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Image(
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp),
                painter = painterResource(id = R.drawable.logo_image),
                contentDescription = "Logo Lion School"
            )

            Column(
                modifier = Modifier
                    .padding(top = 12.dp, start = 32.dp)
            ) {

                Text(
                    text = stringResource(id = R.string.lion),
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(51, 71, 176)
                )

                Divider(
                    modifier = Modifier
                        .height(3.dp)
                        .width(65.dp)
                        .offset(x = 2.dp),
                    color = Color(255, 194, 61)
                )

                Text(
                    text = stringResource(id = R.string.scholl),
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(51, 71, 176)
                )

                Divider(
                    modifier = Modifier
                        .height(3.dp)
                        .width(105.dp)
                        .offset(x = 2.dp),
                    color = Color(255, 194, 61)
                )

            }

        }

        Text(
            modifier = Modifier
                .width(320.dp),
            text = stringResource(id = R.string.choose_a_course),
            fontSize = 35.sp,
            fontFamily = FontFamily.Monospace
        )

        Button(
            modifier = Modifier
                .width(144.dp)
                .height(64.dp)
                .border(2.dp, color = Color(51, 71, 176), RoundedCornerShape(12.dp)),
            colors = ButtonDefaults.buttonColors(Color(255,195,61)),
            shape = RoundedCornerShape(15.dp),
            onClick = {
                var openCourses = Intent(context, CourseActivity::class.java)
                context.startActivity(openCourses)
            }
        ) {

            Text(
                text = stringResource(id = R.string.start),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(51, 71, 176)
            )

        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    LionSchoolTheme {
        MainActivityScreen()
    }
}