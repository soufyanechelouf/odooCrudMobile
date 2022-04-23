package com.example.odooapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.odooapp.Controller.VolleySingleton;
import com.example.odooapp.MainActivity;
import com.example.odooapp.R;
import com.example.odooapp.SERVER.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditData extends AppCompatActivity {




    private EditText nameEdit , phoneEdit,emailEdit,mobileEdit,websiteEdit;
    private Bundle extras;
    private Button editButton;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);


        nameEdit = findViewById(R.id.nameToEditpage);
        phoneEdit = findViewById(R.id.phoneToEditpage);
        emailEdit = findViewById(R.id.emailToEditPage);
        mobileEdit = findViewById(R.id.mobileToEditPage);
        websiteEdit = findViewById(R.id.websiteToEditPage);
        editButton = findViewById(R.id.buttonAddSave);

        extras = getIntent().getExtras();

      //  if(extras != null){
            nameEdit.setText("test"+extras.getString("name"));
            phoneEdit.setText("test"+extras.getString("phone"));
            emailEdit.setText("test"+extras.getString("email"));
            mobileEdit.setText("test"+extras.getString("mobile"));
            websiteEdit.setText("test"+extras.getString("website"));
            id = extras.getInt("id");
     //   }



        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editData(id);

                finish();
                startActivity(new Intent(com.example.odooapp.UI.EditData.this, MainActivity.class));
            }
        });
    }



    private void editData(int id){


        final String myName = nameEdit.getText().toString().trim();
        final String myPhone = phoneEdit.getText().toString().trim();
        final String myEmail = emailEdit.getText().toString().trim();
        final String myMobile = mobileEdit.getText().toString().trim();
        final String myWebsite= websiteEdit.getText().toString().trim();


        if(TextUtils.isEmpty(myName)){
            nameEdit.setError("Enter the supplier name");
            nameEdit.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(myPhone)){
            phoneEdit.setError("Enter the phone number");
            phoneEdit.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(myEmail)){
            emailEdit.setError("Enter the email");
            emailEdit.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(myMobile)){
            mobileEdit.setError("Enter the mobile number");
            mobileEdit.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(myWebsite)){
            websiteEdit.setError("Enter the website");
            websiteEdit.requestFocus();
            return;
        }

/*
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();*/


        JSONObject postparams = new JSONObject();
        try {
            postparams.put("name",myName);
            postparams.put("phone",myPhone);
            postparams.put("email",myEmail);
            postparams.put("mobile",myMobile);
            postparams.put("website",myWebsite);

        }catch (JSONException e){
            e.getMessage();
        }



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest( Request.Method.PUT,
                URLs.URL_ALL_DATA +"/"+id , postparams, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {

                    if(response.getBoolean("success")){
                        Toast.makeText(getApplicationContext(),response.getString("message")
                                , Toast.LENGTH_SHORT).show();
                      //  progressDialog.dismiss();


                    }else{
                        Toast.makeText(getApplicationContext(),"error"
                                , Toast.LENGTH_SHORT).show();
                    //    progressDialog.dismiss();

                    }


                } catch (JSONException e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } )
        {
            public Map<String, String> getHeaders(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("Accept","application/json");

                return params;
            }
        }

                ;

        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);









    }


}

