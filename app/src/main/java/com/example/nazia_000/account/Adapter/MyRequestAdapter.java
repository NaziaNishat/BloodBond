package com.example.nazia_000.account.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nazia_000.account.R;
import com.example.nazia_000.account.classPack.RequestClass;

import java.util.List;

public class MyRequestAdapter extends ArrayAdapter<RequestClass> {

    private Activity context;
    private List<RequestClass> myRequList;


    public MyRequestAdapter(Activity context, List<RequestClass> myRequList) {
        super(context,R.layout.request_list,myRequList);
        this.context = context;
        this.myRequList = myRequList;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listView = inflater.inflate(R.layout.my_req_list,null,true);

        TextView nameTxt = listView.findViewById(R.id.myListName);
        TextView nmbrTxt = listView.findViewById(R.id.myListNumber);
        TextView grpTxt = listView.findViewById(R.id.myListGrp);
        TextView amountTxt = listView.findViewById(R.id.myListAmount);
        //TextView statTxt = listView.findViewById(R.id.myListStatus);

        RequestClass requestClass = myRequList.get(position);
        nameTxt.setText("Name: "+requestClass.getname());
        nmbrTxt.setText("Contact Number: "+requestClass.getnumber());
        grpTxt.setText("Blood Group: "+requestClass.getblood_Group());
        amountTxt.setText("Needed Amount: "+requestClass.getneeded_Amount());

        return listView;
    }
}

