package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static Activity act;
    public static TextView txtDisplay;
    public static ImageView imgPok;
    Button derecha, izquierda;
    MediaPlayer mPlayer;
    Button btnmusica, btnmusica2, btnmusica3;
    public int iterador;
    public int iteradordepoke = 0;
    public int primerpoke = 0;
    public int elijepoke;
    public static int todospoke;
    public String searchpoke;
    private String[] typepoke;
    TextView tBotonVideo;
    VideoView video;
    ///////////////////
    protected ArrayList<String> strTypes; // Create an ArrayList object

    DatabaseReference databaseReference;

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

        strTypes = new ArrayList<String>();
        //databaseReference = FirebaseDatabase.getInstance().getReference().child("String");

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
            }
        });

        derecha.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (iterador >= 1118) {
                    iterador = 1118;
                    String pokSearch = String.valueOf(iterador);
                    fetchData process = new fetchData(pokSearch,elijepoke);
                    process.execute();
                } else {
                    iterador++;
                    String pokSearch = String.valueOf(iterador);
                    fetchData process = new fetchData(pokSearch,elijepoke);
                    process.execute();
                }
            }
        });

        izquierda.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (iterador >= 1118) {
                    iterador = 1118;
                    String pokSearch = String.valueOf(iterador);
                    fetchData process = new fetchData(pokSearch,elijepoke);
                    process.execute();
                } else {
                    iterador--;
                    String pokSearch = String.valueOf(iterador);
                    fetchData process = new fetchData(pokSearch,elijepoke);
                    process.execute();
                }
            }
        });

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
                        fetchData strTypes = new fetchData("type/" + searchpoke,elijepoke);
                        strTypes.execute();

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
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