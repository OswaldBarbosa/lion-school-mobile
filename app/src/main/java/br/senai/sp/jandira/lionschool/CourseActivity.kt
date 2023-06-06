package br.senai.sp.jandira.lionschool

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.model.Courses
import br.senai.sp.jandira.lionschool.model.CoursesList
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    val context = LocalContext.current

    var listCourse by remember {
        mutableStateOf(listOf<Courses>())
    }

    //cria uma chamada para o endpoint
    var call = RetrofitFactory()
        .getCourseService()
        .getCourses()

    //executa a chamada
    call.enqueue(object : Callback<CoursesList> {
        override fun onResponse(
            call: Call<CoursesList>,
            response: Response<CoursesList>
        ) {
            listCourse = response.body()!!.cursos
        }

        override fun onFailure(
            call: Call<CoursesList>,
            t: Throwable
        ) {

            Log.i(
                "ds2t",
                "onFailure: ${t.message}"
            )

        }

    })

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 22.dp)
        ) {

            Text(
                text = stringResource(id = R.string.courses),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color(51, 71, 176)
            )

            Divider(
                modifier = Modifier
                    .height(3.dp)
                    .width(140.dp)
                    .offset(x = 4.dp),
                color = Color(255, 194, 61)
            )

        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

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
                        tint = Color(51, 71, 176)
                    )
                },
                value = "",
                onValueChange = {}
            )

        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            items(listCourse) {

                Button(
                    onClick = {
                        var openStudents = Intent(context, StudentsActivity::class.java)
                        openStudents.putExtra("sigla", it.sigla)
                        context.startActivity(openStudents)
                    },
                    modifier = Modifier
                        .width(390.dp)
                        .height(208.dp),
                    colors = ButtonDefaults.buttonColors(Color(255, 255, 255)),
                    border = BorderStroke(2.dp, color = Color(51, 71, 176)),
                    shape = RoundedCornerShape(10.dp),
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {

                            Text(
                                text = it.sigla,
                                fontSize = 60.sp,
                                fontWeight = FontWeight.Black,
                                color = Color(51, 71, 176)
                            )

                            AsyncImage(
                                modifier = Modifier
                                    .size(90.dp)
                                    .padding(start = 20.dp),
                                colorFilter = ColorFilter.tint(Color(51, 71, 176)),
                                model = it.icone,
                                contentDescription = "",
                            )

                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {

                            Icon(
                                painter = painterResource(id = R.drawable.baseline_timer_24),
                                contentDescription = "",
                                tint = Color(255, 194, 61)
                            )

                            Spacer(
                                modifier = Modifier
                                    .width(4.dp)
                            )

                            Text(
                                text = stringResource(id = R.string.workload),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(51, 71, 176)
                            )

                            Spacer(
                                modifier = Modifier
                                    .width(6.dp)
                            )

                            Text(
                                text = it.carga.toString(),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(255, 194, 61)
                            )

                            Spacer(
                                modifier = Modifier
                                    .width(6.dp)
                            )

                            Text(
                                text = stringResource(id = R.string.hours),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(51, 71, 176)
                            )

                        }
                    }
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