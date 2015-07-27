package br.com.crawler;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by raphael.costa on 19/06/2015.
 */
public class UserCommandParser {
    private final String[] args;

    public UserCommandParser(String[] args) {
        this.args = args;
    }

    public UserCommand parser() {
        if(args.length == 1) {
            File file = new File(args[0]);
            if(!file.isDirectory()) {
                throw new RuntimeException(new IllegalArgumentException("Argumento não é um diretório"));
            }
            return new FileCrawlingCommand(file);
        }

        if(args.length == 2) {
            Pattern pattern = Pattern.compile("http(s)?://(.(/)?)*");
            Matcher matcher = pattern.matcher(args[0]);
            if(matcher.matches()) {
                try {
                    URL url = new URL(args[0]);
                    return new WebCrawlingCommand(url, Integer.parseInt(args[1]));
                } catch (MalformedURLException e) {
                    throw new RuntimeException(new IllegalArgumentException("URL inválida"));
                }
            }
            return new GmailCrawlingCommand(new GmailUser(args[0], args[1]));
        }

        throw new RuntimeException(new IllegalArgumentException("A utilização correta do comando é: \n" +
                "\"java Application [diretorio a ser pesquisado]\" ou \n" +
                "\"java Application [url] [profundidade]\" ou \n" +
                "\"java Application [usuario gmail] [senha gmail]\""));
    }
}
