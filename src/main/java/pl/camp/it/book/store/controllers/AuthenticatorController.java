package pl.camp.it.book.store.controllers;

import jakarta.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.camp.it.book.store.exception.UserLoginExistException;
import pl.camp.it.book.store.exception.UserValidationException;
import pl.camp.it.book.store.model.User;
import pl.camp.it.book.store.services.IAutenthicationService;
import pl.camp.it.book.store.session.SessionObject;
import pl.camp.it.book.store.validator.UserValidator;



@Controller
public class AuthenticatorController {


    @Autowired
    IAutenthicationService autenthicationService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("logged", this.sessionObject.isLogged());
        return "/login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String login, @RequestParam String password) {
        try {
            UserValidator.validateLogin(login);
            UserValidator.validatePassword(password);
    
            if(!autenthicationService.authenticate(login, password)){
                return "redirect:/login";
            }
        } catch (UserValidationException e) {
            e.printStackTrace();
            return "redirect:/login";
        }
        return "redirect:/main";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout() {
        this.autenthicationService.logout();
        return "redirect:/login";

    }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("logged", this.sessionObject.isLogged());
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute User user, @RequestParam String password2) {
        try {
            UserValidator.validateRegisterUser(user, password2);
            this.autenthicationService.registerUser(user);
        } catch (UserValidationException | UserLoginExistException e) {
            e.printStackTrace();
            return "redirect:/register";
        }
        return "redirect:/login";
    }
}
