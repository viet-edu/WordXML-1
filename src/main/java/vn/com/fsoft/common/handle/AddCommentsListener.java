package vn.com.fsoft.common.handle;

import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import vn.com.fsoft.model.Question;

public class AddCommentsListener extends Marshaller.Listener{

    private XMLStreamWriter xsw;

    public AddCommentsListener() {
        super();
    }

    public AddCommentsListener(XMLStreamWriter xsw) {
        this.xsw = xsw;
    }

    @Override
    public void beforeMarshal(Object source) {
        if (source instanceof Question) {
            try {
                xsw.writeComment(source.toString());
            } catch (XMLStreamException e) {
                System.out.println(e);
            }
        }
    }

    @Override
    public void afterMarshal(Object source) {
        if (source instanceof Question) {
            try {
                xsw.writeComment(source.toString());
            } catch (XMLStreamException e) {
                System.out.println(e);
            }
        }
    }
}
