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
		//�n�X
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
		group1.put("group", "�а�");
		group2.put("group", "�ǰ�");
		group3.put("group", "�q��");
		group4.put("group", "���");
		
		groups.add(group1);
		groups.add(group2);
		groups.add(group3);
		groups.add(group4);
		
		//�а�
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
		child1Data1.put("child", "�U�즨�Z");
		child1Data2.put("child", "�����wĵ");
		child1Data3.put("child", "�������Z");
		child1Data4.put("child", "���~���Z");
		child1Data5.put("child", "�׽Ҽf��");
		child1Data6.put("child", "�j�Үy���");
		child1Data7.put("child", "�����\�Ҫ�");
		child1Data8.put("child", "�ǵ{��T");
		child1Data9.put("child", "�^���˩w���Z");
		child1.add(child1Data1);
		child1.add(child1Data2);
		child1.add(child1Data3);
		child1.add(child1Data4);
		child1.add(child1Data5);
		child1.add(child1Data6);
		child1.add(child1Data7);
		child1.add(child1Data8);
		child1.add(child1Data9);
		
		//�ǰ�
		List<Map<String, String>> child2 = new ArrayList<Map<String, String>>();
		Map<String, String> child2Data1 = new HashMap<String, String>();
		child2Data1.put("child", "�X�ʶԲέp");
		child2.add(child2Data1);
		
		//�q��
		List<Map<String, String>> child3 = new ArrayList<Map<String, String>>();
		Map<String, String> child3Data1 = new HashMap<String, String>();
		Map<String, String> child3Data2 = new HashMap<String, String>();
		Map<String, String> child3Data3 = new HashMap<String, String>();
		Map<String, String> child3Data4 = new HashMap<String, String>();
		child3Data1.put("child", "�q�l�H�c");
		child3Data2.put("child", "�����j��");
		child3Data3.put("child", "�����w��(�@)(�ȨѤU��)");
		child3Data4.put("child", "�����w��(�G)(�ȨѤU��)");
		
		child3.add(child3Data1);
		child3.add(child3Data2);
		child3.add(child3Data3);
		child3.add(child3Data4);
		
		//���
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
		child4Data1.put("child", "�U�Z�ҵ{");
		child4Data2.put("child", "���ձЮv�ҵ{");
		child4Data3.put("child", "�@�P���");
		child4Data4.put("child", "�q�ѽҵ{");
		child4Data5.put("child", "�ɬq�ҵ{");
		child4Data6.put("child", "�C�L�w��ҵ{");
		child4Data7.put("child", "��Ҫ�");
		child4Data8.put("child", "���H�\�Ҫ�");
		child4Data9.put("child", "�Z�ť\�Ҫ�");
		child4Data10.put("child", "�Юv�\�Ҫ�");
		child4Data11.put("child", "�q�ѽҮɶ���");
		child4Data12.put("child", "�ЫǨϥΪ��p");
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
		
		//�Τ@��list����O�s�Ҧ����G�ŲM����
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
			//�а�
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
			//�ǰ�
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
			//�q��
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
			//���
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
			intent.putExtra(Intent.EXTRA_SUBJECT, "�N�����X-�j�P�j�Ǯհȸ�T�t��app");
			intent.putExtra(Intent.EXTRA_TEXT, "\n---\n�˸m:"+Build.MODEL+"\nAndroid����:"+Build.VERSION.RELEASE);
			intent.setType("message/rfc822");
			startActivity(Intent.createChooser(intent, "�g�H���@��"));
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
			Log.v("aa", "�}�Ҹ߰ݵ���");
			logoutDialog();
		}
	};
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		logoutDialog();
	}
	
	// �P�_�������A
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
		.setTitle("�ϥΧ����F��?")
		.setMessage("�z�i�H�����n������Ϊ������}�A���ݱz�U���Y�{ :D")
		.setPositiveButton("���}�{��", okClick)//-1
		.setNegativeButton("����", okClick)//-2
		.setNeutralButton("�^�n�J�e��", okClick)//-3
		.show();
	}
	

	class LogoutTask extends AsyncTask<Integer, Void, Integer> {
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progressDialog = ProgressDialog.show(MainActivity.this, "�n�X��","�еy��",true,true);
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

