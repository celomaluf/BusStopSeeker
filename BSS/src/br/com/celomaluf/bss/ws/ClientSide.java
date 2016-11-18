package br.com.celomaluf.bss.ws;

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import br.com.celomaluf.bss.json.BSSCallBackJSON;
import br.com.celomaluf.bss.json.CallBackListener;
import br.com.celomaluf.bss.json.body.JsonBodyRoute;
import br.com.celomaluf.bss.json.body.JsonBodyStopDeparture;

public class ClientSide {

	private static final String FIND_ROUTES_BY_STOP_NAME = "findRoutesByStopName";
	private static final String FIND_STOPS_BY_ROUTE_ID = "findStopsByRouteId";
	private static final String FIND_DEPARTURES_BY_ROUTE_ID = "findDeparturesByRouteId";
	
	private static final String AUTH_VALUE = "Basic V0tENE43WU1BMXVpTThWOkR0ZFR0ek1MUWxBMGhrMkMxWWk1cEx5VklsQVE2OA=="; 
	private static final String ENV_VALUE = "staging";
	
	private static final String PERC = "%";
	private static final String CONTENT_VALUE = "application/json";

	public void findRoutesByStopName (String stopName, CallBackListener callBackListener) {		
		RestAdapter restAdapter = new RestAdapter.Builder()
			.setEndpoint("https://dashboard.appglu.com")
			.setLogLevel(LogLevel.FULL)
			.build();
		JsonBodyRoute jsonBody = new JsonBodyRoute();
		jsonBody.addStopName(PERC +  stopName + PERC);
		Request myPost = restAdapter.create(Request.class);
		myPost.response(AUTH_VALUE, ENV_VALUE, CONTENT_VALUE, FIND_ROUTES_BY_STOP_NAME, jsonBody, new BSSCallBackJSON(callBackListener));

	}
	
	public void findStopsByRouteId (Integer paramId, CallBackListener callBackListener) {		
		RestAdapter restAdapter = new RestAdapter.Builder()
			.setEndpoint("https://dashboard.appglu.com")
			.setLogLevel(LogLevel.FULL)
			.build();
		JsonBodyStopDeparture jsonBodyStop = new JsonBodyStopDeparture();
		jsonBodyStop.addParamId(paramId);

		Request myPost = restAdapter.create(Request.class);
		myPost.response(AUTH_VALUE, ENV_VALUE, CONTENT_VALUE, FIND_STOPS_BY_ROUTE_ID, jsonBodyStop, new BSSCallBackJSON(callBackListener));

	}
	
	public void findDeparturesByRouteId (Integer paramId, CallBackListener callBackListener) {		
		RestAdapter restAdapter = new RestAdapter.Builder()
			.setEndpoint("https://dashboard.appglu.com")
			.setLogLevel(LogLevel.FULL)
			.build();
		JsonBodyStopDeparture jsonBodyDeparture = new JsonBodyStopDeparture();
		jsonBodyDeparture.addParamId(paramId);

		Request myPost = restAdapter.create(Request.class);
		myPost.response(AUTH_VALUE, ENV_VALUE, CONTENT_VALUE, FIND_DEPARTURES_BY_ROUTE_ID, jsonBodyDeparture, new BSSCallBackJSON(callBackListener));
	}
}