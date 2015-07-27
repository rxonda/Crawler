package br.com.crawler;

import rx.Observable;
import rx.Subscriber;

import java.io.*;

/**
 * Created by raphael.costa on 19/06/2015.
 */
public class FileTxtCrawler implements Crawler {
    private File file;

    public FileTxtCrawler(File file) {
        this.file = file;
    }

    public Observable<String> crawl() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            public void call(Subscriber<? super String> subscriber) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String linha;
                    while ((linha = reader.readLine()) != null) {
                        subscriber.onNext(linha);
                    }
                    subscriber.onCompleted();
                } catch (FileNotFoundException e) {
                    subscriber.onError(e);
                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
