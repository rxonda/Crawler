package br.com.crawler;

import rx.Observable;
import rx.Subscriber;

import java.io.File;

/**
 * Created by raphael.costa on 19/06/2015.
 */
public class FilePptXCrawler implements Crawler {
    private File file;

    public FilePptXCrawler(File file) {
        this.file = file;
    }

    public Observable<String> crawl() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Documento PPTX");
            }
        });
    }
}
