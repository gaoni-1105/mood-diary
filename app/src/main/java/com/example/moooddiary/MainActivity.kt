package com.example.moooddiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import com.example.moooddiary.ui.theme.MooodDiaryTheme
import java.util.*

val PastelPink = Color(0xFFFFB6C1)
val PastelBlue = Color(0xFFADD8E6)
val PastelYellow = Color(0xFFFFFACD)
val PastelGreen = Color(0xFF98FB98)
val PastelPurple = Color(0xFFE6E6FA)
val PastelPeach = Color(0xFFFFDAB9)

@Composable
fun MoodFace(mood: Mood, size: Float = 60f) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .drawBehind {
                val center = Offset(this.size.width / 2, this.size.height / 2)
                val radius = this.size.minDimension / 2
                //compose의 drawBehind 기능을 활용하여 5가지 감정 상태에 대한 표정을 그렸음

                // 머리통
                drawCircle(
                    color = mood.color,
                    radius = radius,
                    center = center
                )

                // 머리통 테두리
                drawCircle(
                    color = Color.White.copy(alpha = 0.3f),
                    radius = radius,
                    center = center,
                    style = Stroke(width = 3.dp.toPx())
                )

                val eyeY = center.y - radius * 0.2f
                val eyeDistance = radius * 0.35f

                when (mood) {
                    Mood.VERY_HAPPY -> {
                        // 눈
                        drawArc(
                            color = Color(0xFF5D4E37),
                            startAngle = 0f,
                            sweepAngle = 180f,
                            useCenter = false,
                            topLeft = Offset(center.x - eyeDistance - radius * 0.15f, eyeY - radius * 0.15f),
                            size = androidx.compose.ui.geometry.Size(radius * 0.3f, radius * 0.3f),
                            style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
                        )
                        drawArc(
                            color = Color(0xFF5D4E37),
                            startAngle = 0f,
                            sweepAngle = 180f,
                            useCenter = false,
                            topLeft = Offset(center.x + eyeDistance - radius * 0.15f, eyeY - radius * 0.15f),
                            size = androidx.compose.ui.geometry.Size(radius * 0.3f, radius * 0.3f),
                            style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
                        )
                        // 입
                        drawArc(
                            color = Color(0xFF5D4E37),
                            startAngle = 0f,
                            sweepAngle = 180f,
                            useCenter = false,
                            topLeft = Offset(center.x - radius * 0.4f, center.y + radius * 0.1f),
                            size = androidx.compose.ui.geometry.Size(radius * 0.8f, radius * 0.5f),
                            style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
                        )
                    }
                    Mood.HAPPY -> {
                        // 눈
                        drawCircle(
                            color = Color(0xFF5D4E37),
                            radius = radius * 0.12f,
                            center = Offset(center.x - eyeDistance, eyeY)
                        )
                        drawCircle(
                            color = Color(0xFF5D4E37),
                            radius = radius * 0.12f,
                            center = Offset(center.x + eyeDistance, eyeY)
                        )
                        // 입
                        drawArc(
                            color = Color(0xFF5D4E37),
                            startAngle = 0f,
                            sweepAngle = 180f,
                            useCenter = false,
                            topLeft = Offset(center.x - radius * 0.35f, center.y + radius * 0.15f),
                            size = androidx.compose.ui.geometry.Size(radius * 0.7f, radius * 0.4f),
                            style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
                        )
                    }
                    Mood.NEUTRAL -> {
                        // 눈
                        drawCircle(
                            color = Color(0xFF5D4E37),
                            radius = radius * 0.12f,
                            center = Offset(center.x - eyeDistance, eyeY)
                        )
                        drawCircle(
                            color = Color(0xFF5D4E37),
                            radius = radius * 0.12f,
                            center = Offset(center.x + eyeDistance, eyeY)
                        )
                        // 입
                        drawLine(
                            color = Color(0xFF5D4E37),
                            start = Offset(center.x - radius * 0.3f, center.y + radius * 0.3f),
                            end = Offset(center.x + radius * 0.3f, center.y + radius * 0.3f),
                            strokeWidth = 3.dp.toPx(),
                            cap = StrokeCap.Round
                        )
                    }
                    Mood.SAD -> {
                        // 눈
                        drawCircle(
                            color = Color(0xFF5D4E37),
                            radius = radius * 0.12f,
                            center = Offset(center.x - eyeDistance, eyeY)
                        )
                        drawCircle(
                            color = Color(0xFF5D4E37),
                            radius = radius * 0.12f,
                            center = Offset(center.x + eyeDistance, eyeY)
                        )
                        // 입
                        drawArc(
                            color = Color(0xFF5D4E37),
                            startAngle = 180f,
                            sweepAngle = 180f,
                            useCenter = false,
                            topLeft = Offset(center.x - radius * 0.35f, center.y + radius * 0.15f),
                            size = androidx.compose.ui.geometry.Size(radius * 0.7f, radius * 0.4f),
                            style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
                        )
                    }
                    Mood.ANGRY -> {
                        // 눈썹
                        drawLine(
                            color = Color(0xFF5D4E37),
                            start = Offset(center.x - eyeDistance - radius * 0.15f, eyeY - radius * 0.15f),
                            end = Offset(center.x - eyeDistance + radius * 0.15f, eyeY - radius * 0.05f),
                            strokeWidth = 3.dp.toPx(),
                            cap = StrokeCap.Round
                        )
                        drawLine(
                            color = Color(0xFF5D4E37),
                            start = Offset(center.x + eyeDistance - radius * 0.15f, eyeY - radius * 0.05f),
                            end = Offset(center.x + eyeDistance + radius * 0.15f, eyeY - radius * 0.15f),
                            strokeWidth = 3.dp.toPx(),
                            cap = StrokeCap.Round
                        )
                        // 눈
                        drawCircle(
                            color = Color(0xFF5D4E37),
                            radius = radius * 0.1f,
                            center = Offset(center.x - eyeDistance, eyeY + radius * 0.05f)
                        )
                        drawCircle(
                            color = Color(0xFF5D4E37),
                            radius = radius * 0.1f,
                            center = Offset(center.x + eyeDistance, eyeY + radius * 0.05f)
                        )
                        // 입
                        drawLine(
                            color = Color(0xFF5D4E37),
                            start = Offset(center.x - radius * 0.25f, center.y + radius * 0.35f),
                            end = Offset(center.x + radius * 0.25f, center.y + radius * 0.35f),
                            strokeWidth = 4.dp.toPx(),
                            cap = StrokeCap.Round
                        )
                    }
                }
            }
    )
}

