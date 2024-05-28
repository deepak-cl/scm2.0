package net.scm.scm20.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import net.scm.scm20.entity.User;
import net.scm.scm20.forms.UserForm;
import net.scm.scm20.services.UserService;
import net.scm.scm20.util.Message;
import net.scm.scm20.util.MessageType;


@Controller
public class PageController {

    @Autowired
    UserService userService;

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
    public String register(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }

        // Processing register
    @RequestMapping(path = "/do-register", method=RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session) {
        System.out.println("Processing registeration");
        // Fetch the form data
        // UserForm
        System.out.println(userForm);

        // Validate the form data
        if(rBindingResult.hasErrors()) {
            return "register";
        }
        
        // Save to database 
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("https://tinyurl.com/5am9j5ps");

        User savedUser = userService.saveUser(user);

        System.out.println("User saved: " + savedUser);
        // Message = "Registration successfull"

        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();

        session.setAttribute("message", message);

        // Redirect to login page
        return "redirect:/register";
    }
    
}
