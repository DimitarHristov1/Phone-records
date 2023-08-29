package com.a13118059;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.a13118059.Adapter.DataAdapter;
import com.a13118059.DataModel.Item_Database;
import java.util.ArrayList;

public class Contacts_Details extends AppCompatActivity {
    //Declare the variables that are needed
    ListView contact_list_list_view;
    ImageButton return_menu_button;
    DatabaseHelper myDb;
    DataAdapter dataAdapter;
    AlertDialog.Builder builder;
    private static ArrayList<Item_Database> dataList, userList;
    public static boolean IsActionMode = false;
    public static ActionMode action_mode = null;

    //Create the onCreate method (on "Contacts_Details" class startup)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_details);
        //Initialize the variables and call needed methods on startup
        return_menu_button = findViewById(R.id.return_button);
        contact_list_list_view = findViewById(R.id.contact_list);
        //Connect the multi choice mode listener with the listview, in other words, whey you long click on the listview activate the multi choice mode is active
        contact_list_list_view.setMultiChoiceModeListener(modeListener);
        //Set the choice mode for the listview to be multiple in a modal view, in other words, when you are in a mode when the multi choice mode is active
        contact_list_list_view.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        builder = new AlertDialog.Builder(this);
        myDb = new DatabaseHelper(this);
        dataList = new ArrayList<>();
        userList = new ArrayList<>();
        loadListViewItems();