data class SimpleDate( //날짜 데이터 클래스
    val year: Int,
    val month: Int,
    val day: Int
) {
    override fun equals(other: Any?): Boolean {
        if (other !is SimpleDate) return false
        return year == other.year && month == other.month && day == other.day
    }

    override fun hashCode(): Int {
        return year * 10000 + month * 100 + day
    }

    fun toDisplayString(): String = "${month}월 ${day}일"
}

data class MoodEntry( //moodEntry 데이터 클래스
    val date: SimpleDate, //언제 기록했는지
    val mood: Mood, //어떤 기분
    val note: String = "" //추가 메모
)

enum class Mood(val emoji: String, val color: Color, val label: String) {
    VERY_HAPPY("😄", PastelYellow, "아주 좋아요"),
    HAPPY("😊", PastelGreen, "좋아요"),
    NEUTRAL("😐", PastelBlue, "보통이에요"),
    SAD("😢", PastelPurple, "슬퍼요"),
    ANGRY("😠", PastelPink, "화나요")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MooodDiaryTheme {
                MoodDiaryApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoodDiaryApp() {
    var selectedTab by remember { mutableStateOf(0) }
    var moodEntries by remember { mutableStateOf<Map<SimpleDate, MoodEntry>>(emptyMap()) }
    //중복 방지를 위해 map구조를 활용함. 빠른 검색도 가능하고, 특정 날짜 기록 확인도 쉬움(지능 이슈로 구현 못함)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "나의 기분 일기",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PastelPeach
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color.White
            ) {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Icon(Icons.Default.DateRange, "달력") },
                    label = { Text("달력") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = PastelPink,
                        selectedTextColor = PastelPink,
                        indicatorColor = PastelPink.copy(alpha = 0.2f)
                    )
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Icon(Icons.Default.Info, "통계") },
                    label = { Text("통계") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = PastelBlue,
                        selectedTextColor = PastelBlue,
                        indicatorColor = PastelBlue.copy(alpha = 0.2f)
                    )
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFFFFAF0))
        ) {
            when (selectedTab) {
                0 -> CalendarScreen(
                    moodEntries = moodEntries,
                    onMoodSaved = { date, mood, note ->
                        moodEntries = moodEntries + (date to MoodEntry(date, mood, note))
                    }
                )
                1 -> StatisticsScreen(moodEntries = moodEntries.values.toList())
            }
        }
    }
}

