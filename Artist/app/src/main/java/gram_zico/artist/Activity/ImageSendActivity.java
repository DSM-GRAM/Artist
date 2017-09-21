package gram_zico.artist.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import gram_zico.artist.BaseActivity;
import gram_zico.artist.R;

/**
 * Created by root1 on 2017. 9. 21..
 */

public class ImageSendActivity extends BaseActivity {

    ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageView = (ImageView)findViewById(R.id.imageView);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
