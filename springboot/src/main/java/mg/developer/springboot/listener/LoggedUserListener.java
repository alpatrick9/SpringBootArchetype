package mg.developer.springboot.listener;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LoggedUserListener implements AuthenticationSuccessHandler {

	@Autowired
	HttpSession session;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse rep, Authentication auth)
			throws IOException, ServletException {
		
	    session.setAttribute("email", auth.getName());
	    
	    rep.sendRedirect(req.getContextPath()+"/secure/");
	}

}
