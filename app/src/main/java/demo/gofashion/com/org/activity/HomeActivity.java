package demo.gofashion.com.org.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import demo.gofashion.com.org.fragment.ClassifyFragment;
import demo.gofashion.com.org.fragment.CommunityFragment;
import demo.gofashion.com.org.fragment.IndexFragment;
import demo.gofashion.com.org.utils.UrlUtils;
import demo.gofashion.com.org.utils.dialog.DialogUtil;


public class HomeActivity extends AppCompatActivity implements ClassifyFragment.OnDrawerLayoutIsOpenListener {

    private RadioGroup radioTab;
    private FragmentManager fragmentManager;
    private IndexFragment indexFragment;

    private ClassifyFragment classifyFragment;

    private CommunityFragment communityFragment;
    private DrawerLayout drawerlayout;
    private NavigationView navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        radioTab = ((RadioGroup) findViewById(R.id.tabGruop));
        fragmentManager = getSupportFragmentManager();
        initRadioTab();
        initNavigationview();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        indexFragment = new IndexFragment();
        transaction.replace(R.id.fragmentContainer, indexFragment);
        transaction.commit();

    }

    private void initNavigationview() {
        drawerlayout = ((DrawerLayout) findViewById(R.id.drawerlayout));
        navigation = ((NavigationView) findViewById(R.id.navigation_main));
        //设置导航菜单的宽度
        ViewGroup.LayoutParams params = navigation.getLayoutParams();
        params.width = getResources().getDisplayMetrics().widthPixels * 2 / 3;
        navigation.setLayoutParams(params);
        //找到headview
        LinearLayout view  = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.navigation_latout, navigation,false);
        Button btn_login = (Button) view.findViewById(R.id.btn_login_navigation);
        navigation.addHeaderView(view);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("tag", "点击登录");
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        //设置监听事件
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.action_dhfp:  //兑换Go票
                        goActivity(GoPiaoActivity.class);
                        break;
                    case R.id.action_fanpiao:  //我的Go票
                        goActivity(LoginActivity.class);
                        break;
                    case R.id.action_collect: //收藏记录
                        goActivity(CollectActivity.class);
                        break;
                    case R.id.action_scan: //扫一扫
                        goActivity(NavigationDetailActivity.class);
                        break;
                    case R.id.action_ceyanzhi: //测试值
                        goActivity(CeYanZhiActivtiy.class);
                        break;
                    case R.id.action_bought: //我的订单
                        goActivity(LoginActivity.class);
                        break;
                    case R.id.action_service: //联系客服
                        goActivity(LoginActivity.class);
                        break;
                    case R.id.action_info: //版本信息
                        goActivity(PublishActivity.class);
                        break;
                }

                item.setChecked(true);
                //关闭抽屉
                drawerlayout.closeDrawer(navigation);
                return true;
            }
        });
    }

    private void initRadioTab() {
        radioTab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int childCount = group.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    RadioButton radioButton = (RadioButton) group.getChildAt(i);
                    if (radioButton.getId() == checkedId) {
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        //先隐藏之前的Fragment
                        hideBeforeFragment(transaction);
                        switch (i) {
                            case 0:
                                if (indexFragment == null) {
                                    indexFragment = new IndexFragment();
                                    transaction.add(R.id.fragmentContainer, indexFragment);
                                } else {
                                    transaction.show(indexFragment);
                                }
                                break;
                            case 1:
                                if (classifyFragment == null) {
                                    classifyFragment = new ClassifyFragment();
                                    transaction.add(R.id.fragmentContainer, classifyFragment);
                                } else {
                                    transaction.show(classifyFragment);
                                }

                                break;
                            case 2:
                                if (communityFragment == null) {
                                    communityFragment = new CommunityFragment();
                                    transaction.add(R.id.fragmentContainer, communityFragment);
                                } else {
                                    transaction.show(communityFragment);
                                }
                                break;
                            case 3:
                                Intent intent1 = new Intent(HomeActivity.this,LoginActivity.class);
                                startActivity(intent1);
                                break;
                            case 4:
                                Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
                                startActivity(intent);
                                break;
                        }
                        transaction.commit();
                    }
                }
            }
        });
    }

    private void hideBeforeFragment(FragmentTransaction transaction) {
        if (indexFragment != null) {
            transaction.hide(indexFragment);
        }
        if (classifyFragment != null) {
            transaction.hide(classifyFragment);
        }
        if (communityFragment != null) {
            transaction.hide(communityFragment);
        }
    }

    //跳转到某Activtiy

    protected void goActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {

        DialogUtil.getBuilderDilog(this, R.drawable.ico_collect, "提示",
                "确认要退出吗？");
    }

    @Override
    public void isOpenDrawerLayout(int type) {
        if(type==1){
            drawerlayout.openDrawer(navigation);
            Log.e("temp", "isOpenDrawerLayout: 进来打开了NavigationView"+type);
        }else if(type==2){
            drawerlayout.closeDrawer(navigation);
            Log.e("temp", "isOpenDrawerLayout: 进来关闭了NavigationView" + type);
        }
    }
}
