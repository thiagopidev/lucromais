package tec.br.lucromais.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import tec.br.lucromais.models.Client;
import tec.br.lucromais.models.ClientType;
import tec.br.lucromais.repositories.ClientRepository;
import tec.br.lucromais.services.ClientService;

@Controller
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping
	public ModelAndView search() {
		ModelAndView mv = new ModelAndView("clients/search");
		mv.addObject("clients", clientRepository.findAll());
		mv.addObject("menu", "administration");
		return mv;
	}
	
	@GetMapping("/create")
	public ModelAndView create(Client client) {
		ModelAndView mv = new ModelAndView("clients/create");
		mv.addObject("clientTypes", ClientType.values());
		mv.addObject("menu", "administration");
		return mv;
	}
	
	@PostMapping("/create")
	public ModelAndView create(@Valid Client client, BindingResult result) {
		if(result.hasErrors())
			return create(client);
		clientService.persist(client);
		return new ModelAndView("redirect:/clients");
	}
	
	@GetMapping("/{id}/update")
	public ModelAndView update(@PathVariable("id") Long id, Client client, boolean isInvalid) {
		ModelAndView mv = new ModelAndView("clients/update");
		if(!isInvalid)
			client = clientService.findOrFail(id);
		mv.addObject("client", client);
		mv.addObject("clientTypes", ClientType.values());
		mv.addObject("menu", "administration");
		return mv;
	}
	
	@PostMapping("/update")
	public ModelAndView update(@Valid Client client, BindingResult result) {
		if(result.hasErrors())
			return update(client.getId(), client, true);
		return new ModelAndView("redirect:/clients");
	}
}