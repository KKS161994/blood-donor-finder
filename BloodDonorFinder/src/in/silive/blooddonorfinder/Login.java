package in.silive.blooddonorfinder;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		EditText userid = (EditText) findViewById(R.id.userID);
		EditText password = (EditText) findViewById(R.id.pass);
		Button login = (Button) findViewById(R.id.login);
	}

}
