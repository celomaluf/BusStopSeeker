package br.com.celomaluf.bss.json.body;


public class JsonBodyRoute {
	private StopName params;
	
	public JsonBodyRoute() {
		params = new StopName();
	}

	public void addStopName(String stopName){
		params.setStopName(stopName);
	}
	
	public StopName getParams() {
		return params;
	}

	public void setParams(StopName params) {
		this.params = params;
	}

	public class StopName {
		private String stopName;
		
		public StopName(){
			
		}
		
		public StopName(String stopName){
			this.setStopName(stopName);
		}

		public String getStopName() {
			return stopName;
		}

		public void setStopName(String stopName) {
			this.stopName = stopName;
		}
	}
}
