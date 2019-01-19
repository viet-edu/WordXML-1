/**
 * 
 */
package vn.com.fsoft.model;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

import lombok.Getter;
import lombok.Setter;

/**
 * File.java
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
public class File {

    @XmlAttribute(name = "name")
    @Column(name = "Name", length = 20)
    private String name;

    @XmlAttribute(name = "path")
    @Column(name = "Path", length = 20)
    private String path;

    @XmlAttribute(name = "encoding")
    @Column(name = "Encoding", length = 20)
    private String encoding;

    @XmlValue
    private String value;
}
