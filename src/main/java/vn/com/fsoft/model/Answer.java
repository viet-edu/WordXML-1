package vn.com.fsoft.model;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class Answer extends TypeCommon {

    @XmlAttribute(name = "fraction")
    @Column(name = "Fraction", length = 3)
    private Integer fraction;
}
