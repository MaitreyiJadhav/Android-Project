package ravinder.example.myklan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class whoAreYou extends AppCompatActivity {
    private TextView member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who_are_you);



        member=(TextView)findViewById(R.id.member);
        Intent intent = getIntent();
        String familyMembers = intent.getStringExtra("familyMembers");
        Log.e("familyMembers",familyMembers);

        try {
            JSONArray jsonarray = new JSONArray(familyMembers);
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                String name = jsonobject.getString("name");
                Log.e("name",name);
                member.append(name);

            }

        } catch (
                JSONException e) {
            e.printStackTrace();
        }
    }



    public void dashboard(View view) {

        Intent dashboard = new Intent(getApplicationContext(), Dashboard.class);
        startActivity(dashboard);

    }
}
