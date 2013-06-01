package tw.jackwu.ttu.cis;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.CookieSyncManager;
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
		WebSettings webSettings = browser.getSettings();
		webSettings.setSupportZoom(true);
		webSettings.setBuiltInZoomControls(true);
		webSettings.setJavaScriptEnabled(true);
		
		browser.setWebViewClient(new WebViewClient());
		CookieSyncManager.getInstance().sync();
		browser.loadUrl(url);
		
		
		Button logout = (Button)findViewById(R.id.button1);
		logout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				setResult(RESULT_OK);
				finish();
			}
		});
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Log.v("aa", "��^");
		finish();
	}
}
