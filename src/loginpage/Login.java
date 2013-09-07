package loginpage;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import tw.jackwu.ttu.cis.MainActivity;
import tw.jackwu.ttu.cis.R;
import android.R.color;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends Activity {
	ProgressDialog progressDialog;
	String sessionid;
	public static DefaultHttpClient httpClient;
	public static CookieStore cookieStore;
	public static HttpContext httpContext;
	public static String cookie = "";
	public static Header[] headers;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		findviews();
//		new Thread(downloadVertifyImage).start();
		submitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new LoginTask().execute();
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
		imageView = (ImageView)findViewById(R.id.vertifyImage);
		submitButton = (Button) findViewById(R.id.submit);
		accountText = (EditText) findViewById(R.id.account);
		passwordtText = (EditText) findViewById(R.id.password);
		vertifyCode = (EditText) findViewById(R.id.vertifyCode);
		storeInformation = (CheckBox) findViewById(R.id.checkBox1);
		warningMessage = (TextView) findViewById(R.id.warningmessage);
		get();
		
		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 10000); /// timeout is in millisecond
		HttpConnectionParams.setSoTimeout(httpParams, 10000); 
		httpClient = new DefaultHttpClient(httpParams);
		cookieStore = httpClient.getCookieStore();
		httpContext = new BasicHttpContext();
		httpContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
//		cookie="";
//		headers=null;
//		imageView.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				new Thread(downloadVertifyImage).start();
//			}
//		});
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
	EditText vertifyCode;
	CheckBox storeInformation;
	TextView warningMessage;
	ImageView imageView;

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
	
	class LoginTask extends AsyncTask<Void, Void, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progressDialog = ProgressDialog.show(Login.this, "登入中","請稍後...",true,true);
					
		}
		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			if(haveInternet())
			{
				LoginLogout loginLogout = new LoginLogout();
				try {
					if(loginLogout.login(accountText.getText().toString()
							, passwordtText.getText().toString()
							,vertifyCode.getText().toString()
							,sessionid, Login.this))return "LOGIN SUCCESS";
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return e.toString();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return e.toString();
				}
			}else {
				return "NO INTERNET";
			}
			return "登入失敗，確定你的帳密對嗎?";
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			progressDialog.dismiss();
			if(result.equals("LOGIN SUCCESS"))
			{
				//After Login
				List<Cookie> cookies = cookieStore.getCookies();
				for (int i = 0; i < cookies.size(); i++) {
				    
					cookie+=cookies.get(i).getName()+"="+cookies.get(i).getValue()+";";
				}
//				Log.v("aa", cookie.getDomain());
//				Log.v("aa", cookie.getName());
//				Log.v("aa", cookie.getValue());
			
				finish();
				store(storeInformation.isChecked());
				Intent intent = new Intent(Login.this,MainActivity.class);
				startActivity(intent);
			}
			else if(result.equals("NO INTERNET")){
				warningMessage.setText("請檢查網路連線");
			}else {
				warningMessage.setText(result);
				//new Thread(downloadVertifyImage).start();
			}

		}
		
	}
	Runnable downloadVertifyImage = new Runnable() {
		
		@Override
		public void run() {

			// TODO Auto-generated method stub
			try {
				String url = "http://stucis.ttu.edu.tw/securimage/securimage_show.php";
				String url2 = "http://stucis.ttu.edu.tw";
				HttpGet httpGet = new HttpGet(url);
				HttpResponse response = httpClient.execute(httpGet,httpContext);
				byte[] array = EntityUtils.toByteArray(response.getEntity());
		         final Bitmap bitmap = BitmapFactory.decodeByteArray(array, 0, array.length);
		         runOnUiThread(new Runnable() {
					public void run() {
						imageView.setImageBitmap(bitmap);
					}
				});

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		}
	};
}
