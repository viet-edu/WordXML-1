package vn.com.fsoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <b>IndexController</b>.
 *
 * <p>Version 1.0</p>
 *
 * <p>Date: 27-10-2018</p>
 *
 * <p>Copyright</p>
 *
 * <p>Modification Logs:</p>
 * <p>DATE             AUTHOR      DESCRIPTION</p>
 * ----------------------------------------
 * <p>27-10-2018       ABC123      Create</p>
 */
@Controller
public class IndexController {

    /**
     * Show home page.
     * @return String
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHomePage(Model model) {
        model.addAttribute("title", "WEBSITE HỖ TRỢ ÔN TẬP VÀ KIỂM TRA TRỰC TUYẾN");
        return "index";
    }

    /**
     * Show home page.
     * @return String
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String showHomeAdminPage(Model model) {
        model.addAttribute("title", "Admin | Trang chủ");
        return "admin-index";
    }
}
