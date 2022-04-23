package com.example.odooapp.UI;

import android.app.ProgressDialog;
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

public class AddData extends AppCompatActivity {

    private EditText nameAdd , phoneAdd,emailAdd,mobileAdd,websiteAdd;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        nameAdd = findViewById(R.id.nameToEditpage);
        phoneAdd = findViewById(R.id.phoneToEditpage);
        emailAdd = findViewById(R.id.emailToEditPage);
        mobileAdd = findViewById(R.id.mobileToEditPage);
        websiteAdd = findViewById(R.id.websiteToEditPage);


        saveButton = findViewById(R.id.buttonAddSave);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();

                finish();
                startActivity(new Intent(com.example.odooapp.UI.AddData.this, MainActivity.class));
            }
        });
    }





    private void saveData(){


        final String myName = nameAdd.getText().toString().trim();
        final String myPhone = phoneAdd.getText().toString().trim();
        final String myEmail = emailAdd.getText().toString().trim();
        final String myMobile = mobileAdd.getText().toString().trim();
        final String myWebsite = websiteAdd.getText().toString().trim();

        if(TextUtils.isEmpty(myName)){
            nameAdd.setError("Enter you name please");
            nameAdd.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(myPhone)){
            phoneAdd.setError("Enter you phone please");
            phoneAdd.requestFocus();
            return;
        }



        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


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

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest( Request.Method.POST,
                URLs.URL_ALL_DATA, postparams, new Response.Listener<JSONObject>() {

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
