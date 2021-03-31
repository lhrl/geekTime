package org.geektimes.reactive.streams;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Desc: Subscription适配器
 * User: 刘浪
 * Date: 2021-03-31 01:15
 */
public class SubscriptionAdapter implements Subscription {

    private final DecoratingSubscriber subscriber;

    public SubscriptionAdapter(Subscriber subscriber) {
        this.subscriber = new DecoratingSubscriber(subscriber);
    }

    @Override
    public void request(long n) {
        if (n < 1) {
            throw new IllegalArgumentException("The number of elements to requests must be more than zero!");
        }
        this.subscriber.setMaxRequest(n);
    }

    @Override
    public void cancel() {
        this.subscriber.cancel();
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }
}
