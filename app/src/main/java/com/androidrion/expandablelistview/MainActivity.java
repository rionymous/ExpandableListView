package com.androidrion.expandablelistview;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    List<String> ChildList;
    Map<String, List<String>> ParentListItems;
    ExpandableListView expandablelistView;

    List<String> ParentList = new ArrayList<>();

    {
        ParentList.add("SOCIAL MEDIA");
        ParentList.add("WEBSITE");
    }

    String[] socialmediaName = {
            "Facebook", "Instagram", "Twitter", "LINE",
            "VK", "KakaoTalk", "Telegram", "SnapChat",
            "Vine", "Viber", "Whatsapp", "YouTube",
            "ok.ru"
    };
    String[] websiteName = {
            "Reddit", "Foursquare", "LinkedIn", "Quora",
            "Flickr", "Twitch", "GitHub", "Behance",
            "Steam", "Pinterest", "Tumblr"
    };
    String[] ByDefalutMessage = {"Not Found"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParentListItems = new LinkedHashMap<>();

        for (String HoldItem : ParentList) {
            if (HoldItem.equals("SOCIAL MEDIA")) {
                loadChild(socialmediaName);
            } else if (HoldItem.equals("WEBSITE"))
                loadChild(websiteName);
            else
                loadChild(ByDefalutMessage);

            ParentListItems.put(HoldItem, ChildList);
        }

        expandablelistView = findViewById(R.id.expandListView);
        final ExpandableListAdapter expListAdapter = new ListAdapter(this, ParentList, ParentListItems);
        expandablelistView.setAdapter(expListAdapter);

        expandablelistView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                final String selected = (String) expListAdapter.getChild(
                        groupPosition, childPosition);

                Toast.makeText(getBaseContext(), selected, Toast.LENGTH_LONG)
                        .show();

                return true;
            }
        });
    }

    private void loadChild(String[] ParentElementsName) {
        ChildList = new ArrayList<>();
        Collections.addAll(ChildList, ParentElementsName);
    }
}
