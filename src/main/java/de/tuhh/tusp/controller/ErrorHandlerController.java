package de.tuhh.tusp.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@ControllerAdvice
public class ErrorHandlerController {

	@Autowired
	private RequestMappingHandlerMapping handlerMapping;
	@Autowired
	private ApplicationContext context;

	@ExceptionHandler(value = { Exception.class, RuntimeException.class })
	public String handle500(Exception ex, HttpServletRequest request, HttpServletResponse response) {

		// Gucken, ob back-Butten gehen kann
		String referrer = request.getHeader("referer");
		Set<RequestMappingInfo> rmSet = handlerMapping.getHandlerMethods().keySet();
		for (RequestMappingInfo rm : rmSet) {

			if (referrer != null && "GET".equals(rm.getMethodsCondition().toString())
					&& referrer.equals(rm.getPatternsCondition().toString())) {
				request.setAttribute("back_url", referrer);
				break;
			}
		}

		ErrorHandler errorHandler = new ErrorHandler(ex, context, request);

		request.setAttribute("title", errorHandler.getTitle());
		request.setAttribute("message", errorHandler.getMessage());
		request.setAttribute("errorHandler", errorHandler);

		response.setStatus(500);

		return "error";
	}
}
