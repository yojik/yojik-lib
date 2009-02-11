package amazoncheck;
/**
 *   getter , setter 排除する
 *   getterに関しては、無理
 *   -> インスタンス変数を二つに まだ出来てない
 *   → usedを持った本は別にする
 */

public class Book {
    //http://www.ajaxtower.jp/ecs/responsegroup/index10.html
    BookName title;
    OfferSummary offerSummary;
    public Book(String title, Money price , Money usedPrice) {
        this.title = new BookName(title);
        this.offerSummary = new OfferSummary(price,usedPrice);
    }
    public void format(Out<String> out) {
        out.put("タイトル",title);
        out.put("価格",     price());
        out.put("中古価格", usedPrice());
    }

    public BookDTO represent() {
        return new BookDTO(title,price(),usedPrice());
    }

    private String title() {
        return title.toString();
    }
    
    private  String price() {
        Money  money = offerSummary.price;
        return money.toString();
    }
    private  String usedPrice() {
        Money  money = offerSummary.usedPrice;
        return money.toString();
    }
    

}