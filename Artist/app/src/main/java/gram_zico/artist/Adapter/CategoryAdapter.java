package gram_zico.artist.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import gram_zico.artist.Connect.RetrofitClass;
import gram_zico.artist.Model.CategoryCountModel;
import gram_zico.artist.Model.CategoryModel;
import gram_zico.artist.R;
import gram_zico.artist.ViewHolder.CategoryViewHolder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root1 on 2017. 9. 12..
 */

public class CategoryAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<CategoryModel> datas = new ArrayList<>();

    private String titleStrArr[] = new String[]{"동물", "화가", "캘리그라피", "캐릭터", "음식", "문양", "착시현상", "기타", "인물", "풍경"};
    private String colorCode[] = new String[]{"#FF8E8E", "#B98EFF", "#ADF6FF", "#F7C080", "#A8FF9D", "#EFFF95", "#DFA3FF", "#00EAB4", "#D5D5D5", "#A9A7E5"};

    public CategoryAdapter(Context context, final RecyclerView recyclerView){
        this.context = context;
        RetrofitClass.getInstance().apiInterface.getCategoryCount().enqueue(new Callback<CategoryCountModel>() {
            @Override
            public void onResponse(Call<CategoryCountModel> call, Response<CategoryCountModel> response) {
                if(response.code() == 200){
                    for(int i=0;i<10;i++){
                        datas.add(new CategoryModel(titleStrArr[i],response.body().getData(i), colorCode[i]));
                    }

                    recyclerView.getAdapter().notifyDataSetChanged();
                }
            }


            @Override
            public void onFailure(Call<CategoryCountModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_category_card,parent,false);
        return new CategoryViewHolder(view, context, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CategoryViewHolder categoryViewHolder = (CategoryViewHolder)holder;
        categoryViewHolder.titleText.setText(datas.get(position).getTitle());
        categoryViewHolder.countText.setText(datas.get(position).getCount());
        int color = Color.parseColor(datas.get(position).getColor());
        categoryViewHolder.colorView.setBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
