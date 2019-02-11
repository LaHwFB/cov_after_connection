package com.example.mac.cov_log2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.Statement;

public class SignUpActivity extends AppCompatActivity {

    private EditText email,nom;
    private EditText pass;
    private Button register;
    ProgressDialog progressDialog;
    ConnectionClass connectionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email= (EditText) findViewById(R.id.emailS);
        pass= (EditText)findViewById(R.id.passS);
        nom=(EditText)findViewById(R.id.nomS);
        register= (Button) findViewById(R.id.register);

        connectionClass = new ConnectionClass();

        progressDialog=new ProgressDialog(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Doregister doregister = new Doregister();
                doregister.execute("");
            }
        });
    }

    public class Doregister extends AsyncTask<String,String,String>
    {


        String emailstr=email.getText().toString();
        String passstr=pass.getText().toString();
        String nomlstr=nom.getText().toString();
        String z="";
        boolean isSuccess=false;

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            if(nomlstr.trim().equals("")||emailstr.trim().equals("") ||passstr.trim().equals(""))
                z = "Please enter all fields....";
            else
            {
                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = "Please check your internet connection";
                    } else {

                        String query="insert into users(name,email,password) values('"+nomlstr+"','"+emailstr+"','"+passstr+"')";

                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(query);

                        z = "Register successfull";
                        Log.i("register success", z);
                        isSuccess=true;


                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = "Exceptions"+ex;
                }
            }
            return z;
        }

        @Override
        protected void onPostExecute(String s) {

            Toast.makeText(getBaseContext(),""+z,Toast.LENGTH_LONG).show();


            if(isSuccess) {
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));

            }


            progressDialog.hide();
        }
    }

}
