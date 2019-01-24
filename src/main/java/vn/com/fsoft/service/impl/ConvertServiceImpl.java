package vn.com.fsoft.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.xml.stream.FactoryConfigurationError;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
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

import vn.com.fsoft.common.Helper;
import vn.com.fsoft.common.XMLConverter;
import vn.com.fsoft.dto.ConvertFormRequest;
import vn.com.fsoft.dto.ConvertFormResponse;
import vn.com.fsoft.model.Answer;
import vn.com.fsoft.model.Category;
import vn.com.fsoft.model.GeneralFeedback;
import vn.com.fsoft.model.Question;
import vn.com.fsoft.model.QuestionName;
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

    private static final String REGEX_REMOVE_ALL_HTML_TAG = "<[^>]*>";
    private static final String[] ANSWER_NUMBERING_ARRAY = {"A.", "B.", "C.", "D."};
    private static final String[] GENERAL_FEEDBACK_ARRAY = {"Lời giải"};

    @Override
    public ConvertFormResponse convert(ConvertFormRequest convertFormRequest)
            throws FactoryConfigurationError, Exception {
        MultipartFile file = convertFormRequest.getFile();
        Integer convertType = convertFormRequest.getConvertType();
        ConvertFormResponse res = new ConvertFormResponse();
        String tomcatBase = System.getProperty("catalina.base");
        String webApp = tomcatBase + uploadPath;


        if (convertType == 1) {
            Quiz quiz = (Quiz) converter.convertFromXMLToObject(file, Quiz.class);

            // getCommentsFromXML
            converter.getCommentsFromXML(file, quiz);

            // Write to word file
            // Create Blank document
            XWPFDocument document = new XWPFDocument();

            XWPFParagraph paragraph;
            XWPFRun run;
            String category = null;
            String answerText = null;
            String questionText = null;
            String generalFeedbackText = null;
            int i = 0;
            int j = 0;
            int c = 0;
            int q = 0;
            StringBuffer str = new StringBuffer();
            for (Question question : quiz.getQuestionList()) {

                // Get category
                if (question.getCategory() != null) {
                    category = question.getCategory().getText();
                    continue;
                }

                // Write question name
                run = document.createParagraph().createRun();
                if (StringUtils.indexOfAny(question.getName().getText(), "Câu", "câu", "Cau", "cau") == -1) {
                    run.setText("Câu ");
                }
                run.setText(++q + "(" + question.getQuestionId() + "): ");

                // Write tag
                if (question.getTags() != null) {
                    for (Tag tag : question.getTags()) {
                        str.append(tag.getText());
                        str.append(",");
                    }
                }

                if (str.length() > 0) {
                    str.setLength(str.length() - 1);
                }
                run.setText(str.toString());
                str.setLength(0);

                // Write question
                paragraph = document.createParagraph();
                run = paragraph.createRun();
                questionText = question.getQuestiontext().getText();
                i = 0;
                j = 0;
                c = 0;
                while (true) {
                    j = questionText.indexOf("<p style='text-align: center;'><img", i + 1);
                    if (j == -1)
                        break;
                    run.setText(questionText.toString().substring(i, j).replaceAll(REGEX_REMOVE_ALL_HTML_TAG, ""));
                    run.addPicture(
                            new ByteArrayInputStream(
                                    Base64.getDecoder().decode(question.getQuestiontext().getFile().get(c).getValue())),
                            Document.PICTURE_TYPE_JPEG, question.getQuestiontext().getFile().get(c).getName(),
                            Units.toEMU(500), Units.toEMU(400));
                    c++;
                    i = questionText.indexOf("/>", j) + 6;
                }
                if (c == 0) {
                    run.setText(questionText.replaceAll(REGEX_REMOVE_ALL_HTML_TAG, ""));
                } else {
                    run.setText(
                            questionText.substring(i, questionText.length()).replaceAll(REGEX_REMOVE_ALL_HTML_TAG, ""));
                }

                // Write answer
                i = 0;
                for (Answer answer : question.getAnswerList()) {
                    paragraph = document.createParagraph();
                    run = paragraph.createRun();
                    switch (i) {
                    case 0:
                        answerText = "A";
                        break;
                    case 1:
                        answerText = "B";
                        break;
                    case 2:
                        answerText = "C";
                        break;
                    case 3:
                        answerText = "D";
                        break;
                    default:
                        break;
                    }
                    if (answer.getFraction() > 0) {
                        run.setBold(true);
                        run.setText(answerText);
                    } else {
                        run.setText(answerText);
                    }
                    if (answer.getFraction() > 0) {
                        run.setText(". " + answer.getText().replaceAll(REGEX_REMOVE_ALL_HTML_TAG, ""));
                    } else {
                        run.setText(". " + answer.getText().replaceAll(REGEX_REMOVE_ALL_HTML_TAG, ""));
                    }
                    i++;
                }

                // Write generalfeedback
                paragraph = document.createParagraph();
                run = paragraph.createRun();
                run.setText("Lời giải:");
                generalFeedbackText = question.getGeneralFeedback().getText();
                i = 0;
                j = 0;
                c = 0;
                paragraph = document.createParagraph();
                run = paragraph.createRun();
                while (true) {
                    j = generalFeedbackText.indexOf("<p style='text-align: center;'><img", i + 1);
                    if (j == -1)
                        break;
                    run.setText(
                            generalFeedbackText.toString().substring(i, j).replaceAll(REGEX_REMOVE_ALL_HTML_TAG, ""));
                    run.addPicture(
                            new ByteArrayInputStream(Base64.getDecoder()
                                    .decode(question.getGeneralFeedback().getFile().get(c).getValue())),
                            Document.PICTURE_TYPE_JPEG, question.getGeneralFeedback().getFile().get(c).getName(),
                            Units.toEMU(500), Units.toEMU(400));
                    c++;
                    i = generalFeedbackText.indexOf("/>", j) + 6;
                }
                if (c == 0) {
                    run.setText(generalFeedbackText.replaceAll(REGEX_REMOVE_ALL_HTML_TAG, ""));
                } else {
                    run.setText(generalFeedbackText.substring(i, generalFeedbackText.length())
                            .replaceAll(REGEX_REMOVE_ALL_HTML_TAG, ""));
                }
            }

            // Write the Document in file system
            String fileName = "converted_" + new Date().getTime();
            if (category != null) {
                String [] arrTmp = category.split("[/]");
                fileName = Helper.covertStringToURL(arrTmp[arrTmp.length - 1].replaceAll("[^a-zA-Z0-9- _\\u0080-\\u9fff]", ""));
            }
            String filePath = webApp + "//word//";
            String fullPath = filePath + fileName + ".docx";

            File dir = new File(org.springframework.util.StringUtils.cleanPath(filePath));
            if (!dir.exists()) {
                dir.mkdirs();
            }
            FileOutputStream out = new FileOutputStream(new File(fullPath));
            document.write(out);
            out.close();
            document.close();
            res.setFileName(fileName);
            res.setFilePath("word/" + fileName + ".docx");
        }

        if (convertType == 2) {

            // Read file word.
            StringBuffer strTmp = new StringBuffer();

            File fileIO = new File(file.getOriginalFilename());
            OutputStream out = new FileOutputStream(fileIO);
            IOUtils.copy(file.getInputStream(), out);

            FileInputStream fis = new FileInputStream(fileIO);
            XWPFDocument document = new XWPFDocument(OPCPackage.open(fis));
            List<XWPFParagraph> paragraphList = document.getParagraphs();
            int i_size = 0;
            List<Tag> tmpTags = new ArrayList<Tag>();
            Question questionTmp = new Question();
            QuestionText questionTextTmp = new QuestionText();
            QuestionName questionNameTmp = new QuestionName();
            List<Answer> lstAnswer = new ArrayList<>();
            Answer answerTmp;
            vn.com.fsoft.model.File fileTmp = null;
            List<vn.com.fsoft.model.File> fileTmpList = new ArrayList<>();
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
            catTmp.setText("$course$/top/[" + file.getOriginalFilename().replaceAll(".docx", "") + "]");
            questionTmp = new Question();
            questionTmp.setCategory(catTmp);
            questionTmp.setType("category");
            quiz.getQuestionList().add(questionTmp);
            int i_question_name = 1;
            for (XWPFParagraph paragraph : paragraphList) {
                if (paragraph.getText().indexOf("Câu") == 0) {
                    continue;
                }
                // Reset when #
                if (paragraph.getText() != null && paragraph.getText().length() > 1
                        && paragraph.getText().indexOf("#") == 0
                        || StringUtils.startsWithAny(paragraph.getText(), ANSWER_NUMBERING_ARRAY)
                        || StringUtils.startsWithAny(paragraph.getText(), GENERAL_FEEDBACK_ARRAY)) {
                    i_size += 1;
                    if (strTmp.toString().length() == 0)
                        strTmp.append(paragraph.getText());
                    switch (i_size) {
                    case 1:
                        // Handle tag
                        questionTmp = new Question();
                        setCommonQuestion(questionTmp);
                        questionNameTmp = new QuestionName();
                        questionNameTmp.setText("Câu" + i_question_name);
                        questionTmp.setName(questionNameTmp);
                        i_question_name++;
                        lstAnswer = new ArrayList<Answer>();
                        tmpTags = handleTag(strTmp);
                        questionTmp.setTags(tmpTags);
                        break;
                    case 2:
                        // Handle QuestionText.
                        questionTextTmp = new QuestionText();
                        questionTextTmp.setFormat("html");
                        questionTextTmp.setText("<![CDATA[" + handleTextQuestion(strTmp) + "]]>");
                        questionTextTmp.setFile(fileTmpList);
                        fileTmp = new vn.com.fsoft.model.File();
                        fileTmpList = new ArrayList<>();
                        questionTmp.setQuestiontext(questionTextTmp);
                        i_size += 1;
                        strTmp.setLength(0);
                        strTmp.append(paragraph.getText());
                    case 3:
                    case 4:
                    case 5:
                    case 6: // Handle Answer
                        if (StringUtils.startsWithAny(strTmp.toString(), ANSWER_NUMBERING_ARRAY)) {
                            answerTmp = new Answer();
                            answerTmp.setFormat("html");
                            for (XWPFRun item : paragraph.getRuns()) {
                                if (item.getText(item.getTextPosition()) != null) {
                                    if (StringUtils.startsWithAny(item.getText(item.getTextPosition()), ANSWER_NUMBERING_ARRAY)
                                            && item.isBold()) {
                                        strTmp.insert(0, "BOLD");
                                    }
                                }
                            }
                            if (strTmp.toString().startsWith("BOLD")) {
                                answerTmp.setText("<![CDATA[<p>" + strTmp.toString().substring(6).trim() + "</p>]]>");
                                answerTmp.setFraction(100);
                            }else {
                                answerTmp.setText("<![CDATA[<p>" + strTmp.toString().substring(2).trim() + "</p>]]>");
                                answerTmp.setFraction(0);
                            }
                            lstAnswer.add(answerTmp);
                            questionTmp.setAnswerList(lstAnswer);
                        }
                        break;
                    case 8:
                        // Handle generalfeedback
                        generalTmp = new GeneralFeedback();
                        generalTmp.setFormat("html");
                        if (fileTmpList != null && !fileTmpList.isEmpty()) {
                            generalTmp.setFile(fileTmpList);
                        }
                        fileTmp = new vn.com.fsoft.model.File();
                        fileTmpList = new ArrayList<>();
                        generalTmp.setText("<![CDATA[" + handleTextQuestion(strTmp) + "]]>");
                        questionTmp.setGeneralFeedback(generalTmp);
                        quiz.getQuestionList().add(questionTmp);
                        // Handle tags.
                        i_size = 1;
                        strTmp.setLength(0);
                        strTmp.append(paragraph.getText());
                        // Handle tag
                        questionTmp = new Question();
                        setCommonQuestion(questionTmp);
                        questionNameTmp = new QuestionName();
                        questionNameTmp.setText(String.valueOf(i_question_name));
                        questionTmp.setName(questionNameTmp);
                        i_question_name++;
                        lstAnswer = new ArrayList<Answer>();
                        tmpTags = handleTag(strTmp);
                        questionTmp.setTags(tmpTags);
                    default:
                        break;
                    }
                    strTmp.setLength(0);
                    continue;
                }
                for (XWPFRun item : paragraph.getRuns()) {
                    if (item.getText(item.getTextPosition()) != null) {
                        if (strTmp.toString().length() == 0) {
                            strTmp.append("<p>");
                        }
                        strTmp.append(item.getText(item.getTextPosition()));
                    }
                    for (XWPFPicture itemImg : item.getEmbeddedPictures()) {
                        fileTmp = new vn.com.fsoft.model.File();
                        fileTmp.setEncoding("base64");
                        iNameFileImg++;
                        fileTmp.setName(iNameFileImg + ".jpg");
                        fileTmp.setPath("/");
                        if (strTmp.toString().length() > 0) {
                            strTmp.append("</p>");
                        }

                        strTmp.append("<p style='text-align: center;'><img src='@@PLUGINFILE@@/" + fileTmp.getName()
                                + "' width='400' height='300' role='presentation' class='img-responsive atto_image_button_text-bottom'/></p><p>");
                        fileTmp.setValue(Base64.getEncoder().encodeToString(itemImg.getPictureData().getData()));
                        fileTmpList.add(fileTmp);
                    }
                }
                strTmp.append("\n");
            }
            generalTmp = new GeneralFeedback();
            generalTmp.setFormat("html");
            generalTmp.setText(handleTextQuestion(strTmp));
            generalTmp.setText("<![CDATA[" + strTmp.toString() + "]]>");
            if (fileTmpList != null && !fileTmpList.isEmpty()) {
                generalTmp.setFile(fileTmpList);
            }
            questionTmp.setGeneralFeedback(generalTmp);
            document.close();
            fis.close();
            XMLConverter converter = new XMLConverter();
            quiz.getQuestionList().add(questionTmp);

            String fileName = Helper.covertStringToURL((file.getOriginalFilename()).replaceAll(".docx", ""));
            String filePath = webApp + "//xml//";
            String fullPath = filePath + fileName + ".xml";

            File dir = new File(org.springframework.util.StringUtils.cleanPath(filePath));
            if (!dir.exists()) {
                dir.mkdirs();
            }
            converter.convertFromObjectToXML(quiz, fullPath);

            res.setFileName(fileName);
            res.setFilePath("xml/" + fileName + ".xml");
        }

        return res;
    }

    private String handleTextQuestion(final StringBuffer strTmp) {
        if (strTmp.toString().endsWith("<p>\n")) {
            strTmp.setLength(strTmp.length() - 4);
        } else {
            strTmp.setLength(strTmp.length() - 1);
            strTmp.append("</p>");
        }
        strTmp.append("\n");
        return strTmp.toString();
    }

    private List<Tag> handleTag(final StringBuffer strTmp) {
        List<Tag> tmpTags = new ArrayList<Tag>();
        String[] lstStrTmp = strTmp.toString().split(",");
        Tag tmpTag;
        for (String tmp : lstStrTmp) {
            tmpTag = new Tag();
            tmpTag.setText(StringUtils.replace(tmp, "#", ""));
            tmpTags.add(tmpTag);
        }
        return tmpTags;
    }

    private void setCommonQuestion(Question questionTmp) {
        questionTmp.setShuffleanswers(true);
        questionTmp.setSingle(true);
        questionTmp.setDefaultGrade("1.0000000");
        questionTmp.setPenalty(0.3333333f);
        questionTmp.setHidden(0);
        questionTmp.setAnswernumbering("ABCD");
        questionTmp.setType("multichoice");
    }
}
