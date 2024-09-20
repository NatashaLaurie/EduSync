package org.company.app.presentation.ui.features.course_detail

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.company.app.presentation.utils.TextSnackbarContainer
import com.seiko.imageloader.rememberImagePainter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.company.app.domain.model.Course
import org.company.app.domain.model.Schedule
import org.company.app.presentation.model.ResourceUiState
import org.company.app.presentation.ui.common.ActionBarIcon
import org.company.app.presentation.ui.common.state.ManagementResourceUiState
import org.koin.core.parameter.parametersOf


data class CourseDetailsCallbacks(
    val onFavClick: () -> Unit,
    val onBackClick: () -> Unit,
    val onApplyClick: () -> Unit
)

class CourseDetailsScreen(
    private val course: Course,
) : Screen {
    override val key: ScreenKey = uniqueScreenKey


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val courseDetailViewModel =
            getScreenModel<CourseDetailsViewModel> { parametersOf(course) }

        val snackbarHostState = remember { SnackbarHostState() }
        var showSnackbar by remember { mutableStateOf(false) }
        var snackbarText by remember { mutableStateOf("") }

        val sheetState = rememberModalBottomSheetState()
        val scope = rememberCoroutineScope()
        var showBottomSheet by remember { mutableStateOf(false) }

        var name by remember { mutableStateOf("") }
        var surname by remember { mutableStateOf("") }
        var age by remember { mutableStateOf("") }
        var telephone by remember { mutableStateOf("") }


        val state by courseDetailViewModel.uiState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(key1 = Unit) {
            courseDetailViewModel.effect.collectLatest { effect ->
                when (effect) {
                    CourseDetailsContract.Effect.CourseAdded -> {
                        showSnackbar = true
                        snackbarText = "Курс добавлен в избранное"
                    }

                    CourseDetailsContract.Effect.CourseRemoved -> {
                        showSnackbar = true
                        snackbarHostState.showSnackbar("Курс удален из избранного")
                    }

                    CourseDetailsContract.Effect.BackNavigation -> navigator.pop()
                    CourseDetailsContract.Effect.BottomModalSheetVisible -> showBottomSheet = true
                }
            }
        }

        Surface {
            TextSnackbarContainer(
                snackbarText = snackbarText,
                showSnackbar = showSnackbar,
                onDismissSnackbar = {
                    showSnackbar = false
                    snackbarText = ""
                }
            ) {
                if (showBottomSheet) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            showBottomSheet = false
                        },
                        sheetState = sheetState
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text("Заполните форму", style = MaterialTheme.typography.headlineSmall)

                            Spacer(modifier = Modifier.height(16.dp))

                            OutlinedTextField(
                                value = name,
                                onValueChange = { name = it },
                                label = { Text("Имя") },
                                modifier = Modifier.fillMaxWidth(),
                                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            OutlinedTextField(
                                value = surname,
                                onValueChange = { surname = it },
                                label = { Text("Фамилия") },
                                modifier = Modifier.fillMaxWidth(),
                                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)

                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            OutlinedTextField(
                                value = age,
                                onValueChange = { age = it },
                                label = { Text("Возраст") },
                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            OutlinedTextField(
                                value = telephone,
                                onValueChange = { telephone = it },
                                label = { Text("Номер телефона") },
                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(16.dp))
                            Button(onClick = {
                                scope.launch { sheetState.hide() }.invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        showBottomSheet = false
                                    }
                                }
                            }) {
                                Text("Отправить заявку")
                            }
                        }
                    }
                }
                CourseDetails(
                    course,
                    favorite = state.isFavorite,
                    CourseDetailsCallbacks(
                        onBackClick = { courseDetailViewModel.setEvent(CourseDetailsContract.Event.OnBackPressed) },
                        onFavClick = {
                            courseDetailViewModel.setEvent(CourseDetailsContract.Event.OnFavoriteClick)
                        },
                        onApplyClick = { courseDetailViewModel.setEvent(CourseDetailsContract.Event.OnApplyClick) },
                    )
                )
            }
        }
    }
}

