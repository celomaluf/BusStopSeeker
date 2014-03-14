package br.com.celomaluf.bss.json;

import br.com.celomaluf.bss.json.data.ResponseJson;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.util.Log;

public class BSSCallBackJSON implements Callback<ResponseJson> {

	private CallBackListener listener;
	private final static String messageSuccess = "<<REQUEST TO SERVER SUCCEEDED>>";

	public BSSCallBackJSON(CallBackListener listener) {
		this.listener = listener;
	}
	
	@Override
	public void failure(RetrofitError retrofitError) {
		Log.d("RETURN", retrofitError.getMessage(), retrofitError.getCause());
	}
	@Override
	public void success(ResponseJson responseJson, Response response) {
		Log.i("RETURN", messageSuccess + "<"+ response.getStatus() +">");
		listener.onSuccessResponse(responseJson);
	}
}
