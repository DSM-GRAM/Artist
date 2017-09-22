package gram_zico.artist.Adapter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import gram_zico.artist.Model.TutorialModel;
import gram_zico.artist.R;

/**
 * Created by root1 on 2017. 9. 13..
 */

public class TutorialAdapter extends FragmentPagerAdapter {

    private ArrayList<TutorialModel> datas;

    public TutorialAdapter(FragmentManager fm, ArrayList<TutorialModel> datas) {
        super(fm);
        this.datas = datas;
    }

    @Override
    public Fragment getItem(int position) {
        TutorialModel data = datas.get(position);
        return new TutorialDataFragment(data.getImageID(), data.getContent());
    }

    @Override
    public int getCount() {
        return datas.size();
    }
}

@SuppressLint("ValidFragment")
class TutorialDataFragment extends Fragment{

    private int imageID;
    private String content;

    public TutorialDataFragment(int imageID, String content) {
        this.imageID = imageID;
        this.content = content;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_tutorial, container, false);
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textView = view.findViewById(R.id.textView);
        imageView.setImageResource(imageID);
        textView.setText(content);
        return view;
    }

}