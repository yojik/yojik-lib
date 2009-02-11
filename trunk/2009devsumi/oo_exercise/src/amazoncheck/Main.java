package amazoncheck;

/**
 *  元ネタをコレクションを扱うように変更した方がよいかも
 *  いきなりtry catch
 */
public class Main {
    public static void main(String[] args) {
        try {
            if(args.length < 1) return ;
            AmazonWebService aws = new AmazonWebService();
            Book book = aws.readBookInfo(args[0]);
            BookDTO dto = book.represent();
            //System.out.println(dto);
            book.format(new ConsoleOut());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}