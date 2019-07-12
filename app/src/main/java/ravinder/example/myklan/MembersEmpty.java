package ravinder.example.myklan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MembersEmpty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members_empty);
    }

    public void addMemberClick(View view) {

        Intent whoAreYou = new Intent(getApplicationContext(), whoAreYou.class);
        startActivity(whoAreYou);

    }
}
