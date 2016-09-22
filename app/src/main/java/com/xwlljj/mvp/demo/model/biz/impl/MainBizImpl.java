package com.xwlljj.mvp.demo.model.biz.impl;

import android.util.Log;

import com.xwlljj.mvp.demo.model.biz.MainBiz;
import com.xwlljj.mvp.demo.model.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by XieWei on 16/8/11.
 */
public class MainBizImpl implements MainBiz {

    private static final String[] FAMILY_NAMES = {
            "赵", "钱", "孙", "李", "周"
            , "武", "郑", "王", "冯", "陈"
            , "楮", "卫", "蒋", "沈", "韩"};

    private static final String[] FIRST_NAMES = {
            "吞楞", "要上", "揽眯", "车困", "肯本"
            , "绿斫", "本罗", "一辘", "个龙", "上思"
            , "玉田", "罡", "粗经", "靓仔", "畏胡"
    };

    @Override
    public Observable<List<User>> loadUsers() {
        return Observable.create((subscriber) -> {
            List<User> users = new ArrayList<User>();
            Random random = new Random(15);
            Log.d("xwlljj", "next int is " + random.nextInt(15));
            for (int i = 0; i < 15; i++) {
                User user = new User();
                Log.d("xwlljj", "nextInt : " + random.nextInt(15));
                user.setName(FAMILY_NAMES[random.nextInt(15)] + FIRST_NAMES[random.nextInt(15)]);
                user.setAge(random.nextInt(15) * random.nextInt(i + 1));
                user.setSex(i % 2 == 0 ? (byte) 0 : (byte) 1);
                users.add(user);
            }
            subscriber.onNext(users);
            subscriber.onCompleted();
        });
    }

    public void test() {
        Observable<String> observable = Observable.create(new Observable
                .OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onStart();
                for (int i = 0; i < 50; i++) {
                    if (i == 49) {
                        subscriber.onError(new NullPointerException());
                    } else {
                        subscriber.onNext(i + "----");
                    }
                }
                subscriber.onCompleted();
            }
        });
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .map(new Func1<String, User>() {
                    @Override
                    public User call(String name) {
                        User u = new User();
                        u.setName(name);
                        return u;
                    }
                })
                .filter(new Func1<User, Boolean>() {
                    @Override
                    public Boolean call(User user) {
                        if (user.getName().equals("0")) {
                            return false;
                        }
                        return true;
                    }
                }).flatMap(new Func1<User, Observable<Character>>() {
            @Override
            public Observable<Character> call(User user) {
                return Observable.from(new Character[]{});
            }
        });
    }

    public void test2() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("www.baidu.com").build();

    }
}
