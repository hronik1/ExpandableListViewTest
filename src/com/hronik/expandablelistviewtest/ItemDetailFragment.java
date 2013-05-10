package com.hronik.expandablelistviewtest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hronik.expandablelistviewtest.dummy.DummyContent;

/**
 * A fragment representing a single Item detail screen. This fragment is either
 * contained in a {@link ItemExpandableListActivity} in two-pane mode (on tablets) or a
 * {@link ItemDetailActivity} on handsets.
 */
public class ItemDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The fragment argument representing the type if that this fragment
	 * represents
	 */
	public static final String ARG_TYPE_ID = "type_id";
	
	/**
	 * The dummy content this fragment is presenting.
	 */
	private DummyContent.DummyItem mItem;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public ItemDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Bundle arguments = getArguments();
		if (arguments == null)
			return;
		
		String typeKey = arguments.getString(ARG_TYPE_ID);
		String itemKey = arguments.getString(ARG_ITEM_ID);
		if (itemKey != null && typeKey != null) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = DummyContent.getItem(typeKey, itemKey);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_item_detail,
				container, false);

		// Show the dummy content as text in a TextView.
		if (mItem != null) {
			((TextView) rootView.findViewById(R.id.item_detail))
					.setText(mItem.content);
		}

		return rootView;
	}
}
