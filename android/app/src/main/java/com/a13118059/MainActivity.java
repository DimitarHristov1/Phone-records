package com.a13118059;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.app.AlertDialog;
import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    //Declare the variables that are needed
    DatabaseHelper myDb;
    EditText name,phone;
    Button add_button;
    Button view_button;

    //Create the onCreate method (on "MainActivity" class startup)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialize the variables and call needed methods on startup
        myDb = new DatabaseHelper(this);
        name = findViewById(R.id.editName);
        phone = findViewById(R.id.editPhone);
        add_button = findViewById(R.id.add_button);
        view_button = findViewById(R.id.view_button);
        AddData();
        viewAll();
    }

    //Create the method for inserting data that is typed by the user in the text fields provided on the main screen of the app
    public  void AddData() {
        add_button.setOnClickListener(
                new View.OnClickListener() {
                    @SuppressLint("SuspiciousIndentation")
                    @Override
                    public void onClick(View v) {
                        //Check the user typed data in the name and phone text fields are not blank or start with whitespace, using regex
                        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        if(name.getText().toString().matches("\\s*")){
                            Toast.makeText(MainActivity.this,"Insert a name!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(phone.getText().toString().matches("\\s*")){
                            Toast.makeText(MainActivity.this,"Insert a phone number!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (!name.getText().toString().matches("\\s*") && !phone.getText().toString().matches("\\s*")) {
                            boolean isInserted = myDb.insertData(name.getText().toString(), phone.getText().toString());
                            if(isInserted) {
                                Toast.makeText(MainActivity.this,"Contact Inserted",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(MainActivity.this, "Contact not Inserted", Toast.LENGTH_SHORT).show();
                            }
                            imm.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), 0);
                            name.setText("");
                            name.clearFocus();
                            phone.setText("");
                            phone.clearFocus();
                        }
                    }
                }
        );
    }

    //Create the method when the user clicks on the button to show the contacts from the main page of the app
    public void viewAll() {
        view_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (myDb.getAllData().isEmpty()) {
                            showMessage("Error", "No data in contact list!");
                        }
                        else
                            startActivity(new Intent(MainActivity.this, Contacts_Details.class));
                    }
                }
        );
    }

    //Create the method to show the message when no data is present in the database (used in the the method when the user clicks on the button to show the contacts)
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}