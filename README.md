[ ![Download](https://api.bintray.com/packages/rohitshampur/maven/Glue/images/download.svg) ](https://bintray.com/rohitshampur/maven/Glue/_latestVersion) <a href="https://opensource.org/licenses/Apache-2.0" target="_blank"><img src="https://img.shields.io/badge/License-Apache_v2.0-blue.svg?style=flat"/></a> [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Glue-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/3264) <a href="http://www.methodscount.com/?lib=com.rohitshampur.glue%3Aglue%3A1.0.3"><img src="https://img.shields.io/badge/Methods and size-core: 35 | 18 KB-e91e63.svg"></img></a> <a href="http://developer.android.com/index.html" target="_blank"><img src="https://img.shields.io/badge/platform-android-green.svg"/></a> <a href="https://android-arsenal.com/api?level=14" target="_blank"> <a href="https://android-arsenal.com/api?level=14" target="_blank"><img src="https://img.shields.io/badge/API-15%2B-green.svg?style=flat"/></a> <a href="https://opensource.org/licenses/Apache-2.0" target="_blank">

<a href='https://bintray.com/rohitshampur/maven/Glue/view?source=watch' alt='Get automatic notifications about new "Glue" versions'><img src='https://www.bintray.com/docs/images/bintray_badge_color.png'></a>

# Glue
## A lightweight view and resource injection library for android 

###Usage
#####gradle dependency
Add below dependency to build.gradle

```
repositories {
    jcenter()
}

dependencies {
    compile 'com.rohitshampur.glue:glue:1.0.3'
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
        
        Glue.prepare(R.id.class) //Initialize glue like this

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
        
        Glue.stickTo(this); //Initialize
        
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
        
        Glue.stickTo(this, view);//Initialize
        
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

Copyright &copy; 2016 Rohit Shampur(rohitshampur)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


