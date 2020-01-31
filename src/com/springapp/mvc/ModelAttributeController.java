package com.springapp.mvc;

import com.springapp.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: Minsik Jin
 * Date: 2014-03-27
 */
@Controller
public class ModelAttributeController {
    @Autowired
    UserDao userDao;

    @RequestMapping("/modelAttribute1")
    public String testModelAttribute1(User user, ModelMap map) {

        map.addAttribute("firstName", user.getFirstName());
        map.addAttribute(user);

        userDao.doQuery2("safe");

        return "modelAttribute1";
    }

}
