package br.app.login.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.app.login.model.User;
import br.app.login.repositories.UserRepository;
import br.app.login.services.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class LoginController {
	
	@Autowired
	private UserRepository ur;
	
	@GetMapping("/login")
	public String login() {
		
		return "login";
		
	}

	
	@GetMapping("/")
	public String paginaPrincipal(Model model,HttpServletRequest request) throws UnsupportedEncodingException {
		model.addAttribute("nome", CookieService.getCookie(request, "userNome"));
		return "index";
		
	}
	
	@PostMapping("/logar")
	public String loginUsuario(User user, HttpServletResponse respose) throws UnsupportedEncodingException {
		
		User usuarioLogado = this.ur.login(user.getEmail(), user.getSenha());
		if(usuarioLogado != null) {
			CookieService.setCookie(respose, "userId", String.valueOf(usuarioLogado.getId()), 10000);
			CookieService.setCookie(respose, "userNome", String.valueOf(usuarioLogado.getNome()), 10000);
			return "redirect:/";
			
		}

		return "login";
		
	}
	
	
	@GetMapping("/cadastroUsuario")
	public String cadastro() {
		
		return "cadastro";
		
	}
	
	@PostMapping("/cadastroUsuario")
	public String cadastroUsuario(@Valid User user, BindingResult result) {
		
		if(result.hasErrors()) {
			
			return "redirect:/cadastroUsuario";
			
		}
		
		ur.save(user);
		
		return "redirect:/login";
		
		
	}
	
	@GetMapping("/sair")
	public String sair(HttpServletResponse response) throws UnsupportedEncodingException {
		CookieService.setCookie(response, "userId", "", 0);
		return "login";
		
	}
	
}
