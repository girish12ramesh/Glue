package com.rohitshampur.testglue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @StickToView
    private TextView textView;

    @StickToResource(R.string.app_name)
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Glue.stickTo(this, R.id.class);
        textView.setText("Testing glue");
        Toast.makeText(this,name,Toast.LENGTH_SHORT).show();
    }
}
