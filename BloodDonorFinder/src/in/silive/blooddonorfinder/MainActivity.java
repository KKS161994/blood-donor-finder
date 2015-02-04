package in.silive.blooddonorfinder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

	Dialogbox dbox;
	Spinner bgroupSpinner;
	EditText location;
	String bloodGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button register = (Button) findViewById(R.id.Registerbutton);
		location = (EditText) findViewById(R.id.etLocation);
		Button login = (Button) findViewById(R.id.login);

		register.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openCounter;
				openCounter = new Intent("in.silive.blooddonorfinder.REGISTER");
				startActivity(openCounter);
			}
		});
		login.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openCounter;
				openCounter = new Intent("in.silive.blooddonorfinder.LOGIN");
				startActivity(openCounter);

			}
		});

		bgroupSpinner = (Spinner) findViewById(R.id.BloodGroupspinner);
		ArrayAdapter<CharSequence> bgroup = ArrayAdapter.createFromResource(
				this, R.array.bGroup, android.R.layout.simple_spinner_item);
		bgroupSpinner.setAdapter(bgroup);

		bgroupSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				bloodGroup = (String) bgroupSpinner.getItemAtPosition(arg2);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		init();
		location.setText(init());
	}

	public String init() {
		if (connectivitycheck() == false) {
			Toast.makeText(this, "false", Toast.LENGTH_SHORT).show();
			ConnectivityManager conn = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
			dbox.dialogbox2(MainActivity.this, conn);
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
