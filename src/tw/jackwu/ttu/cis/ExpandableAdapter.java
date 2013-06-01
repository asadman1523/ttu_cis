package tw.jackwu.ttu.cis;

import java.util.List;
import java.util.Map;

import tw.jackwu.ttu.cis.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

 class ExpandableAdapter extends BaseExpandableListAdapter {
	
	private Context context;
	List<Map<String, String>> groups;
	List<List<Map<String, String>>> childs;
	
	/*
	* 構造函數:
	* 參數1:context物件
	* 參數2:一級清單資料來源
	* 參數3:二級清單資料來源
	*/

	public ExpandableAdapter(Context context, List<Map<String, String>> groups, List<List<Map<String, String>>> childs)
	{
		this.groups = groups;
		this.childs = childs;
		this.context = context;
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childs.get(groupPosition).get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		String text = ((Map<String, String>) getChild(groupPosition, childPosition)).get("child");
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		//獲取二級清單對應的佈局檔, 並將其各元素設置相應的屬性
		LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(R.layout.child, null);
		TextView tv = (TextView) linearLayout.findViewById(R.id.child_tv);
		tv.setText(text);

		return linearLayout;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return childs.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return groups.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return groups.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		String text = groups.get(groupPosition).get("group");
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		//獲取一級清單佈局檔,設置相應元素屬性
		LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(R.layout.group, null);
		TextView textView = (TextView)linearLayout.findViewById(R.id.group_tv);
		textView.setText(text);

		return linearLayout;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

}