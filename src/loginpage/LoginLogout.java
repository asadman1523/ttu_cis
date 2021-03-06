package loginpage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.text.StaticLayout;
import android.util.Log;

public class LoginLogout {
	
	
	ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
	
	public boolean login(String account,String password,String sessionid,Context context) throws ClientProtocolException, IOException
	{
		String url2 = "http://stucis.ttu.edu.tw";
		
		String url3 = "http://stucis.ttu.edu.tw/login.php";
		HttpPost httpPost = new HttpPost(url3);
		postParameters = new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair("ID", account));
		postParameters.add(new BasicNameValuePair("PWD", password));
		//postParameters.add(new BasicNameValuePair("captcha_code", vertifyCode));
		postParameters.add(new BasicNameValuePair("Submit", "登入系統"));
		httpPost.setEntity(new UrlEncodedFormEntity(postParameters,"big5"));
		HttpResponse response = Login.httpClient.execute(httpPost,Login.httpContext);
		Login.setHeaders(response.getHeaders("referer"));	//2013/08/08 新增
		String data = EntityUtils.toString(response.getEntity(),"big5");
		if (data.indexOf("系統登入") > 0) {
			Log.v("aa", "登入失敗!");
			return false;
		}
		else {
			Log.v("aa", "登入成功!");
			return true;
		}
	}
	
	public boolean logout() throws ClientProtocolException, IOException {
		String url = "http://stucis.ttu.edu.tw/logout.php";
		String url2 = "http://stucis.ttu.edu.tw/logout2.php";
		
		Log.v("aa", "找驗證碼");
		HttpGet httpGet = new HttpGet(url);
		HttpResponse response = Login.httpClient.execute(httpGet);
		String ConfirmCode = EntityUtils.toString(response.getEntity(),"big5");
		ConfirmCode = ConfirmCode.substring(ConfirmCode.indexOf("value=")+7,ConfirmCode.indexOf("<p a")-3);

		
		//實作登出
		HttpPost httpPost = new HttpPost(url2);
		postParameters = new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair("ConfirmCode", ConfirmCode));
		postParameters.add(new BasicNameValuePair("B1", "確定"));
        httpPost.setEntity(new UrlEncodedFormEntity(postParameters,"big5"));
        Log.v("aa", "登出");
        response = Login.httpClient.execute(httpPost);
        String conString = EntityUtils.toString(response.getEntity(),"big5");
		if(conString.indexOf("Logout operation")>0)
		{
			return true;
		}
		else {
			return false;
		}
	}
	
	

}
