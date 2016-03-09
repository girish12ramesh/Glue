package com.rohitshampur.testglue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.rohitshampur.glue.Glue;
import com.rohitshampur.glue.StickToResource;
import com.rohitshampur.glue.StickToView;

public class MainActivity extends AppCompatActivity {

    @StickToView
    private TextView textView;

    @StickToResource(R.string.app_name)
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Glue.stickTo(this);
        textView.setText("Testing glue");
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, FragmentActivity.class));
    }
}
