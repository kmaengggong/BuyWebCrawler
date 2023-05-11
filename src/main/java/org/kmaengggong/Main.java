package org.kmaengggong;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.IOException;

public class Main{
    //public static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException, InterruptedException {
        //String url = "https://www.fmkorea.com/search.php?mid=hotdeal&category=&listStyle=webzine&search_keyword=%EB%A7%A5%EB%B6%81&search_target=title";
        String url = "https://www.fmkorea.com/hotdeal";
        int timedelay = 1000 * 60 * 10;
        Document doc;
        Elements articles;
        String firstArticleHref = "";
        try {
            doc = Jsoup.connect(url).get();
            articles = doc.select("div.li>h3.title>a.hotdeal_var8Y");
            firstArticleHref = articles.first().attr("href");
            System.out.println("프로그램 시작");
            System.out.println(articles.first().text());
        } catch(Error e){
            System.err.println(e.toString());
        }
        Thread.sleep(timedelay);

        while(true){
            try {
                doc = Jsoup.connect(url).get();
                articles = doc.select("div.li>h3.title>a.hotdeal_var8Y");
                if(!firstArticleHref.equals(articles.first().attr("href"))){
                    firstArticleHref = articles.first().attr("href");
                    System.out.println("new!: " + articles.first().text());
                    JOptionPane.showMessageDialog(null, articles.first().text());
                }
                else{
                    System.out.println("old¡: " + articles.first().text());
                }
            } catch(Error e) {
                System.err.println(e.toString());
                break;
            }
            Thread.sleep(timedelay);
        }
    }
}