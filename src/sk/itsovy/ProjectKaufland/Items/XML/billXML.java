package sk.itsovy.ProjectKaufland.Items.XML;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import sk.itsovy.ProjectKaufland.Items.Drinks.DraftInterface;
import sk.itsovy.ProjectKaufland.Items.Food.Fruit;
import sk.itsovy.ProjectKaufland.Items.Item;
import sk.itsovy.ProjectKaufland.Items.PcsInterface;
import sk.itsovy.ProjectKaufland.Main.Bill;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class billXML {

    public void createXML(Bill bill) throws ParserConfigurationException, ParseException ,TransformerException, IOException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();

        Element rootElement = doc.createElement("bill");
        doc.appendChild(rootElement);

        Element Date = doc.createElement("date");
        rootElement.appendChild(Date);

        Element Date1 = doc.createElement("dateOfPurchase");
        Date1.appendChild(doc.createTextNode(String.valueOf(bill.getDatetime())));
        Date.appendChild(Date1);

        Element items = doc.createElement("items");
        rootElement.appendChild(items);



        for (Item it : bill.getBill()) {
            Element item = doc.createElement("item");
            items.appendChild(item);

            Element name = doc.createElement("name");
            name.appendChild(doc.createTextNode(it.getName()));
            item.appendChild(name);

            Element price = doc.createElement("price");
            price.appendChild(doc.createTextNode(String.valueOf(it.getPrice())));
            item.appendChild(price);

            Element amount = doc.createElement("amount");
            if (it instanceof DraftInterface){
                amount.appendChild(doc.createTextNode(String.valueOf(((DraftInterface) it).getVolume())));
                item.appendChild(amount);
            }
            else if (it instanceof Fruit){
                amount.appendChild(doc.createTextNode(String.valueOf(((Fruit) it).getWeight())));
                item.appendChild(amount);
            }
            else if (it instanceof PcsInterface){
                amount.appendChild(doc.createTextNode(String.valueOf(((PcsInterface) it).getAmount())));
                item.appendChild(amount);
            }

            Element unit = doc.createElement("unit");
            if (item instanceof DraftInterface){
                unit.appendChild(doc.createTextNode("l"));
                item.appendChild(unit);
            }
            else if (item instanceof Fruit){
                unit.appendChild(doc.createTextNode("kg"));
                item.appendChild(unit);
            }
            else if (item instanceof PcsInterface){
                unit.appendChild(doc.createTextNode("pcs"));
                item.appendChild(unit);
            }

        }

        Element price = doc.createElement("price");
        rootElement.appendChild(price);

        Element priceEur = doc.createElement("EUR");
        priceEur.appendChild(doc.createTextNode(String.valueOf(bill.getFinalPrice())));
        price.appendChild(priceEur);

        Element priceDol = doc.createElement("USD");
        priceDol.appendChild(doc.createTextNode(String.valueOf(bill.getFinalPriceDollars())));
        price.appendChild(priceDol);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File( "E:\\Programy\\java projekty\\ProjectKaufland\\src\\sk\\itsovy\\ProjectKaufland\\XMLoutput.xml"));
        transformer.transform(source, result);

        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);
    }
}
