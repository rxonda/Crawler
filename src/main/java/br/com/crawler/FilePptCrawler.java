package br.com.crawler;

import rx.Observable;
import rx.Subscriber;

import java.io.File;

/**
 * Created by raphael.costa on 19/06/2015.
 */
public class FilePptCrawler implements Crawler {
    public FilePptCrawler(File file) {
    }

    public Observable<String> crawl() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Documento PPT");
            }
        });
    }
}
