/**
 *
 */
package vn.com.fsoft.model;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

/**
 * AttributeCommon.java
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
public class AttributeCommon {

    @XmlElement(name = "text")
    @Column(name = "Text", length = 5000)
    private String text;
}
