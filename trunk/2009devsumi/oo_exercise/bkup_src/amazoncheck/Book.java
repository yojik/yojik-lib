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
    public Money getPrice() {
        return offerSummary.price;
    }

    public String getTitle() {
        return title;
    }

    public Money getUsedPrice() {
        return offerSummary.usedPrice;
    }
}
