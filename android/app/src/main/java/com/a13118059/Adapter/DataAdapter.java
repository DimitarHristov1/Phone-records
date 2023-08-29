package com.a13118059.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import com.a13118059.Contacts_Details;
import com.a13118059.DataModel.Item_Database;
import com.a13118059.R;
import java.util.ArrayList;

public class DataAdapter extends BaseAdapter {
    //Declare the context and data list variables
    Context context;
    ArrayList<Item_Database> dataList;

    //Create the method for the listview data adapter
    public DataAdapter(Context context, ArrayList<Item_Database> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    //Get listview item id
    @Override
    public long getItemId(int position) {
        return position;
    }

    //Get listview item
    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    //Get the view of the items in the rows of the listview
    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Inflate the view with the "listview_item.xml" layout and initialize its items in the listview
        LayoutInflater inflater = ((Contacts_Details)context).getLayoutInflater();
        View row = inflater.inflate(R.layout.listview_item,parent,false);
        TextView text_name = row.findViewById(R.id.contact_name);
        TextView text_phone = row.findViewById(R.id.contact_phone);
        CheckBox checkBox = row.findViewById(R.id.checkBox);
        //Get the position corresponding to the selected item in the listview
        Item_Database contact = dataList.get(position);
        //Set the values of the items from the getter methods of the "Item_Database" class
        text_name.setText(contact.getName());
        text_phone.setText(contact.getPhone());
        checkBox.setChecked(contact.isChecked());
        //Show,hide checkbox on startup of the mode listener in the "Contact_Details" class
        if(Contacts_Details.IsActionMode){
            checkBox.setVisibility(View.VISIBLE);
        }
        else {
            checkBox.setVisibility(View.GONE);
        }
        return row;
    }

    //Get listview count
    @Override
    public int getCount() {
        return this.dataList.size();
    }

}
