package com.example.pokemonlistapp.Retrofit

import com.example.pokemonlistapp.Model.Pokedex
import io.reactivex.Observable
import retrofit2.http.GET
import java.util.*

interface IPokemonList {
    @get:GET("pokedex.json")
    val listPokemon:Observable<Pokedex>

}