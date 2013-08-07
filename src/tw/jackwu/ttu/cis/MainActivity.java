package tw.jackwu.ttu.cis;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import loginpage.Login;
import loginpage.LoginLogout;

import org.apache.http.client.ClientProtocolException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

public class MainActivity extends Activity {
	ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//登出
		Button logout = (Button)findViewById(R.id.button1);
		logout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				logoutDialog();
			}
		});
		
		ExpandableListView elv = (ExpandableListView)findViewById(R.id.expandableListView1);
		elv.setGroupIndicator(null);
		
		List<Map<String, String>> groups = new ArrayList<Map<String,String>>();
		Map<String, String> group1 = new HashMap<String, String>();
		Map<String, String> group2 = new HashMap<String, String>();
		Map<String, String> group3 = new HashMap<String, String>();
		Map<String, String> group4 = new HashMap<String, String>();
		group1.put("group", "教務");
		group2.put("group", "學務");
		group3.put("group", "電算");
		group4.put("group", "選課");
		
		groups.add(group1);
		groups.add(group2);
		groups.add(group3);
		groups.add(group4);
		
		//教務
		List<Map<String, String>> child1 = new ArrayList<Map<String, String>>();
		Map<String, String> child1Data1 = new HashMap<String, String>();
		Map<String, String> child1Data2 = new HashMap<String, String>();
		Map<String, String> child1Data3 = new HashMap<String, String>();
		Map<String, String> child1Data4 = new HashMap<String, String>();
		Map<String, String> child1Data5 = new HashMap<String, String>();
		Map<String, String> child1Data6 = new HashMap<String, String>();
		Map<String, String> child1Data7 = new HashMap<String, String>();
		Map<String, String> child1Data8 = new HashMap<String, String>();
		Map<String, String> child1Data9 = new HashMap<String, String>();
		child1Data1.put("child", "各科成績");
		child1Data2.put("child", "期中預警");
		child1Data3.put("child", "期末成績");
		child1Data4.put("child", "歷年成績");
		child1Data5.put("child", "修課審核");
		child1Data6.put("child", "大考座位表");
		child1Data7.put("child", "本期功課表");
		child1Data8.put("child", "學程資訊");
		child1Data9.put("child", "英文檢定成績");
		child1.add(child1Data1);
		child1.add(child1Data2);
		child1.add(child1Data3);
		child1.add(child1Data4);
		child1.add(child1Data5);
		child1.add(child1Data6);
		child1.add(child1Data7);
		child1.add(child1Data8);
		child1.add(child1Data9);
		
		//學務
		List<Map<String, String>> child2 = new ArrayList<Map<String, String>>();
		Map<String, String> child2Data1 = new HashMap<String, String>();
		child2Data1.put("child", "出缺勤統計");
		child2.add(child2Data1);
		
		//電算
		List<Map<String, String>> child3 = new ArrayList<Map<String, String>>();
		Map<String, String> child3Data1 = new HashMap<String, String>();
		Map<String, String> child3Data2 = new HashMap<String, String>();
		Map<String, String> child3Data3 = new HashMap<String, String>();
		Map<String, String> child3Data4 = new HashMap<String, String>();
		child3Data1.put("child", "電子信箱");
		child3Data2.put("child", "網路大學");
		child3Data3.put("child", "網路硬碟(一)(僅供下載)");
		child3Data4.put("child", "網路硬碟(二)(僅供下載)");
		
		child3.add(child3Data1);
		child3.add(child3Data2);
		child3.add(child3Data3);
		child3.add(child3Data4);
		
		//選課
		List<Map<String, String>> child4 = new ArrayList<Map<String, String>>();
		Map<String, String> child4Data1 = new HashMap<String, String>();
		Map<String, String> child4Data2 = new HashMap<String, String>();
		Map<String, String> child4Data3 = new HashMap<String, String>();
		Map<String, String> child4Data4 = new HashMap<String, String>();
		Map<String, String> child4Data5 = new HashMap<String, String>();
		Map<String, String> child4Data6 = new HashMap<String, String>();
		Map<String, String> child4Data7 = new HashMap<String, String>();
		Map<String, String> child4Data8 = new HashMap<String, String>();
		Map<String, String> child4Data9 = new HashMap<String, String>();
		Map<String, String> child4Data10 = new HashMap<String, String>();
		Map<String, String> child4Data11 = new HashMap<String, String>();
		Map<String, String> child4Data12 = new HashMap<String, String>();
		child4Data1.put("child", "各班課程");
		child4Data2.put("child", "全校教師課程");
		child4Data3.put("child", "共同科目");
		child4Data4.put("child", "通識課程");
		child4Data5.put("child", "時段課程");
		child4Data6.put("child", "列印已選課程");
		child4Data7.put("child", "選課表");
		child4Data8.put("child", "本人功課表");
		child4Data9.put("child", "班級功課表");
		child4Data10.put("child", "教師功課表");
		child4Data11.put("child", "通識課時間表");
		child4Data12.put("child", "教室使用狀況");
		child4.add(child4Data1);
		child4.add(child4Data2);
		child4.add(child4Data3);
		child4.add(child4Data4);
		child4.add(child4Data5);
		child4.add(child4Data6);
		child4.add(child4Data7);
		child4.add(child4Data8);
		child4.add(child4Data9);
		child4.add(child4Data10);
		child4.add(child4Data11);
		child4.add(child4Data12);
		
		//用一個list物件保存所有的二級清單資料
		List<List<Map<String, String>>> childs = new ArrayList<List<Map<String, String>>>();
		childs.add(child1);
		childs.add(child2);
		childs.add(child3);
		childs.add(child4);

		ExpandableAdapter viewAdapter = new ExpandableAdapter(this, groups, childs);
		elv.setAdapter(viewAdapter);
		elv.setOnChildClickListener(elvListener);
		
	}
	URL url = null;
	public OnChildClickListener elvListener = new OnChildClickListener() {
		
		@Override
		public boolean onChildClick(ExpandableListView parent, View v,
				int groupPosition, int childPosition, long id) {
			// TODO Auto-generated method stub
//			Log.v("aa", groupPosition+"-"+childPosition);
			String ttuUrl = "http://stucis.ttu.edu.tw";
			switch (groupPosition) {
			//教務
			case 0:
				switch (childPosition) {
				case 0:
					try {
						url=new URL(ttuUrl+"/academic/listscore.php");
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 1:
					try {
						url=new URL(ttuUrl+"/academic/midwarning.php");
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 2:
					try {
						url=new URL(ttuUrl+"/academic/finalscore.php");
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 3:
					try {
						url=new URL(ttuUrl+"/academic/accscore.php");
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 4:
					try {
						url=new URL(ttuUrl+"/academic/gradechklist.php");
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 5:
					try {
						url=new URL(ttuUrl+"/academic/examseat.php");
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 6:
					try {
						url=new URL(ttuUrl+"/academic/schedule.php");
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 7:
					try {
						url=new URL(ttuUrl+"/academic/trackstatus.php");
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 8:
					try {
						url=new URL(ttuUrl+"/etest/engtest.php");
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
				break;
			//學務
			case 1:
				switch (childPosition) {
				case 0:
					try {
						url=new URL(ttuUrl+"/staffair/absrecord.php");
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
				break;
			//電算
			case 2:
				switch (childPosition) {
				case 0:
					try {
						url=new URL(ttuUrl+"/bulletin/bulletinxfer.php");
					} catch (MalformedURLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					break;
				case 1:
					try {
						url=new URL(ttuUrl+"/academic/elearn.php");
					} catch (MalformedURLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					break;
				case 2:
					try {
						url=new URL(ttuUrl+"/cc/webhd.php");
					} catch (MalformedURLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					break;
				case 3:
					try {
						url=new URL(ttuUrl+"/cc/webhd2.php");
					} catch (MalformedURLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					break;
				}
				break;
			//選課
			default:
				switch (childPosition) {
				case 0:
					try {
						url=new URL(ttuUrl+"/selcourse/ListClassCourse.php");
					} catch (MalformedURLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					break;
				case 1:
					try {
						url=new URL(ttuUrl+"/selcourse/ListTeacher.php");
					} catch (MalformedURLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					break;
				case 2:
					try {
						url=new URL(ttuUrl+"/selcourse/ListUGRR.php");
					} catch (MalformedURLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					break;
				case 3:
					try {
						url=new URL(ttuUrl+"/selcourse/ListGeneral.php");
					} catch (MalformedURLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					break;
				case 4:
					try {
						url=new URL(ttuUrl+"/selcourse/ListTime.php");
					} catch (MalformedURLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					break;
				case 5:
					try {
						url=new URL(ttuUrl+"/selcourse/ListSelected.php");
					} catch (MalformedURLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					break;
				case 6:
					try {
						url=new URL(ttuUrl+"/selcourse/ListCourseSheet.php");
					} catch (MalformedURLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					break;
				case 7:
					try {
						url=new URL(ttuUrl+"/selcourse/Schedule.php");
					} catch (MalformedURLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					break;
				case 8:
					try {
						url=new URL(ttuUrl+"/selcourse/ViewClass.php");
					} catch (MalformedURLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					break;
				case 9:
					try {
						url=new URL(ttuUrl+"/selcourse/ViewTeacher.php");
					} catch (MalformedURLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					break;
				case 10:
					try {
						url=new URL(ttuUrl+"/selcourse/ViewGeneral.php");
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case 11:
					try {
						url=new URL(ttuUrl+"/selcourse/ViewRoom.php");
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
				break;
			}
			Bundle bundle = new Bundle();
			bundle.putString("url", url+"");
			Intent intent = new Intent(MainActivity.this,WebViewPage.class);
			intent.putExtras(bundle);
			startActivityForResult(intent, 1);
			return true;
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
			intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"dm3352andy@gmail.com"});
			intent.putExtra(Intent.EXTRA_SUBJECT, "意見反饋-大同大學校務資訊系統app");
			intent.putExtra(Intent.EXTRA_TEXT, "\n---\n裝置:"+Build.MODEL+"\nAndroid版本:"+Build.VERSION.RELEASE);
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
		new AlertDialog.Builder(MainActivity.this)
				.setTitle(R.string.about_title)
				.setMessage(R.string.about_content).show();
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode==RESULT_OK)
		{
			Log.v("aa", "開啟詢問視窗");
			logoutDialog();
		}
	};
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		logoutDialog();
	}
	
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
	
	private void logoutDialog() {
		DialogInterface.OnClickListener okClick = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Log.v("aa", which+"");
				new LogoutTask().execute(which);
				
			}
		};
		
		new AlertDialog.Builder(this)
		.setTitle("使用完畢了嗎?")
		.setMessage("您可以給此軟體評分或直接離開，期待您下次蒞臨 :D")
		.setPositiveButton("離開程式", okClick)//-1
		.setNegativeButton("評分", okClick)//-2
		.setNeutralButton("回登入畫面", okClick)//-3
		.show();
	}
	

	class LogoutTask extends AsyncTask<Integer, Void, Integer> {
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progressDialog = ProgressDialog.show(MainActivity.this, "登出中","請稍後",true,true);
		}

		@Override
		protected Integer doInBackground(Integer... params) {
			// TODO Auto-generated method stub
			if(haveInternet())
			{
				LoginLogout loginLogout = new LoginLogout();
				try {
					loginLogout.logout();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return params[0];
		}
		
		@Override
		protected void onPostExecute(Integer result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			progressDialog.dismiss();
			Log.v("aa", ""+result);
			finish();
			switch (result) {
			case -1:
				//do nothing
				break;
			case -2:
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=tw.jackwu.ttu.cis"));
				startActivity(intent);
				break;
			default:
				intent = new Intent(MainActivity.this,Login.class);
				startActivity(intent);
				break;
			}
		}
		
	}
}

