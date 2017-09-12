package gram_zico.artist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by root1 on 2017. 9. 12..
 */

public class TutorialActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.topView);

        setCountCircle(linearLayout, 2);
    }

    private void setCountCircle(LinearLayout linearLayout, int select){
        linearLayout.removeAllViews();
        for(int i=0; i<5; i++){
            View view = new View(this);
            if(i == select){
                view.setBackgroundResource(R.drawable.tutorial_select_view_shape);
            }else{
                view.setBackgroundResource(R.drawable.tutorial_unselect_view_shape);
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(32,32);
            params.setMargins(16,16,0,0);
            linearLayout.addView(view, params);
        }
    }
}
