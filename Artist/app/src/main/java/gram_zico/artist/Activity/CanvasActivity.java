package gram_zico.artist.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.google.gson.JsonElement;
import com.larswerkman.holocolorpicker.ColorPicker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import gram_zico.artist.BaseActivity;
import gram_zico.artist.Connect.RetrofitClass;
import gram_zico.artist.Model.DrawView;
import gram_zico.artist.Model.IntentDataModel;
import gram_zico.artist.R;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CanvasActivity extends BaseActivity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener{

    LinearLayout colorSelectLayout1, colorSelectLayout2;
    FloatingActionButton clearButton;
    LinearLayout seekLayout;
    SeekBar seekBar;
    DrawView drawView;

    private String userID;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        colorSelectLayout1 = (LinearLayout)findViewById(R.id.colorSelectLayout1);
        colorSelectLayout2 = (LinearLayout)findViewById(R.id.colorSelectLayout2);

        clearButton = (FloatingActionButton)findViewById(R.id.clearButton);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.clear();
            }
        });

//        eraserButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                removeDot();
//                drawView.changeColor(Color.TRANSPARENT);
//            }
//        });

        seekLayout = (LinearLayout)findViewById(R.id.seekLayout);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);

        drawView = (DrawView)findViewById(R.id.paper);

        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        category = intent.getStringExtra("category");

        addColorButton();
    }

    @Override
    protected void onResume() {
        super.onResume();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                OutputStream stream = null;
                try {
                    stream = new FileOutputStream("sdcard/data.png");
                    drawView.getBitmap().compress(Bitmap.CompressFormat.PNG, 100, stream);
                    stream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                File file = new File("sdcard/data.png");
                final RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);

                RetrofitClass.getInstance().apiInterface.uploadImage
                        (userID, MultipartBody.Part.createFormData("img", file.getName(), requestBody)).enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                        if(response.code() == 201){
                            ArrayList<IntentDataModel> data = new ArrayList<>();
                            data.add(new IntentDataModel("count", ""+response.body().getAsDouble()));
                            data.add(new IntentDataModel("category", category));
                            goNextActivity(IntoInfoActivity.class, data);
                        }else{
                            showToast("데이터 전송 오류");
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        };

        new Timer().schedule(timerTask, 1000 * 60);
    }

    RelativeLayout lastColorSelectLayout;

    @Override
    public void onClick(View view) {
        removeDot();

        RelativeLayout relativeLayout = (RelativeLayout) view;
        FloatingActionButton floatingActionButton = (FloatingActionButton)relativeLayout.getChildAt(0);
        drawView.changeColor(floatingActionButton.getBackgroundTintList().getDefaultColor());

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

    private String colorArr[] = new String[]
            {"#000000", "#FF0000","#FFA700","#FFF500","#1DFF00","#00FFEB","#0062FF","#9300FF","#F500FF","#211F65","#868686","#FFFFFF"};

    private void addColorButton(){
        for(int i=0;i<12;i++){
            FloatingActionButton floatingActionButton = new FloatingActionButton(CanvasActivity.this);
            floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colorArr[i])));
            floatingActionButton.setCompatElevation(0);
            floatingActionButton.setTag("button");

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(108,108);
            params.setMargins(32,0,32,0);
            final RelativeLayout relativeLayout = new RelativeLayout(CanvasActivity.this);
            relativeLayout.addView(floatingActionButton, params);
            relativeLayout.setOnClickListener(this);
            if(i == 11){
                relativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        setDialog((FloatingActionButton) view.findViewWithTag("button"));
                        relativeLayout.callOnClick();
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
                button.setBackgroundTintList(ColorStateList.valueOf(colorPicker.getColor()));
                drawView.changeColor(colorPicker.getColor());
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
        drawView.changeWidth(i);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}

