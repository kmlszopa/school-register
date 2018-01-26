package pl.kamilszopa.controller;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.view.RedirectView;

import pl.kamilszopa.model.repository.AdultRepository;

@Controller
public class HomeController {

	@Autowired
	private AdultRepository adultRepository;

	@RequestMapping(value = { "/", "/login" })
	public String index() {
		return "login";
	}

	@RequestMapping(value = "/loginSucces")
	public RedirectView checkLogin(ModelMap modelMap) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User principal = (User) authentication.getPrincipal();
		Collection<GrantedAuthority> authorities = principal.getAuthorities();
		for (Iterator<GrantedAuthority> iterator = authorities.iterator(); iterator.hasNext();) {
			GrantedAuthority grantedAuthority = (GrantedAuthority) iterator.next();
			if (grantedAuthority.getAuthority().equals("PARENT")) {
				modelMap.addAttribute("emailAddres", principal.getUsername());
				return new RedirectView("/getStudent");
			}
		}
		modelMap.addAttribute("emailAddres", principal.getUsername());
		return new RedirectView("/teacherPickStudent");

	}
	

}
