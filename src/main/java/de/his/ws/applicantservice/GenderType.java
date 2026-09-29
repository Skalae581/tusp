
package de.his.ws.applicantservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für genderType.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="genderType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Maennlich"/&gt;
 *     &lt;enumeration value="Weiblich"/&gt;
 *     &lt;enumeration value="Undefiniert"/&gt;
 *     &lt;enumeration value="Divers"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "genderType")
@XmlEnum
public enum GenderType {

    @XmlEnumValue("Maennlich")
    MAENNLICH("Maennlich"),
    @XmlEnumValue("Weiblich")
    WEIBLICH("Weiblich"),
    @XmlEnumValue("Undefiniert")
    UNDEFINIERT("Undefiniert"),
    @XmlEnumValue("Divers")
    DIVERS("Divers");
    private final String value;

    GenderType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GenderType fromValue(String v) {
        for (GenderType c: GenderType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
