package bg.tu_varna.sit.si.video_library.ui.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bg.tu_varna.sit.si.video_library.R
import bg.tu_varna.sit.si.video_library.data.entities.Customer
import bg.tu_varna.sit.si.video_library.di.AppViewModelProvider
import bg.tu_varna.sit.si.video_library.ui.common.GenericHomeScreenBody
import bg.tu_varna.sit.si.video_library.ui.navigation.VideoLibraryTopAppBar

//fun fakeData(): List<Customer> {
//    return listOf(
//        Customer(
//        customerId = 1,
//        personalId = "2658745896",
//        name = "Vasil Popov",
//        address = "Varna, Shipka str."
//    ),
//        Customer(
//            customerId = 2,
//            personalId = "8436958428",
//            name = "Mario Kostov",
//            address = "Varna, Plovdiv str."
//        )
//    )
//}

@Composable
fun CustomerHomeScreen(
    onBackClick: () -> Unit,
    onMenuItemClick: (Int) -> Unit,
    viewModel: CustomersHomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val customersHomeUiState by viewModel.customerHomeUiState.collectAsState()

    Scaffold(
        topBar = {
            VideoLibraryTopAppBar(
                title = stringResource(R.string.customers),
                showBackButton = true,
                onBackClick = onBackClick,
                onMenuItemClick = onMenuItemClick
            )
        }
    ) {
            innerPadding ->
        GenericHomeScreenBody(
            itemList = customersHomeUiState.customersList,
            emptyMessage = stringResource(R.string.no_customers_message),
            modifier = Modifier.padding(innerPadding),
            isLoading = customersHomeUiState.isLoading,
            itemListContent = { customersList ->
                CustomersList(customersList = customersList)
            }
        )
    }
}

@Composable
fun CustomersList(
    customersList: List<Customer>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(customersList) { item ->
            Customer(
                customer = item)
        }
    }
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
                    text = stringResource(R.string.customer_personal_ID_txt)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = customer.personalId
                )
            }
            Row {
                Text(
                    text = stringResource(R.string.customer_address_txt)
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
                Text(text = stringResource(R.string.rented_movies))
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun CustomersListPreview() {
//    VideoLibraryTheme {
//        CustomersList(fakeData())
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun CustomerPreview() {
//    VideoLibraryTheme {
//        Customer(fakeData()[0])
//    }
//}