package com.quincy.shanbay.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.quincy.shanbay.R;
import com.quincy.shanbay.utils.ToastUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by quincy on 1/16/17.
 */
public class HomePageFragment extends Fragment {

    private ViewPager viewPager;
    private RadioGroup radioGroup_hp;
    private RadioButton rb_recite, rb_progress, rb_word_db, rb_recommend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, null);
        initView(view);
        return view;
    }

    private void initView(View view){
        viewPager = (ViewPager) view.findViewById(R.id.ViewPager_container);
        radioGroup_hp = (RadioGroup)view.findViewById(R.id.radioGroup_hp);
        rb_recite = (RadioButton)view.findViewById(R.id.rb_recite);
        rb_progress = (RadioButton)view.findViewById(R.id.rb_progress);
        rb_word_db = (RadioButton)view.findViewById(R.id.rb_word_db);
        rb_recommend = (RadioButton)view.findViewById(R.id.rb_recommend);
        radioGroup_hp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_recite:
                        ToastUtils.showToast(getActivity(), "rb_recite");
                        viewPager.setCurrentItem(0, false);
                        break;
                    case R.id.rb_progress:
                        ToastUtils.showToast(getActivity(), "rb_progress");
                        viewPager.setCurrentItem(1, false);
                        break;
                    case R.id.rb_word_db:
                        ToastUtils.showToast(getActivity(), "rb_word_db");
                        viewPager.setCurrentItem(2, false);
                        break;
                    case R.id.rb_recommend:
                        ToastUtils.showToast(getActivity(), "rb_recommend");
                        viewPager.setCurrentItem(3, false);
                        break;

                }
            }
        });

        Fragment fragment1 = new ReciteFragment();
        Fragment fragment2 = new Fragment();
        Fragment fragment3 = new Fragment();
        Fragment fragment4 = new Fragment();
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentList.add(fragment4);

        viewPager.setAdapter(new PagerAdater((getActivity()).getSupportFragmentManager(), fragmentList));

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        ToastUtils.showToast(getActivity(), "rb_recite");
                        radioGroup_hp.check(R.id.rb_recite);
                        break;
                    case 1:
                        ToastUtils.showToast(getActivity(), "rb_progress");
                        radioGroup_hp.check(R.id.rb_progress);
                        break;
                    case 2:
                        ToastUtils.showToast(getActivity(), "rb_word_db");
                        radioGroup_hp.check(R.id.rb_word_db);
                        break;

                    case 3:
                        ToastUtils.showToast(getActivity(), "rb_recommend");
                        radioGroup_hp.check(R.id.rb_recommend);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public class PagerAdater extends FragmentPagerAdapter {

        private List<Fragment> mList;

        public PagerAdater(FragmentManager fm, List<Fragment> list) {
            super(fm);
            mList = list;
        }

        @Override
        public Fragment getItem(int i) {
            return mList.get(i);
        }

        @Override
        public int getCount() {
            return mList.size();
        }
    }
}
