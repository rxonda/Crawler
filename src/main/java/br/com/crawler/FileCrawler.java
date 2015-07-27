package br.com.crawler;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

import java.io.File;

/**
 * Created by raphael.costa on 19/06/2015.
 */
public class FileCrawler implements Crawler {
    private File file;

    public FileCrawler(File file) {
        this.file = file;
    }

    public Observable<String> crawl() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            public void call(final Subscriber<? super String> subscriber) {
                newInstance().crawl().subscribe(new Action1<String>() {
                    public void call(String s) {
                        subscriber.onNext(s);
                    }
                });
            }
        });
    }

    private Crawler newInstance() {
        if(file.getName().toLowerCase().endsWith(".txt")) {
            return new FileTxtCrawler(file);
        }
        if(file.getName().toLowerCase().endsWith(".pdf")) {
            return new FilePdfCrawler(file);
        }
        if(file.getName().toLowerCase().endsWith(".doc")) {
            return new FileDocCrawler(file);
        }
        if(file.getName().toLowerCase().endsWith(".docx")) {
            return new FileDocXCrawler(file);
        }
        if(file.getName().toLowerCase().endsWith(".ppt")) {
            return new FilePptCrawler(file);
        }
        if(file.getName().toLowerCase().endsWith(".pptx")) {
            return new FilePptXCrawler(file);
        }
        return new Crawler() {
            public Observable<String> crawl() {
                return Observable.create(new Observable.OnSubscribe<String>() {
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext(file.getName());
                        subscriber.onCompleted();
                    }
                });
            }
        };
    }
}
