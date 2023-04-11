package com.example.contact.ui.favorite_contact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contact.model.Contact
import com.example.contact.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: ContactRepository
) : ViewModel() {

    private val _favoriteContactsList = MutableStateFlow<List<Contact>>(emptyList())
    val favoriteContactsList = _favoriteContactsList


    init {
        getFavoriteContactList()
    }

    private fun getFavoriteContactList() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavoriteContacts().collect {
                _favoriteContactsList.value = it
            }
        }
    }
}