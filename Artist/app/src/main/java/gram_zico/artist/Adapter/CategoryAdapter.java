package gram_zico.artist.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import gram_zico.artist.Model.CategoryModel;
import gram_zico.artist.R;
import gram_zico.artist.ViewHolder.CategoryViewHolder;

/**
 * Created by root1 on 2017. 9. 12..
 */

public class CategoryAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<CategoryModel> datas = new ArrayList<>();

    public CategoryAdapter(Context context){
        this.context = context;
//        RetrofitClass.getInstance().apiInterface
        datas.add(new CategoryModel("인물", 13, Color.RED));
        datas.add(new CategoryModel("동물", 6, Color.BLACK));
        datas.add(new CategoryModel("건물", 9, Color.GRAY));
        datas.add(new CategoryModel("사랑", 1, Color.GREEN));
        datas.add(new CategoryModel("인생", 3, Color.YELLOW));
        datas.add(new CategoryModel("소프트웨어", 6, Color.BLUE));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_category_card,parent,false);
        return new CategoryViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CategoryViewHolder categoryViewHolder = (CategoryViewHolder)holder;
        categoryViewHolder.titleText.setText(datas.get(position).getTitle());
        categoryViewHolder.countText.setText(datas.get(position).getCount());
        categoryViewHolder.colorView.setBackgroundColor(datas.get(position).getColor());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

}
