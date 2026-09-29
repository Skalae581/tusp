
package de.his.ws.periodservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für periodInfoWithConditionInfoListType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="periodInfoWithConditionInfoListType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="periodInfoWithCondition" type="{http://www.his.de/ws/PeriodService}PeriodInfoWithCondition" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "periodInfoWithConditionInfoListType", propOrder = {
    "periodInfoWithCondition"
})
public class PeriodInfoWithConditionInfoListType {

    protected List<PeriodInfoWithCondition> periodInfoWithCondition;

    /**
     * Gets the value of the periodInfoWithCondition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the periodInfoWithCondition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPeriodInfoWithCondition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PeriodInfoWithCondition }
     * 
     * 
     */
    public List<PeriodInfoWithCondition> getPeriodInfoWithCondition() {
        if (periodInfoWithCondition == null) {
            periodInfoWithCondition = new ArrayList<PeriodInfoWithCondition>();
        }
        return this.periodInfoWithCondition;
    }

}
