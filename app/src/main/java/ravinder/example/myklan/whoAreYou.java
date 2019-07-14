package ravinder.example.myklan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class whoAreYou extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who_are_you);
    }
//
//    public void userProfile(View view) {
//
//        Intent userProfilePage=new Intent(getApplicationContext(), userProfile.class);
//        startActivity(userProfilePage);
//
//    }

    public void dashboard(View view) {

        Intent dashboard = new Intent(getApplicationContext(), Dashboard.class);
        startActivity(dashboard);

    }
}
