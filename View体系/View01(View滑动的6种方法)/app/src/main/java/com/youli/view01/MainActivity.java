package com.youli.view01;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AnimationUtils;

public class MainActivity extends Activity {

    private MyView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myView= (MyView) findViewById(R.id.myView);

        //方法4：使用动画
        //myView.setAnimation(AnimationUtils.loadAnimation(this,R.anim.translate));

        //方法6：使用Scroll来进行平滑移动
        myView.smoothScrollTo(-400,0);

    }
}
