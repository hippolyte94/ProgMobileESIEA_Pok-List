package com.example.pokemonlistapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonlistapp.Common.Common
import com.example.pokemonlistapp.Common.ItemOffsetDecoration

import com.example.pokemonlistapp.Retrofit.RetrofitPokeList
import com.example.pokemonlistapp.Retrofit.RetrofitClient
import com.example.pokemonlistapp.adaptateur.PokemonListAdaptateur
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PokemonList : Fragment() {

    internal var compositeDisposable = CompositeDisposable()
    internal var retrofitPokeList:RetrofitPokeList
    internal lateinit var recycler_view: RecyclerView

    init {
        val retrofit = RetrofitClient().instance
        retrofitPokeList = retrofit.create(RetrofitPokeList::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val itemView = inflater.inflate(R.layout.fragment_pokemon_list, container, false)
        recycler_view = itemView.findViewById(R.id.pokemon_recyclerView) as RecyclerView
        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = GridLayoutManager(activity,2)
        val itemDecoration = ItemOffsetDecoration(requireActivity(),R.dimen.spacing)
        recycler_view.addItemDecoration(itemDecoration)
        fetchData()

        return itemView
    }

    private fun fetchData() {
        compositeDisposable.add(retrofitPokeList.listPokemon
            .subscribeOn((Schedulers.io()))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ pokemonDex ->
                Common.pokemonList = pokemonDex.pokemon!!
                val adapter = PokemonListAdaptateur(requireActivity(),Common.pokemonList)
                recycler_view.adapter = adapter

            }
        );
    }

}