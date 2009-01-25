package amazoncheck;
import java.io.StringReader;

public class BookCreater {

    /**
     * 適用したエクササイズ
     * 1.プリミティブ型のラップ Money ,
     * 2.getter setter Moneyにて実施
     * 3..一行につきドットは一つまでにすること -> 違反していないが、()のネストは.の連続に通じるのかもしれない
     * 
     * ステップ Moneyの導入
     */
    public Book createFrom(WebResource wr) throws Exception {
        String title = wr.read(
            "/ItemLookupResponse/Items/Item/ItemAttributes/Title/text()");
        //Bookの生成ロジックをカプセル化したBook#createの定義
        //名称型/値段型の定義した
        //OfferSummary
        Money price= Money.parseOf(
            wr.read(
                "/ItemLookupResponse/Items/Item/OfferSummary/LowestNewPrice/Amount/text()"));
        Money usedPrice= Money.parseOf(
            wr.read(
                "/ItemLookupResponse/Items/Item/OfferSummary/LowestUsedPrice/Amount/text()"));
        return  new Book(title,price,usedPrice);
    }
}
    