package com.example.affirmations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.affirmations.data.Datasource
import com.example.affirmations.model.Affirmation
import com.example.affirmations.ui.theme.AffirmationsTheme

class MainActivity : ComponentActivity() {

    private lateinit var list: List<Affirmation>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            list = Datasource().loadAffirmations()
            AffirmationApp(list)
        }
    }
}

@Composable
fun AffirmationApp(list: List<Affirmation>) {
    AffirmationsTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            LazyColumn {
                items(list) { affirmation ->
                    AffirmationCard(
                        affirmation = affirmation
                    )
                }
            }
        }
    }
}

@Composable
fun AffirmationCard(
    affirmation: Affirmation,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .background(colorResource(R.color.background_color))
        ) {
            Image(
                painter = painterResource(affirmation.imageResourceId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Text(
                text = LocalContext.current.getString(affirmation.stringResourceId),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
            )
        }
    }
}



@Preview(
    showBackground = true
)
@Composable
fun AffirmationAppPreview() {
    AffirmationsTheme {
        Surface(
            modifier = Modifier
        ) {
            AffirmationCard(
                affirmation = Affirmation(
                    R.string.affirmation1,
                    R.drawable.image1
                )
            )
        }
    }
}