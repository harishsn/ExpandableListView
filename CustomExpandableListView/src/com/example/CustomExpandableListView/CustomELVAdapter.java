package com.example.CustomExpandableListView;



import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.Animation.AnimationListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.ExpandableListAdapter;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

public class CustomELVAdapter extends BaseExpandableListAdapter implements ExpandableListAdapter {
	
	private LayoutInflater vi;
	private String[][] data;
    private String [][] listinfo;
    private String[] groupname;
    private int[] ImgBckgrnd;
    private Context context;
    BounceInterpolator   bounceInterpolator;
    View v;
  
    
	
	
    private static final int GROUP_ITEM_RESOURCE = R.layout.list_group;
    private static final int CHILD_ITEM_RESOURCE = R.layout.list_item;
    
    
    public CustomELVAdapter(Context context, Activity activity, String[] groupname,  int[] ImgBckgrnd, String [][] listinfo, String[][] data){
    	this.context = context;
    	this.groupname = groupname;
    	this.ImgBckgrnd = ImgBckgrnd;
    	this.listinfo = listinfo;
    	this.data = data;
    	
    	vi = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	
        bounceInterpolator = new BounceInterpolator();
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
    	 String child = getChild(groupPosition, childPosition);
         String list = getList(groupPosition, childPosition);
          v = convertView;
         v = vi.inflate(CHILD_ITEM_RESOURCE, null);
         final ViewHolder  holder = new ViewHolder(v);
      
         
         if (child != null) {      
             
                
                holder.ExpCol.setFocusable(false);
                
                /**
                 *   TO SET CHILDLIST HEAD AND DETAIL *
                 *   */
                
                
                holder.ListHead.setText(Html.fromHtml(child));
                holder.ListDetail.setText(Html.fromHtml(list));
                
                
                  /** EXPAND AND COLAPSE SECOND LEVEL CHILD LAYOUT WITH BOUNCE ANIMATION ;
                    * DEFINE SLIDEUP AND SLIDEDOWN ANIMATION PROPERTIES (SEE res/anim FOLDER) ;
                    *             *PLAY WITH LAYOUT VISIBILITY PROPERTY ; 
                   **/
                
                
                final Animation slidedown = AnimationUtils.loadAnimation(v.getContext(), R.anim.slide_down);
                final Animation slideup = AnimationUtils.loadAnimation(v.getContext(), R.anim.slide_up);
                
               
                
                /**SET BOUNCE INTERPOLATOR TO SLIDEDOWN**/
                slidedown.setInterpolator(bounceInterpolator);
                
                
                slideup.setAnimationListener(new AnimationListener() {

	                @Override
	                public void onAnimationStart(Animation animation) {}

	                @Override
	                public void onAnimationRepeat(Animation animation) {}

	                @Override
	                public void onAnimationEnd(Animation animation) {
	                	holder.toggleLayout.setVisibility(View.GONE) ;
	                }});   
                
                
                
                /**
                 * EXPANDING AND COLAPSING SECOND LEVEL CHILD
                 * **/
                holder.ExpCol.setOnCheckedChangeListener(new OnCheckedChangeListener(){

    				@Override
    				public void onCheckedChanged(CompoundButton buttonView,
    						boolean isChecked) {
    					// TODO Auto-generated method stub
    					
    					
    				
    					if(holder.ExpCol.isChecked()){	
    						holder.toggleLayout.startAnimation(slidedown);	
    					    holder.toggleLayout.setVisibility(View.VISIBLE);
    					
    					}else{
    						
    						holder.toggleLayout.startAnimation(slideup);
    						
    					}
    				}
                	
                });
                
                
            /** 
             * ON CLICK LISTENER FOR CHILD 
             * **/
                holder.ChildLayout.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(holder.ExpCol.isChecked()){
							holder.ExpCol.setChecked(false);
						}else{ 
							holder.ExpCol.setChecked(true);
				       }
						
					}
			     });
                
                
                /**
                 * ONCLICK METHODS FOR SECOND LEVEL CHILD BUTTONS
                 * **/
                holder.directions.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						//DO SOMETHING
						
						Toast.makeText(context,"GET DIRECTIONS",Toast.LENGTH_SHORT).show();
						
					}
                	
                });
                
                holder.details.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						//DO SOMETHING 
						
						Toast.makeText(context,"GET DETAILS",Toast.LENGTH_SHORT).show();
					}
                	
                });
                
          }
    
    	return v;
    }
    
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if(v==null){
        v = vi.inflate(GROUP_ITEM_RESOURCE, null);
        holder = new ViewHolder(v);
        v.setTag(holder);
        }else{
        	holder = (ViewHolder) v.getTag();
        }
        
        if (getGroupName(groupPosition) != null) {
          
           /**SET GROUP HEAD TEXT**/
            holder.GroupHead.setText(getGroupName(groupPosition));
            
            /**SET IMAGE BACKGROUND
             * DO NOT LOAD IMAGES ON UI THREAD
             * USE ASYNCTASK TO LOAD IMAGES FROM WEB **/
            
            holder.LayoutBackground.setBackgroundResource(getImage(groupPosition));
            }
        return v;
    }
    
    
    public int getImage(int groupPosition){
 		return ImgBckgrnd[groupPosition];
     }
     
     public String getGroupName(int groupPosition){
   		return groupname[groupPosition];
       }

	@Override
	public String getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return data[groupPosition][childPosition];
	}
	
	public String getList(int groupPosition, int childPosition){
    	return listinfo[groupPosition][childPosition];
    	
    }

	@Override
    public int getChildrenCount(int groupPosition) {
        return data[groupPosition].length;
    }
	
	public int getGroupCount() {
        return groupname.length;
    }
    
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    
    @Override
	 public long getChildId(int groupPosition, int childPosition) {
	        return childPosition;
	    }
    
    @Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

    @Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

    public String getGroup(int groupPosition) {
        return "group-" + groupPosition;
    }

	


}
