package br.com.celomaluf.bss.actiivty;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import br.com.celomaluf.bss.R;
import br.com.celomaluf.bss.json.CallBackListener;
import br.com.celomaluf.bss.json.data.ResponseJson;
import br.com.celomaluf.bss.json.data.Row;
import br.com.celomaluf.bss.ws.ClientSide;

public class BusListActivity extends Activity implements OnItemClickListener, OnClickListener, CallBackListener {


	private RouteAdapter routeAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bus_list);
		routeAdapter = new RouteAdapter(this, android.R.layout.simple_list_item_1);
		ListView busList = (ListView) findViewById(R.id.listView1);
		busList.setOnItemClickListener(this);
		busList.setAdapter(routeAdapter);
		ImageButton searchButton = (ImageButton) findViewById(R.id.imageButton1);
		searchButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.bus_list, menu);
		return true;
	}

	private class RouteAdapter extends ArrayAdapter<Row> {

		public RouteAdapter(Context context, int resource){
			super(context, resource);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView view = (TextView) super.getView(position, convertView, parent);
			Row item = getItem(position);
			view.setText(item.getShortName() + " - " + item.getLongName());
			return view;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View itemView, int position, long itemId) {
		Intent intent = new Intent(BusListActivity.this, DetailsActivity.class);
		Row item = routeAdapter.getItem(position);
		intent.putExtra("id", item.getId());
		intent.putExtra("shortName", item.getShortName());
		intent.putExtra("longName", item.getLongName());
		startActivity(intent);
	}

	@Override
	public void onSuccessResponse(ResponseJson responseJson) {
		routeAdapter.clear();
		routeAdapter.addAll(responseJson.getRows());
	}

	@Override
	public void onClick(View v) {
		EditText editText = (EditText) findViewById(R.id.editText1);
		new ClientSide().findRoutesByStopName(editText.getText().toString(), BusListActivity.this);
	}
}