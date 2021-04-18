package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

public class MainActivity extends AppCompatActivity {

    EditText name,tell,email,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        name=(EditText)findViewById(R.id.u_name);
        tell=(EditText)findViewById(R.id.u_tell);
        email=(EditText)findViewById(R.id.u_email);
        pass=(EditText)findViewById(R.id.u_pass);

        AndroidNetworking.post(getResources().getString(R.string.url_con))
                .addBodyParameter("PostType","con")
                .setPriority(Priority.HIGH)
                .build()

                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(ANError anError) {

                        Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void loginpage(View view) {
        Intent i=new Intent(MainActivity.this,login.class);
        startActivity(i);
    }


    public void regester(View view) {
        if (isconnected()) {
            if (name.getText().toString().length() > 0) {
                if (tell.getText().toString().length() > 0) {
                    if (email.getText().toString().length() > 0) {
                        if (pass.getText().toString().length() > 0) {

                            AndroidNetworking.post(getResources().getString(R.string.url_con))
                                    .addBodyParameter("PostType", "reg")
                                    .addBodyParameter("Username", name.getText().toString())
                                    .addBodyParameter("Usertell", tell.getText().toString())
                                    .addBodyParameter("Useremail", email.getText().toString())
                                    .addBodyParameter("Userpass", pass.getText().toString())
                                    .setPriority(Priority.HIGH)
                                    .build()
                                    .getAsString(new StringRequestListener() {
                                        @Override
                                        public void onResponse(String response) {
                                            //Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                                            if (response == "exist") {
                                                Toast.makeText(MainActivity.this, "كلمة مرور مضافة مسبقا , جرب كلمة اخرى...", Toast.LENGTH_SHORT).show();
                                            } else if (response == "done") {
                                                Toast.makeText(MainActivity.this, "تم اشتراكك بنجاح, سجل دخولك ..", Toast.LENGTH_SHORT).show();
                                            }
                                            else if (response == "wrong") {
                                                Toast.makeText(MainActivity.this, "لم يتم الاشتراك ", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onError(ANError anError) {
                                            Toast.makeText(MainActivity.this, anError.getMessage(), Toast.LENGTH_SHORT).show();

                                        }
                                    });

                        }
                    }
                }
            }
        }
    }




    public  boolean isconnected()
    {
        ConnectivityManager connectivityManager=
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return networkInfo!=null&&networkInfo.isConnected();
    }
}