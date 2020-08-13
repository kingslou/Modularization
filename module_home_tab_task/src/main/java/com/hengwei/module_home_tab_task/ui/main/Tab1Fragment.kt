package com.hengwei.module_home_tab_task.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.chad.library.adapter.base.BaseQuickAdapter
import com.geen.commonlibary.RouteConfig
import com.geen.commonlibary.base.BaseFragment
import com.geen.commonlibary.mvp.BasePresenter
import com.geen.commonlibary.utils.TimeUtils
import com.hengwei.module_home_tab_task.bean.MainInfo
import com.hengwei.module_home_tab_task.databinding.TabTaskMainFragmentBinding
import com.hengwei.module_home_tab_task.ui.main.adapter.MainAdapter
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

@Route(path = RouteConfig.ROUTE_FRAGMENT_TAB1)
class Tab1Fragment : BaseFragment<BasePresenter?>() {
    private lateinit var mViewModel: MainViewModel
    private lateinit var mBinding: TabTaskMainFragmentBinding
    private lateinit var mainAdapter: MainAdapter
    private val mainInfoList: MutableList<MainInfo> = ArrayList()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mBinding = TabTaskMainFragmentBinding.inflate(inflater, container, false)
        initView()
        return mBinding!!.root
    }

    private fun initView() {
        mainAdapter = MainAdapter(mainInfoList)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mBinding.recycleView.layoutManager = layoutManager
        mBinding.recycleView.adapter = mainAdapter
        mBinding.recycleView.addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))
        mainAdapter.setOnItemClickListener { adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int ->
            val info = mainInfoList[position]
            if (info.isShowExpandView) {
                info.isShowExpandView = false
            } else {
                resetExpandView()
                info.isShowExpandView = true
            }
            mainAdapter!!.setList(mainInfoList)
            testObserver()
        }
    }

    /***
     * 使用RxJava 模拟延时调用
     */
    @SuppressLint("CheckResult")
    private fun testObserver() {
        val viewList = mViewModel.getMain().value!!
        Observable.fromIterable(viewList).concatMap(Function<MainInfo, ObservableSource<*>> { mainInfo: MainInfo ->
            Log.e("结果 concatMap", "" + mainInfo.title + TimeUtils.getNowTime())
            Observable.just(mainInfo).delay(5, TimeUnit.SECONDS)
        }).map { o: Any ->
            val info = o as MainInfo
            val title = info.title
            info.title = "map" + title + "时间" + TimeUtils.getNowTime()
            o
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { o: Any ->
                    val info = o as MainInfo
                    Log.e("结果", info.title + "--" + TimeUtils.getNowTime())
                }
    }

    private fun resetExpandView() {
        for (info in mainAdapter.data) {
            info.isShowExpandView = false
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //mViewModel = ViewModelProvider(mActivity).get(MainViewModel::class.java)
        mViewModel = MainViewModel()
        mViewModel.getMain().observe(viewLifecycleOwner, Observer { mainInfos -> mainAdapter.setList(mainInfos) })
    }

    override fun onResume() {
        super.onResume()
    }

    companion object {
        fun newInstance(): Tab1Fragment {
            return Tab1Fragment()
        }
    }
}