        //On item click method for the listview
        contact_list_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //Declare the variables for the dialog which we will create below
                String name, phone;
                //Get the position of the clicked item
                Item_Database contact = dataList.get(position);
                //Get the name and the phone of the clicked row
                name = contact.getName();
                phone = contact.getPhone();
                //Create the alert dialog for to show the data of the clicked row
                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(Contacts_Details.this,R.style.MyDialog));
                builder.setTitle(name);
                builder.setMessage(phone);
                //Copy the contact name by clicking on the button
                builder.setPositiveButton("Copy contact name", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Store the name of the contact temporarily in the clipboard
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("text",name);
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(getApplicationContext(), "Contact name copied.", Toast.LENGTH_SHORT ).show();
                    }
                });
                //Copy the contact phone by clicking on the button
                builder.setNegativeButton("Copy contact phone", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Store the phone of the contact temporarily in the clipboard
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("text",phone);
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(getApplicationContext(), "Contact phone copied.", Toast.LENGTH_SHORT ).show();
                    }
                });
                //Create the alert dialog and show it
                builder.create();
                builder.show();
            }
        });

        //Create the method when you click on the return_menu_button
        return_menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Finish the activity on which you were before clicking on the back button
                finish();
                //Call the "onBackPressed()" function which is a system function
                onBackPressed();
            }
        });
    }

    //Create the method for loading the data into listview
    private void loadListViewItems() {
        dataList = myDb.getAllData();
        dataAdapter = new DataAdapter(this, dataList);
        contact_list_list_view.setAdapter(dataAdapter);
        dataAdapter.notifyDataSetChanged();
    }

    //Initialize the multi choice mode listener and create its methods
    AbsListView.MultiChoiceModeListener modeListener = new AbsListView.MultiChoiceModeListener() {

        //Method what to do when click on an item when multi choice mode listener is active
        @Override
        public void onItemCheckedStateChanged(ActionMode actionMode, int position, long l, boolean b) {
            //Get the position of the clicked item
            Item_Database contact = dataList.get(position);
            //Set the checkbox to "checked" and vice versa on click
            contact.setChecked(!contact.isChecked());
            //Display the checked items as a total count on the action mode title
            if (userList.contains(contact)) {
                userList.remove(contact);
                action_mode.setTitle(userList.size() + " contact selected...");
            }else {
                userList.add(contact);
                action_mode.setTitle(userList.size() + " contact selected...");
            }if(userList.size() == 0){
                action_mode.setTitle("");
            }if(userList.size() > 1){
                action_mode.setTitle(userList.size() + " contacts selected...");
            }
            //Notify the data change when item is clicked
            dataAdapter.notifyDataSetChanged();
        }
        //Create the method when multi choice mode listener is activated
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            //Show the "my_options_menu.xml" menu when multi choice mode listener is activated
            MenuInflater menuInflater = actionMode.getMenuInflater();
            menuInflater.inflate(R.menu.my_options_menu,menu);
            //Set the "IsActionMode" to true when multi choice mode listener is activated, which is used to display or not the checkboxes
            IsActionMode = true;
            //Set whether to display or not the title when multi choice mode listener is activated, which displays the items that are checked in total
            action_mode = actionMode;
            //Hide the "return_menu" button when multi choice mode listener is activated
            return_menu_button.setVisibility(View.GONE);
            return true;
        }

        //Create the method for refreshing an action mode's action menu whenever it is invalidated. It is not needed for the app so return is set to false!
        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }
        //Create method for executing whenever an action of the action menu is clicked
        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            //Create an alert dialog for the user to confirm the selected contact(s) deletion
            if (menuItem.getItemId() == R.id.delete_button){
                builder.setTitle("Caution");
                if(userList.size() > 1){
                    builder.setMessage("Delete selected contacts?");
                } else if (userList.size() == 1) {
                    builder.setMessage("Delete selected contact?");
                }
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    //Delete the data from database when the user confirms his choice
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for (Item_Database row : userList){
                            /*
                            //Delete data from array (optional)
                            dataList.remove(row);
                            */
                            //Delete data from database based on the checked item's id which is taken from the "getId()" method from the "Item_Database" class
                            myDb.deleteData(row.getId());
                        }
                        //Show a message for the successfully deleted contact(s)
                        if(userList.size() > 1){
                            Toast.makeText(getApplicationContext(), "Contacts successfully deleted.", Toast.LENGTH_SHORT ).show();
                        } else if (userList.size() == 1) {
                            Toast.makeText(getApplicationContext(), "Contact successfully deleted.", Toast.LENGTH_SHORT ).show();
                        }
                        //Call the finish method on the action mode parameter
                        actionMode.finish();
                        //If the database is empty return to "MainActivity" class
                        if(myDb.getAllData().isEmpty()){
                            //Initialize the intent variable with which the user will return to the main activity when he hits on the return_button
                            Intent intent = new Intent(Contacts_Details.this, MainActivity.class);
                            //Return the user to the main screen, without the ability to get back to the "Contacts_Details" class when they hit the return button on their phone
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    //Set what to do if the user cancels his choice
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        /*
                        //Call the finish method on the action mode parameter (optional)
                        actionMode.finish();
                         */
                    }
                });
                //Create the alert dialog and show it
                builder.create();
                builder.show();
                //Return true if the delete_button of the multi choice mode listener is clicked, which is an item in the "my_options_menu.xml" file
                return true;
            }
            else {
                //Return false if the delete_button of the multi choice mode listener is not clicked, which is an item in the "my_options_menu.xml" file
                return false;
            }
        }

        //Create the method when exiting (destroying) the multi choice mode listener
        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            //Refresh the activity, after exiting (destroying) the multi choice mode listener
            Intent intent = new Intent(Contacts_Details.this, Contacts_Details.class);
            finish();
            overridePendingTransition(0, 0);
            startActivity(intent);
            overridePendingTransition(0, 0);
            //Set the "IsActionMode" to false when multi choice mode listener is activated, which is used to display or not the checkboxes
            IsActionMode = false;
            //Set whether to display or not the title when multi choice mode listener is activated, which displays the items that are checked in total
            action_mode = null;
            //Clear the "userList" array from data, which is used to contain the data of the checked items
            userList.clear();
            //Show the "return_menu" button when multi choice mode listener is activated
            return_menu_button.setVisibility(View.VISIBLE);
        }
    };
}