package amazoncheck;

/**
 *   getter , setter 排除する
 *   getterに関しては、無理
 *   -> インスタンス変数を二つに まだ出来てない
 *   → usedを持った本は別にする
 */
public class Book {
    String title;
    Money price;
    Money usedPrice;

    public static Book create(String title, Money price , Money usedPrice) {
        return new Book(title,price,usedPrice);
    }
    
    public  Book(String title, Money price , Money usedPrice) {
        this.title = title;
        this.price = price;
        this.usedPrice = usedPrice;
    }
    
    public Money getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public Money getUsedPrice() {
        return usedPrice;
    }

}
