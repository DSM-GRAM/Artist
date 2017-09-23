package gram_zico.artist.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;

import gram_zico.artist.BaseActivity;
import gram_zico.artist.Connect.RetrofitClass;
import gram_zico.artist.R;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root1 on 2017. 9. 21..
 */

public class IntoInfoActivity extends BaseActivity implements View.OnClickListener {

    Button manButton, womanButton;
    EditText nameEdit, comEdit, ageEdit, phoneEdit;
    Button finishButton;

    String score, category;

    boolean isMan = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_into_info);

        Intent intent = getIntent();
        score = intent.getStringExtra("score");
        category = intent.getStringExtra("category");

        manButton = (Button)findViewById(R.id.manButton);
        womanButton = (Button)findViewById(R.id.womanButton);

        manButton.setOnClickListener(this);
        womanButton.setOnClickListener(this);

        nameEdit = (EditText)findViewById(R.id.nameEdit);
        comEdit = (EditText)findViewById(R.id.comEdit);
        ageEdit = (EditText)findViewById(R.id.ageEdit);
        phoneEdit = (EditText)findViewById(R.id.phoneEdit);

        finishButton = (Button)findViewById(R.id.finishButton);

        final File file = new File("sdcard/data.png");
        final RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNotEmpty(nameEdit) && isNotEmpty(comEdit) && isNotEmpty(ageEdit) && isNotEmpty(phoneEdit)){
                    RetrofitClass.getInstance().apiInterface.saveUserData(getData(phoneEdit),getData(nameEdit),getData(comEdit), getData(ageEdit), getData(category), getData(score),
                            MultipartBody.Part.createFormData("img", file.getName(), requestBody)).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if(response.code() == 201) {
                                goNextActivity(ReadyActivity.class, null);
                                showToast("감사합니다.");
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
                }else{
                    showToast("정보를 모두 다 입력하세요.");
                }
            }
        });
    }

    private Boolean isNotEmpty(EditText editText){
        return !editText.getText().toString().isEmpty();
    }

    private String getText(EditText editText){
        return editText.getText().toString();
    }

    private RequestBody getData(EditText editText){
        return RequestBody.create(MediaType.parse("text/plane"), editText.getText().toString());
    }

    private RequestBody getData(String st){
        Log.d("xxx", "getData: " + st);
        return RequestBody.create(MediaType.parse("text/plane"), st);
    }
    @Override
    public void onClick(View view) {
        view.setBackgroundResource(R.drawable.view_select);

        if(view == manButton){
            womanButton.setBackgroundResource(R.drawable.view_unselect);
            isMan = true;
        }else{
            manButton.setBackgroundResource(R.drawable.view_unselect);
            isMan = false;
        }
    }
}
