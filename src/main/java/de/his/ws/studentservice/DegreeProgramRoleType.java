
package de.his.ws.studentservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für degreeProgramRoleType.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="degreeProgramRoleType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="STUDENT"/&gt;
 *     &lt;enumeration value="DOCCAND"/&gt;
 *     &lt;enumeration value="ADVANCED_STUDIES"/&gt;
 *     &lt;enumeration value="APPLICANT"/&gt;
 *     &lt;enumeration value="PROVISIONAL"/&gt;
 *     &lt;enumeration value="GUEST"/&gt;
 *     &lt;enumeration value="UNKNOWN"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "degreeProgramRoleType")
@XmlEnum
public enum DegreeProgramRoleType {

    STUDENT,
    DOCCAND,
    ADVANCED_STUDIES,
    APPLICANT,
    PROVISIONAL,
    GUEST,
    UNKNOWN;

    public String value() {
        return name();
    }

    public static DegreeProgramRoleType fromValue(String v) {
        return valueOf(v);
    }

}
