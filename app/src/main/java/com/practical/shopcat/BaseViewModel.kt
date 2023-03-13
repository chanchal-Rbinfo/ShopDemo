package com.practical.shopcat

import androidx.lifecycle.ViewModel
import com.practical.shopcat.errors.ErrorManager
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    lateinit var errorManager: ErrorManager
}
