package vn.com.fsoft.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import vn.com.fsoft.common.XMLConverter;
import vn.com.fsoft.dto.ConvertFormRequest;
import vn.com.fsoft.dto.ConvertFormResponse;
import vn.com.fsoft.model.Answer;
import vn.com.fsoft.model.Category;
import vn.com.fsoft.model.GeneralFeedback;
import vn.com.fsoft.model.Question;
import vn.com.fsoft.model.QuestionText;
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
    public ConvertFormResponse convert(ConvertFormRequest convertFormRequest) throws IOException, JAXBException, InvalidFormatException, XMLStreamException, FactoryConfigurationError {
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
            String fileName = (file.getOriginalFilename() + "_converted").replaceAll(".xml", "");
            String filePath = uploadPath + "word\\" + fileName + ".docx";
            FileOutputStream out = new FileOutputStream(new File(filePath));
            document.write(out);
            out.close();
            document.close();
            res.setFileName(fileName);
            res.setFilePath("word/" + fileName + ".docx");
        }

        if (convertType == 2) {
         // Read file word.
            StringBuffer strTmp = new StringBuffer();
            List<String> strInputList = new ArrayList<>();

            File fileIO = new File(file.getOriginalFilename());
            OutputStream out = new FileOutputStream(fileIO);
            IOUtils.copy(file.getInputStream(), out);

            FileInputStream fis = new FileInputStream(fileIO);
            XWPFDocument document = new XWPFDocument(OPCPackage.open(fis));
            List<XWPFParagraph> paragraphList = document.getParagraphs();
            int i_size = 0;
            List<Tag> tmpTags = new ArrayList<Tag>();
            Tag tmpTag;
            Question questionTmp = new Question();
            QuestionText questionTextTmp = new QuestionText();
            List<Answer> lstAnswer = new ArrayList<>();
            Answer answerTmp;
            vn.com.fsoft.model.File fileTmp = null;
            int iNameFileImg = 0;
            GeneralFeedback generalTmp = new GeneralFeedback();
            Quiz quiz = new Quiz();
            quiz.setQuestionList(new ArrayList<Question>());
            Category catTmp = new Category();
            catTmp.setText("$course$/top");
            questionTmp.setCategory(catTmp);
            questionTmp.setType("category");
            quiz.getQuestionList().add(questionTmp);
            catTmp = new Category();
            catTmp.setText("$course$/top/[" + file.getOriginalFilename().replaceAll(".docx", "")+ "]");
            questionTmp.setCategory(catTmp);
            questionTmp.setType("category");
            quiz.getQuestionList().add(questionTmp);
            String[] lstStrTmp;
            for (XWPFParagraph paragraph : paragraphList) {
                // Reset when #
                if (paragraph.getText() != null && paragraph.getText().length() > 1 && paragraph.getText().indexOf("#") == 0) {
                    i_size += 1;
                    if (strTmp.toString().length() > 0)
                        strInputList.add(strTmp.toString());
                    else
                        strTmp.append(paragraph.getText());
                    strInputList.add(paragraph.getText());
                    switch (i_size) {
                    case 1:
                        // Handle tag
                        questionTmp = new Question();
                        questionTmp.setType("multichoice");
                        questionTmp.setShuffleanswers(new Boolean(false));
                        questionTmp.setSingle(new Boolean(false));
                        tmpTags = new ArrayList<Tag>();
                        lstAnswer = new ArrayList<Answer>();
                        lstStrTmp = strTmp.toString().split(",");
                        for (String tmp : lstStrTmp) {
                            tmpTag = new Tag();
                            tmpTag.setText(tmp);
                            tmpTags.add(tmpTag);
                        }
                        questionTmp.setTags(tmpTags);
                        break;
                    case 2:
                        // Handle QuestionText.
                        questionTextTmp = new QuestionText();
                        questionTextTmp.setFormat("html");
                        questionTextTmp.setText("<![CDATA[<p>" + strTmp.toString() + "</p>]]>");
                        questionTextTmp.setFile(fileTmp);
                        questionTmp.setQuestiontext(questionTextTmp);
                        i_size += 1;
                        strTmp.setLength(0);
                        strTmp.append(paragraph.getText());
                    case 3:
                    case 4:
                    case 5:
                    case 6: // Handle Answer
                        answerTmp = new Answer();
                        answerTmp.setFormat("html");
                        if (strTmp.toString().startsWith("#dung")) {
                            answerTmp.setText("<![CDATA[<p>" + strTmp.toString().substring(5) + "</p>]]>");
                            answerTmp.setFraction(100);
                        }
                        if (strTmp.toString().startsWith("#nhieu")) {
                            answerTmp.setText("<![CDATA[<p>" + strTmp.toString().substring(6) + "</p>]]>");
                            answerTmp.setFraction(0);
                        }
                        lstAnswer.add(answerTmp);
                        questionTmp.setAnswerList(lstAnswer);
                        break;
                    case 8:
                        // Handle generalfeedback
                        generalTmp = new GeneralFeedback();
                        generalTmp.setFormat("html");
                        generalTmp.setFile(fileTmp);
                        generalTmp.setText("<![CDATA[<p>" + strTmp.toString() + "</p>]]>");
                        questionTmp.setGeneralFeedback(generalTmp);
                        quiz.getQuestionList().add(questionTmp);
                        // Handle tag
                        strTmp.setLength(0);
                        strTmp.append(paragraph.getText());
                        questionTmp = new Question();
                        questionTmp.setShuffleanswers(new Boolean(false));
                        questionTmp.setSingle(new Boolean(false));
                        questionTmp.setType("multichoice");
                        tmpTags = new ArrayList<Tag>();
                        lstAnswer = new ArrayList<Answer>();
                        lstStrTmp = strTmp.toString().split(",");
                        for (String tmp : lstStrTmp) {
                            tmpTag = new Tag();
                            tmpTag.setText(tmp.replaceAll("#", ""));
                            tmpTags.add(tmpTag);
                        }
                        questionTmp.setTags(tmpTags);
                        i_size = 1;
                    default:
                        break;
                    }
                    strTmp.setLength(0);
                    continue;
                }
                for(XWPFRun item : paragraph.getRuns()) {
                    if (item.getText(item.getTextPosition()) != null) {
                        strTmp.append(item.getText(item.getTextPosition()));
                    }
                    for(XWPFPicture itemImg : item.getEmbeddedPictures()) {
                        fileTmp = new vn.com.fsoft.model.File();
                        fileTmp.setEncoding("base64");
                        iNameFileImg++;
                        fileTmp.setName(iNameFileImg + ".jpq");
                        fileTmp.setPath("/");
                        fileTmp.setValue(Base64.getEncoder().encodeToString(itemImg.getPictureData().getData()));
                    }
                }
                strTmp.append("\n");
            }
            strInputList.add(strTmp.toString());
            generalTmp = new GeneralFeedback();
            generalTmp.setFormat("html");
            generalTmp.setFile(fileTmp);
            generalTmp.setText("<![CDATA[<p>" + strTmp.toString() + "</p>]]>");
            questionTmp.setGeneralFeedback(generalTmp);
            document.close();
            fis.close();
            XMLConverter converter = new XMLConverter();
            quiz.getQuestionList().add(questionTmp);

            String fileName = (file.getOriginalFilename() + "_converted").replaceAll(".docx", "");
            String filePath = uploadPath + "xml\\" + fileName + ".xml";
            converter.convertFromObjectToXML(quiz, filePath);
            res.setFileName(fileName);
            res.setFilePath("xml/" + fileName + ".xml");
        }

        return res;
    }
}
