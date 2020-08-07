package com.hengwei.module_main;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.geen.commonlibary.RouteConfig;
import com.geen.commonlibary.base.BaseActivity;
import com.geen.componentmanger.ServiceFactory;
import com.hengwei.module_main.databinding.MainActivityMainBinding;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

/***
 * @author 86153
 * 首页
 */
@Route(path = RouteConfig.ROUTE_MAIN)
public class MainActivity extends BaseActivity {

    private MainActivityMainBinding mainBinding;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<View> viewList = new ArrayList<>();
    private HomePagerAdapter homePagerAdapter;
    private int index = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = MainActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        initToolbar(mainBinding.toolbar,false);
        initFragment();


    }

    private void initFragment(){

        Fragment tab1 = (Fragment)ARouter.getInstance().build(RouteConfig.ROUTE_FRAGMENT_TAB1).navigation();
        if(tab1!=null){
            fragmentList.add(tab1);
        }
        Fragment tab2 = (Fragment)ARouter.getInstance().build(RouteConfig.ROUTE_FRAGMENT_TAB2).navigation();
        if(tab2!=null){
            fragmentList.add(tab2);
        }
        Fragment tab3 = (Fragment)ARouter.getInstance().build(RouteConfig.ROUTE_FRAGMENT_TAB3).navigation();
        if(tab3!=null){
            fragmentList.add(tab3);
        }

        Fragment tab4 = (Fragment)ARouter.getInstance().build(RouteConfig.ROUTE_FRAGMENT_TAB4).navigation();
        if(tab4!=null){
            fragmentList.add(tab4);
        }

        KLog.e("数量"+fragmentList.size());

        LinearLayoutCompat.LayoutParams params = new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.MATCH_PARENT);

        for(int i=0;i<fragmentList.size();i++){
            index++;
            @SuppressLint("InflateParams")
            View view = getLayoutInflater().inflate(R.layout.main_item_tab,null);
            view.setTag(index);
            AppCompatTextView tabName = view.findViewById(R.id.text_tab);
            tabName.setText("Tab"+i);
            view.findViewById(R.id.llTabRoot).setOnClickListener(v->{
                resetTabSelect();
                view.setSelected(true);
                mainBinding.viewPager.setCurrentItem(Integer.parseInt(view.getTag().toString()),false);
            });
            params.weight = 1;
            mainBinding.llBottomNet.addView(view,params);
            viewList.add(view);
        }

        homePagerAdapter = new HomePagerAdapter(getSupportFragmentManager(),fragmentList);
        mainBinding.viewPager.setAdapter(homePagerAdapter);
        mainBinding.viewPager.setOffscreenPageLimit(fragmentList.size());

        if(fragmentList.size()>0){
            mainBinding.viewPager.setCurrentItem(0);
            viewList.get(0).setSelected(true);
        }
    }

    private void resetTabSelect(){
        for(View view:viewList){
            view.setSelected(false);
        }
    }
}