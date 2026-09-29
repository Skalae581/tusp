
package de.his.ws.studentservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für Person70 complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Person70"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/StudentService}Person"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="stayAbroads" type="{http://www.his.de/ws/StudentService}stayAbroadsType"/&gt;
 *         &lt;element name="personinfo" type="{http://www.his.de/ws/StudentService}PersoninfoDto" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Person70", propOrder = {
    "stayAbroads",
    "personinfo"
})
public class Person70
    extends Person
{

    @XmlElement(required = true)
    protected StayAbroadsType stayAbroads;
    @XmlElement(nillable = true)
    protected PersoninfoDto personinfo;

    /**
     * Ruft den Wert der stayAbroads-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link StayAbroadsType }
     *     
     */
    public StayAbroadsType getStayAbroads() {
        return stayAbroads;
    }

    /**
     * Legt den Wert der stayAbroads-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link StayAbroadsType }
     *     
     */
    public void setStayAbroads(StayAbroadsType value) {
        this.stayAbroads = value;
    }

    /**
     * Ruft den Wert der personinfo-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PersoninfoDto }
     *     
     */
    public PersoninfoDto getPersoninfo() {
        return personinfo;
    }

    /**
     * Legt den Wert der personinfo-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PersoninfoDto }
     *     
     */
    public void setPersoninfo(PersoninfoDto value) {
        this.personinfo = value;
    }

}
