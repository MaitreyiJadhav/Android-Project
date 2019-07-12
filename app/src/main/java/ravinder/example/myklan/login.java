package ravinder.example.myklan;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class login extends Activity {
    private final String tag = "LOGIN BUTTON CALL";

    EditText email;
    EditText password;
    Button loginButton;

    String authenticator = "";


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        email = findViewById(R.id.editEmail);
        password = findViewById(R.id.editPassword);
        loginButton = findViewById(R.id.Login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login(email.getText().toString(),password.getText().toString());
                //GetFamilyData();
            }
        });
    }


    public void onForgetPassword(View view) {

    }

    public void newUserSignUp(View view) {

        Intent fp=new Intent(getApplicationContext(), SignUp.class);
        startActivity(fp);

    }

    public void Login(final String email, final String password){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                HttpsURLConnection connection = null;
                try {
                    String url = "https://1i16orvav2.execute-api.us-east-1.amazonaws.com/dev/login";
                    URL loginEndPoint = new URL(url);
                    connection = (HttpsURLConnection) loginEndPoint.openConnection();

                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/json");

                    connection.setUseCaches(false);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);

                    // Send request
                    DataOutputStream wr = new DataOutputStream(
                            connection.getOutputStream());
                    wr.writeBytes("{\"email\":\"" + email +"\",\"password\":\"" + password +"\"}");
                    wr.flush();
                    wr.close();

                    // Get Response
                    InputStream is = connection.getInputStream();
                    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                    String line;
                    StringBuffer response = new StringBuffer();
                    while ((line = rd.readLine()) != null) {
                        response.append(line);
                        response.append('\r');
                    }
                    rd.close();
                    final JSONObject resp = new JSONObject(response.toString());
                    if(resp.getBoolean("auth")){
                        Intent fp=new Intent(getApplicationContext(), MembersEmpty.class);
                        startActivity(fp);
                        //Log.e(tag,"respnse: " + response);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    authenticator = resp.getString("token");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    //Log.e(tag,"the response: " + resp.getBoolean("auth"));

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        });
    }

    public void GetFamilyData(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                HttpsURLConnection connection = null;
                try {
                    //{"key":"Authorization","value":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InRlc3RAbXlrbGFuLmNvbSIsImlhdCI6MTU2MjcxMjc4MCwiZXhwIjoxNTYyNzk5MTgwfQ.IU9uNrzoiFYgkwp7b4MCWkHPIoCdGP0PIgnGBktmZ5I","description":"","type":"text","enabled":true}
                    String url = "https://1i16orvav2.execute-api.us-east-1.amazonaws.com/dev/me";
                    URL loginEndPoint = new URL(url);
                    connection = (HttpsURLConnection) loginEndPoint.openConnection();
                    connection.addRequestProperty("key","Authorization");
                    connection.addRequestProperty("value",authenticator);

                    //connection.setRequestProperty("key","Authorization");
                    //connection.setRequestProperty("value",authenticator);
                    connection.setRequestProperty("type","text");
                    connection.setRequestProperty("enabled","true");
                    connection.setRequestProperty("description","");
                    //Log.e("test",connection.getHeaderField("value"));
                    //connection.setRequestProperty("value","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InRlc3RAbXlrbGFuLmNvbSIsImlhdCI6MTU2MjcxMjc4MCwiZXhwIjoxNTYyNzk5MTgwfQ.IU9uNrzoiFYgkwp7b4MCWkHPIoCdGP0PIgnGBktmZ5I");

                    connection.setDoOutput(true);

                    // Get Response
                    String responseCode = String.valueOf(connection.getResponseCode());
                    Log.e(tag,"the response: " + responseCode);
                    InputStream is = connection.getInputStream();
                    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                    String line;
                    StringBuffer response = new StringBuffer();
                    while ((line = rd.readLine()) != null) {
                        response.append(line);
                        response.append('\r');
                    }
                    rd.close();
                    Log.e(tag,"the response: " + response);
                    JSONObject resp = new JSONObject(response.toString());
                    //if(resp.getBoolean("auth")){
                        Intent fp=new Intent(getApplicationContext(), MembersEmpty.class);
                        startActivity(fp);
                    //}

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        });
    }

}
