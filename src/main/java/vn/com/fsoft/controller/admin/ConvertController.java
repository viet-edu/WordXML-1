package vn.com.fsoft.controller.admin;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

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
import vn.com.fsoft.dto.ConvertFormRequest;
import vn.com.fsoft.dto.ConvertFormResponse;
import vn.com.fsoft.service.ConvertService;

@Controller
@RequestMapping("admin")
public class ConvertController {

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
            } catch (InvalidFormatException | IOException | JAXBException | XMLStreamException | FactoryConfigurationError e) {
                res.setType("error");
                res.setMessage(e.getMessage());
                e.printStackTrace();
            }
            redirAttrs.addFlashAttribute("res", res);
            return new ModelAndView("redirect:/admin/Convert");
        }
    }
}