@Composable
fun CourseDetails(
    course: Course,
    favorite: ResourceUiState<Boolean>,
    callbacks: CourseDetailsCallbacks,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    var courseScroller by remember {
        mutableStateOf(CourseDetailsScroller(scrollState, Float.MIN_VALUE))
    }
    val transitionState =
        remember(courseScroller) { courseScroller.toolbarTransitionState }
    val toolbarState = courseScroller.getToolbarState(LocalDensity.current)
    val transition = updateTransition(transitionState, label = "")
    val toolbarAlpha = transition.animateFloat(
        transitionSpec = { spring(stiffness = Spring.StiffnessLow) }, label = ""
    ) { toolbarTransitionState ->
        if (toolbarTransitionState == ToolbarState.HIDDEN) 0f else 1f
    }
    val contentAlpha = transition.animateFloat(
        transitionSpec = { spring(stiffness = Spring.StiffnessLow) }, label = ""
    ) { toolbarTransitionState ->
        if (toolbarTransitionState == ToolbarState.HIDDEN) 1f else 0f
    }


    Box(
        modifier
            .fillMaxSize()
            .testTag("box")
    ) {
        CourseDetailsContent(
            scrollState = scrollState,
            course = course,
            imageHeight = with(LocalDensity.current) {
                val candidateHeight =
                    300.dp
                maxOf(candidateHeight, 1.dp)
            },
            onApplyClick = callbacks.onApplyClick,
            onNamePosition = { newNamePosition ->
                if (courseScroller.namePosition == Float.MIN_VALUE) {
                    courseScroller =
                        courseScroller.copy(namePosition = newNamePosition)
                }
            }
        ) { contentAlpha.value }
        CourseToolbar(
            toolbarState,
            course.courseName, favorite, callbacks,
            toolbarAlpha = { toolbarAlpha.value },
            contentAlpha = { contentAlpha.value }
        )

    }
}

@Composable
private fun CourseDetailsContent(
    scrollState: ScrollState,
    course: Course,
    onApplyClick: () -> Unit,
    imageHeight: Dp,
    onNamePosition: (Float) -> Unit,
    contentAlpha: () -> Float,
) {
    Column(Modifier.verticalScroll(scrollState)) {

        CourseImage(
            course = course,
            imageHeight = imageHeight,
            modifier = Modifier
                .alpha(contentAlpha())
        )

        CourseInformation(
            course = course,
            onNamePosition = { onNamePosition(it) },
            onApplyClick = onApplyClick
        )
    }
}

@Composable
private fun CourseImage(
    course: Course,
    imageHeight: Dp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .fillMaxWidth()
            .height(imageHeight)
    ) {
        Image(
            painter = rememberImagePainter(course.imageUrl),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
private fun CourseToolbar(
    toolbarState: ToolbarState,
    courseName: String,
    favorite: ResourceUiState<Boolean>,
    callbacks: CourseDetailsCallbacks,
    toolbarAlpha: () -> Float,
    contentAlpha: () -> Float
) {
    if (toolbarState.isShown) {
        CourseDetailsToolbar(
            courseName = courseName,
            onBackClick = callbacks.onBackClick,
            onFavClick = callbacks.onFavClick,
            onApplyClick = callbacks.onApplyClick,
            favorite = favorite,
            modifier = Modifier.alpha(toolbarAlpha())
        )
    } else {
        CourseHeaderActions(
            onBackClick = callbacks.onBackClick,
            onFavClick = callbacks.onFavClick,
            onApplyClick = callbacks.onApplyClick,
            favorite = favorite,
            modifier = Modifier.alpha(contentAlpha())
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CourseDetailsToolbar(
    courseName: String,
    onBackClick: () -> Unit,
    onApplyClick: () -> Unit,
    favorite: ResourceUiState<Boolean>,
    onFavClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface {
        TopAppBar(
            modifier = modifier
                .statusBarsPadding()
                .background(color = MaterialTheme.colorScheme.surface),
            title = {
                Row {
                    IconButton(
                        onBackClick,
                        Modifier.align(Alignment.CenterVertically)
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = ""
                        )
                    }
                    Text(
                        text = courseName,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    )
                    IconButton(
                        onFavClick,
                        Modifier
                            .align(Alignment.CenterVertically)
                    ) {
                        ManagementResourceUiState(
                            resourceUiState = favorite,
                            successView = {
                                ActionBarIcon(
                                    onClick = onFavClick,
                                    icon = if (it) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
                                )
                            },
                            loadingView = {
                                ActionBarIcon(
                                    enabled = false,
                                    onClick = { onApplyClick() },
                                    icon = Icons.Filled.Favorite
                                )
                            },
                            onCheckAgain = {},
                            onTryAgain = {}
                        )
                    }
                    IconButton(
                        onApplyClick,
                        Modifier.align(Alignment.CenterVertically)
                    ) {
                        Icon(
                            Icons.Filled.Edit,
                            contentDescription = ""
                        )
                    }
                }
            }
        )
    }
}

@Composable
private fun CourseHeaderActions(
    onBackClick: () -> Unit,
    onFavClick: () -> Unit,
    onApplyClick: () -> Unit,
    favorite: ResourceUiState<Boolean>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(
                top = 12.dp,
                start = 8.dp,
                end = 8.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val iconModifier = Modifier
            .sizeIn(
                maxWidth = 32.dp,
                maxHeight = 32.dp
            )
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = CircleShape
            )

        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .padding(start = 12.dp)
                .then(iconModifier)
        ) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = ""
            )
        }
        Row(
            modifier = Modifier
                .padding(end = 12.dp)
        ) {
            IconButton(
                onClick = onFavClick,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .then(iconModifier)
            ) {
                ManagementResourceUiState(
                    resourceUiState = favorite,
                    successView = {
                        ActionBarIcon(
                            onClick = onFavClick,
                            icon = if (it) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
                        )
                    },
                    loadingView = {
                        ActionBarIcon(
                            enabled = false,
                            onClick = {},
                            icon = Icons.Filled.Favorite
                        )
                    },
                    onCheckAgain = {},
                    onTryAgain = {}
                )
            }
            IconButton(
                onClick = onApplyClick,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .then(iconModifier)
            ) {
                Icon(
                    Icons.Filled.Edit,
                    contentDescription = ""
                )
            }

        }

    }
}

