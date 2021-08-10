package com.example.tic_tac_toe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPagerAdapter extends PagerAdapter {

    public Context context;

    public ViewPagerAdapter(Context context){
        this.context = context;
    }

    String title_arr[] = {"How to play?", "Multiplayer"};

    String des_arr[] = {
            "Every player will select one of the either signs 'X' or 'O', each player will get a chance to place their sign onto the board. The first player to create a 3 box straight line Wins",
            "Get a friend and start playing against them in real time"
    };

    int image_arr[] = {R.drawable.tictactoe_image, R.drawable.multiplayer};

    @Override
    public int getCount() {
        return title_arr.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.viewpager_layout, container, false);

        TextView title = view.findViewById(R.id.title);
        TextView description = view.findViewById(R.id.des);
        ImageView imageView = view.findViewById(R.id.image);

        title.setText(title_arr[position]);
        description.setText(des_arr[position]);
        imageView.setImageResource(image_arr[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((ConstraintLayout)object);
    }
}
