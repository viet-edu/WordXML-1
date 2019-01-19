package vn.com.fsoft.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class Question {

    @XmlElement(name = "category")
    private Category category;

    @XmlAttribute(name = "type")
    private String type;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "questiontext")
    private QuestionText questiontext;

    @XmlElement(name = "generalfeedback")
    private GeneralFeedback generalFeedback;

    @XmlElement(name = "defaultgrade")
    private String defaultGrade;

    @XmlElement(name = "penalty")
    private Float penalty;

    @XmlElement(name = "hidden")
    private Integer hidden;

    @XmlElement(name = "single")
    private Boolean single;

    @XmlElement(name = "shuffleanswers")
    private Boolean shuffleanswers;

    @XmlElement(name = "answernumbering")
    private String answernumbering;

    @XmlElement(name = "correctfeedback")
    private CorrectFeedback correctFeedback;

    @XmlElement(name = "partiallycorrectfeedback")
    private PartiallyCorrectFeedback partiallyCorrectFeedback;

    @XmlElement(name = "incorrectfeedback")
    private IncorrectFeedback incorrectFeedback;

    @XmlElement(name = "answer")
    private List<Answer> answerList = new ArrayList<>();

    @XmlElementWrapper(name = "tags")
    @XmlElement(name = "tag")
    private List<Tag> tags;

    @Override
    public String toString() {
        return "123";
    }
}
