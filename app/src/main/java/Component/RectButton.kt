package Component

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RectButton(
    modifier: Modifier,
    buttonText: String,
    onTap: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxHeight()
            .padding(all = 8.dp),
        onClick = onTap
    ) {
        Text(
            text = buttonText,
            fontSize = 30.sp
        )
    }
}