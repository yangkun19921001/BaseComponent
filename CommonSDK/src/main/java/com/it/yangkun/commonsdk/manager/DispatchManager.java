package com.it.yangkun.commonsdk.manager;

import android.os.Bundle;
import android.os.Message;

import com.blankj.utilcode.util.StringUtils;
import com.jess.arms.integration.AppManager;

import org.simple.eventbus.EventBus;

/**
 * Created by yangk on 2018/11/11.
 * 所有发出去消息的总管理
 */

public class DispatchManager<T> {

    private static DispatchManager instance;
    private String TAG = this.getClass().getSimpleName();

    public synchronized static DispatchManager getInstance() {
        if (instance == null)
            instance = new DispatchManager();
        return instance;
    }

    /**
     * 初始化接收到的消息
     *
     * @param appManager
     */
    public void initRevicesMessage(AppManager appManager) {
        appManager.setHandleListener(new AppManager.HandleListener() {
            @Override
            public void handleMessage(AppManager appManager, Message message) {
                switch (message.what) {


                }
            }
        });
    }

    /**
     * 发送消息
     */
    public void sendMessage(int what, T t) {
        Message message = new Message();
        message.what = what;
        message.obj = t;
        AppManager.post(message);
    }

    public void sendMessage(int what, Bundle bundle) {
        Message message = new Message();
        message.what = what;
        message.setData(bundle);
        AppManager.post(message);
    }

    /**
     * 将收到的消息统一分发下去
     *
     * @param tag     接收消息注册的 TAG
     * @param message 发送消息的 body
     */
    public void dispatchMessage(String tag, Message message) {
        if (StringUtils.isEmpty(tag) || message == null)
            throw new NullPointerException("tag is null ? or message is null ?");
        EventBus.getDefault().post(message, tag);
    }

    /**
     * 将收到的消息统一分发下去
     *
     * @param tag     接收消息注册的 TAG
     * @param message 发送消息的 body
     */
    public void dispatchMessage(String tag, String message) {
        if (StringUtils.isEmpty(tag) || message == null)
            throw new NullPointerException("tag is null ? or message is null ?");
        EventBus.getDefault().post(message, tag);
    }
}
