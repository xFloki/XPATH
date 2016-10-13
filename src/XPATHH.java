
import java.io.File;
import java.io.FileInputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author xp
 */
public class XPATHH {

    String salida = "";
    

    public String EjecutaXPath(File archivo, String consulta) {
      
        try {

            //Crea un objeto DocumentBuilderFactory para el DOM (JAXP)
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            //Crear un �rbol DOM (parsear) con el archivo LibrosXML.xml
            Document XMLDoc = factory.newDocumentBuilder().parse(archivo);
            //Crea el objeto XPath          
            XPath xpath = XPathFactory.newInstance().newXPath();
            //Crea un XPathExpression con la consulta deseada
            XPathExpression exp = xpath.compile(consulta);
            
            //Ejecuta la consulta indicando que se ejecute sobre el DOM y que devolver�
            //el resultado como una lista de nodos.
            Object result = exp.evaluate(XMLDoc, XPathConstants.NODESET);
            NodeList nodeList = (NodeList) result;
            System.out.println(nodeList.getLength());
            //Ahora recorre la lista para sacar los resultados
            for (int i = 0; i < nodeList.getLength(); i++) {
                if(nodeList.getLength()==1)  {
                    Element movieElement = (Element) nodeList.item(0);
                   
                         NodeList libroAutor = movieElement.getElementsByTagName("Libro");
                    for (int j = 0; i < libroAutor.getLength(); j++) {
                        salida = salida.trim() + "\n"
                        + libroAutor.item(j).getChildNodes().item(0).getNodeValue();
                    }
                
                }
                salida = salida.trim() + "\n"
                        + nodeList.item(i).getChildNodes().item(0).getNodeValue();
            }
//            System.out.println(salida);
            return salida;
        } catch (Exception ex) {
//            System.out.println("Error: " + ex.toString());
            return "No se ha podido ejecutar el XPath" ;
        }
    }
}
