package com.example.odooapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.odooapp.Controller.Adapter;
import com.example.odooapp.Controller.VolleySingleton;
import com.example.odooapp.Model.Fournisseur;
import com.example.odooapp.SERVER.URLs;
import com.example.odooapp.UI.AddData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    public static Adapter adapter;
    private List<Fournisseur> fournisseurList;

    private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        queue = Volley.newRequestQueue(this);



        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fournisseurList = new ArrayList<>();
        fournisseurList = getData();
        adapter = new Adapter( this,fournisseurList);
        recyclerView.setAdapter(adapter);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(com.example.odooapp.MainActivity.this, AddData.class);
                startActivity(intent);
                finish();

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }


    public static void notifyAdapter(){
        adapter.notifyDataSetChanged();
    }



    public List<Fournisseur> getData(){

        fournisseurList.clear();


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest( Request.Method.GET,
                URLs.URL_ALL_DATA, null, new Response.Listener<JSONArray>() {



            @Override
            public void onResponse(JSONArray response) {
                int count =0;
                while (count<response.length())
                try {


                    JSONObject fourObj = response.getJSONObject(count);

                        Fournisseur fournisseur = new Fournisseur();
                        fournisseur.setId(fourObj.getInt("id"));
                        fournisseur.setName(fourObj.getString("name"));
                        fournisseur.setPhone(fourObj.getString("phone"));
                        fournisseur.setEmail(fourObj.getString("email"));
                        fournisseur.setMobile(fourObj.getString("mobile"));
                        fournisseur.setWebsite(fourObj.getString("website"));


                        fournisseurList.add(fournisseur);
                        count++;
                        adapter.notifyDataSetChanged();




                } catch (JSONException e) {
e.printStackTrace();
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
               // params.put("Authorization","Bearer  "+ token);
                return params;
            }
        }

                ;

        VolleySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest);
        return fournisseurList;
    }




}
