package loginpage;

import java.io.IOException;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import tw.jackwu.ttu.cis.MainActivity;
import tw.jackwu.ttu.cis.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends Activity {
	ProgressDialog progressDialog;
	Thread thread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		findviews();

		submitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (haveInternet()) {
					if (accountText.getText().equals("")
							|| passwordtText.getText().equals(""))
						;
					else {
						progressDialog = ProgressDialog.show(Login.this, "登入中",
								"請稍後...");
						progressDialog.setCancelable(true);
						Log.v("aa", "新緒");
						thread = new Thread(backprocess);
						thread.start();
					}
				} else {
					runOnUiThread(new Runnable() {
						public void run() {
							warningMessage.setText("沒有網路連線");
						}
					});
				}
			}
		});

		// 儲存帳密
		storeInformation
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						store(isChecked);
					}
				});

	}

	private void store(boolean isChecked) {
		if (isChecked) {
			Pref.setpref(Login.this, "account", accountText.getText()
					.toString());
			Pref.setpref(Login.this, "password", passwordtText.getText()
					.toString());
			Pref.setpref(Login.this, "ischeck", "true");
		} else {
			Pref.setpref(Login.this, "account", "");
			Pref.setpref(Login.this, "password", "");
			Pref.setpref(Login.this, "ischeck", "false");
		}
	}

	private void findviews() {
		// TODO Auto-generated method stub

		submitButton = (Button) findViewById(R.id.submit);
		accountText = (EditText) findViewById(R.id.account);
		passwordtText = (EditText) findViewById(R.id.password);
		storeInformation = (CheckBox) findViewById(R.id.checkBox1);
		warningMessage = (TextView) findViewById(R.id.warningmessage);
		get();
	}

	private void get() {
		if (Pref.getpref(Login.this, "account") != null) {
			accountText.setText(Pref.getpref(Login.this, "account"));
		}
		if (Pref.getpref(Login.this, "password") != null) {
			passwordtText.setText(Pref.getpref(Login.this, "password"));
		}
		if (Pref.getpref(Login.this, "ischeck") != null) {
			if (Pref.getpref(Login.this, "ischeck").equals("true")) {
				storeInformation.setChecked(true);
			} else {
				storeInformation.setChecked(false);
			}
		}
	}

	Button submitButton;
	EditText accountText;
	EditText passwordtText;
	CheckBox storeInformation;
	TextView warningMessage;

	// 判斷網路狀態
	private boolean haveInternet() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService("connectivity");
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo == null || !networkInfo.isConnected()) {
			return false;
		} else {
			return true;
		}
	}

	Runnable backprocess = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Log.v("aa", "連線中");
				LoginLogout loginLogout = new LoginLogout();
				if (loginLogout.login(accountText.getText().toString(),
						passwordtText.getText().toString(), Login.this)) {
					// 判斷是否正在登入程序
					if (progressDialog.isShowing()) {
						progressDialog.dismiss();
						// 跳至儲存帳密
						runOnUiThread(new Runnable() {
							public void run() {
								store(storeInformation.isChecked());
								warningMessage.setText("");
							}
						});

						Intent intent = new Intent(Login.this,
								MainActivity.class);
						startActivity(intent);
						finish();
					} else {
						Log.v("aa", "登入取消");
					}
				}
				// 登入不成功
				else {
					if (progressDialog.isShowing()) {
						progressDialog.dismiss();
					}
					runOnUiThread(new Runnable() {
						public void run() {
							warningMessage.setText("帳號或密碼錯囉!");
						}
					});
				}
			} catch (final IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.v("aa", e.toString());
				runOnUiThread(new Runnable() {
					public void run() {
						warningMessage.setText(e.toString() + "\n"
								+ "請檢查網路連線並重試一次");
						if (progressDialog.isShowing()) {
							progressDialog.dismiss();
						}
					}
				});

			}

		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.question:
			Intent intent = new Intent(android.content.Intent.ACTION_SEND);
			intent.putExtra(Intent.EXTRA_EMAIL,
					new String[] { "dm3352andy@gmail.com" });
			intent.putExtra(Intent.EXTRA_SUBJECT, "意見反饋-大同大學校務資訊系統app");
			intent.putExtra(Intent.EXTRA_TEXT, "\n---\n裝置:" + Build.MODEL
					+ "\nAndroid版本:" + Build.VERSION.RELEASE);
			intent.setType("message/rfc822");
			startActivity(Intent.createChooser(intent, "寫信給作者"));
			break;
		case R.id.about:
			openOptionDialog();
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	public void openOptionDialog() {
		new AlertDialog.Builder(Login.this).setTitle(R.string.about_title)
				.setMessage(R.string.about_content).show();
	}
}
