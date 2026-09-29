
package de.his.ws.portalmessageservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für portalMessagePropertiesType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="portalMessagePropertiesType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="portalMessageProperty" type="{http://www.his.de/ws/PortalMessageService}PortalMessageProperty" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "portalMessagePropertiesType", propOrder = {
    "portalMessageProperty"
})
public class PortalMessagePropertiesType {

    protected List<PortalMessageProperty> portalMessageProperty;

    /**
     * Gets the value of the portalMessageProperty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the portalMessageProperty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPortalMessageProperty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PortalMessageProperty }
     * 
     * 
     */
    public List<PortalMessageProperty> getPortalMessageProperty() {
        if (portalMessageProperty == null) {
            portalMessageProperty = new ArrayList<PortalMessageProperty>();
        }
        return this.portalMessageProperty;
    }

}
