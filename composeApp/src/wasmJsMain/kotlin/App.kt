import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.skiko.loadBytesFromPath
import vishalportfolio.composeapp.generated.resources.*

@Composable
fun App() {
    MaterialTheme {
        Column(
            Modifier.fillMaxSize().background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val loadResources = remember { mutableStateOf(false) }
            LaunchedEffect(key1 = Unit) {
                loadAllResources()
                loadResources.value = true
            }


            AnimatedVisibility(
                loadResources.value
            ) {
                val state = rememberScrollState()
                Column(
                    Modifier.fillMaxWidth().verticalScroll(state),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    navigationBar(state)
                    Home()
                    workExperience()
                    TechStack()
                    Contact()
                    Spacer(modifier = Modifier.height(200.dp))
                }
            }

        }
    }
}

@Composable
private fun navigationBar(state: ScrollState) {
    var selectedPage by remember { mutableIntStateOf(0) }
    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            modifier = Modifier.clickable {
                selectedPage = 0
                scope.launch {
                    state.scrollTo(0)
                }
            },
            text = "HOME",
            fontSize = 20.sp,
            fontFamily = Utility.NothingDot,
            color = Color.White,
            textDecoration = if (selectedPage == 0) TextDecoration.Underline else TextDecoration.None
        )

        Spacer(Modifier.width(30.dp))

        Text(
            modifier = Modifier.clickable {
                selectedPage = 1
                scope.launch {
                    state.scrollTo(800)
                }
            },
            text = "ABOUT ME",
            fontSize = 20.sp,
            fontFamily = Utility.NothingDot,
            color = Color.White,
            textDecoration = if (selectedPage == 1) TextDecoration.Underline else TextDecoration.None
        )

        Spacer(Modifier.width(30.dp))

        Text(
            modifier = Modifier.clickable {
                selectedPage = 2
                scope.launch {
                    state.scrollTo(1200)
                }
            },
            text = "WORK",
            fontSize = 20.sp,
            fontFamily = Utility.NothingDot,
            color = Color.White,
            textDecoration = if (selectedPage == 2) TextDecoration.Underline else TextDecoration.None
        )

        Spacer(Modifier.width(30.dp))

        Text(
            modifier = Modifier.clickable {
                selectedPage = 3
                scope.launch {
                    state.scrollTo(state.maxValue - 100)
                }
            },
            text = "Skills",
            fontSize = 20.sp,
            fontFamily = Utility.NothingDot,
            color = Color.White,
            textDecoration = if (selectedPage == 3) TextDecoration.Underline else TextDecoration.None
        )

        Spacer(Modifier.width(30.dp))


        Text(
            modifier = Modifier.clickable {
                selectedPage = 4
                scope.launch {
                    state.scrollTo(state.maxValue)
                }
            },
            text = "CONTACT",
            fontSize = 20.sp,
            fontFamily = Utility.NothingDot,
            color = Color.White,
            textDecoration = if (selectedPage == 4) TextDecoration.Underline else TextDecoration.None
        )

        Spacer(Modifier.width(30.dp))
    }

}


@OptIn(ExperimentalResourceApi::class)
@Composable
fun Home() {
    Spacer(modifier = Modifier.height(50.dp))

    val rainbowColorsBrush = remember {
        Brush.sweepGradient(
            listOf(
                Color(0xFF9575CD),
                Color(0xFFBA68C8),
                Color(0xFFE57373),
                Color(0xFFFFB74D),
                Color(0xFFFFF176),
                Color(0xFFAED581),
                Color(0xFF4DD0E1),
                Color(0xFF9575CD)
            )
        )
    }

    Image(
        modifier = Modifier
            .padding(6.dp)
            .border(BorderStroke(2.dp, rainbowColorsBrush), CircleShape)
            .size(280.dp)
            .clip(CircleShape),
        painter = painterResource(Res.drawable.vishals_photo),
        contentDescription = "",
        contentScale = ContentScale.Fit
    )

    Spacer(modifier = Modifier.height(50.dp))

    Column(
        modifier = Modifier.width(600.dp)
    ) {
        Text(
            text = ">> About Me",
            fontFamily = Utility.NothingDot,
            color = Color.White,
            fontSize = 20.sp,
            textDecoration = TextDecoration.Underline
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "I am a Software Engineer with over 2+ years of experience seeking an opportunity to work in a challenging environment where I can demonstrate my skills, leverage my knowledge and intelligence, and contribute to the growth of the organization.",
            fontSize = 15.sp,
            color = Color.White
        )
    }

}

