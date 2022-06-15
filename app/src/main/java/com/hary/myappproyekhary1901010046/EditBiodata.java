package com.hary.myappproyekhary1901010046;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
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

public class EditBiodata extends AppCompatActivity {

    DatabaseHelper helper;
    EditText TxNomor, TxNama, TxTempatLahir, TxTanggal, TxAlamat;
    Spinner SpJK;
    long id;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_biodata);

        helper = new DatabaseHelper(this);

        id = getIntent().getLongExtra(DatabaseHelper.row_id, 0);


        TxNomor = (EditText) findViewById(R.id.txNomor_Edit);
        TxNama = (EditText) findViewById(R.id.txNama_Edit);
        TxTempatLahir = (EditText) findViewById(R.id.txTempatLahir_Edit);
        TxTanggal = (EditText) findViewById(R.id.txTglLahir_Edit);
        TxAlamat = (EditText) findViewById(R.id.txAlamat_Edit);
        SpJK = (Spinner)findViewById(R.id.spJK_Edit);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        TxTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });
        getData();

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

    private void getData(){
        Cursor cursor = helper.oneData(id);
        if(cursor.moveToFirst()){
            @SuppressLint("Range") String nomor = cursor.getString(cursor.getColumnIndex(DatabaseHelper.row_nomor));
            @SuppressLint("Range") String nama = cursor.getString(cursor.getColumnIndex(DatabaseHelper.row_nama));
            @SuppressLint("Range") String tempatLahir = cursor.getString(cursor.getColumnIndex(DatabaseHelper.row_tempatLahir));
            @SuppressLint("Range") String jk = cursor.getString(cursor.getColumnIndex(DatabaseHelper.row_jk));
            @SuppressLint("Range") String tanggal = cursor.getString(cursor.getColumnIndex(DatabaseHelper.row_tglLahir));
            @SuppressLint("Range") String alamat = cursor.getString(cursor.getColumnIndex(DatabaseHelper.row_alamat));

            TxNomor.setText(nomor);
            TxNama.setText(nama);

            if (jk.equals("Laki-laki")){
                SpJK.setSelection(0);
            }else if (jk.equals("Perempun")){
                SpJK.setSelection(1);
            }
            TxTempatLahir.setText(tempatLahir);
            TxTanggal.setText(tanggal);
            TxAlamat.setText(alamat);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@Nullable MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_edit:
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
                    Toast.makeText(EditBiodata.this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else {
                    helper.updateData(values, id);
                    Toast.makeText(EditBiodata.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
        switch (item.getItemId()){
            case R.id.delete_edit:
                AlertDialog.Builder builder = new AlertDialog.Builder(EditBiodata.this);
                builder.setMessage("Data ini akan dihapus");
                builder.setCancelable(true);
                builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        helper.deleteData(id);
                        Toast.makeText(EditBiodata.this, "Data Terhapus", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}