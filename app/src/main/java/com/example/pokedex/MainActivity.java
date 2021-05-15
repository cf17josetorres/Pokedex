package com.example.pokedex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static Activity act;
    public static TextView txtDisplay;
    public static ImageView imgPok;
    Button derecha, izquierda;
    MediaPlayer mPlayer;
    Button btnmusica, btnmusica2, btnmusica3 , verfavoritoos;
    Button btnfavoritoo;
    public int iterador;
    public int iteradordepoke = 0;
    public int primerpoke = 0;
    public int elijepoke;
    public static int todospoke;
    public String searchpoke, tipo;
    private String[] typepoke;
    TextView tBotonVideo;
    VideoView video;
    public int busquepok;

    protected ArrayList<Pokemonn> strTypes; // Create an ArrayList object
    private String pantalla = "";

    Button mButtonSubirDatosFirebase;
    EditText mEditTextDatosnombre, mEditTextDatosurl, mEditTextDatospokemon;
    public static Pokemonn actual = new Pokemonn();
    public static ImageView [] imgType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        act = this;
        imgType = new ImageView[2];
        typepoke = getResources().getStringArray(R.array.pokemon_types);

        txtDisplay = findViewById(R.id.txtDisplay);
        imgPok = findViewById(R.id.imgPok);
        imgType[0] = findViewById(R.id.imgType0);
        imgType[1] = findViewById(R.id.imgType1);
        derecha = findViewById(R.id.btnRight);
        izquierda = findViewById(R.id.btnLeft);
        tBotonVideo = findViewById(R.id.tVideo);
        btnfavoritoo = findViewById(R.id.btnfavorito);
        verfavoritoos= findViewById(R.id.btnverfavoritos);

        strTypes = new ArrayList<Pokemonn>();

        mButtonSubirDatosFirebase = findViewById(R.id.btndp);
        mEditTextDatosnombre = findViewById(R.id.etNombre);
        mEditTextDatosurl = findViewById(R.id.etUrl);
        mEditTextDatospokemon = findViewById(R.id.etPokemon);
        //mDatabase = FirebaseDatabase.getInstance().getReference();

        //databaseReference = FirebaseDatabase.getInstance().getReference().child("Pokemonn");
        // instantiate in the onCreate method

        /*firebaseDatabase = FirebaseDatabase.getInstance();
        mRootreference = firebaseDatabase.getReference();
        mChildreference = mRootreference.child("Guardar en los POkemon");*/

        /*//Metodo para mostrar y ocultar el menú
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.apartado, menu);
            return true;
        }*/

        /*//Metodo para asignar las funciones correspondientes a las opciones
        public boolean onOptionItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == R.drawable.ic_favorite_white_24dp) {
                Toast.makeText(this, "Opción 1", Toast.LENGTH_SHORT).show();
            } else if (id == R.drawable.ic_favorite_border_white_24dp) {
                Toast.makeText(this, "Opción 1", Toast.LENGTH_SHORT).show();
            } else if (id == R.drawable.ic_share_white_24dp) {
                Toast.makeText(this, "Opción 2", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.salir) {
                Toast.makeText(this, "Opción 3", Toast.LENGTH_SHORT).show();
            }
            return super.onOptionsItemSelected(item);
        }*/

        iterador = 1;
        String pokSearch = String.valueOf(iterador);
        fetchData process = new fetchData(pokSearch, primerpoke);
        process.execute();

        ImageButton btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showTxtSearch();
            }
        });

        ImageButton btnTypes = findViewById(R.id.btnTypes);
        btnTypes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showTypeSearch();
                busquepoke(tipo);
            }
        });

        derecha.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (iterador >= 1118) {
                    iterador = 1118;
                    String pokSearch = String.valueOf(iterador);
                    fetchData process = new fetchData(pokSearch,elijepoke);
                    //Log.e("POke" , process.getPoke().getName());
                    process.execute();
                    //actual = process.getPoke();
                    Log.e("POke" , process.getPoke().getName());
                    //databaseReference.setValue(strTypes);
                } else {
                    iterador++;
                    String pokSearch = String.valueOf(iterador);
                    fetchData process = new fetchData(pokSearch,elijepoke);
                    //Log.e("POke" , process.getPoke().getName());
                    process.execute();
                    //actual = process.getPoke();
                    //databaseReference.setValue(strTypes);
                }
            }
        });

        izquierda.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (iterador >= 1118) {
                    iterador = 1118;
                    String pokSearch = String.valueOf(iterador);
                    fetchData process = new fetchData(pokSearch,elijepoke);
                    //databaseReference.setValue(strTypes);
                    //Log.e("POke" , process.getPoke().getName());
                    process.execute();
                    //actual = process.getPoke();
                    Log.e("POke" , process.getPoke().getName());
                } else {
                    iterador--;
                    String pokSearch = String.valueOf(iterador);
                    fetchData process = new fetchData(pokSearch,elijepoke);
                    //databaseReference.setValue(strTypes);
                    //Log.e("POke" , process.getPoke().getName());
                    process.execute();
                    //Log.e("POke" , process.getPoke().getName());
                    //actual = process.getPoke();
                }
            }
        });

        /*mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("logTest " ,""+dataSnapshot.getChildrenCount());
                strTypes.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Pokemonn btnfavorito = postSnapshot.getValue(Pokemonn.class);
                    strTypes.add(btnfavorito);
                    Log.i("logTest",btnfavorito.url);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Failed to read value
                Log.i("logTest", "Failed to read value.", databaseError.toException());
            }
        });
*/

        verfavoritoos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment verfavoritoss = new DetallePokemon();
                FragmentTransaction transactiontres = getSupportFragmentManager().beginTransaction();
                transactiontres.replace(R.id.contenedor,verfavoritoss);
                transactiontres.commit();
            }
        });

        btnfavoritoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference().child("Favoritos").push();
                if (actual != null) {
                    Log.e("Poke" , actual.getName() + "fav");
                    myRef.setValue(actual);
                }

                //pantalla = txtDisplay.getText().toString();
                //Pokemonn btnfavorito = new Pokemonn(btnfavoritoo.getText().toString());
                //strTypes.add(btnfavorito);
                //Map<String, Object> detallesPokemon = new HashMap<>();
                //detallesPokemon.put("btnfavorito", btnfavorito);
                //mDatabase.child("Pokemonn").push().setValue(detallesPokemon);
                //databaseReference.setValue(strTypes);
            }
        });

        /*mButtonSubirDatosFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = mEditTextDatosnombre.getText().toString();
                String url = mEditTextDatosurl.getText().toString();
                String pokemon = mEditTextDatospokemon.getText().toString();

                Map<String, Object> detallePokemon = new HashMap<>();
                detallePokemon.put("nombre", nombre);
                detallePokemon.put("url", url);
                detallePokemon.put("pokemon", pokemon);

                //mDatabase.child("Pokemonn").push().setValue(detallePokemon);
            }
        });*/

        /*public void writeNewUser(String name, String url, String pokemon, String s) {
            Pokemonn poke = new Pokemonn(name, url, pokemon, s);

            mDatabase.child("Pokemonn").child(name).setValue(poke);
        }*/

        btnmusica = (Button)findViewById(R.id.btnmusica);
        btnmusica.setOnClickListener(this);
        btnmusica2 = (Button)findViewById(R.id.btnmusica2);
        btnmusica2.setOnClickListener(this);
        btnmusica3 = (Button)findViewById(R.id.btnmusica3);
        btnmusica3.setOnClickListener(this);
        tBotonVideo.setOnClickListener(this);
    }

    public void showTxtSearch(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Search a Pokemon");

        final EditText input = new EditText(this);
        input.setHint("Pokemon");
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                primerpoke = 0;
                String pokSearch = input.getText().toString();
                fetchData process = new fetchData(pokSearch,primerpoke);
                process.execute();
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });
        alert.show();
    }

    public void showTypeSearch(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona el tipus de pokemon:")
                .setItems(R.array.pokemon_types, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        int typeName = which + 1;
                        Log.i("logtest", "" + typeName);
                        //fetchData strTypes = new fetchData("type/" + searchpoke,elijepoke);
                        //strTypes.execute();
                        fetchDataatyp typefetch = new fetchDataatyp(typepoke[typeName]);
                        typefetch.execute();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
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

    @Override
    public void onClick(View v) {
        if(mPlayer != null) {
            mPlayer.release();
        }
        int id = v.getId();
        switch (id){
            case R.id.btnmusica:
                mPlayer = MediaPlayer.create(this,R.raw.originalpokerap);
                break;
            case R.id.btnmusica2:
                mPlayer = MediaPlayer.create(this,R.raw.pokemonatrapalosya);
                break;
            case R.id.btnmusica3:
                mPlayer = MediaPlayer.create(this, R.raw.angelesfuimos);
                break;
            case R.id.tVideo:
                video.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.promo));
                videoo();
                break;
        }
        mPlayer.seekTo(0);
        mPlayer.start();
    }

    public void onPause(){
        super.onPause();
        //detiene la musica
        if(mPlayer != null) {
            mPlayer.release();
        }
    }

    public static Context getContext() {
        return getContext();
    }

    public void videoo(){
        Intent videoActivity = new Intent(getApplicationContext(), VideoActivity.class);
        startActivity(videoActivity);
    }
}