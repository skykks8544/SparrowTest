package com.springapp.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
	@Autowired
	UserDao userDao;

	/**
	 * 1. get ModelMap as an parameter<br/>
	 * 2. return view string<br/>
	 * 
	 * @param name
	 * @param age
	 * @param message
	 * @param message2
	 * @param map
	 * @return
	 */
	@RequestMapping("/hello")
	public String testModel(@RequestParam("name") String name, @RequestParam("age") int age, @RequestParam("message") String message, @RequestParam("message2") String message2, ModelMap map) {

		userDao.doQuery(name);

		map.addAttribute("message", message);
		map.addAttribute("message2", message2);

		return "hello";
	}
}