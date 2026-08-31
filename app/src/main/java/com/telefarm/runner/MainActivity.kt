package com.telefarm.runner

import android.content.Intent
import android.net.Uri
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TelefarmTheme {
                TelefarmApp()
            }
        }
    }
}


/* ---------------- THEME ---------------- */

@Composable
fun TelefarmTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(

        colorScheme =
            androidx.compose.material3.darkColorScheme(

                background = Color.Black,

                surface =
                    Color(0xFF111111),

                surfaceVariant =
                    Color(0xFF1B1B1B),

                primary =
                    Color.White,

                onPrimary =
                    Color.Black,

                onBackground =
                    Color.White,

                onSurface =
                    Color.White,

                onSurfaceVariant =
                    Color(0xFFBDBDBD)
            ),

        content = content
    )
}


/* ---------------- MAIN APP ---------------- */

@OptIn(
    androidx.compose.material3.ExperimentalMaterial3Api::class
)
@Composable
fun TelefarmApp() {

    var screen by remember {
        mutableStateOf("home")
    }

    Scaffold(

        containerColor =
            Color.Black,

        topBar = {

            TopAppBar(

                title = {

                    Text(

                        text = "Telefarm",

                        fontWeight =
                            FontWeight.Bold
                    )
                },

                colors =
                    TopAppBarDefaults.topAppBarColors(

                        containerColor =
                            Color.Black,

                        titleContentColor =
                            Color.White
                    )
            )
        }

    ) { padding ->

        when (screen) {

            "home" -> {

                HomeScreen(

                    modifier =
                        Modifier.padding(padding),

                    onProjects = {

                        screen =
                            "projects"
                    },

                    onOwner = {

                        screen =
                            "owner"
                    }
                )
            }


            "projects" -> {

                ProjectsScreen(

                    modifier =
                        Modifier.padding(padding),

                    onBack = {

                        screen =
                            "home"
                    }
                )
            }


            "owner" -> {

                OwnerScreen(

                    modifier =
                        Modifier.padding(padding),

                    onBack = {

                        screen =
                            "home"
                    }
                )
            }
        }
    }
}


/* ---------------- HOME SCREEN ---------------- */

@Composable
fun HomeScreen(

    modifier: Modifier,

    onProjects: () -> Unit,

    onOwner: () -> Unit

) {

    val transition =
        rememberInfiniteTransition(
            label = "owner_animation"
        )

    val alpha by transition.animateFloat(

        initialValue =
            0.65f,

        targetValue =
            1f,

        animationSpec =
            infiniteRepeatable(

                animation =
                    tween(900),

                repeatMode =
                    RepeatMode.Reverse
            ),

        label =
            "owner_alpha"
    )


    Column(

        modifier =
            modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(20.dp),

        verticalArrangement =
            Arrangement.spacedBy(16.dp)
    ) {


        Text(

            text =
                "Bot Manager",

            style =
                MaterialTheme.typography.headlineMedium,

            fontWeight =
                FontWeight.Bold
        )


        Text(

            text =
                "Run and manage your local projects.",

            color =
                Color(0xFFBDBDBD)
        )


        Spacer(
            Modifier.height(4.dp)
        )


        /* PROJECT CARD */

        Card(

            modifier =
                Modifier.fillMaxWidth(),

            colors =
                CardDefaults.cardColors(

                    containerColor =
                        Color(0xFF151515)
                ),

            shape =
                RoundedCornerShape(22.dp)
        ) {

            Column(

                modifier =
                    Modifier.padding(20.dp)
            ) {


                Text(

                    text =
                        "Projects",

                    fontWeight =
                        FontWeight.Bold
                )


                Spacer(
                    Modifier.height(6.dp)
                )


                Text(

                    text =
                        "Create projects and manage up to 5 bots in each project.",

                    color =
                        Color(0xFFBDBDBD)
                )


                Spacer(
                    Modifier.height(16.dp)
                )


                Button(

                    onClick =
                        onProjects,

                    modifier =
                        Modifier.fillMaxWidth()
                ) {

                    Text(
                        text =
                            "Open Projects"
                    )
                }
            }
        }


        /* OWNER CARD */

        Card(

            modifier =
                Modifier
                    .fillMaxWidth()
                    .alpha(alpha)
                    .clickable {

                        onOwner()
                    },

            colors =
                CardDefaults.cardColors(

                    containerColor =
                        Color(0xFF151515)
                ),

            shape =
                RoundedCornerShape(22.dp)
        ) {


            Row(

                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(18.dp),

                verticalAlignment =
                    Alignment.CenterVertically
            ) {


                Image(

                    painter =
                        painterResource(
                            id =
                                R.drawable.profile
                        ),

                    contentDescription =
                        "Owner profile",

                    modifier =
                        Modifier
                            .size(70.dp)
                            .clip(CircleShape)
                )


                Spacer(
                    Modifier.size(16.dp)
                )


                Column {

                    Text(

                        text =
                            "𝙓𝙔𝙍 ( 𝘽𝙊𝙏 𝘿𝙀𝙑 )",

                        fontWeight =
                            FontWeight.Bold
                    )


                    Spacer(
                        Modifier.height(5.dp)
                    )


                    Text(

                        text =
                            "Telefarm Owner",

                        color =
                            Color(0xFFBDBDBD)
                    )


                    Spacer(
                        Modifier.height(4.dp)
                    )


                    Text(

                        text =
                            "Tap to view details",

                        color =
                            Color(0xFF888888)
                    )
                }
            }
        }
    }
}


