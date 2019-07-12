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

    public void addMembers(View view) {

        Intent addMembersForm=new Intent(getApplicationContext(), AddMemberForm.class);
        startActivity(addMembersForm);

    }
}
