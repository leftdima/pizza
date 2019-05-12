package com.baranchik.controller;

import com.baranchik.facade.ManagerFacade;
import com.baranchik.model.OrderClient;
import com.baranchik.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes({"orders","orderClient","pizzerias"})
public class ManagerController {

    @Autowired
    private ManagerFacade managerFacade;

    @RequestMapping(value = "/management", method = RequestMethod.GET)
    public String management(Model model) {
        model.addAttribute("orders", managerFacade.getOrders());
        return "manage";
    }

    @RequestMapping(value = "/process/{id}", method = RequestMethod.GET)
    public String processed(@PathVariable("id") Integer id, Model m) {
        m.addAttribute("orderClient", managerFacade.getOrder(id));
        m.addAttribute("pizzerias", managerFacade.getListPizzeria());
        return "processing";
    }

    @RequestMapping(value = "/processorder/{id}", method = RequestMethod.POST)
    public String processsing(@ModelAttribute("orderClient") OrderClient orderClient, @PathVariable("id") Integer pizzeriaId, Model m) {
        orderClient.setPizzeria(managerFacade.getPizzeria(pizzeriaId));
        managerFacade.sendMailAndProcessOrder(orderClient.getId());
        return "redirect:/management";
    }


    @RequestMapping(value = "/delivery/{id}", method = RequestMethod.GET)
    public String delivery(@PathVariable("id") Integer id) {
        managerFacade.deliveryOrder(id);
        return "redirect:/management";
    }

}
