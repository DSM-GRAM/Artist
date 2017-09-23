package gram_zico.artist.Activity;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

import gram_zico.artist.Adapter.TutorialAdapter;
import gram_zico.artist.BaseActivity;
import gram_zico.artist.Model.TutorialModel;
import gram_zico.artist.R;

/**
 * Created by gameplay on 23/09/2017.
 */

public class TutorialActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        final ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);
        final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.topView);

        final Button nextButton = (Button)findViewById(R.id.nextButton);



        final ArrayList<TutorialModel> datas = new ArrayList<>();

        datas.add(new TutorialModel(R.drawable.scr_info1, getString(R.string.info1)));
        datas.add(new TutorialModel(R.drawable.scr_info2, getString(R.string.info2)));
        datas.add(new TutorialModel(R.drawable.scr_info3, getString(R.string.info3)));
        datas.add(new TutorialModel(R.drawable.scr_info4, getString(R.string.info4)));

        viewPager.setAdapter(new TutorialAdapter(getSupportFragmentManager(), datas));

        setCountCircle(linearLayout, 0, datas.size());

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((Button)view).getText().toString().equals("확인")){
                    goNextActivity(CategorySelectActivity.class, null);
                }else{
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setCountCircle(linearLayout, position, datas.size());
                if(position == datas.size() - 1){
                    nextButton.setText("확인");
                }else{
                    nextButton.setText("다음");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setCountCircle(LinearLayout linearLayout, int select, int count){
        linearLayout.removeAllViews();
        for(int i=0; i<count; i++){
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