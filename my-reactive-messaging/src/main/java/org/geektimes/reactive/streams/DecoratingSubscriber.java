package org.geektimes.reactive.streams;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.logging.Logger;

/**
 * Desc: 订阅者装饰器
 * User: 刘浪
 * Date: 2021-03-31 01:18
 */
public class DecoratingSubscriber<T> implements Subscriber<T> {

    private Logger logger = Logger.getLogger(DecoratingSubscriber.class.getName());

    private final Subscriber subscriber;

    private long maxRequest = -1;

    private boolean canceled = false;

    private boolean completed = false;

    private long requestCount = 0;

    public DecoratingSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscriber.onSubscribe(subscription);
    }

    @Override
    public void onNext(T t) {
        assertRequest();

        if (isCompleted()) {
            logger.severe("The data subscription was completed, This method should not be invoked again!");
            return;
        }

        if (isCanceled()) { // Indicates that the Subscriber invokes Subscription#cancel() method.
            logger.warning(String.format("The Subscriber has canceled the data subscription," +
                    " current data[%s] will be ignored!", t));
            return;
        }

        if (requestCount == maxRequest && maxRequest < Long.MAX_VALUE) {
            onComplete();
            logger.warning(String.format("The number of requests is up to the max threshold[%d]," +
                    " the data subscription is completed!", maxRequest));
            return;
        }

        next(t);
    }

    private void next(T t) {
        try {
            subscriber.onNext(t);
        } catch (Throwable e) {
            onError(e);
        } finally {
            requestCount++;
        }
    }

    @Override
    public void onError(Throwable throwable) {
        subscriber.onError(throwable);
    }

    @Override
    public void onComplete() {
        subscriber.onComplete();
    }

    public long getMaxRequest() {
        return maxRequest;
    }

    public void setMaxRequest(long maxRequest) {
        this.maxRequest = maxRequest;
    }
    public void cancel() {
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public boolean isCompleted() {
        return completed;
    }

    private void assertRequest() {
        if (maxRequest < 1) {
            throw new IllegalStateException("the number of request must be initialized before " +
                    "Subscriber#onNext(Object) method, please set the positive number to " +
                    "Subscription#request(int) method on Publisher#subscribe(Subscriber) phase.");
        }
    }
}
