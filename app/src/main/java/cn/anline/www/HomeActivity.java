package cn.anline.www;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class HomeActivity extends Activity {
    private ViewPager mTabPager;
    private ImageView mTabAnn,mTabService,mTabFind,mTabBiz,mTabZone;
    private int currIndex = 0;// 当前页卡编号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTabPager = (ViewPager)findViewById(R.id.tabpager);
        mTabPager.setOnPageChangeListener(new MyOnPageChangeListener());

        mTabAnn = (ImageView) findViewById(R.id.tab_img_ann);
        mTabService = (ImageView) findViewById(R.id.tab_img_service);
        mTabFind = (ImageView) findViewById(R.id.tab_img_find);
        mTabBiz = (ImageView) findViewById(R.id.tab_img_biz);
        mTabZone = (ImageView) findViewById(R.id.tab_img_zone);
        mTabAnn .setOnClickListener(new MyOnClickListener(0));
        mTabService.setOnClickListener(new MyOnClickListener(1));
        mTabFind.setOnClickListener(new MyOnClickListener(2));
        mTabBiz.setOnClickListener(new MyOnClickListener(3));
        mTabZone.setOnClickListener(new MyOnClickListener(4));

        //将要分页显示的View装入数组中
        LayoutInflater mLi = LayoutInflater.from(this);

        View viewAnn = mLi.inflate(R.layout.activity_ann, null);
        View viewService = mLi.inflate(R.layout.activity_service, null);
        View viewFind = mLi.inflate(R.layout.activity_find, null);
        View viewBiz = mLi.inflate(R.layout.activity_biz, null);
        View viewZone = mLi.inflate(R.layout.activity_zone, null);

        //每个页面的view数据
        final ArrayList<View> views = new ArrayList<View>();
        views.add(viewAnn);
        views.add(viewService);
        views.add(viewFind);
        views.add(viewBiz);
        views.add(viewZone);
        //填充ViewPager的数据适配器
        PagerAdapter mPagerAdapter = new PagerAdapter()
        {
            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public void destroyItem(View container, int position, Object object) {
                ((ViewPager)container).removeView(views.get(position));
            }

            @Override
            public Object instantiateItem(View container, int position) {
                ((ViewPager)container).addView(views.get(position));
                return views.get(position);
            }
        };
        mTabPager.setAdapter(mPagerAdapter);


        //内页控件监听
    }

    /**
     * 头标点击监听
     */
    public class MyOnClickListener implements View.OnClickListener
    {
        private int index = 0;
        public MyOnClickListener(int i)
        {
            index = i;
        }
        @Override
        public void onClick(View v)
        {
            mTabPager.setCurrentItem(index);
        }
    };

    /**
     * 页卡切换监听
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener
    {
        @Override
        public void onPageSelected(int arg0)
        {
            switch (arg0)
            {
                case 0:
                {
                    mTabAnn.setImageDrawable(getResources().getDrawable(R.drawable.annpress));
                    if (currIndex == 1)
                    {
                        mTabService.setImageDrawable(getResources().getDrawable(R.drawable.service));
                    }
                    else if (currIndex == 2)
                    {
                        mTabFind.setImageDrawable(getResources().getDrawable(R.drawable.find));
                    }
                    else if (currIndex == 3)
                    {
                        mTabBiz.setImageDrawable(getResources().getDrawable(R.drawable.biz));
                    }
                    else if (currIndex == 4)
                    {
                        mTabZone.setImageDrawable(getResources().getDrawable(R.drawable.zone));
                    }
                    break;
                }
                case 1:
                {
                    mTabService.setImageDrawable(getResources().getDrawable(R.drawable.servicepress));
                    if (currIndex == 0)
                    {
                        mTabAnn.setImageDrawable(getResources().getDrawable(R.drawable.ann));
                    }
                    else if (currIndex == 2)
                    {
                        mTabFind.setImageDrawable(getResources().getDrawable(R.drawable.find));
                    }
                    else if (currIndex == 3)
                    {
                        mTabBiz.setImageDrawable(getResources().getDrawable(R.drawable.biz));
                    }
                    else if (currIndex == 4)
                    {
                        mTabZone.setImageDrawable(getResources().getDrawable(R.drawable.zone));
                    }
                    break;
                }
                case 2:
                {
                    mTabFind.setImageDrawable(getResources().getDrawable(R.drawable.findpress));
                    if (currIndex == 0)
                    {
                        mTabAnn.setImageDrawable(getResources().getDrawable(R.drawable.ann));
                    }
                    else if (currIndex == 1)
                    {
                        mTabService.setImageDrawable(getResources().getDrawable(R.drawable.service));
                    }
                    else if (currIndex == 3)
                    {
                        mTabBiz.setImageDrawable(getResources().getDrawable(R.drawable.biz));
                    }
                    else if (currIndex == 4)
                    {
                        mTabZone.setImageDrawable(getResources().getDrawable(R.drawable.zone));
                    }
                    break;
                }
                case 3:
                {
                    mTabBiz.setImageDrawable(getResources().getDrawable(R.drawable.bizpress));
                    if (currIndex == 0)
                    {
                        mTabAnn.setImageDrawable(getResources().getDrawable(R.drawable.ann));
                    }
                    else if (currIndex == 2)
                    {
                        mTabFind.setImageDrawable(getResources().getDrawable(R.drawable.find));
                    }
                    else if (currIndex == 1)
                    {
                        mTabService.setImageDrawable(getResources().getDrawable(R.drawable.service));
                    }
                    else if (currIndex == 4)
                    {
                        mTabZone.setImageDrawable(getResources().getDrawable(R.drawable.zone));
                    }
                    break;
                }
                case 4:
                {
                    mTabZone.setImageDrawable(getResources().getDrawable(R.drawable.zonepress));
                    if (currIndex == 0)
                    {
                        mTabAnn.setImageDrawable(getResources().getDrawable(R.drawable.ann));
                    }
                    else if (currIndex == 2)
                    {
                        mTabFind.setImageDrawable(getResources().getDrawable(R.drawable.find));
                    }
                    else if (currIndex == 3)
                    {
                        mTabBiz.setImageDrawable(getResources().getDrawable(R.drawable.biz));
                    }
                    else if (currIndex == 1)
                    {
                        mTabService.setImageDrawable(getResources().getDrawable(R.drawable.service));
                    }
                    break;
                }
            }
            currIndex = arg0;
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }
    public void onBackPressed() {
        new AlertDialog.Builder(this).setTitle("确定退出程序吗？")
                .setIcon(R.drawable.waring)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“确认”后的操作
                        HomeActivity.this.finish();

                    }
                })
                .setMessage("确定后将关闭安浪创想APP。\n非常感谢您对安浪的支持！")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“返回”后的操作,这里不设置没有任何操作
                    }
                }).show();
    }
}