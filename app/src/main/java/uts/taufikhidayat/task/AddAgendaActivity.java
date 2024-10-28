package uts.taufikhidayat.task;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddAgendaActivity extends AppCompatActivity {
    private EditText editTextNamaAgenda, editTextDeskripsi;
    private Spinner spinnerStatus;
    private Button buttonSimpan;
    private AgendaDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_agenda);

        // Inisialisasi view berdasarkan ID
        editTextNamaAgenda = findViewById(R.id.et_nama_agenda);
        editTextDeskripsi = findViewById(R.id.et_deskripsi);
        spinnerStatus = findViewById(R.id.spinner_status);
        buttonSimpan = findViewById(R.id.btn_simpan);

        // Inisialisasi DatabaseHelper dengan singleton pattern
        db = AgendaDatabaseHelper.getInstance(this);

        // Cek apakah elemen ditemukan
        checkViewElements();

        // Set listener untuk button simpan
        buttonSimpan.setOnClickListener(v -> {
            String title = editTextNamaAgenda.getText().toString().trim();
            String description = editTextDeskripsi.getText().toString().trim();
            String status = spinnerStatus.getSelectedItem().toString();

            // Validasi input sebelum menyimpan
            if (validateInput(title, description)) {
                // Simpan data ke database
                saveAgenda(title, description, status);
            }
        });
    }

    private void checkViewElements() {
        if (editTextNamaAgenda == null) {
            Toast.makeText(this, "editTextNamaAgenda not found", Toast.LENGTH_SHORT).show();
        }
        if (editTextDeskripsi == null) {
            Toast.makeText(this, "editTextDeskripsi not found", Toast.LENGTH_SHORT).show();
        }
        if (spinnerStatus == null) {
            Toast.makeText(this, "spinnerStatus not found", Toast.LENGTH_SHORT).show();
        }
        if (buttonSimpan == null) {
            Toast.makeText(this, "buttonSimpan not found", Toast.LENGTH_SHORT).show();
        }

        Log.d("Debug", "editTextNamaAgenda: " + editTextNamaAgenda);
        Log.d("Debug", "editTextDeskripsi: " + editTextDeskripsi);
        Log.d("Debug", "spinnerStatus: " + spinnerStatus);
        Log.d("Debug", "buttonSimpan: " + buttonSimpan);
    }

    private boolean validateInput(String title, String description) {
        if (title.isEmpty()) {
            Toast.makeText(this, "Nama agenda tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (description.isEmpty()) {
            Toast.makeText(this, "Deskripsi tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void saveAgenda(String title, String description, String status) {
        // Simpan ke database
        boolean isInserted = db.insertAgenda(title, description, status);

        if (isInserted) {
            Toast.makeText(this, "Agenda berhasil disimpan", Toast.LENGTH_SHORT).show();
            // Kembali ke AgendaListActivity
            Intent intent = new Intent(AddAgendaActivity.this, AgendaListActivity.class);
            startActivity(intent);
            finish(); // Menutup AddAgendaActivity
        } else {
            Toast.makeText(this, "Gagal menyimpan agenda", Toast.LENGTH_SHORT).show();
        }
    }
}
