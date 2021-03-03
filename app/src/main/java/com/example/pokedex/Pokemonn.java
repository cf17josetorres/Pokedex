package com.example.pokedex;

public class Pokemonn {
    public String name;
    public String url;
    public String pokemon;

    public Pokemonn() {
    }

    public Pokemonn(String name, String url, String pokemon) {
        this.name = name;
        this.url = url;
        this.pokemon = pokemon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPokemon() {
        return pokemon;
    }

    public void setPokemon(String pokemon) {
        this.pokemon = pokemon;
    }
}
