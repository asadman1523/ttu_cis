package loginpage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import tw.jackwu.ttu.cis.MainActivity;
import tw.jackwu.ttu.cis.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
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
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends Activity {
	//ttucis
	ProgressDialog progressDialog;
	String sessionid;
	static DefaultHttpClient httpClient;
	CookieStore cookieStore;
	static HttpContext httpContext;
	private static String cookie;
	private static Header[] headers;
	private NetworkStatus networkStatus;
	
	
	
	public static String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		//手機板網頁
		TextView ttuLink = (TextView)findViewById(R.id.linkBut);
		ttuLink.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://stucis.ttu.edu.tw/mobile/mobilelogin.php"));
				startActivity(intent);
			}
		});
		networkStatus = new NetworkStatus(this);//wifi info
		findviews();
//		new Thread(downloadVertifyImage).start();
		submitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new LoginTask().execute();
			}
		});
		
		submitButton2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
		        if (networkStatus.getSSID().compareTo(getString(R.string.ssid)) != 0) {
		            Toast.makeText(Login.this, getString(R.string.err_ssid_msg), Toast.LENGTH_LONG).show();
		        }else{
				
			      final ProgressDialog progressDialog;
			      progressDialog = ProgressDialog.show(Login.this, "Log In", "Please wait...");
			      
			      new Thread() {
			            @Override
			            public void run() {
			                Resources resources = getResources();

			                String url = getString(R.string.url);
			                String[] params_name = resources.getStringArray(R.array.params_name);
			                String[] params_value = resources.getStringArray(R.array.params_value);
			                params_value[0] = accountText2.getText().toString();
			                params_value[1] = passwordtText2.getText().toString();

			                if (params_name.length != params_value.length)
			                    Log.e(getString(R.string.app_name), "Config file error!");

			                List<BasicNameValuePair> parms = new LinkedList<BasicNameValuePair>();

			                for (int i = 0; i < params_name.length; i++) {
			                    parms.add(new BasicNameValuePair(params_name[i], params_value[i]));
			                }

			                try {
			                    HttpPost httpPost = new HttpPost(url);
			                    httpPost.setEntity(new UrlEncodedFormEntity(parms, "UTF-8"));
			                    new DefaultHttpClient().execute(httpPost);

			                } catch (UnsupportedEncodingException e) {
			                    e.printStackTrace();
			                } catch (ClientProtocolException e) {
			                    // TODO Auto-generated catch block
			                    e.printStackTrace();
			                } catch (IOException e) {
			                    // TODO Auto-generated catch block
			                    e.printStackTrace();
			                }

			                progressDialog.dismiss();
			               // Toast.makeText(Login.this, "Login Success!", Toast.LENGTH_LONG).show();
			                finish();

			            };
			        }.start();
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

		// 儲存帳密
		storeInformation2
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						store2(isChecked);
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
	
	private void store2(boolean isChecked) {
		if (isChecked) {
			Pref.setpref(Login.this, "account2", accountText2.getText()
					.toString());
			Pref.setpref(Login.this, "password2", passwordtText2.getText()
					.toString());
			Pref.setpref(Login.this, "ischeck2", "true");
		} else {
			Pref.setpref(Login.this, "account2", "");
			Pref.setpref(Login.this, "password2", "");
			Pref.setpref(Login.this, "ischeck2", "false");
		}
	}

	private void findviews() {
		// TODO Auto-generated method stub
		submitButton = (Button) findViewById(R.id.submit);
		accountText = (EditText) findViewById(R.id.account);
		passwordtText = (EditText) findViewById(R.id.password);
		storeInformation = (CheckBox) findViewById(R.id.checkBox1);
		warningMessage = (TextView) findViewById(R.id.warningmessage);
		
		submitButton2 = (Button) findViewById(R.id.button);
		accountText2 = (EditText) findViewById(R.id.editText1);
		passwordtText2 = (EditText) findViewById(R.id.editText2);
		storeInformation2 = (CheckBox) findViewById(R.id.checkBox2);
		get();
		
		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 10000); /// timeout is in millisecond
		HttpConnectionParams.setSoTimeout(httpParams, 10000); 
		httpClient = new DefaultHttpClient(httpParams);
		cookieStore = httpClient.getCookieStore();
		httpContext = new BasicHttpContext();
		httpContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
		setCookie("");
		setHeaders(null);
		
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
		
		if (Pref.getpref(Login.this, "account2") != null) {
			accountText2.setText(Pref.getpref(Login.this, "account2"));
		}
		if (Pref.getpref(Login.this, "password2") != null) {
			passwordtText2.setText(Pref.getpref(Login.this, "password2"));
		}
		if (Pref.getpref(Login.this, "ischeck2") != null) {
			if (Pref.getpref(Login.this, "ischeck2").equals("true")) {
				storeInformation2.setChecked(true);
			} else {
				storeInformation2.setChecked(false);
			}
		}
	}
	//ttucis
	Button submitButton;
	EditText accountText;
	EditText passwordtText;
	CheckBox storeInformation;
	TextView warningMessage;
	
	//wifiAutoLogin
	Button submitButton2;
	EditText accountText2;
	EditText passwordtText2;
	CheckBox storeInformation2;

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

	public static Header[] getHeaders() {
		return headers;
	}

	static void setHeaders(Header[] headers) {
		Login.headers = headers;
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
				    
					setCookie(getCookie() + (cookies.get(i).getName()+"="+cookies.get(i).getValue()+";"));
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
	
	public class NetworkStatus {
	    private WifiManager wifiManager;
	    private WifiInfo wifiInfo;

	    public NetworkStatus(Context context) {
	        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
	    }

	    public void update() {
	        wifiInfo = wifiManager.getConnectionInfo();
	    }

	    public String getSSID() {
	        update();
	        String ssid = wifiInfo.getSSID();
	        if(ssid == null) return "";

	        if (ssid.startsWith("\"") && ssid.endsWith("\"")) {
	            ssid = ssid.substring(1, ssid.length() - 1);
	        }

	        return ssid;
	    }

	}

}
