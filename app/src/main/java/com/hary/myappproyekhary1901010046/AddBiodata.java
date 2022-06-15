package com.hary.myappproyekhary1901010046;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddBiodata extends AppCompatActivity {

    DatabaseHelper helper;
    EditText TxNomor, TxNama, TxTempatLahir, TxTanggal, TxAlamat;
    Spinner SpJK;
    long id;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_biodata);

        helper = new DatabaseHelper(this);

        id = getIntent().getLongExtra(DatabaseHelper.row_id, 0);

        TxNomor = (EditText)findViewById(R.id.txNomor_Add);
        TxNama = (EditText)findViewById(R.id.txNama_Add);
        TxTempatLahir = (EditText)findViewById(R.id.txTempatLahir_Add);
        TxTanggal = (EditText)findViewById(R.id.txTglLahir_Add);
        TxAlamat = (EditText)findViewById(R.id.txAlamat_Add);
        SpJK = (Spinner)findViewById(R.id.spJK_Add);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        TxTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });
    }

    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                TxTanggal.setText(dateFormatter.format(newDate.getTime()));
            }
        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@Nullable MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_add:
                String nomor = TxNomor.getText().toString().trim();
                String nama =TxNama.getText().toString().trim();
                String tempatLahir = TxTempatLahir.getText().toString().trim();
                String tanggal = TxTanggal.getText().toString().trim();
                String alamat = TxAlamat.getText().toString().trim();
                String jk = SpJK.getSelectedItem().toString().trim();

                ContentValues values = new ContentValues();
                values.put(DatabaseHelper.row_nomor, nomor);
                values.put(DatabaseHelper.row_nama, nama);
                values.put(DatabaseHelper.row_tempatLahir, tempatLahir);
                values.put(DatabaseHelper.row_tglLahir, tanggal);
                values.put(DatabaseHelper.row_alamat, alamat);
                values.put(DatabaseHelper.row_jk, jk);

                if (nomor.equals("") || nama.equals("") || tempatLahir.equals("") || tanggal.equals("") || alamat.equals("")){
                    Toast.makeText(AddBiodata.this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else {
                    helper.insertData(values);
                    Toast.makeText(AddBiodata.this, "Data Tersimpan", Toast.LENGTH_SHORT).show(); //00.36.24
                    finish();
                }
        }
        return super.onOptionsItemSelected(item);
    }
}