package com.omelchenkoaleks.sharedpreferencestest;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String SP_PERSON_DATA = "person_data";
    private static final String SP_FIRST_NAME = "person_first_name";
    private static final String SP_LAST_NAME = "person_last_name";
    private static final String SP_PERSON_AGE = "person_age_info";

    private static SharedPreferences spPerson;
    private static SharedPreferences.Editor editorPerson;

    ConstraintLayout mainScreen;

    EditText etFirstName;
    EditText etLastName;
    EditText etPersonAge;

    String firstName;
    String lastName;
    String personAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spPerson = getSharedPreferences(SP_PERSON_DATA, Context.MODE_PRIVATE);

        mainScreen = findViewById(R.id.main_screen);
        etFirstName = findViewById(R.id.editTextWrite);
        etLastName = findViewById(R.id.editTextRead);
        etPersonAge = findViewById(R.id.editTextAge);

        if (makeColor() != getResources().getColor(R.color.colorWhite)) {
            mainScreen.setBackgroundColor(makeColor());
        }
    }

    // ЗАПИСЫВАЕМ
    public void onWrite(View view) {

        firstName = etFirstName.getText().toString().trim();
        lastName = etLastName.getText().toString().trim();
        personAge = etPersonAge.getText().toString().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || personAge.isEmpty() ||
                personAge.equals("0") || personAge.equals("00")) {

            Toast.makeText(MainActivity.this, "Внесите данные", Toast.LENGTH_SHORT).show();
        } else {
            // конвертация в Integer
            Integer personAgeValue = Integer.parseInt(personAge);
            // запись SharedPreferences
            editorPerson = spPerson.edit();
            editorPerson.putString(SP_FIRST_NAME, firstName);
            editorPerson.putString(SP_LAST_NAME, lastName);
            editorPerson.putInt(SP_PERSON_AGE, personAgeValue);
            editorPerson.apply();

            etFirstName.getText().clear();
            etLastName.getText().clear();
            etPersonAge.getText().clear();

            Toast.makeText(MainActivity.this, "Данные внесены", Toast.LENGTH_SHORT).show();
        }
    }

    // ЧИТАЕМ
    public void onRead(View view) {
        String firstNameShow = spPerson.getString(SP_FIRST_NAME, "Нет данных");
        String lastNameShow = spPerson.getString(SP_LAST_NAME, "Нет данных");
        Integer personAgeShow = spPerson.getInt(SP_PERSON_AGE, 0);

        Toast.makeText(MainActivity.this, "Имя: " + firstNameShow + "\n" +
            "Фамилия: " + lastNameShow + "\n" + "Возраст: " +
                personAgeShow, Toast.LENGTH_SHORT).show();
    }

    // УДАЛЯЕМ
    public void onDelete(View view) {

        editorPerson = spPerson.edit();

        if (spPerson.contains(SP_FIRST_NAME) || spPerson.contains(SP_LAST_NAME) ||
                spPerson.contains(SP_PERSON_AGE)) {

        // удаляем все SharePreferences
        editorPerson.clear();

        // если нужно удалить отдельное значение, например, Имя
        // editor.remove(SP_FIRST_NAME)

        editorPerson.apply();
        Toast.makeText(MainActivity.this, "Запись очищена", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(MainActivity.this, "Нет данных", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_white:
                mainScreen.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                saveColor(getResources().getColor(R.color.colorWhite));
                return true;
            case R.id.menu_yellow:
                mainScreen.setBackgroundColor(getResources().getColor(R.color.colorYellow));
                saveColor(getResources().getColor(R.color.colorYellow));
                return true;
            case R.id.menu_green:
                mainScreen.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                saveColor(getResources().getColor(R.color.colorGreen));
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    private void saveColor(int color) {
        SharedPreferences spColor = getSharedPreferences("screen_color", MODE_PRIVATE);
        SharedPreferences.Editor editorColor = spColor.edit();
        editorColor.putInt("background_color", color);
        editorColor.apply();
    }

    private int makeColor() {
        SharedPreferences spColor = getSharedPreferences("screen_color", MODE_PRIVATE);
        return spColor.getInt("background_color", getResources().getColor(R.color.colorWhite));
    }
}
