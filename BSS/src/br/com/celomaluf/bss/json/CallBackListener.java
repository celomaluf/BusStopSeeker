package br.com.celomaluf.bss.json;

import br.com.celomaluf.bss.json.data.ResponseJson;

public abstract interface CallBackListener {
	void onSuccessResponse(ResponseJson responseJson);
}
