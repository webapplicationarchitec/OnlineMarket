package miu.edu.cs545.exception;

import miu.edu.cs545.dto.Cart;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler(ProductException.class)
	public ModelAndView handleError(HttpServletRequest req, Exception exception) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("message", exception.getMessage());
		//mav.addObject("exception", exception);
		//mav.addObject("url", req.getRequestURL() + "?" + req.getQueryString());
		mav.setViewName("admin/errors");
		System.out.println("advicdecontroller");
		return mav;
	}

}
