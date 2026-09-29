
package de.his.ws.mimedataservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für targetTypeType.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="targetTypeType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="PERSON"/&gt;
 *     &lt;enumeration value="CAMPUS"/&gt;
 *     &lt;enumeration value="BUILDING"/&gt;
 *     &lt;enumeration value="FLOOR"/&gt;
 *     &lt;enumeration value="ROOM"/&gt;
 *     &lt;enumeration value="UNIT"/&gt;
 *     &lt;enumeration value="COURSE_OF_STUDY"/&gt;
 *     &lt;enumeration value="PORTLET_DEFINITION"/&gt;
 *     &lt;enumeration value="JOB_REPORT_JOB"/&gt;
 *     &lt;enumeration value="KETTLE_JOB"/&gt;
 *     &lt;enumeration value="JOB_EXECUTION"/&gt;
 *     &lt;enumeration value="STATUS_NOTIFICATION"/&gt;
 *     &lt;enumeration value="PROJECT"/&gt;
 *     &lt;enumeration value="PROJECTPROPOSAL"/&gt;
 *     &lt;enumeration value="PROJECTFINAL"/&gt;
 *     &lt;enumeration value="STUDENT"/&gt;
 *     &lt;enumeration value="APPLICANT"/&gt;
 *     &lt;enumeration value="OUTPUT_REQUEST"/&gt;
 *     &lt;enumeration value="SERIAL_EMAIL_JOB"/&gt;
 *     &lt;enumeration value="CAREER_SERVICE_JOB_OFFER"/&gt;
 *     &lt;enumeration value="INTERNSHIP_JOB_OFFER"/&gt;
 *     &lt;enumeration value="DOCUMENT_FIELD_INPUT"/&gt;
 *     &lt;enumeration value="CAREER_SERVICE_EMPLOYER_APPLICATION"/&gt;
 *     &lt;enumeration value="CAREER_SERVICE_EMPLOYER"/&gt;
 *     &lt;enumeration value="REQUEST_FOR_DIRECT_DEBIT_MANDATE"/&gt;
 *     &lt;enumeration value="REQUEST_FOR_DISENROLLMENT"/&gt;
 *     &lt;enumeration value="REQUEST_FOR_LEAVE_OF_ABSENCE"/&gt;
 *     &lt;enumeration value="REQUEST_FOR_LOAN"/&gt;
 *     &lt;enumeration value="REQUEST_FOR_LOAN_ELIGIBILITY"/&gt;
 *     &lt;enumeration value="REQUEST_TO_CHANGE_CONTRACT_PERIOD"/&gt;
 *     &lt;enumeration value="REQUEST_TO_CHANGE_FEE"/&gt;
 *     &lt;enumeration value="REQUEST_TO_CHANGE_STUDY_ACCOUNT"/&gt;
 *     &lt;enumeration value="REQUEST_FOR_EXTENSION_TIME_LIMIT"/&gt;
 *     &lt;enumeration value="REQUEST_FOR_ACCEPTANCE_OF_DOCCAND"/&gt;
 *     &lt;enumeration value="REQUEST_FOR_INITIATION_OF_DOCTORAL_PROCEDURE"/&gt;
 *     &lt;enumeration value="REQUEST_FOR_INTERRUPTION_OF_DOCTORAL_PROGRAM"/&gt;
 *     &lt;enumeration value="REQUEST_FOR_TERMINATION_OF_DOCTORAL_PROGRAM"/&gt;
 *     &lt;enumeration value="REQUEST_FOR_DOCTORATE_ANNOUNCEMENT"/&gt;
 *     &lt;enumeration value="REQUEST_FOR_REGULAR_DURATION_OF_STUDY_INCREASE"/&gt;
 *     &lt;enumeration value="FUNDING_NOTIFICATION"/&gt;
 *     &lt;enumeration value="PERSON_GROUP"/&gt;
 *     &lt;enumeration value="SUPERVISION_AGREEMENT"/&gt;
 *     &lt;enumeration value="REQUEST_FOR_PERSON_DATA_CHANGE"/&gt;
 *     &lt;enumeration value="REQUEST_DOCUMENT_VERIFICATION"/&gt;
 *     &lt;enumeration value="REQUEST_FOR_REFUND"/&gt;
 *     &lt;enumeration value="SMV_FILE"/&gt;
 *     &lt;enumeration value="OAUTH2_CLIENT"/&gt;
 *     &lt;enumeration value="EMREX_TRANSFER"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "targetTypeType")
@XmlEnum
public enum TargetTypeType {

