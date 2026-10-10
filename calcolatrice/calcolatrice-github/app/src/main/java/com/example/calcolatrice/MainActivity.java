package com.example.calcolatrice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView display1, display2, displayOperatore, displayMemoria;
    boolean equalJustClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Gli ID 'dysplay' sono mantenuti per compatibilita' con il tuo codice.
        display1 = findViewById(R.id.dysplay1);
        display2 = findViewById(R.id.dysplay2);
        displayOperatore = findViewById(R.id.dysplayOperatore);
        displayMemoria = findViewById(R.id.dysplayMemoria);

        display1.setText("");
        display2.setText("");
        displayOperatore.setText("");
        displayMemoria.setText("");
    }

    public void clickDigit(View v) {
        Button b = (Button) v;
        String valore = display1.getText().toString();
        if (equalJustClicked || valore.equals("Errore")) {
            valore = "";
        }
        if (valore.equals("0") && !b.getText().toString().equals("0")) {
            valore = "";
        }
        display1.setText(valore + b.getText());
        equalJustClicked = false;
    }

    public void clickDot(View v) {
        String s1 = display1.getText().toString();
        if (equalJustClicked || s1.equals("Errore")) {
            s1 = "";
            equalJustClicked = false;
        }
        if (!s1.contains(".")) {
            display1.setText(s1.isEmpty() ? "0." : s1 + ".");
        }
    }

    public void clickOp(View v) {
        Button b = (Button) v;
        String s1 = display1.getText().toString();
        String s2 = display2.getText().toString();
        String operatorePrecedente = displayOperatore.getText().toString();

        if (s1.isEmpty()) {
            if (!s2.isEmpty()) {
                displayOperatore.setText(b.getText());
            }
            return;
        }
        if (s1.equals("Errore")) return;

        // Se era gia' in corso un'operazione, calcola il parziale.
        if (!s2.isEmpty() && !operatorePrecedente.isEmpty()) {
            try {
                double risultato = calcola(Double.parseDouble(s2),
                        Double.parseDouble(s1), operatorePrecedente);
                if (Double.isNaN(risultato) || Double.isInfinite(risultato)) {
                    mostraErrore();
                    return;
                }
                s1 = formatta(risultato);
            } catch (IllegalArgumentException e) {
                mostraErrore();
                return;
            }
        }

        display2.setText(s1);
        display1.setText("");
        displayOperatore.setText(b.getText());
        equalJustClicked = false;
    }

    public void clickEq(View v) {
        String s1 = display1.getText().toString();
        String s2 = display2.getText().toString();
        String op = displayOperatore.getText().toString();

        if (s1.isEmpty() || s2.isEmpty() || op.isEmpty()) return;

        try {
            double f1 = Double.parseDouble(s1);
            double f2 = Double.parseDouble(s2);
            double res = calcola(f2, f1, op);
            if (Double.isNaN(res) || Double.isInfinite(res)) {
                mostraErrore();
                return;
            }
            display1.setText(formatta(res));
            display2.setText("");
            displayOperatore.setText("");
            equalJustClicked = true;
        } catch (IllegalArgumentException e) {
            mostraErrore();
        }
    }

    private double calcola(double primo, double secondo, String operatore) {
        switch (operatore.charAt(0)) {
            case '+': return primo + secondo;
            case '-':
            case '−': return primo - secondo;
            case '*':
            case '×': return primo * secondo;
            case '/':
            case '÷': return secondo == 0 ? Double.NaN : primo / secondo;
            default: throw new IllegalArgumentException("Operatore sconosciuto");
        }
    }

    private String formatta(double numero) {
        if (numero == Math.rint(numero) && Math.abs(numero) < 1e15) {
            return String.valueOf((long) numero);
        }
        return String.valueOf(numero);
    }

    private void mostraErrore() {
        display1.setText("Errore");
        display2.setText("");
        displayOperatore.setText("");
        equalJustClicked = true;
    }

    public void clickC(View v) {
        display1.setText("");
        display2.setText("");
        displayOperatore.setText("");
        equalJustClicked = false;
    }

    public void clickMS(View v) {
        String valore = display1.getText().toString();
        if (!valore.isEmpty() && !valore.equals("Errore")) {
            displayMemoria.setText(valore);
        }
    }

    public void clickMR(View v) {
        String memoria = displayMemoria.getText().toString();
        if (!memoria.isEmpty() && !memoria.equals("Errore")) {
            display1.setText(memoria);
            equalJustClicked = false;
        }
    }

    public void clickMC(View v) {
        displayMemoria.setText("");
    }
}
