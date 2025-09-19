@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.diariodeclass

import android.R.attr.icon
import android.R.attr.title
import android.graphics.drawable.Icon
import android.os.Build.VERSION_CODES_FULL.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.collection.mutableIntSetOf
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diariodeclass.data.DataSource
import com.example.diariodeclass.ui.theme.DiarioDeClassTheme
import com.rafaelcosta.diariodeclass.data.Aluno
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource

private val Any.padding_small: Int
private val Int.dimen: Any

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiarioDeClassTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DiarioDeClasseApp(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .statusBarsPadding()
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DiarioDeClasseApp(
    modifier: Modifier = Modifier
) {
    val layoutDirection = LocalLayoutDirection.current
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateEndPadding(layoutDirection),
            ),
    ) {
        ListaDeAlunos(
            modifier = modifier,
            listaDeAlunos = DataSource().carregarAlunos()
        )
    }
}

@Composable
fun ListaDeAlunos(
    modifier: Modifier = Modifier,
    listaDeAlunos: List<Aluno>
) {
    LazyColumn(modifier = modifier) {
        items (listaDeAlunos) { aluno ->
            CardAluno(
                modifier = modifier,
                fotoAluno = aluno.foto,
                nomeAluno = aluno.nome,
                cursoAluno = aluno.curso
            )
        }
    }
}

@Composable

fun CardAluno(
    modifier: Modifier = Modifier,
    @DrawableRes fotoAluno: Int,
    nomeAluno: String,
    cursoAluno: String
) {
    var espandir by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),
    shape = RoudedCornerShape(
        bottomEnd= 0.dp,
        topStart= 0.dp,
        bottomStart = 20.dp,
        topEnd = 20.dp
    ),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Row(
            modifier = Modifier,
            verificalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = fotoAluno),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .sizer(100.dp)
                    .weight(1f)
                    .clip(CircleShape)
            )
            Column(
                modifier = modifier
                    .weight(2f)
            )
            {
                Text(
                    text = nomeAluno,
                    modifier.fillMaxWidth()
                )
                Text(
                    text = cursoAluno,
                    modifier = Modifier.fillMaxWidth()
                )

            }
            DetalhesAlunoButton(
                { espandir = !expandir },
                modifier = modifier
                    .weight(0.5f)
                    .wrapContentSize(allign = Allignment.CenterEnd)
            )
        }
        if (espandir) {
            DetalhesAluno()
        }
    }
}
@Composable

    val AbrilFatface = FontFamily(
        Font(R.font.abril_fatface_regular)
    )

    val Montserrat = FontFamily(
        Font(R.font.montserrat_regular),
        Font(R.font.montserrat_bold, weight = FontWeight.Bold)
    )

    val AppTypography = Typography(
        displayLarge = TextStyle(
            fontFamily = AbrilFatface,
            fontWeight = FontWeight.Normal,
            fontSize = 36.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = Montserrat,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )

    val Typography = Typography(
        displayLarge = TextStyle(
            fontFamily = AbrilFatface,
            fontWeight = FontWeight.Normal,
            fontSize = 36.sp
        ),
        displayMedium = TextStyle(
            fontFamily = Montserrat,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        ),
        labelSmall = TextStyle(
            fontFamily = Montserrat,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = Montserrat,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),

    )

    @Composable
    fun DiariodeClass() {
        var presses by remember { mutableStateOf(0) }

        Scaffold(
            topBar = {
                androidx.compose.material3.TopAppBar(
                    title = { Text("Top app bar") },
                    colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary
                    )
                )
            },
            bottomBar = {
                Text(
                    text = "Rodapé",
                    modifier = Modifier.padding(16.dp)
                )
            }
        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                Text("Presses: $presses")
            }
        }
        @Composable
        private fun DetatlhesAlunoButton(
            expanded: Boolean,
            onClick: ()-> Unit,
            modifier: Modifier = Modifier
        ){
        }
        @Composable
        fun DetalhesAlunoButton(
            expanded: Boolean,
            onClick:() -> Unit,
            modifier: Modifier = Modifier
        ){
            IconButton(
                onClick = onClick,
                modifier = Modifier
            ) {
                Icon(
                    ImageVector =Icons.Filled.ExpandMore,
                    contentDescription= null,
                            tint = MaterialTheme.colorScheme.secondary
                )
            }
            Row (
              modifier = Modifier
                  .fillMaxWidth()
                  .padding(dimensionResource(R.dimen.padding_small))
            ){
             DetatlhesAlunoButton(icon.imageResourceId)


            }

        }
    }
    @Composable
    fun DetalhesAluno(){
        Column {
            Text(
                text = "nota: 100"
            )
            Text(
                text = "Faltas: 20%"
            )
        }
    }

    }
