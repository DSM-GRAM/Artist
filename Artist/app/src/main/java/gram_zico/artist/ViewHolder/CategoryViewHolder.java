package gram_zico.artist.ViewHolder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import gram_zico.artist.Activity.PrepareDrawActivity;
import gram_zico.artist.R;

/**
 * Created by root1 on 2017. 9. 20..
 */

public class CategoryViewHolder extends RecyclerView.ViewHolder{

    public TextView titleText, countText;
    public View colorView;

    public CategoryViewHolder(View itemView, final Activity activity, final int count) {
        super(itemView);
        titleText = setViewById(TextView.class,itemView, R.id.titleText);
        countText = setViewById(TextView.class, itemView, R.id.countText);
        colorView = setViewById(View.class, itemView, R.id.colorView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, PrepareDrawActivity.class);
                intent.putExtra("title", titleText.getText().toString());
                intent.putExtra("count", count);
                activity.startActivity(intent);
                activity.finish();
            }
        });
    }

    private <T> T setViewById (Class<T> c,View view, int id){
        return c.cast(view.findViewById(id));
    }
}
