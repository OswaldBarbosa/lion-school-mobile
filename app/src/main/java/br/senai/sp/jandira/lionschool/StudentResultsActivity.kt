package br.senai.sp.jandira.lionschool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.model.StudentResults
import br.senai.sp.jandira.lionschool.model.StudentsList
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentResultsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val getMatricula = intent.getStringExtra("matricula")
        val getNome = intent.getStringExtra("nome")
        val getFoto = intent.getStringExtra("foto")
        setContent {
            LionSchoolTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    StudentResultsScreen(
                        getMatricula.toString(),
                        getNome.toString(),
                        getFoto.toString()
                    )
                }
            }
        }
    }
}

@Composable
fun StudentResultsScreen(matricula: String, nome: String, foto: String) {

    var StudentScore by remember {
        mutableStateOf(StudentResults("", "", "", "", emptyList()))
    }

    //cria uma chamada para o endpoint
    var call = RetrofitFactory()
        .getResultsAluno()
        .getAlunosByMatricula(matricula)

    //executa a chamada }
    call.enqueue(object : Callback<StudentResults> {
        override fun onResponse(
            call: Call<StudentResults>,
            response: Response<StudentResults>
        ) {
            if (response.isSuccessful) {
                val studentResponse = response.body()
                if (studentResponse != null) {
                    StudentScore = studentResponse
                }
            }
        }

        override fun onFailure(call: Call<StudentResults>, t: Throwable) {

        }
    })

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = nome.uppercase(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(51, 71, 176)
            )

            Divider(
                modifier = Modifier
                    .height(4.dp)
                    .fillMaxWidth(),
                color = Color(255, 194, 61)
            )

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Surface(
                modifier = Modifier
                    .width(170.dp)
                    .height(240.dp),
                color = Color(51, 71, 176)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    AsyncImage(
                        model = foto,
                        contentDescription = ""
                    )

                    Text(
                        text = matricula,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(255, 194, 61)
                    )

                }

            }

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Card(
                modifier = Modifier
                    .height(340.dp)
                    .width(340.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    items(StudentScore.disciplinas) {

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {

                            Column(
                                modifier = Modifier
                                    .width(50.dp)
                            ) {

                                Text(
                                    text = it.sigla,
                                    color = getColorScore(it.media.toDouble()),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )

                            }

                            Spacer(
                                modifier = Modifier
                                    .width(10.dp)
                            )

                            Column(
                                modifier = Modifier
                                    .width(180.dp)
                            ) {

                                Surface(

                                ) {
                                    Card(
                                        modifier = Modifier
                                            .height(20.dp)
                                            .width(200.dp),
                                        backgroundColor = Color.Gray
                                    ) {}
                                    Card(
                                        modifier = Modifier
                                            .height(20.dp)
                                            .width(it.media.toDouble().dp),
                                        backgroundColor = getColorScore(it.media.toDouble())
                                    ) {}

                                }

                            }

                            Spacer(
                                modifier = Modifier
                                    .width(10.dp)
                            )

                            Column(
                                modifier = Modifier
                                    .width(35.dp)
                            ) {

                                Text(
                                    text = it.media,
                                    color = getColorScore(it.media.toDouble()),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )

                            }

                        }

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

fun getColorScore(nota: Double): Color {
    return if (nota > 69) {
        Color(51, 71, 176)
    } else if (nota > 49 && nota < 70) {
        Color(229, 182, 87)
    } else {
        Color(193, 16, 16)
    }
}
