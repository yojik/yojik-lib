package amazoncheck;
class ConsoleFormatter implements AttributeListFormatter<String> {
    public void format(String attr, String value) {
        System.out.printf("%s %s \n" , attr, value);
    }
}