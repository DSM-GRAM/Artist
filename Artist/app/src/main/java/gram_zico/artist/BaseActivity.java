package gram_zico.artist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by root1 on 2017. 9. 11..
 */

public class BaseActivity extends AppCompatActivity {
    public void goNextActivity(Class nextClass){
        Intent intent = new Intent(this, nextClass);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        showToast("뒤로가기를 하실 수 없습니다.");
    }

    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public  <T> T setViewById (int id){
        return (T)findViewById(id);
    }
}
