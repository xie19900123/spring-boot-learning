
package cn.lqdev.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cn.lqdev.webservice package. 
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

    private final static QName _GetAuthorByName_QNAME = new QName("http://www.lqdev.cn/webservice", "getAuthorByName");
    private final static QName _GetAuthorByNameResponse_QNAME = new QName("http://www.lqdev.cn/webservice", "getAuthorByNameResponse");
    private final static QName _GetAuthorList_QNAME = new QName("http://www.lqdev.cn/webservice", "getAuthorList");
    private final static QName _GetAuthorListResponse_QNAME = new QName("http://www.lqdev.cn/webservice", "getAuthorListResponse");
    private final static QName _GetAuthorString_QNAME = new QName("http://www.lqdev.cn/webservice", "getAuthorString");
    private final static QName _GetAuthorStringResponse_QNAME = new QName("http://www.lqdev.cn/webservice", "getAuthorStringResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cn.lqdev.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAuthorByName }
     * 
     */
    public GetAuthorByName createGetAuthorByName() {
        return new GetAuthorByName();
    }

    /**
     * Create an instance of {@link GetAuthorByNameResponse }
     * 
     */
    public GetAuthorByNameResponse createGetAuthorByNameResponse() {
        return new GetAuthorByNameResponse();
    }

    /**
     * Create an instance of {@link GetAuthorList }
     * 
     */
    public GetAuthorList createGetAuthorList() {
        return new GetAuthorList();
    }

    /**
     * Create an instance of {@link GetAuthorListResponse }
     * 
     */
    public GetAuthorListResponse createGetAuthorListResponse() {
        return new GetAuthorListResponse();
    }

    /**
     * Create an instance of {@link GetAuthorString }
     * 
     */
    public GetAuthorString createGetAuthorString() {
        return new GetAuthorString();
    }

    /**
     * Create an instance of {@link GetAuthorStringResponse }
     * 
     */
    public GetAuthorStringResponse createGetAuthorStringResponse() {
        return new GetAuthorStringResponse();
    }

    /**
     * Create an instance of {@link AuthorDto }
     * 
     */
    public AuthorDto createAuthorDto() {
        return new AuthorDto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAuthorByName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.lqdev.cn/webservice", name = "getAuthorByName")
    public JAXBElement<GetAuthorByName> createGetAuthorByName(GetAuthorByName value) {
        return new JAXBElement<GetAuthorByName>(_GetAuthorByName_QNAME, GetAuthorByName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAuthorByNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.lqdev.cn/webservice", name = "getAuthorByNameResponse")
    public JAXBElement<GetAuthorByNameResponse> createGetAuthorByNameResponse(GetAuthorByNameResponse value) {
        return new JAXBElement<GetAuthorByNameResponse>(_GetAuthorByNameResponse_QNAME, GetAuthorByNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAuthorList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.lqdev.cn/webservice", name = "getAuthorList")
    public JAXBElement<GetAuthorList> createGetAuthorList(GetAuthorList value) {
        return new JAXBElement<GetAuthorList>(_GetAuthorList_QNAME, GetAuthorList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAuthorListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.lqdev.cn/webservice", name = "getAuthorListResponse")
    public JAXBElement<GetAuthorListResponse> createGetAuthorListResponse(GetAuthorListResponse value) {
        return new JAXBElement<GetAuthorListResponse>(_GetAuthorListResponse_QNAME, GetAuthorListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAuthorString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.lqdev.cn/webservice", name = "getAuthorString")
    public JAXBElement<GetAuthorString> createGetAuthorString(GetAuthorString value) {
        return new JAXBElement<GetAuthorString>(_GetAuthorString_QNAME, GetAuthorString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAuthorStringResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.lqdev.cn/webservice", name = "getAuthorStringResponse")
    public JAXBElement<GetAuthorStringResponse> createGetAuthorStringResponse(GetAuthorStringResponse value) {
        return new JAXBElement<GetAuthorStringResponse>(_GetAuthorStringResponse_QNAME, GetAuthorStringResponse.class, null, value);
    }

}
