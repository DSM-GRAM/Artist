package gram_zico.artist.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import gram_zico.artist.Adapter.CategoryAdapter;
import gram_zico.artist.BaseActivity;
import gram_zico.artist.R;

/**
 * Created by root1 on 2017. 9. 11..
 */

public class CategorySelectActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_select);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new CategoryAdapter(this, recyclerView));
    }
}
