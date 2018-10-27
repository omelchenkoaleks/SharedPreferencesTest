package com.omelchenkoaleks.sharedpreferencestest;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String SP_PERSON_DATA = "person_data";
    private static final String SP_FIRST_NAME = "person_first_name";
    private static final String SP_LAST_NAME = "person_last_name";
    private static final String SP_PERSON_AGE = "person_age_info";

    private static SharedPreferences spPerson;
    private static SharedPreferences.Editor editorPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
