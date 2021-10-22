package com.example.coinflip;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
            }
            else {
                veresegDb++;
                dobasokDb++;
                vereseg.setText("Vereség: " + veresegDb);
            }
            dobasok.setText("Dobások: " + dobasokDb);
            if (veresegDb >= 3 || gyozelemDb >= 3) {
                vege();
            }
        });

        btnIras.setOnClickListener(view -> {
            if (ermeKep() == 1) {
                gyozelemDb++;
                dobasokDb++;
                gyozelem.setText("Győzelem: " + gyozelemDb);
            }
            else {
                veresegDb++;
                dobasokDb++;
                vereseg.setText("Vereség: " + veresegDb);
            }
            dobasok.setText("Dobások: " + dobasokDb);
            if (veresegDb >= 3 || gyozelemDb >= 3) {
                vege();
            }
        });
    }

    private int ermeKep() {
        int veletlenKep = random.nextInt(2);

        if (veletlenKep == 0) {
            erme.setImageResource(R.drawable.heads);

            Toast.makeText(getApplicationContext(), "Fej!", Toast.LENGTH_SHORT).show();
            return 0;
        }
        else {
            erme.setImageResource(R.drawable.tails);

            Toast.makeText(getApplicationContext(), "Írás!", Toast.LENGTH_SHORT).show();
            return 1;
        }
    }

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
                .setPositiveButton("Igen", (dialog, which) -> ujJatek())
                .setNegativeButton("Nem", (dialog, which) -> finish());

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
    }
}
