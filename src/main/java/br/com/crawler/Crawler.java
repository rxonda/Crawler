package br.com.crawler;

import rx.Observable;

/**
 * Created by raphael.costa on 19/06/2015.
 */
public interface Crawler {
    Observable<String> crawl();
}
