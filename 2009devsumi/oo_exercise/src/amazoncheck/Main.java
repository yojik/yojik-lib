package amazoncheck;

/**
 *  元ネタをコレクションを扱うように変更した方がよいかも
 *  いきなりtry catch
 */
public class Main {
    public static void main(String[] args) {
        try {
            if(args.length!=0) {
                AmazonWebService aws = new AmazonWebService();
                Book ret = aws.readBookInfo(args[0]);
                //getter の 排除が必要
                //あとでパラメタライズドにする必要あり
                ret.accept(new AttributeListFormatter<String>() {
                    public void format(String attr, String value) {
                        System.out.printf("%s %s \n" , attr, value);
                    }
                });                 
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}