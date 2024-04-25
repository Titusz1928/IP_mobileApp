package com.example.ip_demo1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        LinearLayout parentLayout = findViewById(R.id.nsvLinearLayout);


        TextView nameText = findViewById(R.id.tcvNameText);
        //nameText.setText(getIntent().getStringExtra("prenume")+getIntent().getStringExtra("id_conv"));

        String prenume = getIntent().getStringExtra("prenume");
        String idConv = getIntent().getStringExtra("id_conv");

        if (prenume != null) {
            nameText.setText(prenume);
        } else {
            // Handle case where one or both extras are null
            // For example, display a default message or handle the null values appropriately
            nameText.setText("Error loading name");
        }

        ImageView sendButton = findViewById(R.id.mscvImageView);
        EditText messageInputField = findViewById(R.id.mscvMessageEditText);


        //loading previous messages
        url=getString(R.string.URLmessages);

        JSONObject conversationData = new JSONObject();
        try {
            UserDataManager userDataManager = UserDataManager.getInstance();
            conversationData.put("id_conv",idConv);
            //to get database id
            conversationData.put("user_email",userDataManager.getAdresa_email());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG, conversationData.toString());

        JsonObjectRequest chatRequest = new JsonObjectRequest(Request.Method.POST, url, conversationData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle successful response
                        Log.d(TAG, "Response: " + response.toString());

                        String message = response.optString("message", "Unknown message");

                        try {
                            String user_id=response.optString("user_id");
                            //System.out.println(user_id);

                            JSONArray continutArray = response.getJSONArray("continut_values");
                            Log.d(TAG,continutArray.toString());
                            JSONArray dataArray = response.getJSONArray("data_values");
                            Log.d(TAG,dataArray.toString());
                            JSONArray idSenderArray = response.getJSONArray("id_sender_values");
                            Log.d(TAG,idSenderArray.toString());

                            List<MessageBox> messageBoxList = parseJsonArrays(continutArray, dataArray,idSenderArray);

                            parentLayout.removeAllViews();

                            //for (int i = messageBoxList.size() - 1; i >= 0; i--) {
                            for(MessageBox messagebox : messageBoxList){
                                //MessageBox messagebox = messageBoxList.get(i);

                                // Declare cardView variable outside the if-else block
                                CardView cardView;

                                // Create a CardView for each pair and add it to the parent LinearLayout
                                if (messagebox.getSender_id() == user_id) {
                                    cardView = createCardViewRight(messagebox.getContinut(), messagebox.getData());
                                } else {
                                    cardView = createCardViewLeft(messagebox.getContinut(), messagebox.getData());
                                }

                                parentLayout.addView(cardView);
                                Log.d(TAG, "Object created: " + messagebox.toString());
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }





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

        Volley.newRequestQueue(SelectedChatJ.this).add(chatRequest);

        nestedScrollView.post(new Runnable() {
            @Override
            public void run() {
                nestedScrollView.fullScroll(View.FOCUS_DOWN);
            }
        });







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

    private CardView createCardViewLeft(String continut, String data) {
        // Create a new CardView
        CardView cardView = new CardView(SelectedChatJ.this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, // Width
                ViewGroup.LayoutParams.WRAP_CONTENT  // Height
        );
        layoutParams.setMargins(16, 16, 16, 16);
        cardView.setLayoutParams(layoutParams);

        cardView.setCardBackgroundColor(ContextCompat.getColor(SelectedChatJ.this, R.color.lightgray));
        cardView.setContentPadding(10, 10, 10, 10);

        // Create a LinearLayout for the card view contents
        LinearLayout linearLayout = new LinearLayout(SelectedChatJ.this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        // Create the TextView for 'continut'
        TextView textViewContinut = new TextView(SelectedChatJ.this);
        textViewContinut.setText(continut);
        textViewContinut.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        textViewContinut.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        // Create the TextView for 'data'
        TextView textViewData = new TextView(SelectedChatJ.this);
        textViewData.setText(data);
        textViewData.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        textViewData.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        // Add TextViews to LinearLayout
        linearLayout.addView(textViewContinut);
        linearLayout.addView(textViewData);

        // Add LinearLayout to CardView
        cardView.addView(linearLayout);

        // Return the constructed CardView
        return cardView;
    }



    private CardView createCardViewRight(String continut, String data) {
        // Create a new CardView
        CardView cardView = new CardView(SelectedChatJ.this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, // Width
                    ViewGroup.LayoutParams.WRAP_CONTENT  // Height
        );
        layoutParams.setMargins(16, 16, 16, 16);
        cardView.setLayoutParams(layoutParams);

        cardView.setCardBackgroundColor(ContextCompat.getColor(SelectedChatJ.this, R.color.background));
        cardView.setContentPadding(10, 10, 10, 10);

        // Create a LinearLayout for the card view contents
        LinearLayout linearLayout = new LinearLayout(SelectedChatJ.this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        // Create the TextView for 'continut'
        TextView textViewContinut = new TextView(SelectedChatJ.this);
        textViewContinut.setText(continut);
        textViewContinut.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        textViewContinut.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        // Create the TextView for 'data'
        TextView textViewData = new TextView(SelectedChatJ.this);
        textViewData.setText(data);
        textViewData.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
        textViewData.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        // Add TextViews to LinearLayout
        linearLayout.addView(textViewContinut);
        linearLayout.addView(textViewData);

        // Add LinearLayout to CardView
        cardView.addView(linearLayout);

        // Return the constructed CardView
        return cardView;
    }



    private List<MessageBox> parseJsonArrays(JSONArray continutArray, JSONArray dataArray, JSONArray idSenderArray) throws JSONException {
        List<MessageBox> messageBoxList = new ArrayList<>();

        int length = Math.min(continutArray.length(), dataArray.length());

        for (int i = 0; i < length; i++) {
            String continut = continutArray.getString(i);
            String data = dataArray.getString(i);
            String idSender = idSenderArray.getString(i);

            MessageBox messageBox = new MessageBox(continut,data,idSender);
            messageBoxList.add(messageBox);
        }

        return messageBoxList;

    }
}
