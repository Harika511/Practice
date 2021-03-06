package com.skylka.ensieg.fragments;

import java.util.ArrayList;

import com.skylka.ensieg.R;
import com.skylka.ensieg.adapters.TimelineAdapter;
import com.skylka.ensieg.database.EnsiegDB;
import com.skylka.ensieg.model.EventModel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class HistoryFragment extends Fragment {
	ListView upcoming_listview;
	TextView nocards_tx;
	EnsiegDB database;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_upcoming, container, false);
		database = new EnsiegDB(getActivity());
		nocards_tx = (TextView) rootView.findViewById(R.id.no_cards_text_up);
		upcoming_listview = (ListView) rootView.findViewById(R.id.upcoming_listview);
		database.Open();
		ArrayList<EventModel> list = database.getHistoryCardsFromDB();
		database.close();
		if (list != null && list.size() > 0) {
			upcoming_listview.setVisibility(View.VISIBLE);
			nocards_tx.setVisibility(View.GONE);
			TimelineAdapter adapter = new TimelineAdapter(getActivity(), list);
			upcoming_listview.setAdapter(adapter);
		} else {
			upcoming_listview.setVisibility(View.GONE);
			nocards_tx.setVisibility(View.VISIBLE);
			nocards_tx.setText("No History cards..");

		}
		return rootView;
	}
}
