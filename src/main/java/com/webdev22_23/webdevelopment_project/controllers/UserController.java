
package com.webdev22_23.webdevelopment_project.controllers;

import com.webdev22_23.webdevelopment_project.database.ConnectionManager;
import com.webdev22_23.webdevelopment_project.database.dao.interfaces.UserDao;
import com.webdev22_23.webdevelopment_project.database.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @GetMapping("/login")
    public ModelAndView getLoginScreen(){
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        UserDao userDao = ConnectionManager.getInstance().getUserDao();
        User user = userDao.getByPrimaryKey(username);

        ModelAndView modelAndView;
        if (password.equals(user.getPassword())) {
            session.setAttribute("user", user);
            modelAndView = new ModelAndView("index");
        } else {
            modelAndView = new ModelAndView("wrongPassword");

        }

        return modelAndView;
    }
}