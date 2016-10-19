
import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


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
    Node yolo;
    NodeList yolos;
     Element e;

    public String EjecutaXPath(File archivo, String consulta) {

        try {

            //Crea un objeto DocumentBuilderFactory para el DOM (JAXP)
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            //Crear un árbol DOM (parsear) con el archivo LibrosXML.xml
            Document XMLDoc = factory.newDocumentBuilder().parse(archivo);
            //Crea el objeto XPath          
            XPath xpath = XPathFactory.newInstance().newXPath();
            //Crea un XPathExpression con la consulta deseada
            XPathExpression exp = xpath.compile(consulta);

            //Ejecuta la consulta indicando que se ejecute sobre el DOM y que devolverá
            //el resultado como una lista de nodos.
            Object result = exp.evaluate(XMLDoc, XPathConstants.NODESET);
            NodeList nodeList = (NodeList) result;
            System.out.println(nodeList.getLength());
            //Ahora recorre la lista para sacar los resultados
            for (int i = 0; i < nodeList.getLength(); i++) {

                  yolo = nodeList.item(i);
                    if(yolo.getNodeType() == Node.ATTRIBUTE_NODE){
                        salida = salida.trim() +  "\n"   + "Publicado en: " ;
                   }  
                     if(yolo.getNodeName()== "Autor"  ){     
                            salida = salida.trim() +  "\n"  + "Autor: ";                           
                  }
                     if(yolo.getNodeName()== "Titulo"  ){
                  e = (Element)yolo;          
                            salida = salida.trim() +  "\n"   + "Titulo: " ;                           
                  }
                  
                 //Si el nodo en el que nos encontramos el Libro añadimos su atributo del año
                 //en el que se pubico
                  if(yolo.getNodeName()== "Libro" && yolo.getNodeType() == Node.ELEMENT_NODE ){
                  e = (Element)yolo;          
                            salida = salida + "\n" + "Publicado en: " + e.getAttribute("publicado_en");                           
                  }
                  
                  //Creamos un nuevo nodelist a partir de los nodos del nodelist anterior
                  //para en el caso de que nos encontremos en el nodo libro que a su vez 
                  //tiene los nodos autor y titulo se creee un nodelist con estos para luego
                  //imprimir su contenido
                  yolos = yolo.getChildNodes();                 
                   for (int j = 0; j < yolos.getLength(); j++) {                   
                    
                     
                     if(yolos.item(j).getNodeName()== "Autor"  ){
                  e = (Element)yolo;          
                            salida = salida.trim() +  "\n"  + "Autor: ";                           
                  }
                  if(yolos.item(j).getNodeName()== "Titulo"  ){
                  e = (Element)yolo;          
                            salida = salida.trim() +  "\n"   + "Titulo: " ;                           
                  }
//                  if(yolos.item(j).getNodeName()== "publicado_en"  ){
//                  e = (Element)yolo;          
//                            salida = salida.trim() +  "\n"   + "Publicado en: " ;                           
//                  }
                    
                   ;
                  
                   salida = salida.trim() + " " + yolos.item(j).getTextContent();
                   }
                  salida = salida.trim() + "\n" + "-----------------------" ;               
            }
            System.out.println(yolo.getNodeName());

            return salida;
        } catch (Exception ex) {
//            System.out.println("Error: " + ex.toString());
            return "No se ha podido ejecutar el XPath";
        }
    }
}
