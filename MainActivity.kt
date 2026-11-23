package com.monday.assistant

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

class MainActivity : ComponentActivity(), TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null
    private var ttsReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tts = TextToSpeech(this, this)

        setContent {
            MondayScreen(
                onSpeak = {
                    speak("At your service, Sir. MONDAY systems are online.")
                },
                onStatus = {
                    speak("All core interface systems are operational, Sir.")
                }
            )
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            ttsReady = true
            tts?.language = Locale.UK
            tts?.setSpeechRate(0.88f)
        }
    }

    private fun speak(message: String) {
        if (ttsReady) {
            tts?.speak(
                message,
                TextToSpeech.QUEUE_FLUSH,
                null,
                "MONDAY_GREETING"
            )
        }
    }

    override fun onDestroy() {
        tts?.stop()
        tts?.shutdown()
        super.onDestroy()
    }
}

@Composable
private fun MondayScreen(
    onSpeak: () -> Unit,
    onStatus: () -> Unit
) {
    val cyan = Color(0xFF39D8FF)
    val bg = Color(0xFF030A14)

    var message by remember {
        mutableStateOf("At your service, Sir.")
    }

    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = cyan,
            background = bg,
            surface = Color(0xFF081827),
            onBackground = Color.White,
            onSurface = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFF061B2D),
                            bg,
                            Color.Black
                        )
                    )
                )
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "MONDAY",
                    color = cyan,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Light,
                    letterSpacing = 7.sp
                )

                Text(
                    "PERSONAL AI SYSTEM",
                    color = Color(0xFF8AAFC4),
                    fontSize = 10.sp,
                    letterSpacing = 3.sp
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(218.dp)
                        .background(
                            Brush.radialGradient(
                                listOf(
                                    Color(0x5539D8FF),
                                    Color(0x2239D8FF),
                                    Color.Transparent
                                )
                            ),
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        Modifier
                            .size(164.dp)
                            .background(
                                Color(0xFF0B2638),
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            Modifier
                                .size(142.dp)
                                .background(
                                    Color(0xFF0E3D59),
                                    CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "M",
                                color = cyan,
                                fontSize = 68.sp,
                                fontWeight = FontWeight.Light
                            )
                        }
                    }
                }

                Spacer(Modifier.height(28.dp))

                Text(
                    "SYSTEM ONLINE",
                    color = cyan,
                    fontSize = 12.sp,
                    letterSpacing = 3.sp
                )

                Spacer(Modifier.height(14.dp))

                Text(
                    message,
                    color = Color.White,
                    fontSize = 17.sp
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        message = "At your service, Sir."
                        onSpeak()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                ) {
                    Text(
                        "SUMMON MONDAY",
                        letterSpacing = 2.sp
                    )
                }

                Spacer(Modifier.height(12.dp))

                OutlinedButton(
                    onClick = {
                        message =
                            "All core interface systems are operational, Sir."
                        onStatus()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text("SYSTEM STATUS")
                }

                Spacer(Modifier.height(18.dp))

                Text(
                    "ONLINE CORE  •  VOICE MODULE READY",
                    color = Color(0xFF64869A),
                    fontSize = 9.sp,
                    letterSpacing = 1.sp
                )
            }
        }
    }
}
