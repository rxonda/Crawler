package br.com.crawler;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by raphael.costa on 19/06/2015.
 */
public class UserCommandParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void deveRetornarErroPassandoMaisDe2Parametros() {
        try {
            new UserCommandParser(new String[] {"1", "2", "3"}).parser();
        } catch (RuntimeException e) {
            Assert.assertEquals("A mensagem deve ser ", "A utilização correta do comando é: \n" +
                    "\"java Application [diretorio a ser pesquisado]\" ou \n" +
                    "\"java Application [url] [profundidade]\" ou \n" +
                    "\"java Application [usuario gmail] [senha gmail]\"", e.getCause().getMessage());
            throw (IllegalArgumentException)e.getCause();
        }
    }

    @Test
    public void deveRetornarFileCrawlingCommand() {
        UserCommand command = new UserCommandParser(new String[]{"C:\\temp"}).parser();
        Assert.assertEquals(FileCrawlingCommand.class, command.getClass());
    }

    @Test
    public void deveRetornarWebCrawlingCommand() {
        UserCommand command = new UserCommandParser(new String[]{"http://www.globosat.com/noticias?dia=10/10/2011", "2"}).parser();
        Assert.assertEquals(WebCrawlingCommand.class, command.getClass());
    }

    @Test
    public void deveRetornarWebCrawlingCommandParaHttps() {
        UserCommand command = new UserCommandParser(new String[]{"https://www.globosat.com/noticias?dia=10/10/2011", "2"}).parser();
        Assert.assertEquals(WebCrawlingCommand.class, command.getClass());
    }

    @Test
    public void deveRetornarGmailCrawlingCommand() {
        UserCommand command = new UserCommandParser(new String[]{"usuario.blas", "senha123"}).parser();
        Assert.assertEquals(GmailCrawlingCommand.class, command.getClass());
    }
}