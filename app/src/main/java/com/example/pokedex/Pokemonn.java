package com.example.pokedex;

public class Pokemonn {
    public String name;
    public String url;
    public String pokemon;
    public String s;

    //public Pokemonn(String s) {
        //this.s = s;
    //}

    public Pokemonn(){
    }

    public Pokemonn(String name, String url) {
        this.name = name;
        this.url = url;
        this.pokemon = pokemon;
        this.s = s;
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
