package com.hronik.expandablelistviewtest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.hronik.expandablelistviewtest.dummy.DummyContent;
import com.hronik.expandablelistviewtest.dummy.DummyContent.DummyItem;

/**
 * A list fragment representing a list of Items. This fragment also supports
 * tablet devices by allowing list items to be given an 'activated' state upon
 * selection. This helps indicate which item is currently being viewed in a
 * {@link ItemDetailFragment}.
 * <p>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class ItemExpandableListFragment extends Fragment {

	/**
	 * The tag used for logcat
	 */
	private final String TAG = getClass().getName();
	
	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * activated item position. Only used on tablets.
	 */
	private static final String STATE_ACTIVATED_POSITION = "activated_position";

	/**
	 * The fragment's current callback object, which is notified of list item
	 * clicks.
	 */
	private Callbacks mCallbacks = sDummyCallbacks;

	/**
	 * The current activated item position. Only used on tablets.
	 */
	private int mActivatedPosition = ListView.INVALID_POSITION;
	
	private ExpandableListView theELV; 
	private ExpandableListAdapter theELAdapter;
	
	/**
	 * A callback interface that all activities containing this fragment must
	 * implement. This mechanism allows activities to be notified of item
	 * selections.
	 */
	public interface Callbacks {
		/**
		 * Callback for when an item has been selected.
		 */
		public void onItemSelected(String id);
	}

	/**
	 * A dummy implementation of the {@link Callbacks} interface that does
	 * nothing. Used only when this fragment is not attached to an activity.
	 */
	private static Callbacks sDummyCallbacks = new Callbacks() {
		@Override
		public void onItemSelected(String id) {
		}
	};

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public ItemExpandableListFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
		
		// TODO: replace with a real list adapter.
//		setListAdapter(new ArrayAdapter<DummyContent.DummyItem>(getActivity(),
//				android.R.layout.simple_list_item_activated_1,
//				android.R.id.text1, DummyContent.ITEMS));
	}
	
	/**
	 * callback for the creation of the user interface for the view
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
	    Log.i(TAG, "onCreateView");
	    
	    View viewer = (View) inflater.inflate(R.layout.fragment_expandable_list_item, container, false);
	    theELV = (ExpandableListView) viewer.findViewById(R.id.fragment_expandable_list_item_elv);
	    theELAdapter = new MyExpandableListAdapter();
	    theELV.setAdapter(theELAdapter);
	    return viewer;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		// Restore the previously serialized activated item position.
//		if (savedInstanceState != null
//				&& savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
//			setActivatedPosition(savedInstanceState
//					.getInt(STATE_ACTIVATED_POSITION));
//		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// Activities containing this fragment must implement its callbacks.
		if (!(activity instanceof Callbacks)) {
			throw new IllegalStateException(
					"Activity must implement fragment's callbacks.");
		}

		mCallbacks = (Callbacks) activity;
	}

	@Override
	public void onDetach() {
		super.onDetach();

		// Reset the active callbacks interface to the dummy implementation.
		mCallbacks = sDummyCallbacks;
	}

//	@Override
//	public void onListItemClick(ListView listView, View view, int position,
//			long id) {
//		super.onListItemClick(listView, view, position, id);
//
//		// Notify the active callbacks interface (the activity, if the
//		// fragment is attached to one) that an item has been selected.
//		mCallbacks.onItemSelected(DummyContent.ITEMS.get(position).id);
//	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mActivatedPosition != ListView.INVALID_POSITION) {
			// Serialize and persist the activated item position.
			outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
		}
	}

//	/**
//	 * Turns on activate-on-click mode. When this mode is on, list items will be
//	 * given the 'activated' state when touched.
//	 */
//	public void setActivateOnItemClick(boolean activateOnItemClick) {
//		// When setting CHOICE_MODE_SINGLE, ListView will automatically
//		// give items the 'activated' state when touched.
//		getListView().setChoiceMode(
//				activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
//						: ListView.CHOICE_MODE_NONE);
//	}
//
//	private void setActivatedPosition(int position) {
//		if (position == ListView.INVALID_POSITION) {
//			getListView().setItemChecked(mActivatedPosition, false);
//		} else {
//			getListView().setItemChecked(position, true);
//		}
//
//		mActivatedPosition = position;
//	}
	
	public class MyExpandableListAdapter extends BaseExpandableListAdapter {

		private String[] groups = DummyContent.getTypeKeyArray();
		private DummyItem[][] children = DummyContent.getAllDummyItems(groups);
		
		@Override
		public Object getChild(int groupPosition, int childPosition) {
			if (isChildPositionValid(groupPosition, childPosition))
				return children[groupPosition][childPosition];
			else
				return null;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                View convertView, ViewGroup parent) {
			if (!isChildPositionValid(groupPosition, childPosition))
				return null;
            TextView textView = getGenericView();
            textView.setText(getChild(groupPosition, childPosition).toString());
            return textView;
        }

		@Override
		public int getChildrenCount(int groupPosition) {
			if (!isGroupPositionValid(groupPosition))
				return 0;
			
			DummyItem[] items = children[groupPosition];
			if (items != null)
				return items.length;
			else
				return 0;
		}

		@Override
		public Object getGroup(int groupPosition) {
			if (isGroupPositionValid(groupPosition))
				return groups[groupPosition];
			else
				return null;
		}

		@Override
		public int getGroupCount() {
			return groups.length;
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                ViewGroup parent) {
			if (!isGroupPositionValid(groupPosition))
				return null;
            TextView textView = getGenericView();
            textView.setText(getGroup(groupPosition).toString());
            return textView;
        }

		@Override
		public boolean hasStableIds() {
			return true;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return isChildPositionValid(groupPosition, childPosition);
		}
		
        public TextView getGenericView() {
            // Layout parameters for the ExpandableListView
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
            		ViewGroup.LayoutParams.MATCH_PARENT, 64);
            
            TextView textView = new TextView(getActivity());
            textView.setLayoutParams(lp);
            // Center the text vertically
            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
            // Set the text starting position
            textView.setPadding(36, 0, 0, 0);
            return textView;
        }
        
		/**
		 * simple helper method to check if groupPosition is in valid range
		 * 
		 * @param groupPosition index into groups and first dimension of children
		 * @return true if valid, false otherwise
		 */
		private boolean isGroupPositionValid(int groupPosition) {
			return (groups != null && 
					children != null && 
					groupPosition >= 0 &&
					groupPosition < groups.length &&
					groupPosition < children.length);
		}
		
		/**
		 * simple helper method to check validity on child position
		 * 
		 * @param groupPosition group of children index
		 * @param childPosition index of child within respective group
		 * @return true if valid, false otherwise
		 */
		private boolean isChildPositionValid(int groupPosition, int childPosition) {
			return (isGroupPositionValid(groupPosition) &&
					childPosition >= 0 &&
					childPosition < children[groupPosition].length);
		}
	}
}
