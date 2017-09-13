package gram_zico.artist.Activity;

import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.larswerkman.holocolorpicker.ColorPicker;

import gram_zico.artist.BaseActivity;
import gram_zico.artist.R;

public class CanvasActivity extends BaseActivity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener{

    LinearLayout colorSelectLayout1, colorSelectLayout2;
    FloatingActionButton clearButton, eraserButton;
    LinearLayout seekLayout;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        colorSelectLayout1 = (LinearLayout)findViewById(R.id.colorSelectLayout1);
        colorSelectLayout2 = (LinearLayout)findViewById(R.id.colorSelectLayout2);

        clearButton = (FloatingActionButton)findViewById(R.id.clearButton);
        eraserButton = (FloatingActionButton)findViewById(R.id.eraserButton);

        seekLayout = (LinearLayout)findViewById(R.id.seekLayout);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);

        eraserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeDot();
            }
        });

        addColorButton();
    }

    RelativeLayout lastColorSelectLayout;

    @Override
    public void onClick(View view) {
        removeDot();


        RelativeLayout relativeLayout = (RelativeLayout) view;
        FloatingActionButton floatingActionButton = (FloatingActionButton)relativeLayout.getChildAt(0);
        Log.d("xxx", "color: " + floatingActionButton.getBackgroundTintList().getDefaultColor());

        View dotView = new View(CanvasActivity.this);
        dotView.setTag("dot");
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(32, 32);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        relativeLayout.addView(dotView, params);
        dotView.setBackgroundResource(R.drawable.canvas_circle_color_shape);
        lastColorSelectLayout = relativeLayout;
    }

    private void removeDot(){
        if(lastColorSelectLayout != null){
            View temp = lastColorSelectLayout.findViewWithTag("dot");
            lastColorSelectLayout.removeView(temp);
        }
    }

    private int colorArr[] = new int[]
                    {Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.GRAY, Color.CYAN,
                    Color.MAGENTA, Color.rgb(255,255,0), Color.rgb(255,255,0), Color.rgb(255,255,0), Color.rgb(255,255,0), Color.rgb(255,255,0)};

    private void addColorButton(){
        for(int i=0;i<12;i++){
            FloatingActionButton floatingActionButton = new FloatingActionButton(CanvasActivity.this);
            floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(colorArr[i]));
            floatingActionButton.setCompatElevation(0);
            floatingActionButton.setTag("button");

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(108,108);
            params.setMargins(32,0,32,0);
            RelativeLayout relativeLayout = new RelativeLayout(CanvasActivity.this);
            relativeLayout.addView(floatingActionButton, params);
            relativeLayout.setOnClickListener(this);
            if(i == 11){
                relativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        setDialog((FloatingActionButton) view.findViewWithTag("button"));
                        return true;
                    }
                });
            }

            if(i > 5){
                colorSelectLayout2.addView(relativeLayout, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT));
            }else{
                colorSelectLayout1.addView(relativeLayout, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT));
            }
        }
    }

    private void setDialog(final FloatingActionButton button){
        final AlertDialog.Builder builder = new AlertDialog.Builder(CanvasActivity.this);
        final ColorPicker colorPicker = new ColorPicker(CanvasActivity.this);
        colorPicker.setShowOldCenterColor(false);
        LinearLayout linearLayout = new LinearLayout(CanvasActivity.this);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.addView(colorPicker, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        builder.setTitle("색상 선택");
        builder.setView(linearLayout);
        builder.setPositiveButton("선택", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("xxx", "color data: " + colorPicker.getColor());
                button.setBackgroundTintList(ColorStateList.valueOf(colorPicker.getColor()));
                builder.create().cancel();
            }
        });

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                builder.create().cancel();
            }
        });
        builder.create().show();
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

