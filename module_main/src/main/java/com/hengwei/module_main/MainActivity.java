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
import com.hengwei.module_main.databinding.MainActivityMainBinding;
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
        LinearLayoutCompat.LayoutParams params = new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.MATCH_PARENT);

        for(int i=0;i<fragmentList.size();i++){
            @SuppressLint("InflateParams")
            View view = getLayoutInflater().inflate(R.layout.main_item_tab,null);
            AppCompatTextView tabName = view.findViewById(R.id.text_tab);
            tabName.setText("Tab"+i);
            view.findViewById(R.id.llTabRoot).setOnClickListener(v->{
                resetTabSelect();
                view.setSelected(true);
            });
            params.weight = 1;
            mainBinding.llBottomNet.addView(view,params);
            viewList.add(view);
        }
    }

    private void resetTabSelect(){
        for(View view:viewList){
            view.setSelected(false);
        }
    }
}