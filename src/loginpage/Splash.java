package loginpage;

import tw.jackwu.ttu.cis.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

 
public class Splash extends Activity {   
 
   private final int SPLASH_DISPLAY_LENGHT = 1500; //����T�� 
 
   @Override 
   public void onCreate(Bundle savedInstanceState) { 
       super.onCreate(savedInstanceState); 
       setContentView(R.layout.splash); 
      
        
       new Handler().postDelayed(new Runnable(){ 
    	   
           public void run() { 
               Intent intent = new Intent(Splash.this,Login.class); 
               Splash.this.startActivity(intent); 
               Splash.this.finish(); 
              
           } 
              
          }, SPLASH_DISPLAY_LENGHT); 
      
       
   }
}