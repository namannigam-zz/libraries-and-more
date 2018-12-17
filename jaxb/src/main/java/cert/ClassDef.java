package cert;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Classes")
public class ClassDef {
    @XmlAttribute(name = "RUNTIME_INCLUDE_JARS")
    public String jars = null;

    @XmlElement(name = "Class")
    public String classes;
}