@Composable
fun CalendarScreen(
    moodEntries: Map<SimpleDate, MoodEntry>,
    onMoodSaved: (SimpleDate, Mood, String) -> Unit
) {
    val calendar = Calendar.getInstance()
    var currentYear by remember { mutableStateOf(calendar.get(Calendar.YEAR)) }
    var currentMonth by remember { mutableStateOf(calendar.get(Calendar.MONTH)) }
    var selectedDate by remember { mutableStateOf<SimpleDate?>(null) }
    var showMoodDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 월 네비게이션
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                if (currentMonth == 0) {
                    currentMonth = 11
                    currentYear--
                } else {
                    currentMonth--
                }
            }) {
                Icon(Icons.Default.KeyboardArrowLeft, "이전 달", tint = PastelPink)
            }

            Text(
                text = "${currentYear}년 ${currentMonth + 1}월",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5D4E37)
            )

            IconButton(onClick = {
                if (currentMonth == 11) {
                    currentMonth = 0
                    currentYear++
                } else {
                    currentMonth++
                }
            }) {
                Icon(Icons.Default.KeyboardArrowRight, "다음 달", tint = PastelPink)
            }
        }

        // 요일 헤더
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("일", "월", "화", "수", "목", "금", "토").forEach { day ->
                Text(
                    text = day,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = if (day == "일") PastelPink else if (day == "토") PastelBlue else Color.Gray,
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 달력 그리드
        CalendarGrid(
            year = currentYear,
            month = currentMonth,
            moodEntries = moodEntries,
            onDateClick = { date ->
                selectedDate = date
                showMoodDialog = true
            }
        )
    }

    // 기분 입력 다이얼로그
    if (showMoodDialog && selectedDate != null) {
        MoodInputDialog(
            date = selectedDate!!,
            currentEntry = moodEntries[selectedDate],
            onDismiss = { showMoodDialog = false },
            onSave = { mood, note ->
                onMoodSaved(selectedDate!!, mood, note)
                showMoodDialog = false
            }
        )
    }
}

