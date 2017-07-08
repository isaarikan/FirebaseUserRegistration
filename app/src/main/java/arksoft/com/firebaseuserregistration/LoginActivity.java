package arksoft.com.firebaseuserregistration;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by isaarikan on 6.07.2017.
 */

public class LoginActivity extends Activity implements View.OnClickListener {
    Button signin;
    EditText email, password;
    TextView txt;
    ProgressDialog dialog;
    FirebaseAuth firebase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        email = findViewById(R.id.signemail);
        password = findViewById(R.id.signpass);

        firebase = FirebaseAuth.getInstance();
        signin = findViewById(R.id.login);
        txt = findViewById(R.id.signup);

        signin.setOnClickListener(this);
        txt.setOnClickListener(this);


        dialog = new ProgressDialog(this);

        if (firebase.getCurrentUser() != null) {
            //Profile Activity

        }


    }

    @Override
    public void onClick(View view) {

        if (view == signin) {
            userLogin();


        } else if (view == txt) {

            startActivity(new Intent(this, MainActivity.class));

        }

    }

    private void userLogin() {
        String mail = email.getText().toString();
        String pass = password.getText().toString();


        if (TextUtils.isEmpty(mail)) {
            //Email empty
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            return;
            //Stop function going further
        }
        if (TextUtils.isEmpty(pass)) {

            Toast.makeText(this, "Please Enter Password ", Toast.LENGTH_SHORT).show();
            return;
        }

        dialog.setMessage("Login is processign");
        dialog.show();
        firebase.signInWithEmailAndPassword(mail, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        dialog.dismiss();
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));


                        }
                    }
                });


    }
}
