package br.com.crawler;

import rx.Observable;
import rx.Subscriber;

import java.net.URL;

/**
 * Created by raphael.costa on 19/06/2015.
 */
public class WebCrawlingCommand implements UserCommand {
    private final URL url;
    private final int deep;

    public WebCrawlingCommand(URL url, int deep) {
        this.url = url;
        this.deep = deep;
    }

    public Observable<Crawler> invoke() {
        return Observable.create(new Observable.OnSubscribe<Crawler>() {
            public void call(Subscriber<? super Crawler> subscriber) {
                try {
                    subscriber.onNext(new WebCrawler(url, deep));
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
