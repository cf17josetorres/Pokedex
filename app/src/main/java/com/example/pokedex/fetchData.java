package com.example.pokedex;


import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.ahmadrosid.svgloader.SvgLoader;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import static com.example.pokedex.MainActivity.todospoke;

public class fetchData extends AsyncTask<Void, Void, Void> {

    protected String data = "";
    protected String results = "";
    protected ArrayList<String> strTypes; // Create an ArrayList object
    protected String pokSearch, tipo;
    public int primerpoke;
    private Pokemonn poke;
    JSONObject jObject = null;
    String img = "";
    String typeName = "";
    String typeObj="";

    private static FirebaseDatabase firebaseDatabase;

    public fetchData(String typeName, String tipo) {
        this.typeName = typeName;
        this.tipo = tipo;

    }

    public fetchData(String pokSearch, int primerpoke) {
        this.pokSearch = pokSearch;
        this.primerpoke = primerpoke;
        this.poke = new Pokemonn();
        strTypes = new ArrayList<String>();
//        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    public Pokemonn getPoke(){
        return poke;
    }

    public void setPoke(Pokemonn poke) {
        this.poke = poke;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = null;
            //Make API connection
            if (primerpoke == 0){
                url = new URL("https://pokeapi.co/api/v2/pokemon/" + pokSearch);
                Log.i("Aquí encuentras","a los pokemon "+ pokSearch);
            } else if (primerpoke == 1) {
                url = new URL("https://pokeapi.co/api/v2/pokemon/)" + pokSearch);
                Log.i("logtest", "https://pokeapi.co/api/v2/pokemon/" + pokSearch);
            }

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            // Read API results
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sBuilder = new StringBuilder();

            // Build JSON String
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                sBuilder.append(line + "\n");
            }

            inputStream.close();
            data = sBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid){
        if (primerpoke == 0) {
            try {
                jObject = new JSONObject(data);

                // Get JSON name, height, weight
                results += "Name: " + jObject.getString("name").toUpperCase() + "\n" +
                        "Height: " + jObject.getString("height") + "\n" +
                        "Weight: " + jObject.getString("weight") + "\n" +
                        "ID: " + jObject.getString("id") + "\n" +
                        "Base: " + jObject.getString("base_experience");

                setPoke(new Pokemonn(jObject.getString("name"),"https://pokeapi.co/api/v2/pokemon/" + pokSearch));
                MainActivity.actual = new Pokemonn(jObject.getString("name"),"https://pokeapi.co/api/v2/pokemon/" + pokSearch);
                Log.e("Poke" , this.poke.getName());

                // Get img SVG
                JSONObject sprites = new JSONObject(jObject.getString("sprites"));
                JSONObject other = new JSONObject(sprites.getString("other"));
                JSONObject dream_world = new JSONObject(other.getString("dream_world"));
                img  = dream_world.getString("front_default");

                // Get type/types
                JSONArray types = new JSONArray(jObject.getString("types"));
                for(int i=0; i<types.length(); i++){
                    JSONObject type = new JSONObject(types.getString(i));
                    JSONObject type2  = new JSONObject(type.getString("type"));
                    strTypes.add(type2.getString("name"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // Set info
            imagenespoke(primerpoke);
            MainActivity.txtDisplay.setText(this.results);
        } else if (primerpoke == 1) {
            try {
                jObject = new JSONObject(data);
                // Get type/types
                JSONArray types = new JSONArray(jObject.getString("pokemon"));
                for(int i=0; i<types.length(); i++){
                    JSONObject type = new JSONObject(types.getString(i));
                    JSONObject type2  = new JSONObject(type.getString("pokemon"));
                    strTypes.add(type2.getString("name"));
                }


                todospoke = strTypes.size();
                Log.i("types:"+primerpoke, ""+strTypes.get(primerpoke));
                setPoke(new Pokemonn(jObject.getString("name"),"https://pokeapi.co/api/v2/pokemon/" + pokSearch));
                MainActivity.actual = new Pokemonn(jObject.getString("name"),"https://pokeapi.co/api/v2/pokemon/" + pokSearch);

                Log.e("Poke" , this.poke.getName());

                results += String.valueOf(strTypes.get(primerpoke));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // Set info
            imagenespoke(primerpoke);
            MainActivity.txtDisplay.setText(this.results);
        }
    }

    public void imagenespoke(int primerpoke) {
        if (primerpoke == 0) {
            //        // Set main img
            SvgLoader.pluck()
                    .with(MainActivity.act)
                    .load(img, MainActivity.imgPok);
            // Set img types
            for(int i=0; i<strTypes.size(); i++){
                MainActivity.imgType[i].setImageResource(MainActivity.act.getResources().getIdentifier(strTypes.get(i), "drawable", MainActivity.act.getPackageName()));
            }
        } else if (primerpoke == 1) {
            //        // Set main img
            SvgLoader.pluck()
                    .with(MainActivity.act)
                    .load(img, MainActivity.imgPok);
            // Set img types
            MainActivity.imgType[0].setImageResource(MainActivity.act.getResources().getIdentifier(strTypes.get(0), "drawable", MainActivity.act.getPackageName()));
            MainActivity.imgType[1].setImageResource(MainActivity.act.getResources().getIdentifier(strTypes.get(1), "drawable", MainActivity.act.getPackageName()));
            /*if(strTypes.size()==1){
                    MainActivity.imgType[0].setImageResource(MainActivity.act.getResources().getIdentifier(strTypes.get(0), "drawable", MainActivity.act.getPackageName()));
                } else {
                     MainActivity.imgType[1].setImageResource(MainActivity.act.getResources().getIdentifier(strTypes.get(1), "drawable", MainActivity.act.getPackageName()));
                }*/
            }
    }
}
