
package de.his.ws.addressservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für notificationCategoryType.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="notificationCategoryType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="APP"/&gt;
 *     &lt;enumeration value="STU"/&gt;
 *     &lt;enumeration value="EXA"/&gt;
 *     &lt;enumeration value="ALU"/&gt;
 *     &lt;enumeration value="ALUG"/&gt;
 *     &lt;enumeration value="ALUK"/&gt;
 *     &lt;enumeration value="ALUR"/&gt;
 *     &lt;enumeration value="JOB"/&gt;
 *     &lt;enumeration value="HRM"/&gt;
 *     &lt;enumeration value="DOK"/&gt;
 *     &lt;enumeration value="WBT"/&gt;
 *     &lt;enumeration value="STUALL"/&gt;
 *     &lt;enumeration value="WBTALL"/&gt;
 *     &lt;enumeration value="DOKALL"/&gt;
 *     &lt;enumeration value="DOSV"/&gt;
 *     &lt;enumeration value="UNIASSIST"/&gt;
 *     &lt;enumeration value="OFFICE"/&gt;
 *     &lt;enumeration value="AFFILIATION"/&gt;
 *     &lt;enumeration value="PWRESET"/&gt;
 *     &lt;enumeration value="PORTAL"/&gt;
 *     &lt;enumeration value="RZ"/&gt;
 *     &lt;enumeration value="COMA"/&gt;
 *     &lt;enumeration value="COM"/&gt;
 *     &lt;enumeration value="UNIVERSITY_PARTNER"/&gt;
 *     &lt;enumeration value="CAREER_SERVICE"/&gt;
 *     &lt;enumeration value="MISC"/&gt;
 *     &lt;enumeration value="MIGRATION"/&gt;
 *     &lt;enumeration value="TEMP"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "notificationCategoryType")
@XmlEnum
public enum NotificationCategoryType {

    APP,
    STU,
    EXA,
    ALU,
    ALUG,
    ALUK,
    ALUR,
    JOB,
    HRM,
    DOK,
    WBT,
    STUALL,
    WBTALL,
    DOKALL,
    DOSV,
    UNIASSIST,
    OFFICE,
    AFFILIATION,
    PWRESET,
    PORTAL,
    RZ,
    COMA,
    COM,
    UNIVERSITY_PARTNER,
    CAREER_SERVICE,
    MISC,
    MIGRATION,
    TEMP;

    public String value() {
        return name();
    }

    public static NotificationCategoryType fromValue(String v) {
        return valueOf(v);
    }

}
