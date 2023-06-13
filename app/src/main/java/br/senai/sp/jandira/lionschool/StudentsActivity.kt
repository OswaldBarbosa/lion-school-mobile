package br.senai.sp.jandira.lionschool

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.model.Courses
import br.senai.sp.jandira.lionschool.model.CoursesList
import br.senai.sp.jandira.lionschool.model.Students
import br.senai.sp.jandira.lionschool.model.StudentsList
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class StudentsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val getSigla = intent.getStringExtra("sigla")
        setContent {
            LionSchoolTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    StudentsScreen(getSigla.toString())
                }
            }
        }
    }
}

@Composable
fun StudentsScreen(sigla: String) {

    val context = LocalContext.current

    var listStudents by remember {
        mutableStateOf(listOf<Students>())
    }

    var nameCourse by remember {
        mutableStateOf("")
    }

    var listStatus by remember {
        mutableStateOf(listOf<Students>())
    }

    //cria uma chamada para o endpoint
    var call = RetrofitFactory()
        .getStudentsService()
        .getStudentsByCourse(sigla)

    //executa a chamada
    call.enqueue(object : retrofit2.Callback<StudentsList> {

        override fun
                onResponse(
            call: Call<StudentsList>,
            response: Response<StudentsList>

        ) {
            nameCourse = response.body()!!.NomeCurso
            listStudents = response.body()!!.aluno
        }

        override fun onFailure(
            call: Call<StudentsList>,
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
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {

            Row {

                Text(
                    text = "Students -",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(51, 71, 176)
                )

                Text(
                    text = " DS",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(51, 71, 176)
                )

            }

            Divider(
                modifier = Modifier
                    .width(185.dp)
                    .height(4.dp),
                color = Color(255, 194, 61)
            )

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Button(
                    onClick = {
                        listStatus = listStudents
                    },
                    modifier = Modifier
                        .width(108.dp)
                        .height(64.dp)
                        .border(
                            2.dp,
                            color = Color(51, 71, 176),
                            shape = RoundedCornerShape(10.dp)
                        ),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(Color(255, 194, 61)),
                ) {

                    Text(
                        text = stringResource(id = R.string.status),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(51, 71, 176)
                    )

                }

                Button(
                    onClick = {
                        listStatus = listStudents.filter { it.status == "Cursando" }
                    },
                    modifier = Modifier
                        .width(108.dp)
                        .height(64.dp)
                        .border(
                            2.dp,
                            color = Color(51, 71, 176),
                            shape = RoundedCornerShape(10.dp)
                        ),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(Color(255, 194, 61)),

                    ) {

                    Text(
                        text = stringResource(id = R.string.studying),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(51, 71, 176)
                    )

                }

                Button(
                    onClick = {
                        listStatus = listStudents.filter { it.status == "Finalizado" }
                    },
                    modifier = Modifier
                        .width(108.dp)
                        .height(64.dp)
                        .border(
                            2.dp,
                            color = Color(51, 71, 176),
                            shape = RoundedCornerShape(10.dp)
                        ),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(Color(255, 194, 61)),
                ) {

                    Text(
                        text = stringResource(id = R.string.finished),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(51, 71, 176)
                    )

                }

            }

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = nameCourse,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(51, 71, 176),
                textAlign = TextAlign.Center
            )
        }

        LazyColumn(
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            items(listStudents) {

                Button(
                    onClick = {
                        var openResults = Intent(context, StudentResultsActivity::class.java)
                        openResults.putExtra("matricula", it.matricula)
                        openResults.putExtra("nome", it.nome)
                        openResults.putExtra("foto", it.foto)
                        context.startActivity(openResults)
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .width(170.dp)
                        .height(240.dp),
                    colors = ButtonDefaults.buttonColors(getStatusColor(it.status))
                ) {

                    Column() {

                        AsyncImage(
                            model = it.foto,
                            contentDescription = ""
                        )

                        Text(
                            text = it.nome.uppercase(),
                            color = Color(255, 255, 255),
                            fontSize = 13.sp,
                            textAlign = TextAlign.Center
                        )

                    }

                }

            }

        }

        Column {

            Divider(
                modifier = Modifier
                    .width(360.dp)
                    .height(4.dp)
                    .offset(y = 12.dp, x = 25.dp),
                color = Color(255, 194, 61)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            )
            {

                Image(
                    modifier = Modifier
                        .size(35.dp),
                    painter = painterResource(id = R.drawable.youtube),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(color = Color(51, 71, 176))
                )

                Image(
                    modifier = Modifier
                        .size(25.dp),
                    painter = painterResource(id = R.drawable.twitter),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(color = Color(51, 71, 176))
                )

                Image(
                    modifier = Modifier
                        .size(25.dp),
                    painter = painterResource(id = R.drawable.instagram),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(color = Color(51, 71, 176))
                )

                Image(
                    modifier = Modifier
                        .size(30.dp),
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(color = Color(51, 71, 176))
                )

            }

        }
    }
}

fun getStatusColor(status: String): Color {
    return if (status == "Finalizado") {
        Color(255, 194, 61)
    } else {
        Color(51, 71, 176)
    }
}