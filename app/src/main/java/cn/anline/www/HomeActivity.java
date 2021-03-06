package cn.anline.www;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.anline.www.ann.OfficeWebsiteActivity;
import cn.anline.www.find.CircleMenuLayout;
import cn.anline.www.ftp.server.primftpd.PrimitiveFtpdActivity;
import cn.anline.www.qrcode.ScannerActivity;
import cn.anline.www.ssh.client.SSHClientActivity;
import cn.anline.www.tools.ZbQZoneActivity;
import cn.anline.www.vpn.local.LocalVPN;

public class HomeActivity extends Activity {
    private ViewPager mTabPager;
    private ImageView mTabAnn,mTabService,mTabFind,mTabBiz,mTabZone;
    private RelativeLayout layout;

    private int currIndex = 0;// 当前页卡编号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        layout = (RelativeLayout) findViewById(R.id.mainweixin);

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
            CircleMenuLayout mCircleMenuLayout;

            String[] mItemTexts = new String[] {
                    "SSH客户端 ",
                    "VNC View",
                    "PHP服务器",
                    "FTP服务器",
                    "FTP客户端",
                    "HTTP服务器",
                    "Python运行时",
                    "Jetty服务器",
                    "VNC服务器",
                    "VPN服务器",
                    "Ruby运行时",
                    "Java 反编译"
            };
            int[] mItemImgs = new int[] {
                    R.drawable.find_ssh_client,
                    R.drawable.find_vnc_client,
                    R.drawable.find_php_server,
                    R.drawable.find_ftp_server,
                    R.drawable.find_ftp_client,
                    R.drawable.find_http_server,
                    R.drawable.find_python_server,
                    R.drawable.find_jetty_server,
                    R.drawable.find_vnc_server,
                    R.drawable.find_vpn_server,
                    R.drawable.find_ruby_server,
                    R.drawable.find_java_decode

            };

