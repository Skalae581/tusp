
package de.his.ws.mimedataservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="personPictures" type="{http://www.his.de/ws/MimedataService}personPicturesType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "personPictures"
})
@XmlRootElement(name = "searchPersonPictureDtoListResponse")
public class SearchPersonPictureDtoListResponse {

    @XmlElement(required = true)
    protected PersonPicturesType personPictures;

    /**
     * Ruft den Wert der personPictures-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PersonPicturesType }
     *     
     */
    public PersonPicturesType getPersonPictures() {
        return personPictures;
    }

    /**
     * Legt den Wert der personPictures-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonPicturesType }
     *     
     */
    public void setPersonPictures(PersonPicturesType value) {
        this.personPictures = value;
    }

}
