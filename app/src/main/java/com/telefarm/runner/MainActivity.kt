package com.telefarm.runner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

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

@Composable
fun TelefarmTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            background = Color.Black,
            surface = Color(0xFF111111),
            surfaceVariant = Color(0xFF1B1B1B),
            primary = Color.White,
            onPrimary = Color.Black,
            onBackground = Color.White,
            onSurface = Color.White,
            onSurfaceVariant = Color(0xFFBDBDBD)
        ),
        content = content
    )
}

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun TelefarmApp() {

    var screen by remember {
        mutableStateOf("home")
    }

    Scaffold(
        containerColor = Color.Black,

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Telefarm",
                        fontWeight = FontWeight.Bold
                    )
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                )
            )
        }

    ) { padding ->

        when (screen) {

            "home" -> {
                HomeScreen(
                    modifier = Modifier.padding(padding),
                    onProjects = {
                        screen = "projects"
                    },
                    onOwner = {
                        screen = "owner"
                    }
                )
            }

            "projects" -> {
                ProjectsScreen(
                    modifier = Modifier.padding(padding),
                    onBack = {
                        screen = "home"
                    }
                )
            }

            "owner" -> {
                OwnerScreen(
                    modifier = Modifier.padding(padding),
                    onBack = {
                        screen = "home"
                    }
                )
            }
        }
    }
}

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

    val animatedAlpha by transition.animateFloat(
        initialValue = 0.65f,
        targetValue = 1f,

        animationSpec = infiniteRepeatable(
            animation = tween(900),
            repeatMode = RepeatMode.Reverse
        ),

        label = "alpha"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(20.dp),

        verticalArrangement =
            Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Bot Manager",

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
                    text = "Projects",
                    fontWeight =
                        FontWeight.SemiBold
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
                    onClick = onProjects,

                    modifier =
                        Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = "Open Projects"
                    )
                }
            }
        }

        Card(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .alpha(animatedAlpha)
                    .clickable {
                        onOwner()
                    },

            colors =
                CardDefaults.cardColors(
                    containerColor =
                        Color.White
                ),

            shape =
                RoundedCornerShape(22.dp)
        ) {

            Column(
                modifier =
                    Modifier.padding(20.dp)
            ) {

                Text(
                    text = "Owner",

                    color =
                        Color.Black,

                    fontWeight =
                        FontWeight.Bold
                )

                Spacer(
                    Modifier.height(4.dp)
                )

                Text(
                    text =
                        "Tap to view owner details",

                    color =
                        Color.DarkGray
                )
            }
        }
    }
}

@Composable
fun ProjectsScreen(
    modifier: Modifier,
    onBack: () -> Unit
) {

    val bots =
        listOf(
            "Bot 1",
            "Bot 2",
            "Bot 3"
        )

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(20.dp)
    ) {

        OutlinedButton(
            onClick = onBack
        ) {

            Text(
                text = "Back"
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
                    text = "Projects",

                    style =
                        MaterialTheme.typography.headlineSmall
                )

                Text(
                    text = "0 / 5 bots",

                    color =
                        Color(0xFFBDBDBD)
                )
            }

            Button(
                onClick = {}
            ) {

                Text(
                    text = "New Project"
                )
            }
        }

        Spacer(
            Modifier.height(18.dp)
        )

        LazyColumn(
            verticalArrangement =
                Arrangement.spacedBy(10.dp)
        ) {

            items(bots) { bot ->

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
                                .padding(18.dp)
                                .fillMaxWidth(),

                        horizontalArrangement =
                            Arrangement.SpaceBetween
                    ) {

                        Column {

                            Text(
                                text = bot,

                                fontWeight =
                                    FontWeight.SemiBold
                            )

                            Text(
                                text = "Stopped",

                                color =
                                    Color(0xFFBDBDBD)
                            )
                        }

                        Text(
                            text = "Open"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun OwnerScreen(
    modifier: Modifier,
    onBack: () -> Unit
) {

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(20.dp)
    ) {

        OutlinedButton(
            onClick = onBack
        ) {

            Text(
                text = "Back"
            )
        }

        Spacer(
            Modifier.height(30.dp)
        )

        Text(
            text = "Owner Details",

            style =
                MaterialTheme.typography.headlineMedium,

            fontWeight =
                FontWeight.Bold
        )

        Spacer(
            Modifier.height(20.dp)
        )

        Text(
            text = "Telefarm"
        )

        Spacer(
            Modifier.height(6.dp)
        )

        Text(
            text = "Owner: SKY_XYR",

            color =
                Color(0xFFBDBDBD)
        )

        Spacer(
            Modifier.height(24.dp)
        )

        Button(
            onClick = {},

            modifier =
                Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Contact Owner"
            )
        }
    }
}
