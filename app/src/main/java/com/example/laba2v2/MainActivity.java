package com.example.laba2v2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText days, moneys;
    SeekBar sDays, sMoneys;
    CheckBox more10;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        days = findViewById(R.id.days);//взятие с xml элементов
        moneys = findViewById(R.id.avarqageInput);
        sDays = findViewById(R.id.daysSlider);
        sMoneys = findViewById(R.id.inputSlider);
        more10 = findViewById(R.id.checkBox);
        result = findViewById(R.id.result);

        sDays.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {  //выставление смены текста при проводе ползунка
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser)
                    days.setText(String.valueOf(progress));
                calculate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        days.addTextChangedListener(new TextWatcher() { //выставление смены позиции ползунка при изменении текста
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = days.getText().toString();
                text = text.length() != 0 ? text : "0";

                sDays.setProgress(Integer.parseInt(text));
                calculate();
            }
        });
        sMoneys.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {  //выставление смены текста при проводе ползунка
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser)
                    moneys.setText(String.valueOf(progress));
                calculate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        moneys.addTextChangedListener(new TextWatcher() { //выставление смены позиции ползунка при изменении текста
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = moneys.getText().toString();
                text = text.length() != 0 ? text : "0";

                sMoneys.setProgress(Integer.parseInt(text));
                calculate();
            }
        });
    }

    public void reselect(View view) {
        calculate();
    }

    private void calculate() {
        double money = sMoneys.getProgress();
        double day = sDays.getProgress();

        double result = more10.isChecked() ?
                money * day :
                money * (day * 0.8 + (((day - 12) < 0) ? 0 : day - 12) * 0.2);
        this.result.setText(String.format("Размер выплаты: %.2f", result));
    }
}