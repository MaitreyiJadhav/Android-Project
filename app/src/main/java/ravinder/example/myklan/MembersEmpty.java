package ravinder.example.myklan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MembersEmpty extends AppCompatActivity {
    private TextView text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members_empty);
        Intent intent = getIntent();
        String message = intent.getStringExtra("message");
        String Members = intent.getStringExtra("MembersData");

        Log.e("UserID", message);
        Log.e("Members", Members);

        text1=(TextView)  findViewById(R.id.labelFamilyName);
        text1.setText(message);
    }

    public void addMemberClick(View view) {

        Intent whoAreYou = new Intent(getApplicationContext(), whoAreYou.class);
        startActivity(whoAreYou);

    }
}