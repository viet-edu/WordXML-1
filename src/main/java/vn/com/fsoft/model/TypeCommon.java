/**
 * 
 */
package vn.com.fsoft.model;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import lombok.Getter;
import lombok.Setter;

/**
 * TypeCommon.java
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
public class TypeCommon extends AttributeCommon{

    @XmlAttribute(name = "format")
    @Column(name = "Format", length = 20)
    private String format;
}
