package gram_zico.artist.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import gram_zico.artist.BaseActivity;
import gram_zico.artist.Connect.RetrofitClass;
import gram_zico.artist.R;
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
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNotEmpty(nameEdit) && isNotEmpty(comEdit) && isNotEmpty(ageEdit) && isNotEmpty(phoneEdit)){
                    Log.d("xxx", getText(nameEdit) + getText(comEdit) + getText(ageEdit) + getText(phoneEdit) + category);

                    RetrofitClass.getInstance().apiInterface.saveUserData(getText(phoneEdit),getText(nameEdit),getText(comEdit), getText(ageEdit), category, score).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if(response.code() == 201) {
                                goNextActivity(FinishActivity.class, null);
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
                    showToast("감사합니다.");
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
