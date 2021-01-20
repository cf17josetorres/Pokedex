package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

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

    public static ImageView [] imgType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        act = this;
        imgType = new ImageView[2];

        txtDisplay = findViewById(R.id.txtDisplay);
        imgPok = findViewById(R.id.imgPok);
        imgType[0] = findViewById(R.id.imgType0);
        imgType[1] = findViewById(R.id.imgType1);
        derecha = findViewById(R.id.btnRight);
        izquierda = findViewById(R.id.btnLeft);

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
                iterador = Integer.parseInt(pokSearch);
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
}