@Composable
fun workExperience() {

    Spacer(modifier = Modifier.height(80.dp))

    Column(
        modifier = Modifier.width(600.dp)
    ) {
        Text(
            text = ">> Work Experience",
            fontFamily = Utility.NothingDot,
            color = Color.White,
            fontSize = 20.sp,
            textDecoration = TextDecoration.Underline
        )
        Spacer(modifier = Modifier.height(50.dp))
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {

        val mirrArHeight = remember { mutableStateOf(0.dp) }
        val mangoHeight = remember { mutableStateOf(0.dp) }
        val rslHeight = remember { mutableStateOf(0.dp) }
        val density = LocalDensity.current
        Column(
            modifier = Modifier.padding(end = 30.dp),
            horizontalAlignment = Alignment.End
        ) {

            Box(
                modifier = Modifier.padding(top = mirrArHeight.value)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 13.dp, vertical = 2.dp),
                    text = "Jun 2023 - Nov 2023",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Serif,
                    color = Color.Black
                )
            }

            Spacer(Modifier.height(10.dp))


            Row(
                modifier = Modifier.width(400.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFF171b22))
                    .onGloballyPositioned {
                        with(density) {
                            mangoHeight.value = it.size.height.toDp()
                        }
                    }
            ) {
                val s = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontFamily = Utility.NothingDot,
                            color = Color.White,
                            fontSize = 20.sp,
                            textDecoration = TextDecoration.Underline
                        ),
                    ) {
                        append("MangoApps- Software Engineer - Pune")
                    }
                    append("\n\n")
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            fontSize = 15.sp,
                        )
                    ) {
                        append(
                            "• Developed data-driven features using native Android components and the Jetpack Compose library .\n" +
                                    "• Led the successful migration of multiple legacy features to a modern API, achieving significant enhancements in\n" +
                                    "performance and streamlining underlying logic. .\n" +
                                    "• I implemented DiffUtil and pagination in RecyclerView to enhance its performance\n" +
                                    "• Integrating them with comprehensive business logic that includes JSON encoding and decoding."
                        )
                    }
                }
                Text(
                    modifier = Modifier.padding(20.dp),
                    text = s,
                    color = Color.White
                )
            }
        }



        Box(
            modifier = Modifier.width(3.dp)
                .height(mirrArHeight.value + mangoHeight.value + rslHeight.value)
                .background(Color.White)
        )

        Column(
            modifier = Modifier.padding(start = 30.dp),
            horizontalAlignment = Alignment.Start
        ) {

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 13.dp, vertical = 2.dp),
                    text = "Nov 2023 - Present",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Serif,
                    color = Color.Black
                )
            }

            Spacer(Modifier.height(10.dp))

            Row(
                modifier = Modifier.width(400.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFF171b22))
                    .onGloballyPositioned {
                        with(density) {
                            mirrArHeight.value = it.size.height.toDp()
                        }
                    }
            ) {
                val s = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontFamily = Utility.NothingDot,
                            color = Color.White,
                            fontSize = 20.sp,
                            textDecoration = TextDecoration.Underline
                        ),
                    ) {
                        append("MirrAR- SDE-2 - bengaluru")
                    }
                    append("\n\n")
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            fontSize = 15.sp,
                        )
                    ) {
                        append(
                            "• Created an Android application centered on beauty augmented reality.\n" +
                                    "• Implemented the MVVM architecture and utilized technologies such as coroutines, Flow, Retrofit, Jetpack\n" +
                                    "Compose, Firebase Cloud Messaging (FCM) for push notifications, and Camera X.\n" +
                                    "• Created a custom impression library from scratch, which significantly enhanced the capability to track user\n" +
                                    "interactions and impressions.\n" +
                                    "• Handling the project from conception to launch as an individual contributor.\n" +
                                    "• The app gained initial traction with over 10,000 downloads on the Play Store"
                        )
                    }
                }
                Text(
                    modifier = Modifier.padding(20.dp),
                    text = s,
                    color = Color.White
                )
            }

            Spacer(Modifier.height(mangoHeight.value))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 13.dp, vertical = 2.dp),
                    text = "Oct 2021 - Jun 2023",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Serif,
                    color = Color.Black
                )
            }

            Row(
                modifier = Modifier.width(400.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFF171b22))
                    .onGloballyPositioned {
                        with(density) {
                            rslHeight.value = it.size.height.toDp()
                        }
                    }
            ) {
                val s = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontFamily = Utility.NothingDot,
                            color = Color.White,
                            fontSize = 20.sp,
                            textDecoration = TextDecoration.Underline
                        ),
                    ) {
                        append("Raja Software Labs - Software Engineer - bengaluru")
                    }
                    append("\n\n")
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            fontSize = 15.sp,
                        )
                    ) {
                        append(
                            "• I worked on a LinkedIn Android application that has 310 million daily active users.\n" +
                                    "• Designed and implemented multiple screens, adhering to both data layer and view layer specifications.\n" +
                                    "• Significantly increased code coverage by approximately 60 percent through the creation of test cases using Espresso and JUnit, thereby enhancing application stability.\n" +
                                    "• Developed and enhanced features for the LinkedIn Android application, focusing on the reporting fow.\n" +
                                    "• Engaged in a project utilizing Kotlin Multiplatform, Acquired skills in reactive programming with Jetpack Compose.\n"
                        )
                    }
                }
                Text(
                    modifier = Modifier.padding(20.dp),
                    text = s,
                    color = Color.White
                )
            }
        }

    }

}

