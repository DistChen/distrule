package dist.common.rules.define;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by ChenYanping on 15-2-2.
 */
public class CommonUtil {

    public static String UTF8="UTF-8";

    public static void println(String info){
        System.out.println("使用 import function :"+info);
    }

    public static Document createDocument(){
        try {
            Document document= DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            return document;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String XMLToString(Document document){
        ByteArrayOutputStream outStream=null;
        Source source =null;
        Result result=null;
        Transformer xformer=null;
        try {
            source = new DOMSource(document);
            outStream = new ByteArrayOutputStream();
            OutputStreamWriter write = new OutputStreamWriter(outStream);
            result = new StreamResult(write);

            xformer = TransformerFactory.newInstance().newTransformer();
            xformer.setOutputProperty(OutputKeys.ENCODING, UTF8);
            xformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return outStream.toString();
    }

    public static Document StringToXML(String str){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document doc=null;
        try   {
            builder  =  factory.newDocumentBuilder();
            doc  =  builder.parse( new ByteArrayInputStream(str.getBytes()));
        }   catch  (Exception e)  {
            e.printStackTrace();
        }
        return doc;
    }
}
