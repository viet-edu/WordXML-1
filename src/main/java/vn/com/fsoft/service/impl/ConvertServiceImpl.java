package vn.com.fsoft.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.xml.bind.JAXBException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import vn.com.fsoft.common.XMLConverter;
import vn.com.fsoft.dto.ConvertFormRequest;
import vn.com.fsoft.dto.ConvertFormResponse;
import vn.com.fsoft.model.Answer;
import vn.com.fsoft.model.Question;
import vn.com.fsoft.model.Quiz;
import vn.com.fsoft.model.Tag;
import vn.com.fsoft.service.ConvertService;

@Service
public class ConvertServiceImpl implements ConvertService {

    @Autowired
    private XMLConverter converter;

    @Value("${storage.uploadPath}")
    private String uploadPath;

    @Override
    public ConvertFormResponse convert(ConvertFormRequest convertFormRequest) throws IOException, JAXBException, InvalidFormatException {
        MultipartFile file = convertFormRequest.getFile();
        Integer convertType = convertFormRequest.getConvertType();
        ConvertFormResponse res = new ConvertFormResponse();

        if (convertType == 1) {
            Quiz quiz = (Quiz) converter.convertFromXMLToObject(file, Quiz.class);

            // Write to word file
            // Create Blank document
            XWPFDocument document = new XWPFDocument();

            // Create new Paragraph
            XWPFParagraph paragraph1 = document.createParagraph();
            XWPFRun run = paragraph1.createRun();

            StringBuffer str = new StringBuffer("#");
            for (Question question : quiz.getQuestionList()) {

                // Write tag
                for (Tag tag : question.getTags()) {
                    str.append(tag.getText());
                    str.append(",");
                }
                str.setLength(str.length() - 1);
                run.setText(str.toString());
                run.addBreak();
                str.setLength(0);
                str.append("#");

                // Write question
                run.setText(question.getQuestiontext().getText());
                run.addBreak();
                run.addPicture(
                        new ByteArrayInputStream(Base64.getDecoder().decode(question.getQuestiontext().getFile().getValue())),
                        Document.PICTURE_TYPE_JPEG,
                        question.getQuestiontext().getFile().getName(),
                        Units.toEMU(200),
                        Units.toEMU(200));
                run.addBreak();

                // Write answer
                for (Answer answer : question.getAnswerList()) {
                    if (answer.getFraction() > 0) {
                        run.setText("#dung" + answer.getText());
                    } else {
                        run.setText("#nhieu" + answer.getText());
                    }
                    run.addBreak();
                }

                // Write generalfeedback
                run.setText("#loigiai:");
                run.addBreak();
                run.setText(question.getGeneralFeedback().getText());
                run.addBreak();
            }

            // Write the Document in file system
            String fileName = file.getOriginalFilename() + "_converted".replaceAll(".xml", "");
            String filePath = uploadPath + "xml\\" + fileName + ".docx";
            FileOutputStream out = new FileOutputStream(new File(filePath));
            document.write(out);
            out.close();
            document.close();
            res.setFileName(fileName);
            res.setFilePath("xml/" + fileName + ".docx");
        }

        if (convertType == 2) {

        }

        return res;
    }
}
