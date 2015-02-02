package in.silive.blooddonorfinder;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Register extends Activity {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	Spinner bgroupSpinner, genderSpinner;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		bgroupSpinner = (Spinner) findViewById(R.id.bGroup);
		genderSpinner = (Spinner) findViewById(R.id.gender);
		ArrayAdapter<CharSequence> sGroup = ArrayAdapter.createFromResource(
				this, R.array.bGroup, android.R.layout.simple_spinner_item);
		bgroupSpinner.setAdapter(sGroup);
		ArrayAdapter<CharSequence> sGender = ArrayAdapter.createFromResource(
				this, R.array.gender, android.R.layout.simple_spinner_item);
		genderSpinner.setAdapter(sGender);

	}

}
