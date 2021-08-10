package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public ViewPager viewPager;
    public TextView dot_1;
    public TextView dot_2;
    public Button nextButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        dot_1 = findViewById(R.id.dots_1);
        dot_2 = findViewById(R.id.dots_2);
        nextButton = findViewById(R.id.nextButton);

        dot_1.setTextColor(Color.parseColor("#111111"));

        ViewPagerAdapter adapter = new ViewPagerAdapter(MainActivity.this);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position)
                {
                    case 0:
                        dot_1.setTextColor(Color.parseColor("#111111"));
                        dot_2.setTextColor(Color.parseColor("#7C7676"));
                        break;

                    case 1:
                        dot_1.setTextColor(Color.parseColor("#7C7676"));
                        dot_2.setTextColor(Color.parseColor("#111111"));
                        nextButton.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Dashboard.class));
            }
        });
    }
}
