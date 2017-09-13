package gram_zico.artist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by root1 on 2017. 9. 11..
 */

public class BaseActivity extends AppCompatActivity {
    public void goNextActivity(Class nextClass){
        Intent intent = new Intent(this, nextClass);
        startActivity(intent);
        this.finish();
    }

}
