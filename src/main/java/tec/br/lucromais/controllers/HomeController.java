package tec.br.lucromais.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Classe controller da página inicial
 * @author Thiago Pinheiro do Nascimento
 * @version 0.1
 * @since 0.1
**/
@Controller
@RequestMapping("/")
public class HomeController {
	
	/**
	 * Action get da página inicial
	 * @return página inicial
	**/
	@GetMapping
	public ModelAndView indexPage() {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("menu", "home");
		return mv;
	}
}