@Composable
private fun CourseInformation(
    course: Course,
    onApplyClick: () -> Unit,
    onNamePosition: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(
            bottom = 84.dp,
            top = 24.dp,
            start = 24.dp,
            end = 24.dp
        )
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = course.courseName,
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 16.dp
                )
                .align(Alignment.CenterHorizontally)
                .onGloballyPositioned { onNamePosition(it.positionInWindow().y) }
        )
        Button(
            onClick = { onApplyClick() },
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
            modifier = Modifier
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 16.dp
                )
                .align(Alignment.CenterHorizontally)
                .onGloballyPositioned { onNamePosition(it.positionInWindow().y) }
        ) {
            Icon(
                Icons.Filled.Edit,
                contentDescription = "description",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("Записаться")
        }
        Box(
            Modifier
                .align(Alignment.CenterHorizontally)
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 16.dp
                )
        ) {
            Column(Modifier.fillMaxWidth()) {
                Text(
                    text = "cрок обучения ${course.programDuration}",
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    textAlign = TextAlign.Center,
                    text = "Для детей ${course.studentsAgeFrom} - ${course.studentsAgeTo} лет",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
        AboutSection(course)

    }
}


@Composable
fun AboutSection(
    course: Course
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(4.dp))
        CourseProperty("Отделение", course.department)
        CourseProperty("Программа", course.program)
        CourseProperty("Срок обучения ", course.programDuration)
        CourseProperty("Оплата", course.paymentTerm)
        Text(
            text = "Расписание",
            modifier = Modifier,
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(4.dp))
        course.schedule.forEach {
            Column {
                ScheduleItem(it)
            }
        }
    }
}

@Composable
fun ScheduleItem(schedule: Schedule) {
    val lessons = listOf(
        "ПН" to schedule.mondayLessons,
        "ВТ" to schedule.tuesdayLessons,
        "СР" to schedule.wednesdayLessons,
        "ЧТ" to schedule.thursdayLessons,
        "ПТ" to schedule.fridayLessons,
        "СБ" to schedule.saturdayLessons,
        "ВС" to schedule.sundayLessons
    )

    Column {
        Text(
            text = schedule.groupName,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.tertiary
        )

        lessons.forEach { (day, lessons) ->
            if (lessons.isNotEmpty() && !lessons.contains("null")) {
                DayLessonsRow(day = day, lessons = lessons)
            }
        }
    }
}

@Composable
fun DayLessonsRow(day: String, lessons: String) {
    Row {
        Text(
            text = day,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = lessons.replace("+", ", "),
            style = MaterialTheme.typography.bodyLarge
        )
    }
    Spacer(modifier = Modifier.width(12.dp))
}


@Composable
fun CourseProperty(label: String, value: String) {
    Column(modifier = Modifier.padding(bottom = 16.dp)) {
        HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            textAlign = TextAlign.Justify,
            text = value,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

