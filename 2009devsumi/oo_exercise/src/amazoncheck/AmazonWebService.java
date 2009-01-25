package amazoncheck;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;


/**
 * 適切な名前を付ける → DownloadLogic → AmazonWebService
 * ダウンローダの中核
 * @author hanao
 * @since 2008/12/31
 * @version $Id: AWSRestAmazonService.java 59 2009-01-03 07:21:35Z tadayosi $
 */
public class  AmazonWebService {
    
    private static final String AWS_URL             = "http://ecs.amazonaws.jp/onca/xml";
    private static final String AWS_PARAMS_TEMPLATE = "?Service=AWSECommerceService"
    + "&AWSAccessKeyId=041WR28EQVEF1112J6G2"
    + "&Operation=ItemLookup"
    + "&ItemId=%s"
    + "&Condition=All"
    + "&MerchantId=All"
    + "&ResponseGroup=Offers,Small";

    private BookCreater  bookCreater = new BookCreater();
    public Book readBookInfo(String asin) throws Exception {
        //URLとかがカプセル化されてない or webResourceにURLを
        //RESTクライアント系のライブラリとかを参考にする
        //AmazonWebServiceはAmazon固有
        //Book
        WebResource webResource = readWebPage(AWS_URL + String.format(AWS_PARAMS_TEMPLATE, asin));
        return bookCreater.createFrom(webResource);
    }
    public WebResource readWebPage(String url) throws Exception {
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
        return new WebResource(page);
    }
}
