package com.xwlljj.mvp.demo.ui.view;

import com.xwlljj.mvp.demo.model.entity.User;

import java.util.List;

/**
 * Created by XieWei on 16/8/11.
 */
public interface MainView extends BaseView {

    void showUsers(List<User> users);

    void showUser(User user);

}
