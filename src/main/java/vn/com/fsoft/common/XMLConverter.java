package vn.com.fsoft.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.InputSource;

@Component
public class XMLConverter {

    public void convertFromObjectToXML(Object object, String filepath) throws JAXBException, FileNotFoundException {
        JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(object, new File(filepath));
        marshaller.marshal(object, System.out);
    }

    public Object convertFromXMLToObject(String xmlfile, Class<?> clazz) throws IOException, JAXBException {
        FileInputStream fileInputStream = new FileInputStream(xmlfile);
        InputStreamReader reader = new InputStreamReader(fileInputStream,"UTF-8");
        InputSource is = new InputSource(reader);
        is.setEncoding("UTF-8");
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return unmarshaller.unmarshal(is);
    }

    public Object convertFromXMLToObject(MultipartFile file, Class<?> clazz) throws IOException, JAXBException {
        InputStreamReader reader = new InputStreamReader(file.getInputStream(), "UTF-8");
        InputSource is = new InputSource(reader);
        is.setEncoding("UTF-8");
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return unmarshaller.unmarshal(is);
    }
}
