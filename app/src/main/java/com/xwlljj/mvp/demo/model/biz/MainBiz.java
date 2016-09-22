package com.xwlljj.mvp.demo.model.biz;

import com.xwlljj.mvp.demo.model.entity.User;

import java.util.List;

import rx.Observable;

/**
 * Created by XieWei on 16/8/11.
 */
public interface MainBiz {

    Observable<List<User>> loadUsers();
}
