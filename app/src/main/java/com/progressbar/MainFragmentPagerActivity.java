package com.progressbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class MainFragmentPagerActivity extends FragmentActivity implements
        View.OnClickListener {

    ViewPager viewpager;
    Button[] btnArray;
    MainFragmentPagerAdapter adapter;
    private int currentPageIndex = 0;
    private ArrayList<Fragment> datas;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.mainfragmentpager);


        viewpager = (ViewPager) this.findViewById(R.id.viewPager);

        adapter = new MainFragmentPagerAdapter(this.getSupportFragmentManager(), null);
        viewpager.setAdapter(adapter);


        setupView();
        addListener();
        setButtonColor();
        viewpager.setCurrentItem(2);

    }


    private void setButtonColor() {
        for (int i = 0; i < btnArray.length; i++) {
            if (i == currentPageIndex) {
                btnArray[i].setTextColor(0xFFFFFFFF);
            } else {
                btnArray[i].setTextColor(0xFF000000);
            }

        }
    }


    long oldTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        long currentTime = System.currentTimeMillis();

        if (keyCode == KeyEvent.KEYCODE_BACK && currentTime - oldTime > 3 * 1000) {
            Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            oldTime = System.currentTimeMillis();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK && currentTime - oldTime < 3 * 1000) {
            oldTime = 0;
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


    private void addListener() {
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int index) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int pageIndex) {
                currentPageIndex = pageIndex;
                setButtonColor();
            }
        });

        for (Button btn : btnArray) {
            btn.setOnClickListener(this);
        }
    }


    Fragment1 f1 = null;

    private void setupView() {
        datas = new ArrayList<Fragment>();
        datas.add(f1 = new Fragment1());


        adapter.setDatas(datas);
        adapter.notifyDataSetChanged();


        btnArray = new Button[]{
                (Button) findViewById(R.id.btnFriendList),
        };
    }


    public void doOnClick(View v) {
        f1.doOnClick(v);
    }


    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.btnFriendList:
                    currentPageIndex = 0;
                    break;
            }
            viewpager.setCurrentItem(currentPageIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}