/* ---------------- PROJECTS SCREEN ---------------- */

@Composable
fun ProjectsScreen(

    modifier: Modifier,

    onBack: () -> Unit

) {

    var projects by remember {

        mutableStateOf(
            listOf(
                "My Project"
            )
        )
    }


    Column(

        modifier =
            modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(20.dp)
    ) {


        OutlinedButton(

            onClick =
                onBack
        ) {

            Text(
                text =
                    "Back"
            )
        }


        Spacer(
            Modifier.height(18.dp)
        )


        Row(

            modifier =
                Modifier.fillMaxWidth(),

            horizontalArrangement =
                Arrangement.SpaceBetween,

            verticalAlignment =
                Alignment.CenterVertically
        ) {


            Column {

                Text(

                    text =
                        "Projects",

                    style =
                        MaterialTheme.typography.headlineSmall,

                    fontWeight =
                        FontWeight.Bold
                )


                Spacer(
                    Modifier.height(4.dp)
                )


                Text(

                    text =
                        "Maximum 5 bots per project",

                    color =
                        Color(0xFFBDBDBD)
                )
            }


            Button(

                onClick = {

                    if (projects.size < 5) {

                        projects =
                            projects +
                                "Project ${projects.size + 1}"
                    }
                }
            ) {

                Text(
                    text =
                        "New Project"
                )
            }
        }


        Spacer(
            Modifier.height(20.dp)
        )


        LazyColumn(

            verticalArrangement =
                Arrangement.spacedBy(10.dp)
        ) {


            items(projects) { project ->


                Card(

                    modifier =
                        Modifier.fillMaxWidth(),

                    colors =
                        CardDefaults.cardColors(

                            containerColor =
                                Color(0xFF151515)
                        ),

                    shape =
                        RoundedCornerShape(18.dp)
                ) {


                    Row(

                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(18.dp),

                        horizontalArrangement =
                            Arrangement.SpaceBetween,

                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {


                        Column {

                            Text(

                                text =
                                    project,

                                fontWeight =
                                    FontWeight.SemiBold
                            )


                            Spacer(
                                Modifier.height(4.dp)
                            )


                            Text(

                                text =
                                    "0 / 5 bots",

                                color =
                                    Color(0xFFBDBDBD)
                            )
                        }


                        Text(
                            text =
                                "Open"
                        )
                    }
                }
            }
        }
    }
}


/* ---------------- OWNER SCREEN ---------------- */

@Composable
fun OwnerScreen(

    modifier: Modifier,

    onBack: () -> Unit

) {

    /*
     * IMPORTANT:
     * startActivity() directly is not available inside
     * a Composable.
     *
     * LocalContext gives us the current Android context.
     */

    val context =
        LocalContext.current


    Column(

        modifier =
            modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(20.dp)
    ) {


        OutlinedButton(

            onClick =
                onBack
        ) {

            Text(
                text =
                    "Back"
            )
        }


        Spacer(
            Modifier.height(35.dp)
        )


        /* PROFILE IMAGE */

        Image(

            painter =
                painterResource(
                    id =
                        R.drawable.profile
                ),

            contentDescription =
                "Owner profile",

            modifier =
                Modifier
                    .size(120.dp)
                    .clip(CircleShape)
        )


        Spacer(
            Modifier.height(20.dp)
        )


        /* OWNER NAME */

        Text(

            text =
                "𝙓𝙔𝙍 ( 𝘽𝙊𝙏 𝘿𝙀𝙑 )",

            style =
                MaterialTheme.typography.headlineSmall,

            fontWeight =
                FontWeight.Bold
        )


        Spacer(
            Modifier.height(6.dp)
        )


        Text(

            text =
                "Telefarm Owner",

            color =
                Color(0xFFBDBDBD)
        )


        Spacer(
            Modifier.height(30.dp)
        )


        /* TELEGRAM BUTTON */

        Button(

            onClick = {

                val intent =
                    Intent(

                        Intent.ACTION_VIEW,

                        Uri.parse(
                            "https://t.me/SKY_XYR"
                        )
                    )

                context.startActivity(intent)
            },

            modifier =
                Modifier.fillMaxWidth()
        ) {

            Text(
                text =
                    "Contact Owner"
            )
        }
    }
}
