package pkg20serialization;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlRootElement;

/*
 * serialization2JaxbDemo.java — XML binding with JAXB (Jakarta XML Bind).
 */
public class serialization2JaxbDemo {

    @XmlRootElement(name = "book")
    static class Book {
        public String title;
        public String author;
        Book() {}
        Book(String title, String author) { this.title = title; this.author = author; }
    }

    public static void main(String[] args) throws Exception {
        JAXBContext ctx = JAXBContext.newInstance(Book.class);
        Marshaller marshaller = ctx.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        Book book = new Book("Java Concurrency", "Goetz");
        java.io.StringWriter sw = new java.io.StringWriter();
        marshaller.marshal(book, sw);
        System.out.println("JAXB XML:\n" + sw);

        Unmarshaller unmarshaller = ctx.createUnmarshaller();
        Book parsed = (Book) unmarshaller.unmarshal(new java.io.StringReader(sw.toString()));
        System.out.println("Parsed title: " + parsed.title);
    }
}
