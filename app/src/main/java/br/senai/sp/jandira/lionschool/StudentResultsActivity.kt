package br.senai.sp.jandira.lionschool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import coil.compose.AsyncImage

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
                    StudentResultsScreen(getMatricula.toString(), getNome.toString(), getFoto.toString())
                }
            }
        }
    }
}

@Composable
fun StudentResultsScreen(matricula: String, nome: String, foto: String) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            
            Text(
                text = nome.uppercase(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(51,71,176)
            )

            Divider(
                modifier = Modifier
                    .height(4.dp)
                    .fillMaxWidth(),
                color = Color(255,194,61)
            )

        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Surface(
                modifier = Modifier
                    .width(170.dp)
                    .height(240.dp),
                color = Color(51,71,176)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    AsyncImage(
                        model = foto,
                        contentDescription = ""
                    )

                    Text(
                        text = matricula,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(255,194,61)
                    )

                }

            }

        }

    }

}