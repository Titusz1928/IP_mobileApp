package com.example.ip_demo1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ChatFragmentJ extends Fragment {

    String url;
    private static final String TAG = "MyTag";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat_j2, container, false);

        //CardView to_s_chat = view.findViewById(R.id.llChat2CardView);

        ImageView searchButton = view.findViewById(R.id.strImageView);
        EditText searchInputField = view.findViewById(R.id.strEditText);

        LinearLayout parentLayout = view.findViewById(R.id.cltrLinearLayout);

//        to_s_chat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(requireContext(), SelectedChatJ.class);
//                requireContext().startActivity(intent);
//            }
//        });

        //LOADING IN EXISTING CHATS
        url=getString(R.string.URLchats);
        JSONObject userData = new JSONObject();
        try {
            UserDataManager userDataManager = UserDataManager.getInstance();
            userData.put("user_email",userDataManager.getAdresa_email());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG, userData.toString());

        JsonObjectRequest chatRequest = new JsonObjectRequest(Request.Method.POST, url, userData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle successful response
                        Log.d(TAG, "Response: " + response.toString());

                        String message = response.optString("message", "Unknown message");

                        try {
                            JSONArray emailArray = response.getJSONArray("email_values");
                            Log.d(TAG,emailArray.toString());
                            JSONArray prenumeArray = response.getJSONArray("prenume_values");
                            Log.d(TAG,prenumeArray.toString());
                            JSONArray dateArray = response.getJSONArray("data_values");
                            Log.d(TAG,dateArray.toString());
                            JSONArray lastMsgArray = response.getJSONArray("last_msg_values");
                            Log.d(TAG,lastMsgArray.toString());
                            JSONArray id_convArray = response.getJSONArray("id_conv_values");
                            Log.d(TAG,id_convArray.toString());
                            List<EmailNamePair> emailNamePairsExisting = parseJsonArraysExisting(emailArray, prenumeArray,dateArray,lastMsgArray, id_convArray);

                            parentLayout.removeAllViews();

                            // Sort emailNamePairsExisting list by date
                            Collections.sort(emailNamePairsExisting, new Comparator<EmailNamePair>() {
                                @Override
                                public int compare(EmailNamePair pair1, EmailNamePair pair2) {
                                    // Compare the dates of the pairs
                                    // Assuming the date format is "yyyy-MM-dd", you can use String.compareTo()
                                    return pair2.getDate().compareTo(pair1.getDate());
                                }
                            });

                            // Now you have a list of EmailNamePair objects, you can use it as needed
                            for (EmailNamePair pair : emailNamePairsExisting) {
                                // Create a CardView for each pair and add it to the parent LinearLayout
                                CardView cardView = createCardViewExisting(pair.getEmail(), pair.getPrenume(),pair.getDate(),pair.getLast_msg(),pair.getId_conv());
                                parentLayout.addView(cardView);
                                Log.d(TAG,"obiect created: "+ pair.toString());

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }




                        // Display the message in a Toast
                        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error response
                Log.e(TAG, "Volley error: " + error.getMessage());
                Toast.makeText(requireContext(), "Error occurred!", Toast.LENGTH_SHORT).show();
            }
        });

        Volley.newRequestQueue(requireContext()).add(chatRequest);



        searchButton.setOnClickListener(v -> {

            url=getString(R.string.URLsearch);

            JSONObject searchData = new JSONObject();
            try {
                searchData.put("searched_email",searchInputField.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d(TAG, searchData.toString());



            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, searchData,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // Handle successful response
                            Log.d(TAG, "Response: " + response.toString());

                            String message = response.optString("message", "Unknown message");

                            // Display the message in a Toast
                            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();

                            try {
                                JSONArray emailArray = response.getJSONArray("email_values");
                                Log.d(TAG,emailArray.toString());
                                JSONArray prenumeArray = response.getJSONArray("prenume_values");
                                Log.d(TAG,prenumeArray.toString());
                                List<EmailNamePair> emailNamePairs = parseJsonArrays(emailArray, prenumeArray);

                                parentLayout.removeAllViews();

                                // Now you have a list of EmailNamePair objects, you can use it as needed
                                for (EmailNamePair pair : emailNamePairs) {
                                    // Create a CardView for each pair and add it to the parent LinearLayout
                                    CardView cardView = createCardView(pair.getEmail(), pair.getPrenume());
                                    parentLayout.addView(cardView);
                                    Log.d(TAG,"obiect created: "+ pair.toString());

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // Handle error response
                    Log.e(TAG, "Volley error: " + error.getMessage());
                    Toast.makeText(requireContext(), "Error occurred!", Toast.LENGTH_SHORT).show();
                }
            });

            Volley.newRequestQueue(requireContext()).add(request);

        });



        return view;
    }

    //FUNCTIONS

    // Method to parse JSON arrays and create EmailNamePair objects
    private List<EmailNamePair> parseJsonArrays(JSONArray emailArray, JSONArray prenumeArray) throws JSONException {
        List<EmailNamePair> emailNamePairs = new ArrayList<>();

        int length = Math.min(emailArray.length(), prenumeArray.length());

        for (int i = 0; i < length; i++) {
            String email = emailArray.getString(i);
            String prenume = prenumeArray.getString(i);

            EmailNamePair emailNamePair = new EmailNamePair(email, prenume);
            emailNamePairs.add(emailNamePair);
        }

        return emailNamePairs;
    }

    private List<EmailNamePair> parseJsonArraysExisting(JSONArray emailArray, JSONArray prenumeArray,JSONArray dateArray, JSONArray lastMsgArray, JSONArray id_convArray) throws JSONException {
        List<EmailNamePair> emailNamePairs = new ArrayList<>();

        int length = Math.min(emailArray.length(), prenumeArray.length());

        for (int i = 0; i < length; i++) {
            String email = emailArray.getString(i);
            String prenume = prenumeArray.getString(i);
            String date = dateArray.getString(i);
            String lastMsg = lastMsgArray.getString(i);
            String id_conv = id_convArray.getString(i);

            // Truncate lastMsg to 10 characters and add ellipsis if there was truncation
            if (lastMsg.length() > 10) {
                lastMsg = lastMsg.substring(0, 10) + "...";
            }

            EmailNamePair emailNamePair = new EmailNamePair(email, prenume,date,lastMsg,id_conv);
            emailNamePairs.add(emailNamePair);
        }

        return emailNamePairs;
    }

    //Card view creating
    private CardView createCardView(String email, String prenume) {
        CardView cardView = new CardView(requireContext());

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                950, // Width
                300  // Height
        );
        cardView.setRadius(30);
        layoutParams.setMargins(16, 16, 16, 16); // Left, top, right, bottom margins
        cardView.setLayoutParams(layoutParams);

        //cardView.setCardCornerRadius(30); // Card corner radius
        cardView.setCardBackgroundColor(Color.WHITE); // Card background color
        cardView.setContentPadding(10, 10, 10, 10); // Card content padding

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(),SelectedChatJ.class);
                intent.putExtra("email", email);
                intent.putExtra("prenume", prenume);
                intent.putExtra("id_conv","-1");
                startActivity(intent);
            }
        });


        // Create a ConstraintLayout for the card view
        ConstraintLayout constraintLayout = new ConstraintLayout(requireContext());

        // Set constraint layout properties
        ConstraintLayout.LayoutParams constraintLayoutParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
        );
        constraintLayout.setLayoutParams(constraintLayoutParams);

        cardView.addView(constraintLayout);

        // Create and add TextView for prenume to the ConstraintLayout
        TextView textPrenume = new TextView(requireContext());
        textPrenume.setId(View.generateViewId());
        textPrenume.setLayoutParams(new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        ));
        textPrenume.setText(prenume);
        textPrenume.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28);
        textPrenume.setTextColor(Color.BLACK);
        constraintLayout.addView(textPrenume);

        // Set constraints for prenume TextView
        ConstraintSet constraintSetPrenume = new ConstraintSet();
        constraintSetPrenume.clone(constraintLayout);
        constraintSetPrenume.connect(textPrenume.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
        constraintSetPrenume.connect(textPrenume.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        constraintSetPrenume.applyTo(constraintLayout);

        // Create and add TextView for email to the ConstraintLayout
        TextView textEmail = new TextView(requireContext());
        textEmail.setId(View.generateViewId());
        textEmail.setLayoutParams(new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        ));
        textEmail.setText(email);
        textEmail.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        textEmail.setTextColor(Color.BLACK);
        constraintLayout.addView(textEmail);

        // Set constraints for email TextView
        ConstraintSet constraintSetEmail = new ConstraintSet();
        constraintSetEmail.clone(constraintLayout);
        constraintSetEmail.connect(textEmail.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
        constraintSetEmail.connect(textEmail.getId(), ConstraintSet.TOP, textPrenume.getId(), ConstraintSet.BOTTOM); // Set below prenume TextView
        constraintSetEmail.applyTo(constraintLayout);



        return cardView;
    }

    private CardView createCardViewExisting(String email, String prenume, String date, String lastMsg, String id_conv) {
        CardView cardView = new CardView(requireContext());

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                950, // Width
                300  // Height
        );
        cardView.setRadius(30);
        layoutParams.setMargins(16, 16, 16, 16); // Left, top, right, bottom margins
        cardView.setLayoutParams(layoutParams);

        //cardView.setCardCornerRadius(30); // Card corner radius
        cardView.setCardBackgroundColor(Color.WHITE); // Card background color
        cardView.setContentPadding(10, 10, 10, 10); // Card content padding

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(),SelectedChatJ.class);
                intent.putExtra("email", email);
                intent.putExtra("prenume", prenume);
                intent.putExtra("id_conv",id_conv);
                startActivity(intent);
            }
        });


        // Create a ConstraintLayout for the card view
        ConstraintLayout constraintLayout = new ConstraintLayout(requireContext());

        // Set constraint layout properties
        ConstraintLayout.LayoutParams constraintLayoutParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
        );
        constraintLayout.setLayoutParams(constraintLayoutParams);

        cardView.addView(constraintLayout);

        // Create and add TextView for prenume to the ConstraintLayout
        TextView textPrenume = new TextView(requireContext());
        textPrenume.setId(View.generateViewId());
        textPrenume.setLayoutParams(new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        ));
        textPrenume.setText(prenume);
        textPrenume.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28);
        textPrenume.setTextColor(Color.BLACK);
        constraintLayout.addView(textPrenume);

        // Set constraints for prenume TextView
        ConstraintSet constraintSetPrenume = new ConstraintSet();
        constraintSetPrenume.clone(constraintLayout);
        constraintSetPrenume.connect(textPrenume.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
        constraintSetPrenume.connect(textPrenume.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        constraintSetPrenume.applyTo(constraintLayout);


        // Create and add TextView for email to the ConstraintLayout
        TextView textLastMsg = new TextView(requireContext());
        textLastMsg.setId(View.generateViewId());
        textLastMsg.setLayoutParams(new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        ));
        textLastMsg.setText(lastMsg);
        textLastMsg.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textLastMsg.setTextColor(Color.BLACK);
        constraintLayout.addView(textLastMsg);

        // Set constraints for email TextView
        ConstraintSet constraintSetLastmsg = new ConstraintSet();
        constraintSetLastmsg.clone(constraintLayout);
        constraintSetLastmsg.connect(textLastMsg.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
        constraintSetLastmsg.connect(textLastMsg.getId(), ConstraintSet.TOP, textPrenume.getId(), ConstraintSet.BOTTOM);
        constraintSetLastmsg.applyTo(constraintLayout);

        // Create and add TextView for email to the ConstraintLayout
        TextView textDate = new TextView(requireContext());
        textDate.setId(View.generateViewId());
        textDate.setLayoutParams(new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        ));
        textDate.setText(date);
        textDate.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textDate.setTextColor(Color.GRAY);
        constraintLayout.addView(textDate);

        // Set constraints for date TextView
        ConstraintSet constraintSetDate = new ConstraintSet();
        constraintSetDate.clone(constraintLayout);
        constraintSetDate.connect(textDate.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END); // Align to the right side of the ConstraintLayout
        constraintSetDate.connect(textDate.getId(), ConstraintSet.TOP, textLastMsg.getId(), ConstraintSet.BOTTOM); // Position below textEmail
        constraintSetDate.applyTo(constraintLayout);

        // Create and add TextView for email to the ConstraintLayout
        TextView textEmail = new TextView(requireContext());
        textEmail.setId(View.generateViewId());
        textEmail.setLayoutParams(new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        ));
        textEmail.setText(email);
        textEmail.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textEmail.setTextColor(Color.GRAY);
        constraintLayout.addView(textEmail);

        // Set constraints for email TextView
        ConstraintSet constraintSetEmail = new ConstraintSet();
        constraintSetEmail.clone(constraintLayout);
        constraintSetEmail.connect(textEmail.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
        constraintSetEmail.connect(textEmail.getId(), ConstraintSet.TOP, textDate.getId(), ConstraintSet.BOTTOM);
        constraintSetEmail.applyTo(constraintLayout);



        return cardView;
    }



}
