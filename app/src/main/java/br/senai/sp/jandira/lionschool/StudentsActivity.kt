package br.senai.sp.jandira.lionschool

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.model.Courses
import br.senai.sp.jandira.lionschool.model.CoursesList
import br.senai.sp.jandira.lionschool.model.Students
import br.senai.sp.jandira.lionschool.model.StudentsList
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
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
            listStudents = response.body()!!.alunos
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
                    onClick = {},
                    modifier = Modifier
                        .width(108.dp)
                        .height(64.dp),
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
                    onClick = {},
                    modifier = Modifier
                        .width(108.dp)
                        .height(64.dp),
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
                    onClick = {},
                    modifier = Modifier
                        .width(108.dp)
                        .height(64.dp),
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

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {

            items(listStudents) {

                Button(
                    onClick = {},
                    modifier = Modifier
                        .width(120.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(Color(51,71,176))
                ) {

                    Text(text = it.nome)

                }

            }

        }

    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun DefaultPreview3() {
//    LionSchoolTheme {
//        StudentsScreen()
//    }
//}