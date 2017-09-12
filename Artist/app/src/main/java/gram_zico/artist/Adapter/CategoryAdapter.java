package gram_zico.artist.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gram_zico.artist.R;

/**
 * Created by root1 on 2017. 9. 12..
 */

public class CategoryAdapter extends RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_category_card,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 16;
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder{
        public CategoryViewHolder(View itemView) {
            super(itemView);
        }
    }
}
