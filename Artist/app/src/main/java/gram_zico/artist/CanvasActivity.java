package gram_zico.artist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class CanvasActivity extends BaseActivity implements SeekBar.OnSeekBarChangeListener{

    LinearLayout colorSelectLayout1, colorSelectLayout2;
    FloatingActionButton clearButton, showSeekButton;
    LinearLayout seekLayout;
    SeekBar seekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        colorSelectLayout1 = (LinearLayout)findViewById(R.id.colorSelectLayout1);
        colorSelectLayout2 = (LinearLayout)findViewById(R.id.colorSelectLayout2);

        clearButton = (FloatingActionButton)findViewById(R.id.clearButton);
        showSeekButton = (FloatingActionButton)findViewById(R.id.showSeekButton);

        seekLayout = (LinearLayout)findViewById(R.id.seekLayout);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);

        addColorButton();
    }


    private void addColorButton(){
        for(int i=0;i<12;i++){
            ImageButton imageButton = new ImageButton(CanvasActivity.this);
            imageButton.setBackgroundResource(R.drawable.canvas_circle_color_shape);
            if(i > 5){
                colorSelectLayout2.addView(imageButton, new LinearLayout.LayoutParams(32,32));
            }else{
                colorSelectLayout1.addView(imageButton, new LinearLayout.LayoutParams(32,32));
            }
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        Log.d("xxx", "seek data" + i);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}

