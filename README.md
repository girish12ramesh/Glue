# Glue  [ ![Download](https://api.bintray.com/packages/rohitshampur/maven/Glue/images/download.svg) ](https://bintray.com/rohitshampur/maven/Glue/_latestVersion) [![License](https://img.shields.io/hexpm/l/plug.svg)](http://www.apache.org/licenses/LICENSE-2.0)
## A lightweight view and resource injection library for android 

###Usage
#####gradle dependency
Add below dependency to build.gradle

```
repositories {
    jcenter()
}

dependencies {
    compile 'com.rohitshampur.glue:glue:1.0.2'
}
```

#####Maven dependency

Add this dependency to pom.xml
```
<dependency>
  <groupId>com.rohitshampur.glue</groupId>
  <artifactId>glue</artifactId>
  <version>1.0.1</version>
  <type>pom</type>
</dependency>
```

To start using this library create a class MyApplication extending Application class.

```
package com.example.app;

import android.app.Application;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Glue.init(R.id.class) //Initialize glue like this

    }
}
```
Then add the name of Application sub-class to the AndroidManifest.xml
```
<application
        ----------------------
        ---------------------
        android:name=".MyApplication"
        ----------------------------
        ----------------------   >
```

Now start using the library like this

**In Activities** :
```
public class MainActivity extends AppCompatActivity {

    @StickToView
    private TextView textView;  //variable name must be same as the id in the layout xml
    
    @StickToView(R.id.textView1)
    private TextView text1;

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

```
**In Fragments**:
```
public class MyFragment extends Fragment {

    public MyFragment() {
    }

    @StickToView
    Button button;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Glue.stickTo(this, view);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "click!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
```
####NOTE : To use @StickToView without specifiying the id you need to declare the variable name as same as the id in the layout xml

This library is inspired from [**Android Annotations library**](https://android-arsenal.com/details/1/128) and 
[**InjectIO**](https://android-arsenal.com/details/1/2994)

###License

Copyright &copy; 2016 Rohit Shampur

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


