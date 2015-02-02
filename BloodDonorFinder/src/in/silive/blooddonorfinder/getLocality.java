package in.silive.blooddonorfinder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

public class getLocality {
	Context myContext;
	Geocoder geocoder;

	public void LocationFinder(Context context) {
		myContext = context;
		geocoder = new Geocoder(myContext);
	}

	public String getaddress(Double lat, Double longi) {
		try {
			geocoder = new Geocoder(myContext, Locale.getDefault());
		} catch (Exception e) {
			Log.d("gl", " " + e);
		}
		Address address;
//		String result = null;
		List<Address> list = null;
		try {
			list = geocoder.getFromLocation(lat, longi, 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		address = list.get(0);
		Log.d("location", " " + address.getLocality());
		return null;

	}
}
