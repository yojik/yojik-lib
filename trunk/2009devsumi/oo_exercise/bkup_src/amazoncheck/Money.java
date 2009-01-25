package amazoncheck;
public class Money {
    int amount;
    public static Money parseOf(String amount) throws NumberFormatException{
        return new Money(Integer.parseInt(amount));
    }
    public Money(int amount) {
        this.amount=amount;
    }
    @Override
    public String toString() {
        return String.valueOf(amount);
    }
}