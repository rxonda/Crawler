package br.com.crawler;

import rx.Observable;
import rx.Subscriber;

import java.io.File;

/**
 * Created by raphael.costa on 19/06/2015.
 */
public class FileCrawlingCommand implements UserCommand {
    private File file;

    public FileCrawlingCommand(File file) {
        this.file = file;
    }

    public Observable<Crawler> invoke() {
        return Observable.create(new Observable.OnSubscribe<Crawler>() {
            public void call(Subscriber<? super Crawler> subscriber) {
                subscriber.onNext(new DirectoryCrawler(file));
            }
        });
    }
}
