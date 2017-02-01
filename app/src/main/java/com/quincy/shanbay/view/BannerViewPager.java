package com.quincy.shanbay.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.quincy.shanbay.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by quincy on 17/1/27.
 */

public class BannerViewPager extends ViewPager implements ViewPager.OnPageChangeListener{

    private Context mContext;
    private final String TAG = "BannerViewPager";
    private final int DEFAULT_DELAY = 5000;
    private final int DEFAULT_PERIOD = 5000;
    private int mCurPosition = 0;
    private boolean isPressd = false;
    private List<String> mList;
    private List<ImageView> indicatorViewList = new ArrayList<ImageView>();
    private RelativeLayout indicators_rl;
    private Handler handler = new Handler();
    private Timer timer;
    private TimerTask mTimerTask;
    public BannerViewPager(Context context){
        super(context);
        mContext = context;
        initImageLoader();
    }

    public BannerViewPager(Context context, AttributeSet attrs){
        super(context, attrs);
        mContext = context;
        initImageLoader();
    }

    private void initImageLoader(){
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(mContext);
        ImageLoader.getInstance().init(configuration);
    }

    public void setData(List<String> list){
        if(list == null) {
            return;
        }
        mList = list;
        this.setAdapter(new BannerAdapter(mContext, list));
        startTimer(DEFAULT_DELAY, DEFAULT_PERIOD);
    }

//    public void initIndicators(){
//        indicators_rl = new RelativeLayout(mContext);
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//        this.addView(indicators_rl);
//        indicators_rl.setHorizontalGravity(Gravity.CENTER_VERTICAL);
//        for(int i = 0; i < mList.size(); i ++) {
//            indicatorViewList.add(new ImageView(mContext));
//            indicatorViewList.get(i).setImageResource(R.drawable.indicator_common);
//            indicators_rl.addView(indicatorViewList.get(i));
//        }
//    }

    public void startTimer(long delay, long period){
        if(timer == null){
            timer = new Timer();
        }
        if(mTimerTask == null){
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    if(!isPressd) {
                        mCurPosition = (mCurPosition + 1) % mList.size();
                        final int pos = mCurPosition;
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                setCurrentItem(pos, false);
                            }
                        });
                    }
                }
            };
        }
        timer.schedule(mTimerTask, delay, period);
    }

    public void stopTimer(){
        if(timer != null){
            timer.cancel();
            timer = null;
        }
        if(mTimerTask != null){
            mTimerTask.cancel();
            mTimerTask = null;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                isPressd = true;
                stopTimer();
                break;
            case MotionEvent.ACTION_UP:
                isPressd = false;
                startTimer(DEFAULT_DELAY, DEFAULT_PERIOD);
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){
        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
        mCurPosition = position;
    }

    public void onPageSelected(int position){
    }

    public void onPageScrollStateChanged(int state){
    }

    private class BannerAdapter extends PagerAdapter {

        private Context mContext;

        public BannerAdapter(Context context, List<String> list){
            mContext = context;
            mList = list;
        }

        public boolean isViewFromObject(View view, Object object){
            return view == object;
        }

        public int getCount(){
           return mList.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item, container, false);
            ImageView image = (ImageView) view.findViewById(R.id.image);

            DisplayImage(mList.get(position), image);
            final int pos = position;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "POS ：" + pos , Toast.LENGTH_SHORT).show();
                }
            });
            container.addView(view);

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

//    public class BannerInfo{
//        private String imageUrl;
//        private String targetUrl;
//
//        public String getImageUrl(){
//            return imageUrl;
//        }
//        public void setImageUrl(String imageUrl){
//            this.imageUrl = imageUrl;
//        }
//    }

    private void DisplayImage(String url,ImageView image){
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .resetViewBeforeLoading(true)
                .build();
        ImageLoader.getInstance().displayImage(url,image, options);
    }
}
