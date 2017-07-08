package arksoft.com.firebaseuserregistration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by isaarikan on 6.07.2017.
 */

public class ProfileActivity extends Activity   implements View.OnClickListener{
    private FirebaseAuth firebaseAuth;
    private Button logout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        firebaseAuth=FirebaseAuth.getInstance();
        logout=findViewById(R.id.logout);
        logout.setOnClickListener(this);

        if(firebaseAuth.getCurrentUser()==null){
            finish();
            //User not logged
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }

        FirebaseUser user=firebaseAuth.getCurrentUser();



    }

    @Override
    public void onClick(View view) {

        if(view==logout){

            firebaseAuth.signOut();
            startActivity(new Intent(this,LoginActivity.class));
        }


    }
}
