package amazoncheck;

/**
 *  元ネタをコレクションを扱うように変更した方がよいかも
 *  いきなりtry catch
 */
public class Main {
    public static void main(String[] args) {
        try {
            if(args.length!=0) {
                DownloadLogic dl = new DownloadLogic();
                Book ret = dl.getBookData(args[0]);
                //getter の 排除が必要
                System.out.printf("title      : %s \n" , ret.getTitle());
                System.out.printf("price      : %s \n" , ret.getPrice());
                System.out.printf("used price : %s \n" , ret.getUsedPrice());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}