package com.hary.myappproyekhary1901010046;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class VolumeBola extends AppCompatActivity implements View.OnClickListener {
    private EditText edtJari;
    private Button btnCalculate;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume_bola);

        edtJari = findViewById(R.id.edt_jari);
        btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);

        btnCalculate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_calculate) {
            String inputJari = edtJari.getText().toString().trim();
            boolean isEmptyFields = false;
            boolean isInvalidDouble = false;
            if (TextUtils.isEmpty(inputJari)) {
                isEmptyFields = true;
                edtJari.setError("Field ini tidak boleh kosong");
            }
            Double jari = toDouble(inputJari);
            if (jari == null) {
                isInvalidDouble = true;
                edtJari.setError("Field ini harus berupa nomer yang valid");
            }

            if (!isEmptyFields && !isInvalidDouble) {
                double volume = 4 * (3.14 * jari * jari * jari)/3;
                tvResult.setText(String.valueOf(volume));
            }
        }
    }
    private Double toDouble(String str) {
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}