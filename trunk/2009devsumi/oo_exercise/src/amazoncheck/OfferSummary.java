package amazoncheck;

/**
 *   getter , setter 排除する
 *   getterに関しては、無理
 *   -> インスタンス変数を二つに まだ出来てない
 *   → usedを持った本は別にする
 */
public class OfferSummary {
    Money price;
    Money usedPrice;
    public  OfferSummary(Money price , Money usedPrice) {
        this.price = price;
        this.usedPrice = usedPrice;
    }
}
