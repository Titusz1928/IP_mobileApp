package com.example.ip_demo1.controller;

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
import com.example.ip_demo1.R;
import com.example.ip_demo1.model.User;
import com.example.ip_demo1.model.UserSession;
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
        //declaring buttons
        Button buttonLogin = findViewById(R.id.LOGifcvLoginButton);
        Button buttonRegister = findViewById(R.id.LOGifcvRegisterButton);
        TextView buttonForgot = findViewById(R.id.LOGifcvForgotPasswordText);
        //declaring inputs
        TextInputEditText emailEditText = findViewById(R.id.tilEmail);
        TextInputEditText passwordEditText = findViewById(R.id.tilPassword);

        //executed after pressing login button
        buttonLogin.setOnClickListener(v -> {
            buttonLogin.setEnabled(false);
            buttonLogin.setClickable(false);

            url=getString(R.string.URLlogin);

            //creating JSON object
            JSONObject userLoginData = new JSONObject();
            try {
                userLoginData.put("adresa_email",emailEditText.getText().toString());
                userLoginData.put("parola",passwordEditText.getText().toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d(TAG, userLoginData.toString());


            //creating request
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

                                //using UserDataManager Singleton object to store the user data
                                User user = new User();

                                user.setId(Integer.parseInt(response.optString("id","Unknown")));
                                user.setLastName(response.optString("nume", "Unknown"));
                                user.setFirstName(response.optString("prenume", "Unknown"));
                                user.setAge(response.optString("varsta", "Unknown"));
                                user.setCnp(response.optString("cnp", "Unknown"));
                                user.setStreet(response.optString("strada", "Unknown"));
                                user.setCity(response.optString("oras", "Unknown"));
                                user.setCounty(response.optString("judet", "Unknown"));
                                user.setCounty(response.optString("tara", "Unknown"));
                                user.setPhoneNumber(response.optString("numar_tel", "Unknown"));
                                user.setProfession(response.optString("profes", "Unknown"));
                                user.setWorkplace(response.optString("loc_mun", "Unknown"));
                                user.setEmailAddress(response.optString("adresa_email", "Unknown"));
                                user.setPassword(response.optString("parola", "Unknown"));

                                Log.d("MyTag","attempting to save user");
                                UserSession.getInstance(LoginActivity.this).setUser(user);
                                Log.d("MyTag","attempt was made");

                                //going to MenuActivity (Home Fragment)
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
        //Going to register activity
        buttonRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });
        //forgot password activity
        buttonForgot.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity1J.class);
            startActivity(intent);
        });
    }
}