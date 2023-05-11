package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main{
    //public static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException, InterruptedException {
        String url = "https://finance.naver.com/item/board.naver?code=005930";
        Document doc;
        Elements articles;
        String lastArticleText = "";
        try {
            doc = Jsoup.connect(url).get();
            articles = doc.select("table.type2>tbody>tr>td.title>a");
            lastArticleText = articles.first().attr("title");
            System.out.println("프로그램 시작");
            System.out.println(lastArticleText);
        } catch(Error e){
            System.err.println(e.toString());
        }
        Thread.sleep(1000);

        while(true){
            try {
                doc = Jsoup.connect(url).get();
                articles = doc.select("table.type2>tbody>tr>td.title>a");
                if(!lastArticleText.equals(articles.first().attr("title"))){
                    lastArticleText = articles.first().attr("title");
                    System.out.println("new!: " + lastArticleText);
                    Thread.sleep(10000);
                }
                else{
                    System.out.println("no new");
                    //System.out.println(lastArticleText);
                }

                //sb.append("https://finance.naver.com/");
                //sb.append(lastElement.attr("href"));
                //System.out.println(sb);

                //for(Element element : elements) System.out.println(element.text());
            } catch(Error e) {
                System.err.println(e.toString());
                break;
            }
            Thread.sleep(1000);
        }
    }
}