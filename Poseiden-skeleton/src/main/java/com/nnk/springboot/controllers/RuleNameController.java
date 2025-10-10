package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
//import javax.validation.Valid;
import jakarta.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RuleNameController {
    // TODO: Inject RuleName service
    @Autowired
    private RuleNameService ruleNameService;

    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        // TODO: find all RuleName, add to model
        model.addAttribute("ruleNames", ruleNameService.getAllRuleNames());
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid @ModelAttribute("ruleName") RuleName ruleName, BindingResult result,
                           Model model, RedirectAttributes ra) {
        // TODO: check data valid and save to db, after saving return RuleName list
        if (!result.hasErrors()) {
            ruleNameService.saveRuleName(ruleName);
            ra.addFlashAttribute("successSaveMessage", "Your rule name was successfully added");
            model.addAttribute("ruleName", ruleNameService.getAllRuleNames());
           return "redirect:/ruleName/list";
        }
       return "ruleName/add";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get RuleName by Id and to model then show to the form
        RuleName ruleName = ruleNameService.getRuleNameById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid RuleName Id:" + id));
        model.addAttribute("ruleName", ruleName);
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update RuleName and return RuleName list
        if (result.hasErrors()) {
            ruleName.setId(id);
            return "ruleName/update";
        }
        ruleNameService.saveRuleName(ruleName);
        model.addAttribute("ruleNames", ruleNameService.getAllRuleNames());
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        // TODO: Find RuleName by Id and delete the RuleName, return to Rule list
        RuleName ruleName = ruleNameService.getRuleNameById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid RuleName Id:" + id));
        ruleNameService.deleteRuleName(ruleName.getId());
        model.addAttribute("ruleNames", ruleNameService.getAllRuleNames());
        return "redirect:/ruleName/list";
    }
}
