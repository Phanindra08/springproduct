package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

	@Autowired
	private ProductService service;
	
	@Autowired
	private BillingService bservice;

    
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Product> listProducts = service.listAll();
		model.addAttribute("listProducts", listProducts);
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewProductPage(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "new_product";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
		service.save(product);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/saveproduct", method = RequestMethod.POST)
	public String saveNewProduct(@ModelAttribute("product") Product product,Model model) {
		Product p=service.findCode(product.getCode());
		if(p==null)
		{
			service.save(product);
			return "redirect:/";
		}
		else
		{
			model.addAttribute("message","Product Code already exists");
			return "new_product";
		}
	}
	
	@RequestMapping("/edit/{code}")
	public ModelAndView showEditProductPage(@PathVariable(name = "code") String code) {
		ModelAndView mav = new ModelAndView("edit_product");
		Product product = service.get(code);
		mav.addObject("product", product);
		return mav;
	}
	
	@RequestMapping("/delete/{code}")
	public String deleteProduct(@PathVariable(name = "code") String code) {
		service.delete(code);
		return "redirect:/";		
	}
	
	@RequestMapping(value = "/searchproduct", method = RequestMethod.GET)
	public ModelAndView search(@RequestParam(value = "search", required = false) String q, Model model) 
	{
		ModelAndView mav = new ModelAndView("billing");
		Product product=service.find(q);
		if(product!=null)
		{
			Billing billing=new Billing(product.getCode(),product.getName(),
					product.getGst(),product.getPrice(),1);
			mav.addObject("billing", billing);
			model.addAttribute("search", billing);
			List<Billing> listBilling = bservice.listAll();
			model.addAttribute("listBillings", listBilling);
			float total=bservice.calculate();
			model.addAttribute("total",total);
		}
		else
		{
			Billing billing=null;
			mav.addObject("billing", billing);
			model.addAttribute("search", billing);
			List<Billing> listBilling = bservice.listAll();
			model.addAttribute("listBillings", listBilling);
			float total=bservice.calculate();
			model.addAttribute("total",total);
		}
		return mav;
	}
	
	@RequestMapping("/indextosearch")
	public String showSearchPage(Model model) {
		List<Billing> listBilling = bservice.listAll();
		model.addAttribute("listBillings", listBilling);
		float total=bservice.calculate();
		model.addAttribute("total",total);
		return "billing";
	}
	
	@RequestMapping(value="/addbilling", method = RequestMethod.POST)
	public String addBilling(@ModelAttribute("billing") Billing billing) {
		Billing b=bservice.findCode(billing.getBcode());
		int q=0;
		if(b!=null)
		{
			q=b.getBquantity();
			q+=billing.getBquantity();
			billing.setBquantity(q);
		}
		bservice.save(billing);
		return "redirect:/indextosearch";
	}

    @RequestMapping(value="/editaddedbilling", method = RequestMethod.POST)
	public String editBilling(@ModelAttribute("billing") Billing billing) {
		bservice.save(billing);
		return "redirect:/indextosearch";
	}
	
	@RequestMapping("/editbilling/{code}")
	public ModelAndView showEditBillingPage(@PathVariable(name = "code") String code) {
		ModelAndView mav = new ModelAndView("edit_billing");
		Billing billing = bservice.get(code);
		mav.addObject("billing", billing);
		
		return mav;
	}
	
	@RequestMapping("/deletebilling/{code}")
	public String deleteBilling(@PathVariable(name = "code") String code) {
		bservice.delete(code);
		return "redirect:/indextosearch";		
	}
	
}
