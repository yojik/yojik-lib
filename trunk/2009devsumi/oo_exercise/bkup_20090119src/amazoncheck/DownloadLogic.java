package amazoncheck;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;


/**
 * ダウンローダの中核
 * @author hanao
 * @since 2008/12/31
 * @version $Id: AWSRestAmazonService.java 59 2009-01-03 07:21:35Z tadayosi $
 */
public class  DownloadLogic {
    private static final String AWS_URL             = "http://ecs.amazonaws.jp/onca/xml";
    private static final String AWS_PARAMS_TEMPLATE = "?Service=AWSECommerceService"
    + "&AWSAccessKeyId=041WR28EQVEF1112J6G2"
    + "&Operation=ItemLookup"
    + "&ItemId=%s"
    + "&Condition=All"
    + "&MerchantId=All"
    + "&ResponseGroup=Offers,Small";

    /**
     * 適用したエクササイズ
     * 1.プリミティブ型のラップ Money ,
     * 2.getter setter Moneyにて実施
     * 3..一行につきドットは一つまでにすること -> 違反していないが、()のネストは.の連続に通じるのかもしれない
     * 
     * ステップ Moneyの導入
     */
    public Book getBookData(String asin) throws Exception {
    	String xml = readWebPage(AWS_URL + String.format(AWS_PARAMS_TEMPLATE, asin));
        String title = readElement(xml,"/ItemLookupResponse/Items/Item/ItemAttributes/Title/text()");
        //Bookの生成ロジックをカプセル化したBook#createの定義
        //名称型/値段型の定義した
        //OfferSummary
        Money price= Money.parseOf(
            readElement(
                xml,"/ItemLookupResponse/Items/Item/OfferSummary/LowestNewPrice/Amount/text()"));
        Money usedPrice= Money.parseOf(
            readElement(
                xml,"/ItemLookupResponse/Items/Item/OfferSummary/LowestUsedPrice/Amount/text()"));
        return  Book.create(title,price,usedPrice);
    }
    
    public String readWebPage(String url) throws Exception {
        HttpMethod method = new GetMethod(url);
        method.setRequestHeader(
            "User-Agent",
            "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
        String page = null;
        try {
            int status = new HttpClient().executeMethod(method);
            if (HttpStatus.SC_OK != status) {
                System.err.printf("ページ読込に失敗 ({$s}) {$s}\n", status, url);
            } else {
                page = method.getResponseBodyAsString();
            }
        } catch (Exception e) {
            System.err.printf("ページ読込に失敗 %s %s \n" + url, e);
        } finally {
            method.releaseConnection();
        }
        if (page==null || page.length()==0) {
            throw new Exception("ページの読込に失敗" + url);
        }
        return page;
    }

    private String readElement(String xml, String expression) throws Exception {
        XPath xpath = XPathFactory.newInstance().newXPath();
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(xml)));
        return xpath.evaluate(expression, document);
    }

}
