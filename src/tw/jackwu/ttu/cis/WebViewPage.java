package tw.jackwu.ttu.cis;

import java.util.HashMap;
import java.util.Map;

import loginpage.Login;
import loginpage.LoginLogout;

import org.apache.http.cookie.Cookie;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class WebViewPage extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webviewpage);
		Bundle bundle = this.getIntent().getExtras();
		String url = bundle.getString("url");
		
		WebView browser = (WebView)findViewById(R.id.webView1);
		
		browser.setDownloadListener(new DownloadListener() {
	        public void onDownloadStart(String url, String userAgent,
	                String contentDisposition, String mimetype,
	                long contentLength) {
	          Intent i = new Intent(Intent.ACTION_VIEW);
	          i.setData(Uri.parse(url));
	          startActivity(i);
	        }
	    });
		
		WebSettings webSettings = browser.getSettings();
		webSettings.setSupportZoom(true);
		webSettings.setBuiltInZoomControls(true);
		webSettings.setJavaScriptEnabled(true);
		browser.setWebViewClient(new WebViewClient());
		
		//Cookie sessionCookie = Login.cookie;
		CookieSyncManager.createInstance(this);
		CookieManager cookieManager = CookieManager.getInstance();
//		if (sessionCookie != null) {

//			Log.v("aa", sessionCookie.getName()+"a");
//			Log.v("aa", sessionCookie.getValue()+"b");
//			Log.v("aa", sessionCookie.getDomain()+"c");
		    //String cookieString = sessionCookie.getName() + "=" + sessionCookie.getValue();
		    cookieManager.setCookie("http://stucis.ttu.edu.tw/", Login.getCookie());
		    
		    CookieSyncManager.getInstance().sync();
//		}   
		Map<String, String> map = new HashMap<String, String>();
		for(int i=0;i<Login.getHeaders().length;i++) {
			map.put(Login.getHeaders()[i].getName(), Login.getHeaders()[i].getValue());
		}
		
		browser.loadUrl(url ,map);
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Log.v("aa", "ªð¦^");
		finish();
	}
}
