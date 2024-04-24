package com.example.ip_demo1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.List;

public class SelectedChatJ extends AppCompatActivity {

    String url;
    private static final String TAG = "MyTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_chat2);

        FloatingActionButton back = findViewById(R.id.SCHbcvBackButton);
        NestedScrollView nestedScrollView = findViewById(R.id.clNestedScrollView);

        TextView nameText = findViewById(R.id.tcvNameText);
        nameText.setText(getIntent().getStringExtra("prenume"));

        ImageView sendButton = findViewById(R.id.mscvImageView);
        EditText messageInputField = findViewById(R.id.mscvMessageEditText);

        url=getString(R.string.URLsend);




        sendButton.setOnClickListener(v -> {

            url=getString(R.string.URLsend);

            JSONObject messageData = new JSONObject();
            try {
                UserDataManager userDataManager = UserDataManager.getInstance();

                String senderEmail = userDataManager.getAdresa_email();
                String receiverEmail = getIntent().getStringExtra("email");

                messageData.put("message",messageInputField.getText().toString());
                messageData.put("semail",senderEmail);
                messageData.put("remail",receiverEmail);


            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d(TAG, messageData.toString());



            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, messageData,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // Handle successful response
                            Log.d(TAG, "Response: " + response.toString());

                            String message = response.optString("message", "Unknown message");

                            // Display the message in a Toast
                            Toast.makeText(SelectedChatJ.this, message, Toast.LENGTH_SHORT).show();



                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // Handle error response
                    Log.e(TAG, "Volley error: " + error.getMessage());
                    Toast.makeText(SelectedChatJ.this, "Error occurred!", Toast.LENGTH_SHORT).show();
                }
            });

            Volley.newRequestQueue(SelectedChatJ.this).add(request);
            messageInputField.setText("");

        });

        //put nested scroll view fully down
        nestedScrollView.post(new Runnable() {
            @Override
            public void run() {
                nestedScrollView.fullScroll(View.FOCUS_DOWN);
            }
        });

        //go back to chat list
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectedChatJ.this, MenuActivityJ.class);
                startActivity(intent);
            }
        });
    }
}
