package com.xwlljj.mvp.demo.presenter.impl;

import android.util.Log;

import com.xwlljj.mvp.demo.model.biz.MainBiz;
import com.xwlljj.mvp.demo.model.biz.impl.MainBizImpl;
import com.xwlljj.mvp.demo.model.entity.User;
import com.xwlljj.mvp.demo.presenter.MainPresenter;
import com.xwlljj.mvp.demo.ui.view.MainView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by XieWei on 16/8/11.
 */
public class MainPresenterImpl implements MainPresenter {
    private MainView mainView;
    private MainBiz mainBiz;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        mainBiz = new MainBizImpl();
    }

    @Override
    public void loadUsers() {
        mainBiz.loadUsers().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<User>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("xwlljj", "onCompleted");
                        mainView.hideDialog();
                        mainView.complite();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainView.hideDialog();
                        mainView.showDialog(e.getMessage());
                        e.printStackTrace();
                        Log.e("xwlljj", "onError: " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(List<User> users) {
                        Log.d("xwlljj", "onNext: " + users);
                        mainView.showUsers(users);
                        mainView.hideDialog();
                    }
                });
    }

    @Override
    public void onDestory() {
        mainView = null;
        mainBiz = null;
    }
}
