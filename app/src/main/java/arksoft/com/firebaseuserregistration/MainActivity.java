package arksoft.com.firebaseuserregistration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText email, password;
    Button register;
    TextView link;
    private ProgressDialog progressBar;

    private FirebaseAuth firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebase=FirebaseAuth.getInstance();

        register = (Button) findViewById(R.id.buttonRegister);
        email = (EditText) findViewById(R.id.etemail);
        password = (EditText) findViewById(R.id.etpassword);
        link = (TextView) findViewById(R.id.signin);

        progressBar=new ProgressDialog(this);

        register.setOnClickListener(this);
        link.setOnClickListener(this);


    }

    public void registerUser() {

        String mail = email.getText().toString();
        String sifre = password.getText().toString();

        if (TextUtils.isEmpty(mail)) {
            //Email empty
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            return;
            //Stop function going further
        }
        if (TextUtils.isEmpty(sifre)) {

            Toast.makeText(this, "Please Enter Password ", Toast.LENGTH_SHORT).show();
            return;
        }
        progressBar.setMessage("Registering User ");
        progressBar.show();


        firebase.createUserWithEmailAndPassword(mail,sifre).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //Successful open the page
                    progressBar.cancel();
                    startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                    Toast.makeText(MainActivity.this, "Kayıt Başarılı ", Toast.LENGTH_SHORT).show();
                }else{
                    progressBar.cancel();
                    Toast.makeText(MainActivity.this, "Kayıt Başarısız Tekrar Deneyin ", Toast.LENGTH_SHORT).show();


                }


            }
        });



    }

    @Override
    public void onClick(View view) {
        if (view == register) {

            registerUser();
        } else if (view == link) {

            //Will open login activity here
            startActivity(new Intent(this,LoginActivity.class));
        }

    }
}
