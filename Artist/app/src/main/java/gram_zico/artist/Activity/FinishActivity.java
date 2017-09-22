package gram_zico.artist.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

import gram_zico.artist.BaseActivity;
import gram_zico.artist.R;

/**
 * Created by root1 on 2017. 9. 23..
 */

public class FinishActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                goNextActivity(ReadyActivity.class, null);
            }
        }, 1000 * 3);
    }
}
