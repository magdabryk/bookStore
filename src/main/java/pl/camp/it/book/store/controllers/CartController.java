package pl.camp.it.book.store.controllers;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.book.store.exception.NotEnoughBookException;
import pl.camp.it.book.store.services.ICartService;
import pl.camp.it.book.store.session.SessionObject;

@Controller
public class CartController {

    @Resource
    SessionObject sessionObject;
    @Autowired
    ICartService cartService;

    @RequestMapping(path="/cart/add/{bookId}", method = RequestMethod.GET)
    public String addBookToCart(@PathVariable int bookId){
        try {
            this.cartService.addBookToCart(bookId);
        } catch (NotEnoughBookException e) {
            this.sessionObject.setInfo("niepoprawna ilość produków");
            return "redirect:/";
        }
        return "redirect:/";
    }

    @RequestMapping(path="/cart", method = RequestMethod.GET)
    public String cart(Model model){
        model.addAttribute("sessionObject", this.sessionObject);
        model.addAttribute("cart", this.cartService.getCart());
        model.addAttribute("sum", this.cartService.calculateCartSum());
        return "cart";
    }

    @RequestMapping(path="cart/remove/{bookId}", method = RequestMethod.GET)
    public String removeBookFromCat(@PathVariable int bookId){
        this.cartService.removeFromCart(bookId);
        return "redirect:/cart";
    }

}
