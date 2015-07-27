package br.com.crawler;

import rx.functions.Action1;

/**
 * Created by raphael.costa on 19/06/2015.
 */
public class Application {
    public static void main(String[] args) {
        new UserCommandParser(args)
                .parser()
                .invoke()
                .subscribe(new Action1<Crawler>() {
                    public void call(Crawler crawler) {
                        crawler.crawl()
                                .subscribe(new Action1<String>() {
                                    public void call(String s) {
                                        System.out.println(s);
                                    }
                                }, new Action1<Throwable>() {
                                    public void call(Throwable throwable) {
                                        System.err.println(throwable.getMessage());
                                    }
                                });
                    }
                });
    }
}
