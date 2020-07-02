package app.littlezilla.littlezilla.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import app.littlezilla.littlezilla.R;

public class SplashActivity extends AppCompatActivity {
    ImageView imageview;
    Animation top,bottom;
    TextView titile,subtitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageview=findViewById(R.id.splash_image);
        titile=findViewById(R.id.title);
        subtitle=findViewById(R.id.sub_title);
        top= AnimationUtils.loadAnimation(this,R.anim.top);
        bottom=AnimationUtils.loadAnimation(this,R.anim.bottom);
        imageview.setAnimation(top);
        titile.setAnimation(bottom);
        subtitle.setAnimation(bottom);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Thread splashthread=new Thread(){
            public void run() {
                try{
                    sleep(5000);
                    Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    e.printStackTrace();
                }

                super.run();
            }
        };
        splashthread.start();


    }
}