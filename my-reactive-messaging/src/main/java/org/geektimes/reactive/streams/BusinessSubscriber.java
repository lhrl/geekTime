package org.geektimes.reactive.streams;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Desc: 业务订阅者
 * User: 刘浪
 * Date: 2021-03-31 01:08
 */
public class BusinessSubscriber<T> implements Subscriber<T> {


    private Subscription subscription;

    private int count = 0;

    private final long maxRequest;

    public BusinessSubscriber(int maxRequest) {
        this.maxRequest = maxRequest;
    }

    @Override
    public void onSubscribe(Subscription s) {
        this.subscription = s;
        this.subscription.request(maxRequest);
    }

    @Override
    public void onNext(Object data) {
        if (count++ > 2) {
            subscription.cancel();
            return;
        }
        System.out.println("收到数据:" + data);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("遇到异常:" + throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("收到数据完成:");
    }
}
