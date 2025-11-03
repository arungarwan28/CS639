package com.example.circleapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.circleapp.ui.theme.CircleAppTheme
import com.example.circleapp.ui.theme.LamboWhite
import com.example.circleapp.ui.theme.NardoGrey
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import kotlin.math.PI
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CircleAppTheme {
                CircleCalculatorApp()
            }
        }
    }
}

@Composable
fun CircleCalculatorApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(navController = navController)
        }
        composable(
            route = "result/{radius}",
            arguments = listOf(navArgument("radius") { type = NavType.FloatType })
        ) { backStackEntry ->
            val radius = backStackEntry.arguments?.getFloat("radius") ?: 0f
            ResultScreen(navController = navController, radius = radius)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    var radiusInput by remember { mutableStateOf("") }
    var areaText by remember { mutableStateOf("") }
    var calculationText by remember { mutableStateOf("") }
    var radiusText by remember { mutableStateOf("") }
    var showMenu by remember { mutableStateOf(false) }
    var showAboutDialog by remember { mutableStateOf(false) }
    var showHelpDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val df = remember { DecimalFormat("#,##0.###") }

    fun clearResults() {
        areaText = ""
        calculationText = ""
        radiusText = ""
    }

    fun clearAll() {
        radiusInput = ""
        clearResults()
    }

    fun validateAndCalculate(input: String) {
        radiusInput = input
        val radius = input.toDoubleOrNull()

        if (input.isEmpty()) {
            clearResults()
            return
        }

        if (radius == null || radius <= 0.0) {
            clearResults()
            scope.launch {
                snackbarHostState.showSnackbar(context.getString(R.string.invalid_radius_snackbar))
            }
        } else {
            val area = PI * radius * radius
            val formattedArea = df.format(area)
            val formattedRadius = df.format(radius)

            radiusText = "Radius: $formattedRadius"
            areaText = "Area: $formattedArea"
            calculationText = "Calculations: \u03C0 * $formattedRadius * $formattedRadius = $formattedArea"
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(Icons.Default.MoreVert, contentDescription = stringResource(R.string.more), tint = MaterialTheme.colorScheme.onPrimary)
                    }
                    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                        DropdownMenuItem(text = { Text(stringResource(R.string.reset)) }, onClick = {
                            clearAll()
                            showMenu = false
                        })
                        DropdownMenuItem(text = { Text(stringResource(R.string.about)) }, onClick = {
                            showAboutDialog = true
                            showMenu = false
                        })
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val shareText = if (areaText.isNotEmpty()) {
                    "$radiusText\n$calculationText\n$areaText"
                } else {
                    context.getString(R.string.share_no_result)
                }
                shareText(context, shareText)
            }) {
                Icon(Icons.Default.Share, contentDescription = stringResource(R.string.share))
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.circlehomesc),
                    contentDescription = stringResource(R.string.circle_icon_description)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = radiusInput,
                    onValueChange = { validateAndCalculate(it) },
                    label = { Text(stringResource(R.string.enter_radius)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f),
                    trailingIcon = {
                        if (radiusInput.isNotEmpty()) {
                            IconButton(onClick = { clearAll() }) {
                                Icon(Icons.Default.Clear, contentDescription = stringResource(R.string.clear_input))
                            }
                        }
                    }
                )
                TextButton(onClick = { showHelpDialog = true }) {
                    Text(stringResource(R.string.help))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        val currentRadius = radiusInput.toDoubleOrNull() ?: 0.0
                        val newRadius = currentRadius + 1
                        validateAndCalculate(newRadius.toString())
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = NardoGrey,
                        contentColor = LamboWhite
                    )
                ) {
                    Text(stringResource(R.string.plus_one_radius))
                }
                Button(
                    onClick = {
                        val randomRadius = Random.nextInt(1, 21).toFloat()
                        navController.navigate("result/$randomRadius")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = NardoGrey,
                        contentColor = LamboWhite
                    )
                ) {
                    Text(stringResource(R.string.random_radius))
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                if (areaText.isNotEmpty()) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = radiusText, fontSize = 20.sp)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = calculationText, fontSize = 20.sp)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = areaText, fontSize = 32.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }

    if (showAboutDialog) {
        AlertDialog(
            onDismissRequest = { showAboutDialog = false },
            title = { Text(stringResource(R.string.about_dialog_title)) },
            text = { Text(stringResource(R.string.about_dialog_message)) },
            confirmButton = {
                TextButton(onClick = { showAboutDialog = false }) {
                    Text(stringResource(R.string.ok))
                }
            }
        )
    }

    if (showHelpDialog) {
        AlertDialog(
            onDismissRequest = { showHelpDialog = false },
            text = { Text(stringResource(R.string.help_toast)) },
            confirmButton = {
                TextButton(onClick = { showHelpDialog = false }) {
                    Text(stringResource(R.string.ok))
                }
            }
        )
    }
}

fun shareText(context: Context, text: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
    }
    context.startActivity(Intent.createChooser(intent, context.getString(R.string.share_via)))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(navController: NavController, radius: Float) {
    val area = PI * radius * radius
    val df = remember { DecimalFormat("#,##0.###") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.result_screen_title)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.back), tint = MaterialTheme.colorScheme.onPrimary)
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(stringResource(R.string.radius_label_detailed, radius))
            Spacer(modifier = Modifier.height(16.dp))
            Text(stringResource(R.string.area_label_detailed, df.format(area)), fontSize = 48.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(stringResource(R.string.formula))
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = { navController.popBackStack() }) {
                Text(stringResource(R.string.back))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    CircleAppTheme {
        MainScreen(navController = rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    CircleAppTheme {
        ResultScreen(navController = rememberNavController(), radius = 10f)
    }
}