@OptIn(ExperimentalResourceApi::class, ExperimentalLayoutApi::class)
@Composable
fun TechStack() {

    Spacer(modifier = Modifier.height(60.dp))

    Column(
        modifier = Modifier.width(600.dp)
    ) {

        Text(
            text = ">> Skills",
            fontFamily = Utility.NothingDot,
            color = Color.White,
            fontSize = 20.sp,
            textDecoration = TextDecoration.Underline
        )

        Divider(modifier = Modifier.height(3.dp).weight(1f).background(Color.White))

        FlowRow(
            modifier = Modifier.fillMaxWidth().padding(top = 30.dp),
            horizontalArrangement = Arrangement.Center,
        ) {

            Image(
                modifier = Modifier.size(50.dp),
                painter = painterResource(Res.drawable.java),
                contentDescription = ""
            )

            Spacer(modifier = Modifier.width(40.dp))


            Image(
                modifier = Modifier.size(50.dp),
                painter = painterResource(Res.drawable.kotlin_new),
                contentDescription = ""
            )

            Spacer(modifier = Modifier.width(40.dp))

            Image(
                modifier = Modifier.size(50.dp).clip(CircleShape).background(Color.White),
                painter = painterResource(Res.drawable.github),
                contentDescription = ""
            )

            Spacer(modifier = Modifier.width(40.dp))

            Image(
                modifier = Modifier.size(50.dp),
                painter = painterResource(Res.drawable.android_icon),
                contentDescription = ""
            )

            Spacer(modifier = Modifier.width(40.dp))

            Image(
                modifier = Modifier.size(50.dp),
                painter = painterResource(Res.drawable.go),
                contentDescription = ""
            )

        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Contact() {

    Spacer(modifier = Modifier.height(60.dp))

    Column(
        modifier = Modifier.width(600.dp)
    ) {
        Text(
            text = ">> Cantact Me",
            fontFamily = Utility.NothingDot,
            fontSize = 20.sp,
            textDecoration = TextDecoration.Underline,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.clickable {
                sendEmail()
            }
        ) {
            Image(
                modifier = Modifier.size(30.dp),
                painter = painterResource(Res.drawable.gmail),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                modifier = Modifier,
                text = "khedekarvishal1999@gmail.com",
                fontSize = 14.sp,
                fontFamily = FontFamily.Serif,
                color = Color.White
            )
        }


        Spacer(modifier = Modifier.height(10.dp))


        Row(
            modifier = Modifier.clickable {
                copyToClipboard("7249641610")
            }
        ) {
            Image(
                modifier = Modifier.size(30.dp),
                painter = painterResource(Res.drawable.call),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "7249641610",
                fontSize = 14.sp,
                fontFamily = Utility.NothingDot,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.clickable {
                navigateToUrlInSameTab("https://www.linkedin.com/in/vishal-khedekar-0b2b5a179/")
            }
        ) {
            Image(
                modifier = Modifier.size(30.dp),
                painter = painterResource(Res.drawable.linkedin),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "link",
                fontSize = 14.sp,
                fontFamily = Utility.NothingDot,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.clickable {
                navigateToUrlInSameTab("https://github.com/vishalx4")
            }
        ) {
            Image(
                modifier = Modifier.size(30.dp).clip(CircleShape).background(Color.White),
                painter = painterResource(Res.drawable.github),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "link",
                fontSize = 14.sp,
                fontFamily = Utility.NothingDot,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        //https://www.linkedin.com/in/vishal-khedekar-0b2b5a179/

    }
}



fun navigateToUrlInSameTab(url: String) {
    js("window.location.href = url")
}

fun copyToClipboard(phoneNumber: String) {
//    js(
//        "phoneNumber.select();\n" +
//            "  phoneNumber.setSelectionRange(0, 99999); // For mobile devices\n" +
//            "\n" +
//            "   // Copy the text inside the text field\n" +
//            "  navigator.clipboard.writeText(phoneNumber.value);\n" +
//            "\n"
//    )
}

fun sendEmail() {
    js("window.open('mailto:khedekarvishal1999@gmail.com');")
}


@Composable
fun Line() {
    Box(
        modifier = Modifier.width(3.dp).height(70.dp).background(Color.White)
    )

    Box(
        modifier = Modifier.size(20.dp)
            .clip(CircleShape)
            .background(Color.White)
    )
}


private suspend fun loadAllResources() {

    val regular = loadResource("nothing_dot.ttf")
    Utility.NothingDot = FontFamily(
        androidx.compose.ui.text.platform.Font(
            identity = "NothingDotRegular",
            data = regular,
            weight = FontWeight.Normal
        )
    )
}

private suspend fun loadResource(path: String): ByteArray {
    return loadBytesFromPath(path)
}











