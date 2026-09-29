
package de.his.ws.personaddressservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für addressTypeType.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="addressTypeType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="HYPERLINK"/&gt;
 *     &lt;enumeration value="MESSENGER"/&gt;
 *     &lt;enumeration value="PHONE"/&gt;
 *     &lt;enumeration value="EMAIL"/&gt;
 *     &lt;enumeration value="POSTADDRESS"/&gt;
 *     &lt;enumeration value="ROOMADDRESS"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "addressTypeType")
@XmlEnum
public enum AddressTypeType {

    HYPERLINK,
    MESSENGER,
    PHONE,
    EMAIL,
    POSTADDRESS,
    ROOMADDRESS;

    public String value() {
        return name();
    }

    public static AddressTypeType fromValue(String v) {
        return valueOf(v);
    }

}
