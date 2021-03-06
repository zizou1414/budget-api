/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.budgetapi.domain.rest;


import com.faculte.budgetapi.domain.bean.CompteBudgitaire;
import com.faculte.budgetapi.domain.model.service.CompteBudgitaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author AMINE
 */
@RequestMapping("/budget/compte_budgitaires")
@RestController()
public class CompteBudgitaireRest {
    @Autowired
  private CompteBudgitaireService compteBudgitaireService;
    @GetMapping("/code/{code}")
    public CompteBudgitaire findByCode(@PathVariable String code) {
        return compteBudgitaireService.findByCode(code);
    }

    public void creerCompteBudgitaire(CompteBudgitaire compteBudgitaire) {
        compteBudgitaireService.creerCompteBudgitaire(compteBudgitaire);
    }
    
    public CompteBudgitaireService getCompteBudgitaireService() {
        return compteBudgitaireService;
    }

    public void setCompteBudgitaireService(CompteBudgitaireService compteBudgitaireService) {
        this.compteBudgitaireService = compteBudgitaireService;
    }
    
    
}
