package com.example.apple.homework;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText nameEt,update_id,update_name,update_sex,delete_id;
    private EditText ageEt;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEt = (EditText) findViewById(R.id.name_et);
        ageEt = (EditText) findViewById(R.id.age_et);
        update_id = (EditText) findViewById(R.id.update_id);
        update_name = (EditText) findViewById(R.id.update_name);
        update_sex = (EditText) findViewById(R.id.update_sex);
        delete_id = (EditText) findViewById(R.id.delete_id);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void getAllUsers(View view) {
        new Thread() {

            @Override
            public void run() {
                HttpURLConnection con = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("http://192.168.21.69:3000/users");
                    con = (HttpURLConnection) url.openConnection();

                    //getInputstream ,connect to net!!!!
                    InputStream in = con.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder builder = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                    Log.i("", builder.toString());

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    con.disconnect();

                }
            }
        }.start();
    }

    public void addUser(View view) {
        final String name = nameEt.getText().toString();
        final String sex = ageEt.getText().toString();
        new Thread() {
            @Override
            public void run() {
                HttpURLConnection con = null;
                OutputStream out = null;
                BufferedReader reader = null;

                try {
                    URL url = new URL("http://192.168.21.69:3000/users");
                    con = (HttpURLConnection) url.openConnection();
                    con.setDoOutput(true);
                    con.setRequestMethod("POST");
                    con.addRequestProperty("Content-Type", "application/json");
                    out = con.getOutputStream();

                    String jsonString = null;

                    JSONObject jso = new JSONObject();
                    jso.put("name", name);
                    jso.put("sex", sex);
                    jsonString = jso.toString();

                    out.write(jsonString.getBytes());
                    //getInputstream ,connect to net!!!!
                    InputStream in = con.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder builder = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                    Log.i("john", builder.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        out.close();
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    con.disconnect();
                }


            }
        }.start();

    }

    public void updateUser(View view) {
        final String uid = update_id.getText().toString();
        final String name = update_name.getText().toString();
        final String sex = update_sex.getText().toString();
        new Thread() {
            @Override
            public void run() {
                HttpURLConnection con = null;
                OutputStream out = null;
                BufferedReader reader = null;

                try {
                    URL url = new URL("http://192.168.21.69:3000/users/"+uid);
                    con = (HttpURLConnection) url.openConnection();
                    con.setDoOutput(true);
                    con.setRequestMethod("PUT");
                    con.addRequestProperty("Content-Type", "application/json");
                    out = con.getOutputStream();

                    String jsonString = null;

                    JSONObject jso = new JSONObject();
                    jso.put("name", name);
                    jso.put("sex", sex);
                    jsonString = jso.toString();

                    out.write(jsonString.getBytes());
                    //getInputstream ,connect to net!!!!
                    InputStream in = con.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder builder = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                    Log.i("", builder.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        out.close();
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    con.disconnect();
                }


            }
        }.start();

    }

    public void deleteUser(View view) {
        final String did = delete_id.getText().toString();
        new Thread() {
            @Override
            public void run() {
                HttpURLConnection con = null;
                BufferedReader reader = null;

                try {
                    URL url = new URL("http://192.168.21.69:3000/users/"+did);
                    con = (HttpURLConnection) url.openConnection();
                    con.setDoOutput(true);
                    con.setRequestMethod("DELETE");
                    con.addRequestProperty("Content-Type", "application/json");

                    //getInputstream ,connect to net!!!!
                    InputStream in = con.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder builder = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                    Log.i("", builder.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }  finally {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    con.disconnect();
                }


            }
        }.start();

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
