package com.quincy.shanbay.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quincy.shanbay.R;
import com.quincy.shanbay.activity.SelfTestActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quincy on 1/22/17.
 */
public class ReciteFragment extends Fragment {

    private final String TAG = this.getClass().getSimpleName();
    private BannerViewPager mViewPager;
    private TextView mStartView;
    private List<String> mList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recite, container, false);
        mViewPager = (BannerViewPager) view.findViewById(R.id.view_pager);
        mStartView = (TextView) view.findViewById(R.id.Start_Word) ;
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

        mStartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onclick start");
                Intent intent = new Intent(getActivity(), SelfTestActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }

}
