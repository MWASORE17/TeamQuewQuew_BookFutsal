package quewquewcrew.appngasal.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import quewquewcrew.appngasal.view.fragment.auth.LoginFragment;
import quewquewcrew.appngasal.R;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        startAnimation();


        //textanimation();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);

                finish();
            }
        },2000);

    }
    private void startAnimation(){
        Animation animation= AnimationUtils.loadAnimation(this, R.anim.fadein);
        animation.reset();
        ImageView imageView=(ImageView)findViewById(R.id.imageView);
        imageView.clearAnimation();
        imageView.startAnimation(animation);
    }
}
