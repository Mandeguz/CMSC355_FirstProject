package com.example.guzmanjj.guzmanprojectone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivityTwo extends AppCompatActivity {
    public final boolean TEST = false;//true; //defines testing mode of app
    private String unlock_bool = "1";
    private String PASSWORD = "1234";
    private String CODE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);
        final TextView code_string = (TextView) findViewById(R.id.string_code); //Input combination to unlock app
        code_string.setText("");
        if(TEST) {  //check lock combination while testing
            code_string.setVisibility(View.VISIBLE);
        } else {
            code_string.setVisibility(View.INVISIBLE);
        }

        //Button Descriptions
        final Button btn_1 = (Button) findViewById(R.id.btn_1);
        btn_in(btn_1, "1", code_string);
        final Button btn_2 = (Button) findViewById(R.id.btn_2);
        btn_in(btn_2, "2", code_string);
        final Button btn_3 = (Button) findViewById(R.id.btn_3);
        btn_in(btn_3, "3", code_string);
        final Button btn_4 = (Button) findViewById(R.id.btn_4);
        btn_in(btn_4, "4", code_string);
        final Button btn_sub = (Button) findViewById(R.id.btn_sub);  //Submit button
        btn_sub.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //determine the lock status of the app
                if(CODE.equals(PASSWORD)){
                    unlock_bool = "0";
                } else{
                    unlock_bool = "1";
                }
                Intent intent = new Intent(MainActivityTwo.this, MainActivity.class);
                //Return the lock status of the app
                intent.putExtra("value",unlock_bool); //unlock_bool = 1 if locked, 0 if unlocked
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    public void btn_in(Button btn, final String label, final TextView add2str){  //Defines button
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                CODE += label;
                add2str.setText(CODE);
            }
        });
    }
}
