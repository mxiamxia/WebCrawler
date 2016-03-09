package com.min.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.min.log.Log;
import com.min.model.Item;
import com.min.queue.QueueUtils;
import com.min.service.ItemService;
import com.min.validation.ItemFormValidator;

@Controller
public class MainController {

	@Autowired
	ItemService itemService;
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	@Autowired
	private	QueueUtils queue;
	public void setQueue(QueueUtils queue) {
		this.queue = queue;
	}
	
	@Autowired
	ItemFormValidator itemFormValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(itemFormValidator);
	}
	
	@Autowired
	private ApplicationContext appContext;
	public void setApplicationContext(ApplicationContext appContext) {
		this.appContext = appContext;
	}
	
	@RequestMapping(value = "/index.mx", method = RequestMethod.GET)
	public String mainEntry(HttpServletRequest request, HttpServletResponse response, Model model) {
		Log.info("showAllUsers()");
		List<Item> list = new ArrayList<Item>();
//		model.addAttribute("items", itemService.findAll());
		refreshQueue();
		list.addAll(this.queue.getRequestQueue());
		model.addAttribute("items", list);
		model.addAttribute("status", "ready");
		return "home";
	}

	@RequestMapping(value = "/item/add.mx", method = RequestMethod.GET)
	public String showAddItemForm(Model model) {
		Log.info("showAddItemForm()");
		Item item = new Item();
		model.addAttribute("exist", false);
		model.addAttribute("itemForm", item);
		return "itemForm";
	}

	// save or update user
	@RequestMapping(value = "/items.mx", method = RequestMethod.POST)
	public String saveOrUpdateUser(@ModelAttribute("itemForm") @Validated Item item,
			BindingResult result, Model model) {

		Log.info("saveOrUpdateUser() : {}");

		if (result.hasErrors()) {
			model.addAttribute("exist", false);
			model.addAttribute("itemForm", item);
			return "itemForm";
		} else {
			model.addAttribute("css", "success");
//			redirectAttributes.addFlashAttribute("css", "success");
			if(itemService.findById(item.getItem_id()) == null){
				item.setStatus(false);
				itemService.save(item);
				model.addAttribute("msg", "User added successfully!");
			}else{
				itemService.update(item);
				model.addAttribute("msg", "User updated successfully!");
			}
			refreshQueue();
			return "redirect:/item/" + item.getItem_id() +".mx";
		}
	}
	@RequestMapping(value = "/item/{id}.mx", method = RequestMethod.GET)
	public String showItem(@PathVariable("id") String id, Model model, HttpServletRequest request) {
		Log.info("showUser() id: {}");
		
		System.out.println("=== Request data ===");
		if(request.getParameter("css") != null) {
			model.addAttribute("css", request.getParameter("css"));
		}
		if(request.getParameter("msg") != null ) {
			model.addAttribute("msg", request.getParameter("msg"));
		}
		
		Item item = itemService.findById(id);
		if (item == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Item not found");
		}
		model.addAttribute("item", item);

		return "show";
	}
	
	@RequestMapping(value = "/item/{id}/update.mx", method = RequestMethod.GET)
	public String itemUpdate(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response, Model model) {
		Log.info("itemUpdate()");
		Item item = itemService.findById(id);
		if(item != null) {
			model.addAttribute("exist", true);
			model.addAttribute("itemForm", item);
			return "itemForm";
		}
		refreshQueue();
		List<Item> list = new ArrayList<Item>();
		list.addAll(this.queue.getRequestQueue());
		model.addAttribute("items", list);
//		model.addAttribute("items", itemService.findAll());
		model.addAttribute("status", "ready");
		return "home";
	}
	
	@RequestMapping(value = "/item/{id}/delete.mx", method = RequestMethod.GET)
	public String itemDelete(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response, Model model) {
		Log.info("itemDelete()");
		itemService.delete(id);
//		model.addAttribute("items", itemService.findAll());
		refreshQueue();
		List<Item> list = new ArrayList<Item>();
		list.addAll(this.queue.getRequestQueue());
		model.addAttribute("items", list);
		model.addAttribute("status", "ready");
		return "home";
	}
	
	@RequestMapping(value = "/item/query.mx", method = RequestMethod.GET)
	public String itemQuery(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response, Model model) {
		Log.info("itemDelete()");
		itemService.delete(id);
		model.addAttribute("items", itemService.findAll());
		model.addAttribute("status", "ready");
		return "home";
	}
	
	private void refreshQueue() {
		List<Item> list = new ArrayList<Item>();
		list = itemService.findAll();
		this.queue.getRequestQueue().clear();
		this.queue.getRequestQueue().addAll(list);
	}
}
