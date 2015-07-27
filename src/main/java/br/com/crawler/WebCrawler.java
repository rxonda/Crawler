package br.com.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

import java.io.IOException;
import java.net.URL;

/**
 * Created by raphael.costa on 19/06/2015.
 */
public class WebCrawler implements Crawler {
    private final URL url;
    private final int deep;

    public WebCrawler(URL url, int deep) {
        this.url = url;
        this.deep = deep;
    }

    public Observable<String> crawl() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            public void call(final Subscriber<? super String> subscriber) {
                try {
                    Document doc = Jsoup.parse(url, 10000);
                    Element body = doc.body();
                    subscriber.onNext(body.text());
                    if (deep == 0) {
                        subscriber.onCompleted();
                        return;
                    }
                    Elements links = body.select("a");
                    for (Element link : links) {
                        URL linkUrl = new URL(link.attr("abs:href"));
                        new WebCrawler(linkUrl, deep - 1)
                                .crawl()
                                .subscribe(new Action1<String>() {
                                    public void call(String s) {
                                        subscriber.onNext(s);
                                    }
                                }, new Action1<Throwable>() {
                                    public void call(Throwable throwable) {
                                        subscriber.onError(throwable);
                                    }
                                });
                    }
                    subscriber.onCompleted();
                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
