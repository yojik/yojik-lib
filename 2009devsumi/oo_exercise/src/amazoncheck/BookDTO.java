package amazoncheck;
class BookDTO {
    String title;
    String price;
    String usedPrice;
    BookDTO(String title, String price , String usedPrice) {
        this.title = title;
        this.price = price;
        this.usedPrice = usedPrice;
    }
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("タイトル:" + title +"\n");
        buf.append("価格:"     + price + "\n");
        buf.append("中古価格:" + usedPrice + "\n");
        return buf.toString();
    }
}