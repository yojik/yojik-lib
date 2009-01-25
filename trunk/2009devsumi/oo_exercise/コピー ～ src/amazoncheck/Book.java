package amazoncheck;
/**
 *   getter , setter 排除する
 *   getterに関しては、無理
 *   -> インスタンス変数を二つに まだ出来てない
 *   → usedを持った本は別にする
 */

public class Book {
    //http://www.ajaxtower.jp/ecs/responsegroup/index10.html

    String title;
    OfferSummary offerSummary;
    public Book(String title, Money price , Money usedPrice) {
        this.title = title;
        this.offerSummary = new OfferSummary(price,usedPrice);
    }

    public void accept(AttributeListFormatter<String> f) {
        f.format("タイトル",title);
        f.format("価格",  offerSummary.price.toString());
        f.format("中古価格", offerSummary.usedPrice.toString());
    }


    
}