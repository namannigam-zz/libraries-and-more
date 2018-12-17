package cert;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Main {
    static public void test() throws JAXBException {
        InputStream is = new ByteArrayInputStream(
                "<Classes RUNTIME_INCLUDE_JARS=\"\"><Class></Class></Classes>".getBytes(StandardCharsets.UTF_8));
        JAXBContext jaxbContext = JAXBContext.newInstance(ClassDef.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.unmarshal(is);
    }
}