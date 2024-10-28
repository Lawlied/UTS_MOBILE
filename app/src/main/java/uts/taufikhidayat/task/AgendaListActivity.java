package uts.taufikhidayat.task;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.List;

public class AgendaListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton fabAdd;
    private AgendaAdapter agendaAdapter;
    private AgendaDatabaseHelper db; // Deklarasi DatabaseHelper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_list);

        recyclerView = findViewById(R.id.recyclerViewAgenda);
        fabAdd = findViewById(R.id.fabAdd);
        Calendar DatabaseHelper = null;

        fabAdd.setOnClickListener(v -> {
            startActivity(new Intent(AgendaListActivity.this, AddAgendaActivity.class));
        });

        // Tampilkan daftar agenda menggunakan RecyclerView
        loadAgendaList();
    }

    private void loadAgendaList() {
        List<Agenda> agendaList = db.getAllAgendas(); // Ambil data dari database
        if (agendaList.isEmpty()) {
            Toast.makeText(this, "Tidak ada agenda yang ditemukan", Toast.LENGTH_SHORT).show();
        } else {
            agendaAdapter = new AgendaAdapter(agendaList); // Buat adapter dengan data
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(agendaAdapter); // Set adapter untuk RecyclerView
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAgendaList(); // Refresh daftar saat kembali dari AddAgendaActivity
    }
}

