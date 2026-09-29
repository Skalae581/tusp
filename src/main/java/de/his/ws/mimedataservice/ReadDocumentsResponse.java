
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
 *         &lt;element name="documentMetadatas" type="{http://www.his.de/ws/MimedataService}documentMetadatasType"/&gt;
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
    "documentMetadatas"
})
@XmlRootElement(name = "readDocumentsResponse")
public class ReadDocumentsResponse {

    @XmlElement(required = true)
    protected DocumentMetadatasType documentMetadatas;

    /**
     * Ruft den Wert der documentMetadatas-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DocumentMetadatasType }
     *     
     */
    public DocumentMetadatasType getDocumentMetadatas() {
        return documentMetadatas;
    }

    /**
     * Legt den Wert der documentMetadatas-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentMetadatasType }
     *     
     */
    public void setDocumentMetadatas(DocumentMetadatasType value) {
        this.documentMetadatas = value;
    }

}
