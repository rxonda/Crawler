package br.com.crawler;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

import java.io.File;

/**
 * Created by raphael.costa on 19/06/2015.
 */
public class DirectoryCrawler implements Crawler {
    private File directory;

    public DirectoryCrawler(File directory) {
        this.directory = directory;
    }

    public Observable<String> crawl() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            public void call(final Subscriber<? super String> subscriber) {
                for(File f : directory.listFiles()) {
                    if(f.isDirectory()) {
                        new DirectoryCrawler(f).crawl().subscribe(new Action1<String>() {
                            public void call(String s) {
                                subscriber.onNext(s);
                            }
                        });
                    }
                    if(f.isFile()) {
                        new FileCrawler(f).crawl().subscribe(new Action1<String>() {
                            public void call(String s) {
                                subscriber.onNext(s);
                            }
                        });
                    }
                }
            }
        });
    }
}
