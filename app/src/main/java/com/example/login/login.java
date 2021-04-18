package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.renderscript.RenderScript;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.renderscript.RenderScript;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

public class login extends AppCompatActivity {

    EditText user_name ,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        user_name=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.pass);


    }
    public  boolean isconnected()
    {
        ConnectivityManager connectivityManager=
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return networkInfo!=null&&networkInfo.isConnected();
    }

    public void logg(View view) {
        if (isconnected()){
            if(user_name.getText().toString().length()>0) {
                if (password.getText().toString().length()>0){
                    AndroidNetworking.post(getResources().getString(R.string.url_con))
                            .addBodyParameter("PostType", "Login")
                            .addBodyParameter("Username",user_name.getText().toString())
                            .addBodyParameter("Password",password.getText().toString())
                            .setPriority(Priority.HIGH)
                            .build()
                            .getAsString(new StringRequestListener() {
                                @Override
                                public void onResponse(String response) {
                                    //Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                                    if (response=="Yes")
                                    {
                                        Intent i=new Intent(login.this,Home.class);
                                        startActivity(i);
                                    }
                                    else  if (response=="No")
                                    {
                                        Toast.makeText(login.this, "تسجيل دخول خاطئ...", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onError(ANError anError) {
                                    Toast.makeText(login.this, anError.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });

                }}
        }
    }


}