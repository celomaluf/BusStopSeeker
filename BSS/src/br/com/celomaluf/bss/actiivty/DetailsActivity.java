package br.com.celomaluf.bss.actiivty;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import br.com.celomaluf.bss.R;
import br.com.celomaluf.bss.json.CallBackListener;
import br.com.celomaluf.bss.json.data.ResponseJson;
import br.com.celomaluf.bss.json.data.Row;
import br.com.celomaluf.bss.ws.ClientSide;

public class DetailsActivity extends Activity {

	private StopAdapter stopAdapter;
	private DepartureAdapter departureAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
	
		prepareActionBar();
		initAdapterSetListViewAdapter();

		CallBackListener stopCallBackListener = new CallBackListener() {
			@Override
			public void onSuccessResponse(ResponseJson responseJson) {
				stopAdapter.clear();
				stopAdapter.addAll(responseJson.getRows());
				
			}
		};
		
		CallBackListener departureCallBackListener = new CallBackListener() {
			@Override
			public void onSuccessResponse(ResponseJson responseJson) {
				departureAdapter.clear();
				departureAdapter.addAll(responseJson.getRows());
			}
		};
		Integer id = getIntent().getExtras().getInt("id");
		new ClientSide().findStopsByRouteId(id, stopCallBackListener);
		new ClientSide().findDeparturesByRouteId(id, departureCallBackListener);
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.details, menu);
		return true;
	}

	private class StopAdapter extends ArrayAdapter<Row> {

		public StopAdapter(Context context, int resource){
			super(context, resource);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView view = (TextView) super.getView(position, convertView, parent);
			Row item = getItem(position);
			view.setText(item.getSequence() + " - " + item.getName());
			return view;
		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			DetailsActivity.this.finish();
			return true;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private class DepartureAdapter extends ArrayAdapter<Row> {

		public DepartureAdapter(Context context, int resource){
			super(context, resource);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView view = (TextView) super.getView(position, convertView, parent);
			Row item = getItem(position);
			view.setText(item.getCalendar() +  " - " + item.getTime());
			return view;
		}

	}
	
	private void prepareActionBar() {
		String longName = getIntent().getExtras().getString("longName");
		String shortName = getIntent().getExtras().getString("shortName");
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayUseLogoEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(true); 
		actionBar.setHomeButtonEnabled(true);
		actionBar.setTitle(shortName + " - " + longName);
	}
	
	private void initAdapterSetListViewAdapter() {
		stopAdapter = new StopAdapter(this, android.R.layout.simple_list_item_1);
		ListView stopListView = (ListView) findViewById(R.id.listview_stops);
		stopListView.setAdapter(stopAdapter);
		
		departureAdapter = new DepartureAdapter(this, android.R.layout.simple_list_item_1);
		ListView departureListView = (ListView) findViewById(R.id.listview_departures);
		departureListView.setAdapter(departureAdapter);
	}
}