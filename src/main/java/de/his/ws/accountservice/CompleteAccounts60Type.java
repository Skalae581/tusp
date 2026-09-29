
package de.his.ws.accountservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für completeAccounts60Type complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="completeAccounts60Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="completeAccount60" type="{http://www.his.de/ws/AccountService}CompleteAccount60" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "completeAccounts60Type", propOrder = {
    "completeAccount60"
})
public class CompleteAccounts60Type {

    protected List<CompleteAccount60> completeAccount60;

    /**
     * Gets the value of the completeAccount60 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the completeAccount60 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCompleteAccount60().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CompleteAccount60 }
     * 
     * 
     */
    public List<CompleteAccount60> getCompleteAccount60() {
        if (completeAccount60 == null) {
            completeAccount60 = new ArrayList<CompleteAccount60>();
        }
        return this.completeAccount60;
    }

}