    PERSON("PERSON"),
    CAMPUS("CAMPUS"),
    BUILDING("BUILDING"),
    FLOOR("FLOOR"),
    ROOM("ROOM"),
    UNIT("UNIT"),
    COURSE_OF_STUDY("COURSE_OF_STUDY"),
    PORTLET_DEFINITION("PORTLET_DEFINITION"),
    JOB_REPORT_JOB("JOB_REPORT_JOB"),
    KETTLE_JOB("KETTLE_JOB"),
    JOB_EXECUTION("JOB_EXECUTION"),
    STATUS_NOTIFICATION("STATUS_NOTIFICATION"),
    PROJECT("PROJECT"),
    PROJECTPROPOSAL("PROJECTPROPOSAL"),
    PROJECTFINAL("PROJECTFINAL"),
    STUDENT("STUDENT"),
    APPLICANT("APPLICANT"),
    OUTPUT_REQUEST("OUTPUT_REQUEST"),
    SERIAL_EMAIL_JOB("SERIAL_EMAIL_JOB"),
    CAREER_SERVICE_JOB_OFFER("CAREER_SERVICE_JOB_OFFER"),
    INTERNSHIP_JOB_OFFER("INTERNSHIP_JOB_OFFER"),
    DOCUMENT_FIELD_INPUT("DOCUMENT_FIELD_INPUT"),
    CAREER_SERVICE_EMPLOYER_APPLICATION("CAREER_SERVICE_EMPLOYER_APPLICATION"),
    CAREER_SERVICE_EMPLOYER("CAREER_SERVICE_EMPLOYER"),
    REQUEST_FOR_DIRECT_DEBIT_MANDATE("REQUEST_FOR_DIRECT_DEBIT_MANDATE"),
    REQUEST_FOR_DISENROLLMENT("REQUEST_FOR_DISENROLLMENT"),
    REQUEST_FOR_LEAVE_OF_ABSENCE("REQUEST_FOR_LEAVE_OF_ABSENCE"),
    REQUEST_FOR_LOAN("REQUEST_FOR_LOAN"),
    REQUEST_FOR_LOAN_ELIGIBILITY("REQUEST_FOR_LOAN_ELIGIBILITY"),
    REQUEST_TO_CHANGE_CONTRACT_PERIOD("REQUEST_TO_CHANGE_CONTRACT_PERIOD"),
    REQUEST_TO_CHANGE_FEE("REQUEST_TO_CHANGE_FEE"),
    REQUEST_TO_CHANGE_STUDY_ACCOUNT("REQUEST_TO_CHANGE_STUDY_ACCOUNT"),
    REQUEST_FOR_EXTENSION_TIME_LIMIT("REQUEST_FOR_EXTENSION_TIME_LIMIT"),
    REQUEST_FOR_ACCEPTANCE_OF_DOCCAND("REQUEST_FOR_ACCEPTANCE_OF_DOCCAND"),
    REQUEST_FOR_INITIATION_OF_DOCTORAL_PROCEDURE("REQUEST_FOR_INITIATION_OF_DOCTORAL_PROCEDURE"),
    REQUEST_FOR_INTERRUPTION_OF_DOCTORAL_PROGRAM("REQUEST_FOR_INTERRUPTION_OF_DOCTORAL_PROGRAM"),
    REQUEST_FOR_TERMINATION_OF_DOCTORAL_PROGRAM("REQUEST_FOR_TERMINATION_OF_DOCTORAL_PROGRAM"),
    REQUEST_FOR_DOCTORATE_ANNOUNCEMENT("REQUEST_FOR_DOCTORATE_ANNOUNCEMENT"),
    REQUEST_FOR_REGULAR_DURATION_OF_STUDY_INCREASE("REQUEST_FOR_REGULAR_DURATION_OF_STUDY_INCREASE"),
    FUNDING_NOTIFICATION("FUNDING_NOTIFICATION"),
    PERSON_GROUP("PERSON_GROUP"),
    SUPERVISION_AGREEMENT("SUPERVISION_AGREEMENT"),
    REQUEST_FOR_PERSON_DATA_CHANGE("REQUEST_FOR_PERSON_DATA_CHANGE"),
    REQUEST_DOCUMENT_VERIFICATION("REQUEST_DOCUMENT_VERIFICATION"),
    REQUEST_FOR_REFUND("REQUEST_FOR_REFUND"),
    SMV_FILE("SMV_FILE"),
    @XmlEnumValue("OAUTH2_CLIENT")
    OAUTH_2_CLIENT("OAUTH2_CLIENT"),
    EMREX_TRANSFER("EMREX_TRANSFER");
    private final String value;

    TargetTypeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TargetTypeType fromValue(String v) {
        for (TargetTypeType c: TargetTypeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
