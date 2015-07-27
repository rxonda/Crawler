package br.com.crawler;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import rx.Observable;
import rx.Subscriber;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by raphael.costa on 19/06/2015.
 */
public class FileDocCrawler implements Crawler {
    private File file;

    public FileDocCrawler(File file) {
        this.file = file;
    }

    public Observable<String> crawl() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            public void call(Subscriber<? super String> subscriber) {
                try {
                    FileInputStream fis = new FileInputStream(file.getAbsolutePath());
                    HWPFDocument document = new HWPFDocument(fis);
                    WordExtractor extractor = new WordExtractor(document);
                    String[] fileData = extractor.getParagraphText();
                    for (int i = 0; i < fileData.length; i++) {
                        if (fileData[i] != null){
                            subscriber.onNext(fileData[i]);
                        }
                    }
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
