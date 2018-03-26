package com.pizza.crm.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditNomenclature {

    @GetMapping("editNomenclature")
    public String editNomenclature() {
        return "admin/editNomenclature";
    }
}
