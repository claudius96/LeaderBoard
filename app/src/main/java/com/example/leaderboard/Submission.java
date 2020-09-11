package com.example.leaderboard;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leaderboard.data.Result;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.text.Html.*;

public class Submission extends AppCompatActivity implements FirstDialogFragment.FirstDialogListener{
    private EditText firstName, lastName, emailAccount,githubAccount;
    private Button submitButton;
    private ImageButton backButton;
    private String first,last, email_acc, github;
    private TextView mTextView;
    private FragmentManager mFragmentManager;

    public Submission(){
        mFragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_submission);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        firstName = (EditText) findViewById(R.id.first_name);
        lastName = (EditText) findViewById(R.id.last_name);
        emailAccount = (EditText) findViewById(R.id.email);
        githubAccount = (EditText) findViewById(R.id.github);
        submitButton = (Button) findViewById(R.id.submit);
        backButton = (ImageButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mTextView = (TextView) findViewById(R.id.textView);
        String htmlString="<u>Project Submission</u>";
        mTextView.setText(fromHtml(htmlString));


        first = firstName.getText().toString();
        last = lastName.getText().toString();
        email_acc = emailAccount.getText().toString();
        github = githubAccount.getText().toString();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();

                FirstDialogFragment firstDialogFragment = FirstDialogFragment.newInstance();
                firstDialogFragment.show(fm,"log in");

            }
        });

            }


    @Override
    public void submit(String email_acc, String first, String last, String github) {
        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .submit(email_acc,first,last,github);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    //String s = response.body().toString();
                if(!response.isSuccessful()){
                    int Ok = response.code();
                    successful();


                }else {
                    failure();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                failure();
                Toast.makeText(Submission.this,t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });



    }
    public void cancel(){

        Toast.makeText(Submission.this, "Not successful", Toast.LENGTH_SHORT);

    }
    public void yes(){
        submit(first,last, email_acc,github);
        Toast.makeText(Submission.this, "Successful",Toast.LENGTH_SHORT);

    }
   public void failure(){
       Failure failure = Failure.newInstance();
       failure.show(mFragmentManager,"");
   }
   public void successful(){
       Successful successful = Successful.newInstance();
       successful.show(mFragmentManager,"log in");

    }
}
