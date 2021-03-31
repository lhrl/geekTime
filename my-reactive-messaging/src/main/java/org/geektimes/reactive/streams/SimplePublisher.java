package org.geektimes.reactive.streams;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc: 简单发布器
 * User: 刘浪
 * Date: 2021-03-31 01:26
 */
public class SimplePublisher<T> implements Publisher<T> {

    private List<Subscriber> subscribers = new ArrayList<>();


    @Override
    public void subscribe(Subscriber<? super T> subscriber) {
        SubscriptionAdapter subscriptionAdapter = new SubscriptionAdapter(subscriber);
        subscriber.onSubscribe(subscriptionAdapter);
        subscribers.add(subscriptionAdapter.getSubscriber());
    }

    public void publish(T data) {
        subscribers.forEach(s->{
            s.onNext(data);
        });
    }

    public static void main(String[] args) {
        SimplePublisher simplePublisher = new SimplePublisher<>();

        simplePublisher.subscribe(new BusinessSubscriber(6));

        for (int i = 0; i < 5; i++) {
            simplePublisher.publish(i);
        }

    }
}
