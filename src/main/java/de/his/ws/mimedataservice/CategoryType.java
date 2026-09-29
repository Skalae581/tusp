
package de.his.ws.mimedataservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für categoryType.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="categoryType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="CHIPCARD"/&gt;
 *     &lt;enumeration value="ALL"/&gt;
 *     &lt;enumeration value="LIBRARY_IDENTITY_CARD"/&gt;
 *     &lt;enumeration value="FOTO"/&gt;
 *     &lt;enumeration value="SOCIAL_NETWORK"/&gt;
 *     &lt;enumeration value="DOCUMENT_FOR_BUILDING"/&gt;
 *     &lt;enumeration value="FLOOR_PLAN"/&gt;
 *     &lt;enumeration value="DOCUMENT"/&gt;
 *     &lt;enumeration value="SCREENSHOT"/&gt;
 *     &lt;enumeration value="TEMPLATE"/&gt;
 *     &lt;enumeration value="REPORT"/&gt;
 *     &lt;enumeration value="ITAN_LIST"/&gt;
 *     &lt;enumeration value="AGREEMENT_DOCUMENT"/&gt;
 *     &lt;enumeration value="FINAL_REPORT"/&gt;
 *     &lt;enumeration value="PROPOSAL_DOCUMENT"/&gt;
 *     &lt;enumeration value="PROTOCOL"/&gt;
 *     &lt;enumeration value="LOGO"/&gt;
 *     &lt;enumeration value="DIRECT_DEBIT_MANDATE"/&gt;
 *     &lt;enumeration value="CONFIGURATION_EXPORT"/&gt;
 *     &lt;enumeration value="EXAMINATION_DOCUMENT"/&gt;
 *     &lt;enumeration value="FINAL_EXAM_DOCUMENT"/&gt;
 *     &lt;enumeration value="CERTIFICATE_DOCUMENT"/&gt;
 *     &lt;enumeration value="APPLICATION_DOCUMENTS"/&gt;
 *     &lt;enumeration value="STUDENT_DOCUMENTS"/&gt;
 *     &lt;enumeration value="EXAM_DOCUMENTS"/&gt;
 *     &lt;enumeration value="SUPERVISION_AGREEMENTS"/&gt;
 *     &lt;enumeration value="APPLICATION"/&gt;
 *     &lt;enumeration value="APPROVAL"/&gt;
 *     &lt;enumeration value="CONTRACT"/&gt;
 *     &lt;enumeration value="FINANCES"/&gt;
 *     &lt;enumeration value="PROJECT_REPORT"/&gt;
 *     &lt;enumeration value="THIRD_PARTY_FUNDING_NOTIFICATION"/&gt;
 *     &lt;enumeration value="SMV_FILE"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "categoryType")
@XmlEnum
public enum CategoryType {

    CHIPCARD,
    ALL,
    LIBRARY_IDENTITY_CARD,
    FOTO,
    SOCIAL_NETWORK,
    DOCUMENT_FOR_BUILDING,
    FLOOR_PLAN,
    DOCUMENT,
    SCREENSHOT,
    TEMPLATE,
    REPORT,
    ITAN_LIST,
    AGREEMENT_DOCUMENT,
    FINAL_REPORT,
    PROPOSAL_DOCUMENT,
    PROTOCOL,
    LOGO,
    DIRECT_DEBIT_MANDATE,
    CONFIGURATION_EXPORT,
    EXAMINATION_DOCUMENT,
    FINAL_EXAM_DOCUMENT,
    CERTIFICATE_DOCUMENT,
    APPLICATION_DOCUMENTS,
    STUDENT_DOCUMENTS,
    EXAM_DOCUMENTS,
    SUPERVISION_AGREEMENTS,
    APPLICATION,
    APPROVAL,
    CONTRACT,
    FINANCES,
    PROJECT_REPORT,
    THIRD_PARTY_FUNDING_NOTIFICATION,
    SMV_FILE;

    public String value() {
        return name();
    }

    public static CategoryType fromValue(String v) {
        return valueOf(v);
    }

}
