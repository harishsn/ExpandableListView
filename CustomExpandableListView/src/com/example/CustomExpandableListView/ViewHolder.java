package com.example.CustomExpandableListView;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ViewHolder {
	public ToggleButton ExpCol;
	public RelativeLayout toggleLayout;
	public RelativeLayout ChildLayout;
	public  ImageView LayoutBackground;
	TextView GroupHead;
	TextView ListHead;
	TextView ListDetail;
	Button directions;
	Button details;
	
	public static String GroupName;
    public static String ChildName;
    public static String list_info;
	
	 public ViewHolder(View v) {
		    this.LayoutBackground = (ImageView)v.findViewById(R.id.listbackground);
		    this.ChildLayout = (RelativeLayout)v.findViewById(R.id.list_Item_layout);
		    this.directions = (Button)v.findViewById(R.id.directions);
		    this.details = (Button)v.findViewById(R.id.details);
		    this.GroupHead = (TextView)v.findViewById(R.id.lblListHeader);
		    this.ListHead = (TextView)v.findViewById(R.id.lblListItem);
	        this.ListDetail = (TextView)v.findViewById(R.id.listItemInfo);
	        this.toggleLayout = (RelativeLayout)v.findViewById(R.id.toggle_layout);
	        this.ExpCol=(ToggleButton)v.findViewById(R.id.expand_colapse);
	 }
	 

}
