package com.example.nazia_000.account.classPack;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nazia_000.account.R;

import java.util.PriorityQueue;

public class ShowProfile {

    Activity context;
    Object obj = null;

    public ShowProfile() {}

    public ShowProfile(Activity context,Object obj){
        this.obj = obj;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listView = inflater.inflate(R.layout.users_view,null,true);

        TextView nameTxt = listView.findViewById(R.id.userListName);
        TextView nmbrTxt = listView.findViewById(R.id.userListNumber);
        TextView grpTxt = listView.findViewById(R.id.userListGrp);
        TextView ageTxt = listView.findViewById(R.id.userListAge);
        TextView locTxt = listView.findViewById(R.id.userListLoc);

        ProfilesClass profilesClass = new ProfilesClass();
        nameTxt.setText("Name: "+profilesClass.getname());
        nmbrTxt.setText("Contact Number: "+profilesClass.getnumber());
        grpTxt.setText("Blood Group: "+profilesClass.getblood_group());

        return listView;
    }


}
