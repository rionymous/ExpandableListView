package com.androidrion.expandablelistview;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

public class ListAdapter extends BaseExpandableListAdapter {

    private Activity context;
    private Map<String, List<String>> ParentListItems;
    private List<String> Items;

    ListAdapter(Activity context, List<String> Items,
                Map<String, List<String>> ParentListItems) {
        this.context = context;
        this.ParentListItems = ParentListItems;
        this.Items = Items;
    }

    //Parent
    public Object getGroup(int groupPosition) {
        return Items.get(groupPosition);
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @SuppressLint("InflateParams")
    public View getGroupView(int groupPosition, boolean isExpanded, View ListView, ViewGroup parent) {
        String CoursesFull = (String) getGroup(groupPosition);
        if (ListView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert infalInflater != null;
            ListView = infalInflater.inflate(R.layout.parent_item, null);
        }
        TextView item = ListView.findViewById(R.id.textViewParent);
        item.setText(CoursesFull);
        return ListView;
    }

    public int getGroupCount() {
        return Items.size();
    }


    //Child
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public Object getChild(int groupPosition, int childPosition) {
        return Objects.requireNonNull(ParentListItems.get(Items.get(groupPosition))).get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @SuppressLint("InflateParams")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View ListView, ViewGroup parent) {
        final String CoursesName = (String) getChild(groupPosition, childPosition);
        LayoutInflater inflater = context.getLayoutInflater();

        if (ListView == null) {
            ListView = inflater.inflate(R.layout.child_item, null);
        }

        TextView item = ListView.findViewById(R.id.textViewChild);

        item.setText(CoursesName);
        return ListView;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public int getChildrenCount(int groupPosition) {
        return Objects.requireNonNull(ParentListItems.get(Items.get(groupPosition))).size();
    }


    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
