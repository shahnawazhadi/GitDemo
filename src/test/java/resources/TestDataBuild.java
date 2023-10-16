package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddAdress;
import pojo.AddLocation;

public class TestDataBuild {
	
	public AddAdress addplacepayload(String name,String language,String address) {
		AddAdress ad=new AddAdress();
		ad.setName(name);
		ad.setPhone_number("(+91) 983 893 3937");
		ad.setAccuracy(50);
		ad.setAddress(address);
		ad.setWebsite("http://google.com");
		ad.setLanguage(language);
		AddLocation loc=new AddLocation();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		ad.setLocation(loc);
		List<String> ty=new ArrayList<String>();
		ty.add("shoe park");
		ty.add("shop");
		ad.setTypes(ty);
		return ad;
		
	}
	public String deletePlacePayload(String placeId) {
		return "{\r\n\"place_id\";\""+placeId+"\"\r\n}";
	}

}
