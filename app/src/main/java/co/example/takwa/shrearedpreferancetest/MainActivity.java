package co.example.takwa.shrearedpreferancetest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername, etUserPassword;
     private TextView tvResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        initView();
    }

    private void initView() {

        setContentView(R.layout.activity_main);
        etUsername = findViewById(R.id.txtId);
        etUserPassword =findViewById(R.id.passId);
        tvResult = findViewById(R.id.resultId);

    }

    public void btnClick(View view) {
        if(view.getId() == R.id.saveBtn){
            //save data

            saveData();
        }
        if(view.getId()== R.id.viewBtn){
            //view data

            viewData();
        }
    }

    private void viewData() {
        SharedPreferences sharedPreferences = getSharedPreferences("my_db", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("user","user not found");
        int pass = sharedPreferences.getInt("pass",-1);

        if(pass ==-1){
            Toast.makeText(this, "password not found", Toast.LENGTH_SHORT).show();

        }
        tvResult.setText("user :" + user +", pass :"+pass);
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("my_db", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String user = etUsername.getText().toString().trim();



        if(user.isEmpty()){
            etUsername.setError("please enter username");
            etUsername.requestFocus();
            return;
        }

        if(etUserPassword.getText().toString().trim().isEmpty()){
            etUserPassword.setError("enter password");
            etUsername.requestFocus();

            return;
        }

        int pass = Integer.parseInt(etUserPassword.getText().toString());



        editor.putString("user",user);
        editor.putInt("pass",pass);

        //save data

        boolean isSaved = editor.commit();
        if(isSaved == true){
            Toast.makeText(this, "Saved successful", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this, "something is wrong", Toast.LENGTH_SHORT).show();
        }


    }
}
