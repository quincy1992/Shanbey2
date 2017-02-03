package com.quincy.shanbay.activity;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.quincy.shanbay.adapter.MyFragementAdapter;
import com.quincy.shanbay.view.HomePageFragment;
import com.jauker.widget.BadgeView;
import com.quincy.shanbay.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private RadioGroup radioGroup;
    private RadioButton rb_homepage, rb_discovery, rb_me;
    private ViewPager scrollViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        //actionBar
        ActionBar actionBar=getActionBar();
        actionBar.setCustomView(R.layout.actionbar_title);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        View target = findViewById(R.id.notification);
        BadgeView badgeView = new BadgeView(this);
        badgeView.setTargetView(target);
        badgeView.setBadgeCount(3);

        //radiogroup
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        rb_homepage = (RadioButton)findViewById(R.id.rb_homepage);
        rb_discovery = (RadioButton)findViewById(R.id.rb_discovery);
        rb_me = (RadioButton)findViewById(R.id.rb_me);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_homepage:

                        scrollViewPager.setCurrentItem(0, false);
                        break;
                    case R.id.rb_discovery:

                        scrollViewPager.setCurrentItem(1, false);
                        break;
                    case R.id.rb_me:

                        scrollViewPager.setCurrentItem(2, false);
                        break;

                }
            }
        });

        scrollViewPager = (ViewPager)findViewById(R.id.scrollPages);
        Fragment fragment1 = new HomePageFragment();
        Fragment fragment2 = new Fragment();
        Fragment fragment3 = new Fragment();
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        scrollViewPager.setAdapter(new MyFragementAdapter(getSupportFragmentManager(), fragmentList));
        scrollViewPager.setCurrentItem(0, false);
        //不需要滑动
        scrollViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        Toast.makeText(MainActivity.this, "单词", Toast.LENGTH_SHORT).show();
                        radioGroup.check(R.id.rb_homepage);
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this, "发现", Toast.LENGTH_SHORT).show();
                        radioGroup.check(R.id.rb_discovery);
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this, "我的", Toast.LENGTH_SHORT).show();
                        radioGroup.check(R.id.rb_me);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
