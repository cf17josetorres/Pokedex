package com.example.pokedex;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class fetchDataatyp  extends AsyncTask<Void, Void, Void> {
    protected String data = "";
    protected String results = "";
    protected ArrayList<String> strTypes; // Create an ArrayList object
    protected String pokSearch;
    protected String tipo;
    public int primerpoke;
    JSONObject jObject = null;
    String img = "";
    String typeName = "";
    String typeObj="";
    public int busquepok;

    public fetchDataatyp(String tipo) {
        this.tipo = tipo;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = null;
            //Make API connection
            url = new URL("https://pokeapi.co/api/v2/type/" + tipo);
            Log.i("Aquí encuentras","a los pokemon "+ tipo);

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
    protected void onPostExecute(Void aVoid) {
        Log.i("Venga", "onPostExecute");
        if (primerpoke == 0) {
            try {
                jObject = new JSONObject(data);

                // Get type/types
                JSONArray types = jObject.getJSONArray("pokemon");
                for (int i = 0; i < types.length(); i++) {
                    JSONObject type = new JSONObject(types.getString(i));
                    Log.i("impremete joder","siiii " +type.getJSONObject("pokemon").getString("name"));

                    //type2 = new JSONObject(type.getString("pokemon"));
                    //strTypes.add(type2.getString("name"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            busquepoke(tipo);
        }
    }

    public void busquepoke(String tipo) {
        try {
            URL url = null;
            //Make API connection
            if (busquepok == 0) {
                url = new URL("https://pokeapi.co/api/v2/type/" + tipo);
                Log.i("Aquí encuentras", "los tipos de pokemon " + tipo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
