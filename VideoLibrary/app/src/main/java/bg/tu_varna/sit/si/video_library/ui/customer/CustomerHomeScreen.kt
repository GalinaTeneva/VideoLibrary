package bg.tu_varna.sit.si.video_library.ui.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import bg.tu_varna.sit.si.video_library.data.entities.Customer
import bg.tu_varna.sit.si.video_library.ui.theme.VideoLibraryTheme

fun fakeData(): List<Customer> {
    return listOf(
        Customer(
        customerId = 1,
        personalId = "2658745896",
        name = "Vasil Popov",
        address = "Varna, Shipka str."
    ),
        Customer(
            customerId = 2,
            personalId = "8436958428",
            name = "Mario Kostov",
            address = "Varna, Plovdiv str."
        )
    )
}

@Composable
fun Customer(
    customer: Customer,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = customer.name,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
            )
            Row {
                Text(
                    text = "Personal ID: "
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = customer.personalId
                )
            }
            Row {
                Text(
                    text = "Address:"
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = customer.address
                )
            }
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Rented Movies")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomerPreview() {
    VideoLibraryTheme {
        Customer(fakeData()[0])
    }
}