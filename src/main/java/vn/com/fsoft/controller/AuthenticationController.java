package vn.com.fsoft.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.com.fsoft.common.Constants;
import vn.com.fsoft.model.HocSinh;
import vn.com.fsoft.service.HocSinhService;

@Controller
@RequestMapping("auth")
@Transactional
public class AuthenticationController {

    public static final String FORBIDDEN_MESSAGE = "403 Forbidden - You do not have permission to access this page!";

    @Autowired
    private HocSinhService hocSinhService;

    @RequestMapping(value = "/DangNhap", method = RequestMethod.GET)
    public String loginPage(Model model) {
        model.addAttribute("title", "Đăng nhập");
        return "auth/login";
    }

    @RequestMapping(value = "/DangKy", method = RequestMethod.GET)
    public String registerPage(Model model) {
        model.addAttribute("title", "Đăng ký");
        model.addAttribute(Constants.DEFAULT_MODEL_NAME, new HocSinh());
        return "auth/register";
    }

    @PostMapping(value = "/DangKyAction")
    public String registerAction(
            @Valid @ModelAttribute(Constants.DEFAULT_MODEL_NAME) HocSinh hocSinh,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute(Constants.DEFAULT_MODEL_NAME, hocSinh);
            return "auth/register";
        } else {
            try {
                hocSinhService.saveHocSinh(hocSinh);
                redirectAttributes.addFlashAttribute("success", "Register success, Please login!");
                return "redirect:/auth/DangNhap";
            } catch (Exception e) {
                model.addAttribute("error", e.getMessage());
                model.addAttribute(Constants.DEFAULT_MODEL_NAME, hocSinh);
                return "auth/register";
            }
        }
    }

    @RequestMapping(value = "DoiThongTin", method = RequestMethod.GET)
    public ModelAndView showUpdateInfoPage(Model model, Principal principal) {
        if (principal != null && principal.getName() != null) {
            model.addAttribute("action", "updateAction");
            model.addAttribute("title", "Update User");
            HocSinh hocSinh = hocSinhService.findByUsername(principal.getName());
            return new ModelAndView("user/update-infor", Constants.DEFAULT_MODEL_NAME, hocSinh);
        } else {
            return new ModelAndView("redirect:/auth/DangNhap");
        }

    }

    @RequestMapping(value = "DoiThongTinAction", method = RequestMethod.POST)
    public String updateUserhAction(
            @Validated @ModelAttribute(Constants.DEFAULT_MODEL_NAME) HocSinh hocSinh,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("action", "updateAction");
            model.addAttribute("title", "Update user");
            model.addAttribute(Constants.DEFAULT_MODEL_NAME, hocSinh);
            return "user/update-infor";
        } else {
            try {
                hocSinhService.updateHocSinh(hocSinh);
                redirectAttributes.addFlashAttribute("success", "Update success!");
                return "redirect:/auth/DoiThongTin";
            } catch (Exception e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("error", e.getMessage());
                return "redirect:/auth/DoiThongTin";
            }
        }
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
        model.addAttribute("title", "403 - Forbidden");
        model.addAttribute("errorCode", 403);
        if (principal != null) {
            model.addAttribute("errorMsg", "Hi " + principal.getName() + "<br>" + FORBIDDEN_MESSAGE);
        } else {
            model.addAttribute("errorMsg", FORBIDDEN_MESSAGE);
        }

        return "error-page";
    }
}
