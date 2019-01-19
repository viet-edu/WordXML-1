/**
 *
 */
package vn.com.fsoft.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

/**
 * Quiz.java
 *
 * Version 1.0
 *
 * Date: Jan 18, 2019
 *
 * Copyright
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 15/8/2016        QuangH          Create
 */
@Getter
@Setter
@XmlRootElement(name = "quiz")
@XmlAccessorType(XmlAccessType.FIELD)
public class Quiz {

    @XmlElement(name = "question")
    private List<Question> questionList;
}
