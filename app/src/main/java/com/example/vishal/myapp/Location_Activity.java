package com.example.vishal.myapp;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class Location_Activity extends AppCompatActivity {

    private static final String TAG = "Location_Activity";

    private static final int ERROR_DIALOG_REQUEST = 9001;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_);

        if(isServiceOk()){
            init();
        }
    }

    public  void init()
    {
        btn=(Button)findViewById(R.id.buttonMap);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Location_Activity.this,Login_Activity.class);
                startActivity(i);
            }
        });
    }

    public boolean isServiceOk(){
        Log.d(TAG,"isServiceOk: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(Location_Activity.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make the map request
            Log.d(TAG,"isServiceOk: Google Play Service is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            // an error can occured but we can resolve it
            Log.d(TAG,"isServiceOk:an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(Location_Activity.this,available,ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "you can't map requests", Toast.LENGTH_SHORT).show();
                }
                return false;
    }
}