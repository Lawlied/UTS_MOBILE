package uts.taufikhidayat.task;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextUsername, editTextPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(v -> {
            String username = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();

            // Validasi login sederhana
            if (username.equals("admin") && password.equals("admin")) {
                startActivity(new Intent(LoginActivity.this, AgendaListActivity.class));
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Username atau Password salah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
