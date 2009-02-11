package amazoncheck;
class ConsoleOut implements Out<String> {
    public void put(String attr, String value) {
        System.out.printf("%s %s \n" , attr, value); 
    }
}