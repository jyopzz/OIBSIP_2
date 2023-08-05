package com.example.todo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class SignUpActivity extends AppCompatActivity {

    private EditText etFullName;
    private EditText etNewUsername;
    private EditText etNewPassword;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etFullName = findViewById(R.id.etFullName);
        etNewUsername = findViewById(R.id.etNewUsername);
        etNewPassword = findViewById(R.id.etNewPassword);
        Button btnSignUp = findViewById(R.id.btnSignUp);
        TextView tvLogin = findViewById(R.id.tvLogin);

        dbHelper = new DBHelper(this);

        btnSignUp.setOnClickListener(v -> {
            String fullName = etFullName.getText().toString();
            String username = etNewUsername.getText().toString();
            String password = etNewPassword.getText().toString();

            // Insert user into the database
            long userId = insertUser(fullName, username, password);

            if (userId != -1) {
                // After successful sign-up, navigate back to login activity
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            }  // Handle sign-up error
            // You could show a toast or a message indicating that sign-up failed

        });

        tvLogin.setOnClickListener(v -> {
            // Navigate back to login activity
            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private long insertUser(String fullName, String username, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_FULL_NAME, fullName);
        values.put(DBHelper.COLUMN_USERNAME, username);
        values.put(DBHelper.COLUMN_PASSWORD, password);

        long newRowId = db.insert(DBHelper.TABLE_USERS, null, values);

        // Close the database after the operation is complete
        db.close();

        return newRowId;
    }

}