@Composable
fun CalendarGrid(
    year: Int,
    month: Int,
    moodEntries: Map<SimpleDate, MoodEntry>,
    onDateClick: (SimpleDate) -> Unit
) {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, 1)

    val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1 // 0 = 일요일

    val today = Calendar.getInstance()
    val todayDate = SimpleDate(
        today.get(Calendar.YEAR),
        today.get(Calendar.MONTH),
        today.get(Calendar.DAY_OF_MONTH)
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        contentPadding = PaddingValues(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // 빈 칸 추가
        items(firstDayOfWeek) {
            Box(modifier = Modifier.aspectRatio(1f))
        }

        // 날짜 표시
        items(daysInMonth) { day ->
            val date = SimpleDate(year, month, day + 1)
            val moodEntry = moodEntries[date]
            val isToday = date == todayDate

            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        when {
                            isToday -> PastelYellow.copy(alpha = 0.3f)
                            moodEntry != null -> moodEntry.mood.color.copy(alpha = 0.3f)
                            else -> Color.White
                        }
                    )
                    .clickable { onDateClick(date) }
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${day + 1}",
                        fontSize = 12.sp,
                        fontWeight = if (isToday) FontWeight.Bold else FontWeight.Normal,
                        color = Color(0xFF5D4E37)
                    )
                    if (moodEntry != null) {
                        Box(modifier = Modifier.size(20.dp)) {
                            MoodFace(mood = moodEntry.mood, size = 20f)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MoodInputDialog(
    date: SimpleDate,
    currentEntry: MoodEntry?,
    onDismiss: () -> Unit,
    onSave: (Mood, String) -> Unit
) {
    var selectedMood by remember { mutableStateOf(currentEntry?.mood ?: Mood.NEUTRAL) }
    var note by remember { mutableStateOf(currentEntry?.note ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "${date.month + 1}월 ${date.day}일의 기분",
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 400.dp)
            ) {
                Text("오늘의 기분은 어떠셨나요?", fontSize = 14.sp, color = Color.Gray)

                Spacer(modifier = Modifier.height(16.dp))

                // 기분 선택 (2줄로 표시)
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Mood.values().take(3).forEach { mood ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(
                                        if (selectedMood == mood)
                                            mood.color.copy(alpha = 0.3f)
                                        else
                                            Color.Transparent
                                    )
                                    .clickable { selectedMood = mood }
                                    .padding(8.dp)
                            ) {
                                MoodFace(mood = mood, size = 50f)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = mood.label,
                                    fontSize = 10.sp,
                                    color = Color(0xFF5D4E37),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Spacer(modifier = Modifier.weight(0.5f))
                        Mood.values().drop(3).forEach { mood ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(
                                        if (selectedMood == mood)
                                            mood.color.copy(alpha = 0.3f)
                                        else
                                            Color.Transparent
                                    )
                                    .clickable { selectedMood = mood }
                                    .padding(8.dp)
                            ) {
                                MoodFace(mood = mood, size = 50f)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = mood.label,
                                    fontSize = 10.sp,
                                    color = Color(0xFF5D4E37),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        Spacer(modifier = Modifier.weight(0.5f))
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 메모 입력 창
                OutlinedTextField(
                    value = note,
                    onValueChange = { note = it },
                    label = { Text("메모 (선택사항)") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 3,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = selectedMood.color,
                        focusedLabelColor = selectedMood.color
                    )
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { onSave(selectedMood, note) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = selectedMood.color
                )
            ) {
                Text("저장", color = Color.Black)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("취소")
            }
        },
        containerColor = Color.White
    )
}

@Composable
fun StatisticsScreen(moodEntries: List<MoodEntry>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "기분 통계",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5D4E37),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (moodEntries.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "📝",
                        fontSize = 64.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "아직 기록된 기분이 없어요",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "달력에서 기분을 기록해보세요!",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
        } else {
            // 기분별 통계
            val moodCounts = moodEntries.groupingBy { it.mood }.eachCount()
            val totalEntries = moodEntries.size

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "총 ${totalEntries}일 기록됨",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5D4E37),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Mood.values().forEach { mood ->
                        val count = moodCounts[mood] ?: 0
                        val percentage = if (totalEntries > 0) (count * 100f / totalEntries) else 0f

                        MoodStatItem(
                            mood = mood,
                            count = count,
                            percentage = percentage
                        )

                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }

            // 최근 기록 창
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "최근 기록",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5D4E37),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    val sortedEntries = moodEntries.sortedWith(
                        compareByDescending<MoodEntry> { it.date.year }
                            .thenByDescending { it.date.month }
                            .thenByDescending { it.date.day }
                    ).take(5)

                    sortedEntries.forEach { entry ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(modifier = Modifier.size(40.dp)) {
                                MoodFace(mood = entry.mood, size = 40f)
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = entry.date.toDisplayString(),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                if (entry.note.isNotEmpty()) {
                                    Text(
                                        text = entry.note,
                                        fontSize = 12.sp,
                                        color = Color.Gray,
                                        maxLines = 1
                                    )
                                }
                            }
                        }
                        if (entry != sortedEntries.last()) {
                            HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MoodStatItem(mood: Mood, count: Int, percentage: Float) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.size(30.dp)) {
                MoodFace(mood = mood, size = 30f)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = mood.label,
                fontSize = 14.sp,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "${count}일 (${percentage.toInt()}%)",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5D4E37)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        LinearProgressIndicator(
            progress = { percentage / 100f },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = mood.color,
            trackColor = mood.color.copy(alpha = 0.2f)
        )
    }
}