package com.example.nazia_000.account.Adapter;

import android.app.Activity;
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

public class RequestAdapter extends ArrayAdapter<RequestClass> {

    private Activity context;
    private List<RequestClass> requList;


    public RequestAdapter(Activity context, List<RequestClass> requList) {
        super(context,R.layout.request_list,requList);
        this.context = context;
        this.requList = requList;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listView = inflater.inflate(R.layout.request_list,null,true);

        TextView nameTxt = listView.findViewById(R.id.listName);
        TextView nmbrTxt = listView.findViewById(R.id.listNumber);
        TextView grpTxt = listView.findViewById(R.id.listGrp);
        TextView amountTxt = listView.findViewById(R.id.listAmount);
        TextView statTxt = listView.findViewById(R.id.listStatus);

        RequestClass requestClass = requList.get(position);
        nameTxt.setText(""+requestClass.getname());
        nmbrTxt.setText(""+requestClass.getnumber());
        grpTxt.setText(""+requestClass.getblood_Group());
        amountTxt.setText(""+requestClass.getneeded_Amount());
        statTxt.setText(""+requestClass.getstatus());

        return listView;
    }
}
