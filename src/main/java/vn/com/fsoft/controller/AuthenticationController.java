package vn.com.fsoft.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("auth")
@Transactional
public class AuthenticationController {

    public static final String FORBIDDEN_MESSAGE = "403 Forbidden - You do not have permission to access this page!";

    @RequestMapping(value = "/DangNhap", method = RequestMethod.GET)
    public String loginPage(Model model) {
        model.addAttribute("title", "Đăng nhập");
        return "auth/login";
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
