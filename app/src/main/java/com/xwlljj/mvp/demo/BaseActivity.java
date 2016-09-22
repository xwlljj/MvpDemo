package com.xwlljj.mvp.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.xwlljj.mvp.demo.ui.view.BaseView;

/**
 * Created by XieWei on 16/8/11.
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        initViews();
    }

    protected void initViews() {

    }

    protected abstract int getContentViewId();

    @Override
    public void showDialog(String msg) {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void showProgress(int progerss) {

    }

    @Override
    public void complite() {

    }
}
