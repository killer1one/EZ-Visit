package Util;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SelectableListAdapter<T> extends ArrayAdapter<T> {
	
	public SelectableListAdapter(Context context, int textViewResourceId, List<T> objects) {
		super(context, textViewResourceId, objects);
		//init();
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView currView = (TextView) super.getView(position, convertView, parent);
		
		
			        if (currView.isSelected()) {
			        } else {
			            currView.setWidth(parent.getWidth());
			        }
			        return currView;
	}
	
	
    public long getItemId(int position)
    {
        return position;
    }
	
	
	
}

