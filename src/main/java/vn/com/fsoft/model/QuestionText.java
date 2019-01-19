/**
 * 
 */
package vn.com.fsoft.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

/**
 * QuestionText.java
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
@XmlAccessorType(XmlAccessType.FIELD)
public class QuestionText extends TypeCommon{

    @XmlElement(name = "file")
    private File file;
}
