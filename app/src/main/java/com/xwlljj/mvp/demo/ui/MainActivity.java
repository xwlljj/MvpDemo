package com.xwlljj.mvp.demo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xwlljj.mvp.demo.BaseActivity;
import com.xwlljj.mvp.demo.R;
import com.xwlljj.mvp.demo.adapter.UsersAdapter;
import com.xwlljj.mvp.demo.model.entity.User;
import com.xwlljj.mvp.demo.presenter.MainPresenter;
import com.xwlljj.mvp.demo.presenter.impl.MainPresenterImpl;
import com.xwlljj.mvp.demo.ui.view.MainView;
import com.xwlljj.mvp.demo.ui.widget.RecyclerViewDivider;

import java.util.List;

/**
 * Created by XieWei on 16/8/11.
 */
public class MainActivity extends BaseActivity implements MainView {
    private MainPresenter mainPresenter;
    private RecyclerView rvUsers;
    private UsersAdapter usersAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mainPresenter = new MainPresenterImpl(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews() {
        rvUsers = (RecyclerView) findViewById(R.id.rv_users);
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewDivider rvd = new RecyclerViewDivider(this, LinearLayoutManager.HORIZONTAL);
        rvd.setDividerHeight(10);
        rvUsers.addItemDecoration(rvd);
        usersAdapter = new UsersAdapter();
        rvUsers.setAdapter(usersAdapter);
        mainPresenter.loadUsers();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.main;
    }

    @Override
    public void showUsers(List<User> users) {
        usersAdapter.addUsers(users);
    }

    @Override
    public void showUser(User user) {

    }

    @Override
    protected void onDestroy() {
        mainPresenter.onDestory();
        super.onDestroy();
    }

}
