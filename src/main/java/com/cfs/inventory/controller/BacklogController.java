package com.cfs.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cfs.inventory.model.BacklogItem;
import com.cfs.inventory.model.BacklogItemRepository;

@Controller
@RequestMapping("/backlog")
public class BacklogController {

	@Autowired
	private BacklogItemRepository backlogItemRepository;
	
	@GetMapping("")
	public ModelAndView getBacklogItems(BacklogItem backlogItem) {
		ModelAndView mav = new ModelAndView("backlog");
		mav.addObject("backlogItemList",backlogItemRepository.findAll());
		return mav;
	}
	
	@PostMapping("/save")
	public String saveBacklogItem(@ModelAttribute("backlogItem")BacklogItem backlogItem) {
		backlogItemRepository.save(backlogItem);
		return "redirect:/backlog";
	}
	
	@PostMapping("/delete")
	public String deleteBacklogItem(@RequestParam("deleteId") Long backlogItemId) {
		backlogItemRepository.deleteById(backlogItemId);
		return "redirect:/backlog";
	}
}
