package ru.nechay.zuzextest.testwork.controllers;

import java.util.Comparator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.nechay.zuzextest.testwork.business.PageParser;
import ru.nechay.zuzextest.testwork.models.ChildPage;
import ru.nechay.zuzextest.testwork.models.MainPage;
import ru.nechay.zuzextest.testwork.service.ChildPageService;
import ru.nechay.zuzextest.testwork.service.MainPageService;

@Controller
@RequestMapping("/page-app")
public class PagesController {
	
	private final int COUNT_OF_THREADS = 5;
	
	private final MainPageService mainPageService;
	private final ChildPageService childPageService;
	
	@Autowired
	public PagesController(MainPageService mainPageService, ChildPageService childPageService) {
		this.mainPageService = mainPageService;
		this.childPageService = childPageService;
	}
	
	@GetMapping()
	public String index(Model model) {
		model.addAttribute("page", new MainPage());
		return "page-app/index";
	}
	
	@PostMapping()
	public String create(@ModelAttribute("page") @Valid MainPage mainPage,
											BindingResult bindingResult) {
		PageParser pp = new PageParser();
		if (bindingResult.hasErrors()) {
			 return "page-app/index";
		}
		mainPageService.addMainPage(mainPage);
		System.out.println(mainPage);
		pp.getChildPages(mainPage, COUNT_OF_THREADS)
				.forEach(child -> childPageService.addChildPage(child));
		return "redirect:/page-app";
	}
	
	@GetMapping("/show")
	public String showAll(Model model) {
		model.addAttribute("pages", mainPageService.findAllMainPages());
		return "page-app/show";
	}
	
	
	@GetMapping("/childs/{id}")
	public String showConcrete(@PathVariable("id") int id,
									Model model) {
		
		List<ChildPage> list =childPageService.findChildPageByMainPageId(id);
		ChildPage pageMinCount = list	
									.stream()
									.min( Comparator.comparing( ChildPage::getCount ))
									.orElseThrow();
		ChildPage pageMaxCount = list
									.stream()
									.max( Comparator.comparing(ChildPage::getCount) )
									.orElseThrow();
		
		model.addAttribute("mincount", pageMinCount.getCount());
		model.addAttribute("maxcount", pageMaxCount.getCount());
		model.addAttribute("childs",list);
		model.addAttribute("urlOfMainPage",mainPageService
											.findMainPageById(id)
											.getSelfUrl());
		return "page-app/childs";
	}
	
}
