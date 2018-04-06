package com.abbott.launchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by jinyabo on 6/4/2018.
 */

public class SingleTaskC extends Activity implements View.OnClickListener{
    Button btnA, btnB, btnC, btnD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);

        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);
        TextView tv = findViewById(R.id.tv_title);
        tv.setText(getClass().getName().toString());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnA:
                startActivity(new Intent(this,StandardA.class));
                break;
            case R.id.btnB:
                startActivity(new Intent(this,SingleTopB.class));
                break;

            case R.id.btnC:
                startActivity(new Intent(this,SingleTaskC.class));
                break;
            case R.id.btnD:
                startActivity(new Intent(this,SingleInstanceD.class));
                break;
        }
    }
}
