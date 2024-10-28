package uts.taufikhidayat.task;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Menjalankan handler untuk berpindah ke LoginActivity setelah 2 detik (2000 ms)
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Tutup MainActivity agar tidak dapat kembali ke layar ini
        }, 2000); // 2000 ms = 2 detik
    }
}