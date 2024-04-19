package com.example.ip_demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "MyTag";
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button buttonLogin = findViewById(R.id.LOGifcvLoginButton);
        Button buttonRegister = findViewById(R.id.LOGifcvRegisterButton);
        TextView buttonForgot = findViewById(R.id.LOGifcvForgotPasswordText);

        TextInputEditText emailEditText = findViewById(R.id.tilEmail);
        TextInputEditText passwordEditText = findViewById(R.id.tilPassword);

        buttonLogin.setOnClickListener(v -> {
            buttonLogin.setEnabled(false);
            buttonLogin.setClickable(false);

            url=getString(R.string.URLlogin);

            JSONObject userLoginData = new JSONObject();
            try {
                userLoginData.put("adresa_email",emailEditText.getText().toString());
                userLoginData.put("parola",passwordEditText.getText().toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d(TAG, userLoginData.toString());



            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, userLoginData,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // Handle successful response
                            Log.d(TAG, "Response: " + response.toString());

                            String message = response.optString("message", "Unknown message");

                            // Display the message in a Toast
                            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                            if(message.equals("Welcome!")) {
                                Intent intent = new Intent(LoginActivity.this, MenuActivityJ.class);
                                startActivity(intent);
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // Handle error response
                    Log.e(TAG, "Volley error: " + error.getMessage());
                    Toast.makeText(LoginActivity.this, "Error occurred!", Toast.LENGTH_SHORT).show();
                }
            });

            Volley.newRequestQueue(this).add(request);
            buttonLogin.setEnabled(true);
            buttonLogin.setClickable(true);

        });

        buttonRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });

        buttonForgot.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity1J.class);
            startActivity(intent);
        });
    }
}