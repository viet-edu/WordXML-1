package vn.com.fsoft.controller.admin;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.com.fsoft.common.Constants;
import vn.com.fsoft.common.XMLConverter;
import vn.com.fsoft.dto.ConvertFormRequest;
import vn.com.fsoft.dto.ConvertFormResponse;
import vn.com.fsoft.model.Answer;
import vn.com.fsoft.service.ConvertService;

@Controller
@RequestMapping("admin")
public class ConvertController {

    @Autowired
    private XMLConverter converter;

    @Autowired
    private ConvertService convertService;

    @GetMapping("/Convert")
    public ModelAndView showXMLToWordPage(Model model) {
        model.addAttribute("title", "Convert Page");
        return new ModelAndView("admin/convert", Constants.DEFAULT_MODEL_NAME, new ConvertFormRequest());
    }

    @PostMapping("/ConvertAction")
    public ModelAndView xmlToWord(
            @Validated @ModelAttribute(Constants.DEFAULT_MODEL_NAME) ConvertFormRequest convertFormRequest,
            BindingResult result,
            RedirectAttributes redirAttrs,
            Model model) {
        model.addAttribute("title", "Convert Page");
        if (result.hasErrors()) {
            return new ModelAndView("admin/convert", Constants.DEFAULT_MODEL_NAME, convertFormRequest);
        } else {
            ConvertFormResponse res = new ConvertFormResponse();
            try {
                res = convertService.convert(convertFormRequest);
                res.setMessage("Convert thành công!");
                res.setType("success");
            } catch (InvalidFormatException | IOException | JAXBException e) {
                res.setType("error");
                res.setMessage(e.getMessage());
                e.printStackTrace();
            }
            redirAttrs.addFlashAttribute("res", res);
            return new ModelAndView("redirect:/admin/Convert");
        }
    }

    @GetMapping("/WordToXML")
    public String wordToXML() {
        try {
            Answer answer = new Answer();
            answer.setFormat("html");
            answer.setFraction(100);
            answer.setText("Answer text");
            converter.convertFromObjectToXML(answer, "D:\\dev2\\WordXML\\requirement\\answer_output.xml");
        } catch (FileNotFoundException | JAXBException e) {
            e.printStackTrace();
        }
        return "admin/xml-to-word";
    }
}
