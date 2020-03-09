package com.example.pokedex

import android.app.Application
import com.example.pokedex.network.Api

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Api.INSTANCE = Api(this)
    }
}