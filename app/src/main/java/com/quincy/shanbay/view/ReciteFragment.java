package com.quincy.shanbay.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.quincy.shanbay.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quincy on 1/22/17.
 */
public class ReciteFragment extends Fragment {

    private final String TAG = this.getClass().getSimpleName();
    private BannerViewPager mViewPager;
    private List<String> mList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recite, container, false);
        mViewPager = (BannerViewPager) view.findViewById(R.id.view_pager);
        init();
        return view;
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    private void init(){
        mList = new ArrayList<>();
        mList.add("https://static.baydn.com/media/media_store/image/7ffbf4084480462a6db952859e7d8b4d.png");
        mList.add("https://static.baydn.com/media/media_store/image/feeee16e97407bbca8957702a674e421.png");
        mList.add("https://static.baydn.com/media/media_store/image/0121935e7513b1e001d04a39c62b6247.png");
        mViewPager.setData(mList);
    }

}
