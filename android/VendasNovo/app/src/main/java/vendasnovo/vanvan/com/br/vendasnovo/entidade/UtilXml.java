package vendasnovo.vanvan.com.br.vendasnovo.entidade;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by evandro on 15/10/17.
 */

public class UtilXml {

    public String getObjectToXmlString(Object o) {
        String request = null;
        try {
            Serializer serializer = new Persister();
            Writer xmlW = new StringWriter();
            serializer.write(o, xmlW);
            request = xmlW.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return request;
    }

    public Object getXmlStringToObject(Object o, String xml) {
        Object ret = null;
        try {
            Serializer serializer = new Persister();
            Writer xmlW = new StringWriter();
            ret = serializer.read(o.getClass(), xmlW.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
}
