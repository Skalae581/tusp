
package de.his.ws.applicationservice;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.his.ws.applicationservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.his.ws.applicationservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FindApplication }
     * 
     */
    public FindApplication createFindApplication() {
        return new FindApplication();
    }

    /**
     * Create an instance of {@link FindApplicationResponse }
     * 
     */
    public FindApplicationResponse createFindApplicationResponse() {
        return new FindApplicationResponse();
    }

    /**
     * Create an instance of {@link ApplicationInfoListType }
     * 
     */
    public ApplicationInfoListType createApplicationInfoListType() {
        return new ApplicationInfoListType();
    }

    /**
     * Create an instance of {@link GetOrCreateApplicationForApplicant }
     * 
     */
    public GetOrCreateApplicationForApplicant createGetOrCreateApplicationForApplicant() {
        return new GetOrCreateApplicationForApplicant();
    }

    /**
     * Create an instance of {@link GetOrCreateApplicationForApplicantResponse }
     * 
     */
    public GetOrCreateApplicationForApplicantResponse createGetOrCreateApplicationForApplicantResponse() {
        return new GetOrCreateApplicationForApplicantResponse();
    }

    /**
     * Create an instance of {@link CreateNewRequest }
     * 
     */
    public CreateNewRequest createCreateNewRequest() {
        return new CreateNewRequest();
    }

    /**
     * Create an instance of {@link RequestParam }
     * 
     */
    public RequestParam createRequestParam() {
        return new RequestParam();
    }

    /**
     * Create an instance of {@link CreateNewRequestResponse }
     * 
     */
    public CreateNewRequestResponse createCreateNewRequestResponse() {
        return new CreateNewRequestResponse();
    }

    /**
     * Create an instance of {@link RequestIdResponse }
     * 
     */
    public RequestIdResponse createRequestIdResponse() {
        return new RequestIdResponse();
    }

    /**
     * Create an instance of {@link ServiceFault }
     * 
     */
    public ServiceFault createServiceFault() {
        return new ServiceFault();
    }

    /**
     * Create an instance of {@link ValidationMessage }
     * 
     */
    public ValidationMessage createValidationMessage() {
        return new ValidationMessage();
    }

    /**
     * Create an instance of {@link RequestSubjectParam }
     * 
     */
    public RequestSubjectParam createRequestSubjectParam() {
        return new RequestSubjectParam();
    }

    /**
     * Create an instance of {@link ApplicationInfo }
     * 
     */
    public ApplicationInfo createApplicationInfo() {
        return new ApplicationInfo();
    }

    /**
     * Create an instance of {@link RequestSubjectsType }
     * 
     */
    public RequestSubjectsType createRequestSubjectsType() {
        return new RequestSubjectsType();
    }

    /**
     * Create an instance of {@link RequestSubjectIdSetType }
     * 
     */
    public RequestSubjectIdSetType createRequestSubjectIdSetType() {
        return new RequestSubjectIdSetType();
    }

}
