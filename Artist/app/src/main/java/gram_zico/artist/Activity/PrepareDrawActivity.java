package gram_zico.artist.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import gram_zico.artist.BaseActivity;
import gram_zico.artist.R;

/**
 * Created by root1 on 2017. 9. 13..
 */

public class PrepareDrawActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare_draw);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        TextView titleText = (TextView)findViewById(R.id.titleText);
        titleText.setText(title);
        Button startButton = (Button)findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goNextActivity(CanvasActivity.class);
            }
        });
    }
}
