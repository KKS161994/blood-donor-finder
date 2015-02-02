package in.silive.blooddonorfinder;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class CurrentLocation extends Service implements LocationListener {

	LocationManager locationManager;
	Location location;
	Double latitude, longitude;
	private Context mcontext;
	private static final long MIN_DISTANCE_FOR_UPDATE = 10;
	private static final long MIN_TIME_FOR_UPDATE = 1000 * 60 * 2;
	boolean isGPSenabled = false, isNetworkEnabled = false,
			canGetLocation = false;

	public CurrentLocation(Context context) {
		this.mcontext = context;
	}

	public Location getLocation() {
		try {
			locationManager = (LocationManager) mcontext
					.getSystemService(LOCATION_SERVICE);

		} catch (Exception e) {
			Log.d("error", "Exception " + e);
		}
		try {
			isGPSenabled = locationManager
					.isProviderEnabled(LocationManager.GPS_PROVIDER);
		} catch (Exception e) {
			Log.d("error", "Exception 2 " + e);
		}
		try {
			isNetworkEnabled = locationManager
					.isProviderEnabled(locationManager.NETWORK_PROVIDER);

			if (!isGPSenabled && !isNetworkEnabled) {
				Log.d("Service status", "Not connected");
			} else {
				this.canGetLocation = true;
				locationManager.requestLocationUpdates(
						LocationManager.NETWORK_PROVIDER, MIN_TIME_FOR_UPDATE,
						MIN_DISTANCE_FOR_UPDATE, this);
				Log.d("Network", "network");
				if (locationManager != null) {
					location = locationManager
							.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

					if (location != null) {
						latitude = location.getLatitude();
						longitude = location.getLongitude();

//						Log.d("co ordinates", " " + latitude + "  " + longitude);
						return location;
					}
				} else {
					Log.d("location", "null");
				}
			}
		} catch (Exception e) {
			Log.d("Error", "exception3  " + e);
		}
		return null;
	}

	@Override
	public void onLocationChanged(Location location) {
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
