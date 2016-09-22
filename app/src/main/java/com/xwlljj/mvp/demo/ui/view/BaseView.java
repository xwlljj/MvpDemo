package com.xwlljj.mvp.demo.ui.view;

/**
 * Created by XieWei on 16/8/11.
 */
public interface BaseView {

    void showDialog(String msg);

    void hideDialog();

    void showProgress(int progerss);

    void complite();

}
