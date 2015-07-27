package br.com.crawler;

import rx.Observable;

/**
 * Created by raphael.costa on 19/06/2015.
 */
public class GmailCrawlingCommand implements UserCommand {
    public GmailCrawlingCommand(GmailUser user) {
    }

    public Observable<Crawler> invoke() {
        return null;
    }
}
