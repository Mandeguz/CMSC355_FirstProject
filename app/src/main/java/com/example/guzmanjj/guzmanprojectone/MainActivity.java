package com.example.guzmanjj.guzmanprojectone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public final boolean TEST = false;//true; //defines testing mode of app
    private final int THE_MESSAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lockCheck("1"); //App starts locked

        final Button button = (Button) findViewById(R.id.unlock_btn);   //defines Unlock Button
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivityTwo.class);
                startActivityForResult(intent, THE_MESSAGE);            //Expecting result from unlock code
            }
        });
    }

    //not sure what requestCode is, got from AndroidStudio.com and Stack Overflow,
    //got rid of loop because it caused problems in code, left it in onActivityResult
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String lock_status = data.getStringExtra("value");
                lockCheck(lock_status); //checks status of lock and gives appropriate message
            }
        //}
    }

    public void lockCheck(String lock_status){                               //checks status of lock and gives appropriate message
        String MAIN = "";
        final TextView bool_text = (TextView) findViewById(R.id.return_val); //Possibly visible lock status
        bool_text.setText(lock_status);                                      //0=unlocked, 1=locked
        if(TEST) {  //check lock status while testing
            bool_text.setVisibility(View.VISIBLE);
        } else {
            bool_text.setVisibility(View.INVISIBLE);
        }
        final TextView main = (TextView) findViewById(R.id.main_text);       //defines main text
        String strx = (String) bool_text.getText();                          //grabbing lock status
        if(strx.equals("1")){   //locked
            MAIN = "Welcome to the App! The App is LOCKED!";
        } else {                //unlocked
            MAIN = "The App is Unlocked!";
        }
        main.setText(MAIN); //sets main text accordingly
    }
}
