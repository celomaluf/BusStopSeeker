package br.com.celomaluf.bss.json.body;

public class JsonBodyStopDeparture {
	private ParamId params;
	
	public JsonBodyStopDeparture() {
		params = new ParamId();
	}

	public void addParamId(Integer paramId){
		params.setParamId(paramId);
	}

	public class ParamId {
		private Integer paramId;
		
		public ParamId(){
			
		}
		
		public ParamId(Integer paramId){
			this.setParamId(paramId);
		}

		public Integer getParamId() {
			return paramId;
		}

		public void setParamId(Integer paramId) {
			this.paramId = paramId;
		}

	}
}
