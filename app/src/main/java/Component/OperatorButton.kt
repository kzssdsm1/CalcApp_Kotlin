package Component

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OperatorButton(
    modifier: Modifier,
    operatorStr: String,
    onTap: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxHeight()
            .padding(all = 4.dp),
        onClick = onTap
    ) {
        Text(
            text = operatorStr,
            fontSize = 30.sp
        )
    }
}