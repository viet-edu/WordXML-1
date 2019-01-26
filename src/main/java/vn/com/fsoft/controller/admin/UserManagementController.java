package vn.com.fsoft.controller.admin;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import vn.com.fsoft.common.Constants;
import vn.com.fsoft.model.HocSinh;
import vn.com.fsoft.model.Permission;
import vn.com.fsoft.service.HocSinhService;
import vn.com.fsoft.service.PermissionService;

@Controller
@RequestMapping("admin/QuanLyUser")
public class UserManagementController {

    @Autowired
    private HocSinhService hocSinhService;

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String showCreateUserPage(Model model) {
        model.addAttribute("action", "createAction");
        model.addAttribute("title", "Đăng kí user");
        model.addAttribute(Constants.DEFAULT_MODEL_NAME, new HocSinh());
        return "admin/create-user";
    }

    @RequestMapping(value = "createAction", method = RequestMethod.POST)
    public String createUserhAction(@Validated @ModelAttribute(Constants.DEFAULT_MODEL_NAME) HocSinh hocSinh,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("title", "Đăng kí cuser");
            model.addAttribute("action", "createAction");
            model.addAttribute(Constants.DEFAULT_MODEL_NAME, hocSinh);
            return "admin/create-user";
        } else {
            try {
                hocSinhService.saveHocSinh(hocSinh);
                return "redirect:/admin/QuanLyUser/list";
            } catch (Exception e) {
                model.addAttribute("title", "Đăng kí cuser");
                model.addAttribute("action", "createAction");
                model.addAttribute(Constants.DEFAULT_MODEL_NAME, hocSinh);
                model.addAttribute("error", e.getMessage());
                return "admin/create-user";
            }
        }
    }

    @RequestMapping(value = "update/{maHocSinh}", method = RequestMethod.GET)
    public ModelAndView showUpdateUserPage(Model model, @PathVariable("maHocSinh") Integer maHocSinh) {
        model.addAttribute("action", "updateAction");
        model.addAttribute("title", "Update User");
        HocSinh hocSinh = hocSinhService.findByMaHocSinh(maHocSinh);
//        List<Permission> permissionListReturn = permissionService.getPermissionList().stream().map(tmp -> {
//            for (Permission permission : hocSinh.getRole().getPermissionList()) {
//                if (permission.getPermissionId() == permission.getPermissionId()) {
//                    return tmp;
//                }
//            }
//            return tmp;
//        }).collect(Collectors.toList());
//        hocSinh.setPermissionList(permissionListReturn);
        model.addAttribute("permissionList", permissionService.getPermissionList());
        hocSinh.setPermissionList(hocSinh.getRole().getPermissionList());
        return new ModelAndView("admin/create-user", Constants.DEFAULT_MODEL_NAME, hocSinh);
    }

    @RequestMapping(value = "updateAction", method = RequestMethod.POST)
    public String updateUserhAction(@Validated @ModelAttribute(Constants.DEFAULT_MODEL_NAME) HocSinh hocSinh,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("action", "updateAction");
            model.addAttribute("title", "Update user");
            model.addAttribute(Constants.DEFAULT_MODEL_NAME, hocSinh);
            return "admin/create-user";
        } else {
            try {
                hocSinhService.updateHocSinh(hocSinh);
                return "redirect:/admin/QuanLyUser/list";
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("action", "updateAction");
                model.addAttribute("title", "Update user");
                model.addAttribute(Constants.DEFAULT_MODEL_NAME, hocSinh);
                model.addAttribute("error", e.getMessage());
                return "admin/create-user";
            }
        }
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String showListUser(Model model) {
        model.addAttribute("userList", hocSinhService.getAllHocSinh());
        return "admin/list-user";
    }
}
