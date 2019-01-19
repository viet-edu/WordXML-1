package vn.com.fsoft.service;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import vn.com.fsoft.dto.ConvertFormRequest;
import vn.com.fsoft.dto.ConvertFormResponse;

public interface ConvertService {
    ConvertFormResponse convert(ConvertFormRequest convertFormRequest) throws IOException, JAXBException, InvalidFormatException;
}
