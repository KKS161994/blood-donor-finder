package in.silive.blooddonorfinder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

	Dialogbox dbox;
	Spinner bgroupSpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button register = (Button) findViewById(R.id.Registerbutton);
		register.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openCounter;
				openCounter = new Intent(

				"in.silive.blooddonorfinder.REGISTER");
				startActivity(openCounter);
			}
		});
		bgroupSpinner = (Spinner) findViewById(R.id.BloodGroupspinner);
		ArrayAdapter<CharSequence> bgroup = ArrayAdapter.createFromResource(
				this, R.array.bGroup, android.R.layout.simple_spinner_item);
		bgroupSpinner.setAdapter(bgroup);
	}

	public void init() {
		if (connectivitycheck() == false) {
			Toast.makeText(this, "false", Toast.LENGTH_SHORT).show();
			ConnectivityManager conn = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
			dbox.dialogbox2(MainActivity.this, conn);
		} else {
			// Toast.makeText(this, "true", Toast.LENGTH_SHORT).show();

		}

	}

	public void Search(View view) {
		init();
		Location loc = null;
		String currentCity = "";
		try {
			CurrentLocation location = new CurrentLocation(this);
			loc = location.getLocation();
		} catch (Exception e) {
			Log.d("excep", " " + e);
		}

		Log.d("co ordinates",
				" " + loc.getLatitude() + "  " + loc.getLongitude());

		// getLocality city = new getLocality();
		// currentCity = city
		// .getaddress(loc.getLatitude(), loc.getLongitude());

		Geocoder geocoder;
		try {
			geocoder = new Geocoder(this, Locale.getDefault());
			Address address;
			String result = null;
			List<Address> list = null;
			try {
				list = geocoder.getFromLocation(loc.getLatitude(),
						loc.getLongitude(), 1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			address = list.get(0);
			result = address.getLocality();
			Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
			Log.d("location", " " + result);

		} catch (Exception e) {

			Log.d("Error city", " " + e);
		}

	}

	public boolean connectivitycheck() {
		ConnectivityManager conn = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
		Connectivity object = new Connectivity();
		return (object.isConnected(conn));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
