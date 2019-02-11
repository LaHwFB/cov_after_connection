package com.example.mac.cov_log2;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

public class RegLogActivity extends AsyncTask<String,Void,String>{

//    protected void onPostExecute(final Boolean success) {
//        mAuthTask = null;
//        showProgress(false);
//
//        if (success) {
////                getSupportFragmentManager().beginTransaction().replace(R.id.login_progress,new FragementHome()).commit();
//            //finish();
//            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
//            startActivity(i);
////                if(Toastdata.length()<1){
//            Toast ts=Toast.makeText(getApplicationContext(),Toastdata,Toast.LENGTH_LONG);
//            ts.show();//}
//
//        } else {
//            mPasswordView.setError(getString(R.string.error_incorrect_password));
//            mPasswordView.requestFocus();
//        }
//    }
    Context ctx;
    //String[] params=new String[];
    RegLogActivity(Context ctx){
        this.ctx=ctx;
    }

//    public RegLogActivity(String method, AutoCompleteTextView mEmailView, EditText mPasswordView) {
//        params[0]=method;
//        params[1]=mEmailView;
//        params[2]=mPasswordView;
//    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }
    @Override
    protected String doInBackground(String... params) {
        // TODO: attempt authentication against a network service.
//            try{
//                String link="https://files.000webhost.com/connect.php";
//                String data  = URLEncoder.encode("mail", "UTF-8") + "=" +
//                        URLEncoder.encode(mEmail, "UTF-8");
//                data += "&" + URLEncoder.encode("password", "UTF-8") + "=" +
//                        URLEncoder.encode(mPassword, "UTF-8");
//
//                // just for test mate
//                Toastdata=data;
//
//                URL url = new URL(link);
//                URLConnection conn = url.openConnection();
//
//
//                conn.setDoOutput(true);
//                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
//                Log.i("ss", Toastdata);
//                wr.write( data );
//                wr.flush();
//
//                BufferedReader reader = new BufferedReader(new
//                        InputStreamReader(conn.getInputStream()));
//
//                StringBuilder sb = new StringBuilder();
//                String line = "";
//                DUMMY_CREDENTIALS[0]=sb.toString();
//                // Read Server Response
//                while((line = reader.readLine()) != null) {
//                    sb.append(line);
//                    break;
//                }
//                } catch(Exception e){
//                Log.i("Error except", new String("Exception: " + e.getMessage()));();
//                //return false;
//            }
            //new test
            try{
                // Load CAs from an InputStream
// (could be from a resource or ByteArrayInputStream or ...)
//                CertificateFactory cf = CertificateFactory.getInstance("X.509");
//// From https://www.washington.edu/itconnect/security/ca/load-der.crt
//                InputStream caInput = new BufferedInputStream(new FileInputStream("load-der.crt"));
//                Certificate ca;
//                try {
//                    ca = cf.generateCertificate(caInput);
//                    System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
//                } finally {
//                    caInput.close();
//                }
//
//// Create a KeyStore containing our trusted CAs
//                String keyStoreType = KeyStore.getDefaultType();
//                KeyStore keyStore = KeyStore.getInstance(keyStoreType);
//                keyStore.load(null, null);
//                keyStore.setCertificateEntry("ca", ca);
//
//// Create a TrustManager that trusts the CAs in our KeyStore
//                String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
//                TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
//                tmf.init(keyStore);
//
//// Create an SSLContext that uses our TrustManager
//                SSLContext context = SSLContext.getInstance("TLS");
//                context.init(null, tmf.getTrustManagers(), null);
//                // Tell the URLConnection to use a SocketFactory from our SSLContext
//                URL url_ssl = new URL("https://solutions-qa.riverbed.cc/api/scm.config/1.0/orgs");
//                HttpsURLConnection urlConnection =
//                        (HttpsURLConnection)url_ssl.openConnection();
//                urlConnection.setSSLSocketFactory(context.getSocketFactory());
//                InputStream in = urlConnection.getInputStream();
//                inputStreamToOutputStream(in,System.out);

                //
                String link="http://10.0.2.2/register.php";
                String data  = URLEncoder.encode("mail", "UTF-8") + "=" +
                        URLEncoder.encode(params[1], "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8") + "=" +
                        URLEncoder.encode(params[2], "UTF-8");

                URL url = new URL(link);
                HttpURLConnection httpurl =(HttpURLConnection) url.openConnection();
                httpurl.setRequestMethod("POST");
                httpurl.setDoOutput(true);
                Log.i("httpurl ", "works fine: ");

                OutputStream os=httpurl.getOutputStream();
                BufferedWriter buff=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                Log.i("Buffer", "just fine : ");
                buff.write(data);
                buff.flush();
                buff.close();
                os.close();
                Log.i("before istream ", "5/5: ");
                InputStream is= httpurl.getInputStream();
                is.close();
                return "Registration succes";
            }catch(Exception e){
                Log.i("Error except", new String("Exception: " + e.getMessage()));
                return "Registration failed";
            }
        // STUFF original
//        try {
//            // Simulate network access.
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            return false;
//        }
//
//        Log.i("Dummy : ", DUMMY_CREDENTIALS[1]);
//        for (String credential : DUMMY_CREDENTIALS) {
//            String[] pieces = credential.split(":");
//            if (pieces[0].equals(mEmail)) {
//                // Account exists, return true if the password matches.
//                return pieces[1].equals(mPassword);
//            }
//        }

        // TODO: register the new account here.
//        return null;
    }

    @Override
    protected  void onProgressUpdate(Void... values){
        super.onProgressUpdate(values);
    }

    @Override
    protected  void onPostExecute(String result){
        Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
    }

    void inputStreamToOutputStream(final InputStream inputStream, final OutputStream out) {
        Thread t = new Thread(new Runnable() {

            public void run() {
                try {
                    int d;
                    while ((d = inputStream.read()) != -1) {
                        out.write(d);
                    }
                } catch (IOException ex) {
                    //TODO make a callback on exception.
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }
}
