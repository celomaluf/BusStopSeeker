package br.com.celomaluf.bss.ws;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import br.com.celomaluf.bss.json.body.JsonBodyRoute;
import br.com.celomaluf.bss.json.body.JsonBodyStopDeparture;
import br.com.celomaluf.bss.json.data.ResponseJson;

public interface Request {

	@POST("/v1/queries/{queryName}/run")
	void response(@Header("Authorization") String auth,
				  @Header("X-AppGlu-Environment") String appGluEnvironment,
				  @Header("Content-Type") String contentType,
				  @Path("queryName") String queryName, 
				  @Body JsonBodyRoute body,
				  Callback<ResponseJson> cb);
	
	
	@POST("/v1/queries/{queryName}/run")
	void response(@Header("Authorization") String auth,
				  @Header("X-AppGlu-Environment") String appGluEnvironment,
				  @Header("Content-Type") String contentType,
				  @Path("queryName") String queryName, 
				  @Body JsonBodyStopDeparture body,
				  Callback<ResponseJson> cb);

}