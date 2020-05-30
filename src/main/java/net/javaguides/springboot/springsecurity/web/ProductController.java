package net.javaguides.springboot.springsecurity.web;

import net.javaguides.springboot.springsecurity.model.Product;
import net.javaguides.springboot.springsecurity.model.User;
import net.javaguides.springboot.springsecurity.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import net.javaguides.springboot.springsecurity.service.ProductService;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class ProductController {
	
	private final ProductService productService;
	private final UserService userService;

    public ProductController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @RequestMapping("addproduct")
    public String browseNewProductPage(@ModelAttribute("product") Product product) {
        return "new_product";
    }

    @RequestMapping("addproduct/save")
    public String saveNewProduct(@ModelAttribute("product") Product product) {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            String principalName = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.findByEmail(principalName);
            product.setUser(user);
            productService.save(product);

            return "redirect:/index";
        } else {
            return "login";
        }
    }

    @RequestMapping
    public String getProducts(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            List<Product> listProducts = productService.getByUserEmail(
                    SecurityContextHolder.getContext().getAuthentication().getName()
            );
            model.addAttribute("listProducts", listProducts);

            return "index";
        } else {
            return "login";
        }
    }

    @RequestMapping("updateProduct")
    public String updateProduct(@ModelAttribute("product") Product product) {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            String principalName = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.findByEmail(principalName);
            product.setUser(user);
            productService.save(product);
            return "redirect:/index";
        } else {
            return "login";
        }
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_product");
        Product product = productService.get(id);
        mav.addObject("product", product);

        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        productService.delete(id);
        return "redirect:/index";
    }
}
