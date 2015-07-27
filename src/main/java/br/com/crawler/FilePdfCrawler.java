package br.com.crawler;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import rx.Observable;
import rx.Subscriber;

import java.io.File;
import java.io.IOException;

/**
 * Created by raphael.costa on 19/06/2015.
 */
public class FilePdfCrawler implements Crawler {
    private File file;

    public FilePdfCrawler(File file) {
        this.file = file;
    }

    public Observable<String> crawl() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            public void call(Subscriber<? super String> subscriber) {
                try {
                    PDDocument document = PDDocument.load(file);
                    document.getClass();
                    if (document.isEncrypted()) {
                        throw new RuntimeException("Documento esta encriptado");
                    }
                    PDFTextStripper stripper = new PDFTextStripper();
                    String st = stripper.getText(document);
                    subscriber.onNext(st);

                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}