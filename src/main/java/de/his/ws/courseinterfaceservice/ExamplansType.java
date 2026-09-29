
package de.his.ws.courseinterfaceservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für examplansType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="examplansType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="examplan" type="{http://www.his.de/ws/CourseInterfaceService}ExamrelationDto" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "examplansType", propOrder = {
    "examplan"
})
public class ExamplansType {

    protected List<ExamrelationDto> examplan;

    /**
     * Gets the value of the examplan property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the examplan property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExamplan().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExamrelationDto }
     * 
     * 
     */
    public List<ExamrelationDto> getExamplan() {
        if (examplan == null) {
            examplan = new ArrayList<ExamrelationDto>();
        }
        return this.examplan;
    }

}
