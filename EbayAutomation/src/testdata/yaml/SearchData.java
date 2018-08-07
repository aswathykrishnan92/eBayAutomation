package testdata.yaml;

import utils.YamlUtils;

public class SearchData {
	public LoginData LoginDetails;
	public String searchSku;
	
	public static SearchData fetch(String key) {
		SearchData data=(SearchData)YamlUtils.loadYaml(key, System.getProperty("user.dir")+"/resources/search.yaml");
		if(data!=null) {
			return data;
		}else {
			return null;
		}
		
	}
}
