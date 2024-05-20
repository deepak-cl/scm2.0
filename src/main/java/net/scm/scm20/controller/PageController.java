package net.scm.scm20.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PageController {

    @RequestMapping(path = "/home", method=RequestMethod.GET)  
    public String home(Model model) {

        //Sending data to view
        model.addAttribute("name", "Youtube channel");
        model.addAttribute("youtubeChannel", "Code decode");
        model.addAttribute("githubRepo", "https://github.com/deepak-cl");
        return "home";
    }

    // about page
    @RequestMapping(path = "/about", method=RequestMethod.GET)
    public String aboutPage(Model model) {
        model.addAttribute("isLogin", false);
        return "about";
    }
    
    // services page
    @RequestMapping(path = "/services", method=RequestMethod.GET)
    public String servicesPageString() {
        return "services";
    }

    //contact page 
    @RequestMapping(path = "/contact", method=RequestMethod.GET)
    public String contact() {
        return "contact";
    }

    //login page 
    @RequestMapping(path = "/login", method=RequestMethod.GET)
    public String login() {
        return "login";
    }

    //register page 
    @RequestMapping(path = "/register", method=RequestMethod.GET)
    public String register() {
        return "register";
    }
    
}
