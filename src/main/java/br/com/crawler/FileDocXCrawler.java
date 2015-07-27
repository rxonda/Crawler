package br.com.crawler;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import rx.Observable;
import rx.Subscriber;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by raphael.costa on 19/06/2015.
 */
public class FileDocXCrawler implements Crawler {
    private File file;

    public FileDocXCrawler(File file) {
        this.file = file;
    }

    public Observable<String> crawl() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            public void call(Subscriber<? super String> subscriber) {
                try {
                    FileInputStream fis = new FileInputStream(file.getAbsolutePath());
                    XWPFDocument document = new XWPFDocument(fis);
                    XWPFWordExtractor extractor = new XWPFWordExtractor(document);
                    String fileData = extractor.getText();
                    subscriber.onNext(fileData);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
