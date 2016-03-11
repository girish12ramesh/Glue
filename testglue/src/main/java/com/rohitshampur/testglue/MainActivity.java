package com.rohitshampur.testglue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rohitshampur.glue.Glue;
import com.rohitshampur.glue.StickToResource;
import com.rohitshampur.glue.StickToView;

public class MainActivity extends AppCompatActivity {

    @StickToView
    private TextView textView;

    @StickToView(R.id.button)
    private Button button;

    @StickToResource(R.string.app_name)
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Glue.stickTo(this);
        textView.setText("Testing glue");
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FragmentActivity.class));
            }
        });

    }
}
