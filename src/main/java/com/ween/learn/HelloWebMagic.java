package com.ween.learn;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by wen on 2017/5/16.
 * WebMagic spider demo
 */
public class HelloWebMagic implements PageProcessor{

    //抓取网站配置，编码，抓取间隔，重试次数
    private Site site=Site.me().setRetryTimes(3).setSleepTime(1000);

    //爬虫逻辑核心接口，在该方法编写抽取逻辑
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
        if (page.getResultItems().get("name")==null){
            //skip this page
            page.setSkip(true);
        }
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-]+/[\\w\\-]+)").all());
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new HelloWebMagic()).addUrl("https://github.com/code4craft").thread(5).run();
    }
}
