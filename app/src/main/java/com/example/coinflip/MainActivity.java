package com.example.coinflip;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button btnFej, btnIras;
    private ImageView erme;
    private TextView dobasok, gyozelem, vereseg;
    private Random random;
    private int gyozelemDb, dobasokDb, veresegDb;
    private AlertDialog.Builder alertDialogVege;
    private String szoveg;
    private ObjectAnimator flipAnimation1, flipAnimation2, flipAnimation3, flipAnimation4, flipAnimation5;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        ujJatek();

        btnFej.setOnClickListener(view -> {
            if (ermeKep() == 0) {
                gyozelemDb++;
                dobasokDb++;
                gyozelem.setText("Győzelem: " + gyozelemDb);
                dobasok.setText("Dobások: " + dobasokDb);
            }
            else {
                veresegDb++;
                dobasokDb++;
                vereseg.setText("Vereség: " + veresegDb);
                dobasok.setText("Dobások: " + dobasokDb);
            }
            if (veresegDb >= 3 || gyozelemDb >= 3) {
                vege();
            }
        });

        btnIras.setOnClickListener(view -> {
            if (ermeKep() == 1) {
                gyozelemDb++;
                dobasokDb++;
                gyozelem.setText("Győzelem: " + gyozelemDb);
                dobasok.setText("Dobások: " + dobasokDb);
            }
            else {
                veresegDb++;
                dobasokDb++;
                vereseg.setText("Vereség: " + veresegDb);
                dobasok.setText("Dobások: " + dobasokDb);
            }
            if (veresegDb >= 3 || gyozelemDb >= 3) {
                vege();
            }
        });
    }

    private void forgas() {
        handler.postDelayed(kepcsereFej,1000);
        flipAnimation1.start();

        flipAnimation2.setStartDelay(1000);
        flipAnimation2.start();
        handler.postDelayed(kepcsereIras,1000);

        flipAnimation3.setStartDelay(2000);
        flipAnimation3.start();
        handler.postDelayed(kepcsereFej,2000);
    }

    private int ermeKep() {
        int veletlenKep = random.nextInt(2);
        forgas();

        if (veletlenKep == 0) {
            Toast.makeText(getApplicationContext(), "Fej!", Toast.LENGTH_SHORT).show();
            erme.setRotationY(0);
            return 0;
        }
        else {
            flipAnimation4.setStartDelay(3000);
            flipAnimation4.start();
            handler.postDelayed(kepcsereIras,4000);
            flipAnimation5.setStartDelay(4000);
            flipAnimation5.start();
            Toast.makeText(getApplicationContext(), "Írás!", Toast.LENGTH_SHORT).show();
            return 1;
        }
    }
    
    private Runnable kepcsereFej = new Runnable() {
        @Override
        public void run() {
            erme.setImageResource(R.drawable.heads);
        }
    };

    private Runnable kepcsereIras = new Runnable() {
        @Override
        public void run() {
            erme.setImageResource(R.drawable.tails);
        }
    };

    private void ujJatek() {
        gyozelemDb = 0;
        dobasokDb = 0;
        veresegDb = 0;
        gyozelem.setText("Győzelem: " + gyozelemDb);
        vereseg.setText("Vereség: " + veresegDb);
        dobasok.setText("Dobások: " + dobasokDb);
        erme.setImageResource(R.drawable.heads);
    }

    private void vege() {
        szoveg = "";
        if (gyozelemDb > veresegDb) {
            szoveg = "Győzelem";
        }
        else {
            szoveg = "Vereség";
        }
        alertDialogVege = new AlertDialog.Builder(MainActivity.this);

        alertDialogVege.setCancelable(false).setTitle(szoveg)
                .setMessage("Szeretne új játékot játszani?")
                .setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ujJatek();
                    }
                })
                .setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        AlertDialog dialog = alertDialogVege.create();
        dialog.show();
    }

    private void init() {
        btnFej = findViewById(R.id.fej);
        btnIras = findViewById(R.id.iras);
        erme = findViewById(R.id.kep);
        dobasok = findViewById(R.id.dobasok);
        gyozelem = findViewById(R.id.gyozelem);
        vereseg = findViewById(R.id.vereseg);
        random = new Random();
        flipAnimation1 = ObjectAnimator.ofFloat(erme, "rotationY", 0f, 90f);
        flipAnimation2 = ObjectAnimator.ofFloat(erme, "rotationY", 90f, 180f);
        flipAnimation3 = ObjectAnimator.ofFloat(erme, "rotationY", 270f, 360f);
        flipAnimation4 = ObjectAnimator.ofFloat(erme, "rotationY", erme.getRotationY(), 90f);
        flipAnimation5 = ObjectAnimator.ofFloat(erme, "rotationY", 90f, 180f);
        flipAnimation1.setDuration(1000);
        flipAnimation2.setDuration(1000);
        flipAnimation3.setDuration(1000);
        flipAnimation4.setDuration(1000);
        flipAnimation5.setDuration(1000);
        handler = new Handler();
    }
}
