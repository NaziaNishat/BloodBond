package com.example.nazia_000.account.homePack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nazia_000.account.mapPack.MyLocation;
import com.example.nazia_000.account.R;

public class SearchPre extends AppCompatActivity {

    private Button searchPreMap,searchPreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pre);

        searchPreMap = findViewById(R.id.mapSearcgPre);
        searchPreList = findViewById(R.id.listSearchPre);

        searchPreMap.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchPre.this,MyLocation.class);
                startActivity(intent);
            }
        });

        searchPreList.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchPre.this,searchActivity.class);
                startActivity(intent);
            }
        });

    }
}
