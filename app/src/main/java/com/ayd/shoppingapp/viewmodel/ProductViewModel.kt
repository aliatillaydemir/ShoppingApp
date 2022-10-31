package com.ayd.shoppingapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ayd.shoppingapp.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel


class ProductViewModel(application: Application): AndroidViewModel(application) {

        fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String,String> = HashMap()

        queries[Constants.QUERY_LIMIT] = "30"
        //queries[""] ...
        return queries
    }

}