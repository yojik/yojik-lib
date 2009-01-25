package amazoncheck;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import java.io.StringReader;


public class WebResource {
    private String xml; 
    
    public WebResource(String xml) {
       this.xml = xml;
    }
    String read(String expression) throws Exception {
        XPath xpath = XPathFactory.newInstance().newXPath();
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(xml)));
        return xpath.evaluate(expression, document);
    }
}