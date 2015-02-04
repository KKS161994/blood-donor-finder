package in.silive.blooddonorfinder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Register extends Activity {

	Spinner bgroupSpinner, genderSpinner;
	EditText district = null, state = null;
	Dialogbox dbox;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		bgroupSpinner = (Spinner) findViewById(R.id.bGroup);
		genderSpinner = (Spinner) findViewById(R.id.gender);
		district = (EditText) findViewById(R.id.district);
		state = (EditText) findViewById(R.id.state);
		ArrayAdapter<CharSequence> sGroup = ArrayAdapter.createFromResource(
				this, R.array.bGroup, android.R.layout.simple_spinner_item);
		bgroupSpinner.setAdapter(sGroup);
		ArrayAdapter<CharSequence> sGender = ArrayAdapter.createFromResource(
				this, R.array.gender, android.R.layout.simple_spinner_item);
		genderSpinner.setAdapter(sGender);
		String location = init();
		for (int i = 0; i < location.length(); i++) {
			if (location.charAt(i) == ',') {
				district.setText(location.substring(0, i));
				state.setText(location.substring(i + 2));
			}

		}
	}

	public String init() {
		if (connectivitycheck() == false) {
			Toast.makeText(this, "false", Toast.LENGTH_SHORT).show();
			ConnectivityManager conn = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
			dbox.dialogbox2(this, conn);
		}
		Location loc = null;
		String currentLocation = "";
		try {
			CurrentLocation location = new CurrentLocation(this);
			loc = location.getLocation();
		} catch (Exception e) {
			Log.d("excep", " " + e);
		}

		Geocoder geocoder;
		try {
			geocoder = new Geocoder(this, Locale.getDefault());
			Address address;
			List<Address> list = null;
			try {
				list = geocoder.getFromLocation(loc.getLatitude(),
						loc.getLongitude(), 1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			address = list.get(0);
			currentLocation = address.getAddressLine(0);
			Toast.makeText(this, currentLocation, Toast.LENGTH_SHORT).show();
			Log.d("error", "loc " + currentLocation);
		} catch (Exception e) {

			Log.d("Error city", " " + e);
		}
		return currentLocation;

	}

	public boolean connectivitycheck() {
		ConnectivityManager conn = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
		Connectivity object = new Connectivity();
		return (object.isConnected(conn));
	}

}
