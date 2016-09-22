package com.xwlljj.mvp.demo.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xwlljj.mvp.demo.BaseApp;
import com.xwlljj.mvp.demo.R;
import com.xwlljj.mvp.demo.model.entity.User;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by XieWei on 16/8/11.
 */
public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {
    private static final String TAG = "UsersAdapter";
    private List<User> data;

    public UsersAdapter() {
        data = new ArrayList<>();
    }

    public void addUsers(List<User> users) {
        if (users == null || users.isEmpty()) {
            return;
        }
        int lastIndex = data.size();
        data.addAll(users);
        notifyItemRangeChanged(lastIndex, users.size());
        Log.d(TAG, "更新了" + getItemCount());
    }

    public void addUser(User user) {
        if (user == null) {
            return;
        }
        data.add(user);
        notifyItemChanged(data.size() - 1);
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.users_item, parent, false));
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.showUser(data.get(position));
        Log.d(TAG, "onBindViewHolder() --->  " + position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivIcon;
        private TextView tvName;
        private TextView tvSex;
        private TextView tvAge;


        public UserViewHolder(View itemView) {
            super(itemView);
            ivIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvSex = (TextView) itemView.findViewById(R.id.tv_sex);
            tvAge = (TextView) itemView.findViewById(R.id.tv_age);
        }

        public void showUser(User user) {
            tvName.setText(user.getName());
            tvAge.setText(user.getAge() + BaseApp.getApp().getString(R.string.age));
            tvSex.setText(user.getSex() == 0 ? BaseApp.getApp().getString(R.string.male)
                    : BaseApp.getApp().getString(R.string.female));
            if (!TextUtils.isEmpty(user.getIconPath())) {
                Observable.create((Subscriber<? super Bitmap> subscriber) -> {
                    subscriber.onNext(BitmapFactory.decodeFile(user.getIconPath()));
                    subscriber.onCompleted();
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((bitmap) -> {
                            ivIcon.setImageBitmap(bitmap);
                        });
            }
        }
    }
}
