package vn.com.fsoft.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.InputSource;

import vn.com.fsoft.model.Quiz;

@Component
public class XMLConverter {

    public void convertFromObjectToXML(Object object, String filepath)
            throws JAXBException, XMLStreamException, FactoryConfigurationError, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        File file = new File(filepath);
        OutputStream stream = new FileOutputStream(file);
        marshaller.marshal(object, stream);
        stream.close();
        // Replace <>
        String fileContext = FileUtils.readFileToString(file, "UTF-8");
        fileContext = fileContext.replaceAll("&lt;", "<");
        fileContext = fileContext.replaceAll("&gt;", ">");
        FileUtils.write(file, fileContext, "UTF-8");
    }

    public Object convertFromXMLToObject(String xmlfile, Class<?> clazz) throws IOException, JAXBException {
        FileInputStream fileInputStream = new FileInputStream(xmlfile);
        InputStreamReader reader = new InputStreamReader(fileInputStream, "UTF-8");
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

    public void getCommentsFromXML(MultipartFile file, Quiz quiz) throws IOException, XMLStreamException {

        if (quiz == null || quiz.getQuestionList() == null || quiz.getQuestionList().isEmpty()) {
            return;
        }

        XMLStreamReader xr = XMLInputFactory.newInstance().createXMLStreamReader(file.getInputStream());
        int i = 0;
        int s = quiz.getQuestionList().size();
        String questionId;
        while (xr.hasNext()) {
            if (quiz.getQuestionList().get(i).getCategory() != null) {
                i++;
            }
            if (xr.next() == XMLStreamConstants.COMMENT) {
                String comment = xr.getText();
                questionId = comment.replaceAll("[^0-9]", "");
                if (!"0".equals(questionId)) {
                    quiz.getQuestionList().get(i++).setQuestionId(questionId);
                }
                if (i == s) return;
            }
        }
        xr.close();
    }
}
