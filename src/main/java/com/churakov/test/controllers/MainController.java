package com.churakov.test.controllers;

import com.churakov.test.dao.Filter;
import com.churakov.test.dao.PageHelper;
import com.churakov.test.model.Part;
import com.churakov.test.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

@Controller
public class MainController {

    @Autowired
    private PartService partService;

    @Autowired
    @Qualifier("findByNameValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @GetMapping("/list")
    public String list(@RequestParam(value = "page", defaultValue = "1", required = false) int p,
                       @RequestParam(value = "filter", defaultValue = "all", required = false) Filter f,
                       @RequestParam(value = "name", defaultValue = "", required = false) String n,
                       @ModelAttribute("part") @Validated Part part,
                       BindingResult bindingResult,
                       ModelMap model){

        model.addAttribute("part", part);
        model.addAttribute("page", p);
        model.addAttribute("filter", f);
        model.addAttribute("name", n);
        model.addAttribute("computersCount", this.partService.getComputersCount());

        if (!bindingResult.hasErrors()){

            PageHelper<Part> partPageHelper = this.partService.listParts(p, f, UriUtils.decode(n, "UTF8"));

            model.addAttribute("listParts", partPageHelper.getPage());

            model.addAttribute("startpage", 1);
            model.addAttribute("endpage", partPageHelper.getTotalPages());

        }
        return "hello";
    }

    @PostMapping("/list")
    public String listPost(@RequestParam(value = "filter", defaultValue = "all", required = false) Filter f,
                           @ModelAttribute("part") Part part,
                           BindingResult bindingResult,
                           ModelMap model){
        return String.format("redirect:/list?filter=%s&name=%s", f, UriUtils.encode(part.getName(), "UTF8"));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(ModelMap model) {

        return "redirect:/list";

//        model.addAttribute("findPart", new Part());
//        model.addAttribute("part", new Part());
//        model.addAttribute("listParts", this.partService.listParts());
//        model.addAttribute("computersCount", this.partService.getComputersCount());
//
//        model.addAttribute("startpage", 1);
//        model.addAttribute("endpage", 5);
//
//        return "hello";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("part") Part p){

        if(p.getId() == 0){
            this.partService.addPart(p);
        }else{
            this.partService.updatePart(p);
        }

        return "redirect:/";

    }

    @RequestMapping("/remove/{id}")
    public String remove(@PathVariable("id") int id){
        this.partService.removePart(id);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id,
                       ModelMap model){
        model.addAttribute("part", this.partService.getPartById(id));
        return "part";
    }

    @GetMapping("/addPart")
    public String addPart(ModelMap model){
        model.addAttribute("part", new Part());
        return "part";
    }

}
