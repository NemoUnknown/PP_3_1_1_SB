package ru.javamentor.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.springboot.model.User;
import ru.javamentor.springboot.service.UserService;


@Controller
@RequestMapping("/")
public class UserController {

	@GetMapping
	public String show(Model model) {
		return getUsersView(model);
	}

	@PostMapping
	public String postShow(Model model) {
		return getUsersView(model);
	}

	private String getUsersView(Model model) {
		model.addAttribute("message", "Список пользователей");
		model.addAttribute("url", "/users");
		return "index";
	}
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
		userService.addUsers();
	}

	@GetMapping("users/add")
	public String createForm(@ModelAttribute("user") User user) {
		return "add";
	}

	@PostMapping ("users")
	public String create(@ModelAttribute("user") User user) {
		userService.add(user);
		return "redirect:users";
	}

	@GetMapping("/users")
	public String index(Model model) {
		model.addAttribute("users", userService.getList());
		return "all";
	}

	@GetMapping("users/{id}")
	public String read(Model model, @PathVariable(name = "id") long id) {
		model.addAttribute("user", userService.findById(id));
		return "find";
	}

	@GetMapping("users/{id}/change")
	public String changeForm(Model model, @PathVariable(name = "id") long id) {
		model.addAttribute("user", userService.findById(id));
		return "change";
	}

	@PatchMapping("users/{id}")
	public String change(@ModelAttribute("user") User user, @PathVariable("id") long id) {
		userService.change(id, user);
		return "redirect:/users";
	}

	@DeleteMapping("users/{id}")
	public String delete(@PathVariable("id") long id) {
		userService.delete(id);
		return "redirect:/users";
	}

}