            boolean isFindSetMenu = false;

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
                ((ViewPager) container).removeView(views.get(position));
            }

            @Override
            public Object instantiateItem(View container, int position) {
                ((ViewPager)container).addView(views.get(position));


                if(position == 0) {//第一个页面的控件监听


                    EditText edit_search;
                    ImageView iv_qrscan,iv_voice,home_menu1_iv,home_menu5_iv;
                    TextView home_menu1_tv,home_menu5_tv;
                    LinearLayout home_menu1,home_menu5;
                    iv_qrscan = (ImageView) findViewById(R.id.scan_qrcode);
                    iv_voice = (ImageView) findViewById(R.id.scan_voice);
                    iv_qrscan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intentScanQRcode = new Intent(getApplicationContext(), ScannerActivity.class);
                            startActivity(intentScanQRcode);
                        }
                    });
                    edit_search = (EditText) findViewById(R.id.edit_search);
                    edit_search.setImeOptions(EditorInfo.IME_ACTION_SEND);
                    edit_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                        @Override
                        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                            Toast.makeText(getApplicationContext(), "没有搜索到相关内容", Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    });
                    iv_voice.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getApplicationContext(), "语音识别", Toast.LENGTH_SHORT).show();
                        }
                    });
                    home_menu1 = (LinearLayout) findViewById(R.id.home_menu1);
                    home_menu5 = (LinearLayout) findViewById(R.id.home_menu5);

                    home_menu1_iv = (ImageView) findViewById(R.id.home_menu1_iv);
                    home_menu5_iv = (ImageView) findViewById(R.id.home_menu5_iv);


                    home_menu1_tv = (TextView) findViewById(R.id.home_menu1_tv);
                    home_menu5_tv = (TextView) findViewById(R.id.home_menu5_tv);



                    home_menu1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            goOfficeWebsite();

                        }
                    });
                    home_menu1_iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            goOfficeWebsite();

                        }
                    });
                    home_menu1_tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            goOfficeWebsite();

                        }
                    });
                    home_menu5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            goZbTool();

                        }
                    });
                    home_menu5_iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            goZbTool();

                        }
                    });
                    home_menu5_tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            goZbTool();

                        }
                    });
                }
                if(position == 1){//第2个页面的控件监听 服务



                }
                if(position == 2){//第3个页面的控件监听 发现


                    if (isFindSetMenu){

                    }else {

                        mCircleMenuLayout = (CircleMenuLayout) findViewById(R.id.id_menulayout);
                        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);

                        mCircleMenuLayout.setOnMenuItemClickListener(new CircleMenuLayout.OnMenuItemClickListener() {

                            @Override
                            public void itemClick(View view, int pos) {
                                switch (pos){
                                    case 0:
                                    {//SSH客户端
                                        Intent goSSHWindow = new Intent(getApplicationContext(), SSHClientActivity.class);
                                        startActivity(goSSHWindow);

                                    }
                                        break;
                                    case 1:
                                    {
                                    //VNC客户端
                                    }
                                    break;
                                    case 2:
                                    {
                                    //PHP服务器
                                    }
                                    break;
                                    case 3:
                                    {
                                    //FTP服务器
                                        Intent goFtpSer =new Intent(getApplicationContext(),PrimitiveFtpdActivity.class);
                                        startActivity(goFtpSer);
                                    }
                                    break;
                                    case 4:
                                    {
                                    //FTP客户端
                                    }
                                    break;
                                    case 5:
                                    {
                                    //HTTP服务器
                                    }
                                    break;
                                    case 6:
                                    {
                                    //Python服务器
                                    }
                                    break;
                                    case 7:
                                    {
                                    //Jetty服务器
                                    }
                                    break;
                                    case 8:
                                    {
                                        //VNC服务器
                                    }
                                    break;
                                    case 9:
                                    {
                                        //VPN服务器
                                        Intent goVPNLocalIntent = new Intent(getApplicationContext(), LocalVPN.class);
                                        startActivity(goVPNLocalIntent);
                                    }
                                    break;
                                    case 10:
                                    {
                                        //Ruby服务器
                                    }
                                    break;
                                    case 11:
                                    {
                                        //Java反编译
                                    }
                                    break;
                                    default:
                                        break;
                                }
                                Toast.makeText(getApplicationContext(), mItemTexts[pos],
                                        Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void itemCenterClick(View view) {

                            }
                        });
                        isFindSetMenu = true;
                    }






                }
                if(position == 3){//第4个页面的控件监听 商业


                }
                if(position == 4){//第5个页面的控件监听 空间


                }


                return views.get(position);
            }

            private void goOfficeWebsite(){
                Intent officWebSiteIntent = new Intent(getApplicationContext(), OfficeWebsiteActivity.class);
                startActivity(officWebSiteIntent);
            }
            private void goZbTool(){
                Intent zbToolIntent = new Intent(getApplicationContext(), ZbQZoneActivity.class);
                startActivity(zbToolIntent);
            }


        };
        mTabPager.setAdapter(mPagerAdapter);


    }
    public void setLayoutBg(int annn){
        switch (annn) {
            case 0:
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.ann_bg));
                break;
            case  1:
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.service_bg));
                break;
            case 2:
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.find_bg));
                break;
            case 3:
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.biz_bg));
                break;
            case 4:
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.zone_bg));
                break;
            default:
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.anbg));
                break;
        }

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
//                    setLayoutBg(0);

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
//                    setLayoutBg(1);
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
//                    setLayoutBg(2);
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
//                    setLayoutBg(3);
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
//                    setLayoutBg(4);
//                    zoneListView.setSelector(new ColorDrawable(Color.BLUE));
//                    adapterListView = new AdapterListView(getApplicationContext(),aTitle,aPic,aDesc);
//                    zoneListView.setAdapter(adapterListView);
//                    zoneListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            Toast.makeText(getApplicationContext(), "您点击了" + aTitle[position], Toast.LENGTH_LONG).show();
//                        }
//                    });
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