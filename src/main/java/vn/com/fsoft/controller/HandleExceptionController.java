package vn.com.fsoft.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * <b>HandleExceptionController</b>.
 *
 * <p>Version 1.0</p>
 *
 * <p>Date: 12-11-2018</p>
 *
 * <p>Copyright</p>
 *
 * <p>Modification Logs:</p>
 * <p>DATE             AUTHOR      DESCRIPTION</p>
 * ----------------------------------------
 * <p>12-11-2018       ABC123      Create</p>
 */
@Controller
public class HandleExceptionController {

    /**
     * Handle redirect Error Page.
     * @param httpRequest : HttpServletRequest
     * @return ModelAndView
     */
    @RequestMapping(value = "/errorPage", method = RequestMethod.GET)
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest, Model model) {
        ModelAndView errorPage = new ModelAndView("error-page");
        String errorCode = "";
        String errorMsg = "";
        int httpErrorCode = getErrorCode(httpRequest);
        switch (httpErrorCode) {
            case 400: {
                errorCode = "400";
                errorMsg = "Bad Request.";
                break;
            }
            case 401: {
                errorCode = "401";
                errorMsg = "Unauthorized.";
                break;
            }
            case 404: {
                errorCode = "404";
                errorMsg = "Page not found.";
                break;
            }
            case 500: {
                errorCode = "500";
                errorMsg = "Internal Server Error.";
                break;
            }
            default: {
                errorCode = "";
                errorMsg = "Oops! Something went wrong.";
                break;
            }
        }
        errorPage.addObject("errorMsg", errorMsg);
        errorPage.addObject("errorCode", errorCode);
        model.addAttribute("title", "Error page");
        return errorPage;
    }

    /**
     * Get error code from request.
     * @param httpRequest : HttpServletRequest
     * @return int
     */
    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
    }
}
