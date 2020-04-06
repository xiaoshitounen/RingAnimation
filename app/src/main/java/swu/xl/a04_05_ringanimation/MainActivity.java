package swu.xl.a04_05_ringanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    //第二层
    RelativeLayout level2;
    //第三层
    RelativeLayout level3;

    //记录菜单的打开状态
    boolean isLevel2Open = true;
    boolean isLevel3Open = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化视图
        initView();

        //第一层按钮
        findViewById(R.id.ib_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLevel2Open){
                    //当前是打开的
                    if (isLevel3Open){
                        //先关闭第三层菜单
                        closeLevel(level3,0);
                        //延迟一会儿关闭第二层菜单
                        closeLevel(level2,500);

                        //切换第三层的状态
                        isLevel3Open = !isLevel3Open;
                    }else {
                        closeLevel(level2,0);
                    }
                }else {
                    //当前是关闭的
                    openLevel(level2);
                }

                //切换状态
                isLevel2Open = !isLevel2Open;
            }
        });

        //第二层按钮
        findViewById(R.id.ib_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLevel3Open){
                    //当前是打开的
                    closeLevel(level3,0);
                }else {
                    //当前是关闭的
                    openLevel(level3);
                }

                //切换状态
                isLevel3Open = !isLevel3Open;
            }
        });
    }

    private void initView() {
        level2 = findViewById(R.id.root_level_2);
        level3 = findViewById(R.id.root_level_3);
    }

    /**
     * 打开动画
     * @param view
     */
    private void openLevel(View view){
        //System.out.println("----------"+view.getWidth()); 此时控件的宽度已经测量出来

        //创建旋转动画
        ObjectAnimator rotation = ObjectAnimator.ofFloat(view,
                "Rotation", -180, -360);

        //设置旋转点
        view.setPivotX(view.getWidth() >> 1);
        view.setPivotY(view.getHeight());

        //设置动画时间
        rotation.setDuration(2000);

        //设置插值器
        rotation.setInterpolator(new AccelerateDecelerateInterpolator());

        //开启动画
        rotation.start();
    }

    /**
     * 关闭动画
     * @param view
     * @param delay
     */
    private void closeLevel(View view, long delay){
        //创建旋转动画
        ObjectAnimator rotation = ObjectAnimator.ofFloat(view,
                "Rotation", 0, -180);

        //设置旋转点
        view.setPivotX(view.getWidth() >> 1);
        view.setPivotY(view.getHeight());

        //设置动画时间
        rotation.setDuration(2000);

        //设置动画延迟时间
        rotation.setStartDelay(delay);

        //设置插值器
        rotation.setInterpolator(new AccelerateDecelerateInterpolator());

        //开启动画
        rotation.start();
    }
}
