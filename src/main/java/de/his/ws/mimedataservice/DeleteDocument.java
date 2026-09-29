
package de.his.ws.mimedataservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="documentMetadataId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
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
    "documentMetadataId"
})
@XmlRootElement(name = "deleteDocument")
public class DeleteDocument {

    protected long documentMetadataId;

    /**
     * Ruft den Wert der documentMetadataId-Eigenschaft ab.
     * 
     */
    public long getDocumentMetadataId() {
        return documentMetadataId;
    }

    /**
     * Legt den Wert der documentMetadataId-Eigenschaft fest.
     * 
     */
    public void setDocumentMetadataId(long value) {
        this.documentMetadataId = value;
    }

}
