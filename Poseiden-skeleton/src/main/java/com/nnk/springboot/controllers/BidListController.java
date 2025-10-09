package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bidList")
public class BidListController {

    @Autowired
    private BidListService bidListService;

    /**
     * Affiche la liste des BidList.
     */
    @GetMapping("/list")
    public String home(Model model) {
        model.addAttribute("bidLists", bidListService.findAll());
        return "bidList/list";
    }

    /**
     * Formulaire d’ajout.
     */
    @GetMapping("/add")
    public String addBidForm(Model model) {
        model.addAttribute("bidList", new BidList());
        return "bidList/add";
    }

    /**
     * Validation et sauvegarde d’un nouveau BidList.
     */
    @PostMapping("/validate")
    public String validate(@Valid @ModelAttribute("bidList") BidList bidList,
                           BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "bidList/add";
        }
        bidListService.save(bidList);
        return "redirect:/bidList/list";
    }

    /**
     * Affiche le formulaire de mise à jour.
     */
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        BidList bidList = bidListService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid BidList Id:" + id));
        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    /**
     * Sauvegarde de la mise à jour.
     */
    @PostMapping("/update/{id}")
    public String updateBid(@PathVariable("id") Integer id,
                            @Valid @ModelAttribute("bidList") BidList bidList,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            bidList.setBidListId(id);
            return "bidList/update";
        }
        bidListService.update(bidList);
        return "redirect:/bidList/list";
    }

    /**
     * Suppression d’un BidList.
     */
    @GetMapping("/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id) {
        bidListService.deleteById(id);
        return "redirect:/bidList/list";
    }
}
