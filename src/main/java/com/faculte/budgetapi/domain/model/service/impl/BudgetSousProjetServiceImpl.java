/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.budgetapi.domain.model.service.impl;


import com.faculte.budgetapi.domain.bean.BudgetFaculte;
import com.faculte.budgetapi.domain.bean.BudgetSousProjet;
import com.faculte.budgetapi.domain.model.dao.BudgetSousProjetDao;
import com.faculte.budgetapi.domain.model.service.BudgetEntiteAdministratifService;
import com.faculte.budgetapi.domain.model.service.BudgetFaculteService;
import com.faculte.budgetapi.domain.model.service.BudgetSousProjetService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AMINE
 */
@Service
public class BudgetSousProjetServiceImpl implements BudgetSousProjetService {

    @Autowired
    private BudgetSousProjetService budgetSousProjetService;

    @Autowired
    private BudgetSousProjetDao budgetSousProjetDao;

    @Autowired
    private BudgetFaculteService budgetFaculteService;
    @Autowired
    private BudgetEntiteAdministratifService budgetEntiteAdministratifService;

    public BudgetEntiteAdministratifService getBudgetEntiteAdministratifService() {
        return budgetEntiteAdministratifService;
    }

    public void setBudgetEntiteAdministratifService(BudgetEntiteAdministratifService budgetEntiteAdministratifService) {
        this.budgetEntiteAdministratifService = budgetEntiteAdministratifService;
    }

   
    public BudgetFaculteService getBudgetFaculteService() {
        return budgetFaculteService;
    }

    public void setBudgetFaculteService(BudgetFaculteService budgetFaculteService) {
        this.budgetFaculteService = budgetFaculteService;
    }

//    public void setBudgetEntiteAdministratifService(BudgetEntiteAdministratifService budgetEntiteAdministratifService) {
//        this.budgetEntiteAdministratifService = budgetEntiteAdministratifService;
//    }
    public BudgetSousProjetService getBudgetSousProjetService() {
        return budgetSousProjetService;
    }

    public void setBudgetSousProjetService(BudgetSousProjetService budgetSousProjetService) {
        this.budgetSousProjetService = budgetSousProjetService;
    }

    public BudgetSousProjetDao getBudgetSousProjetDao() {
        return budgetSousProjetDao;
    }

    public void setBudgetSousProjetDao(BudgetSousProjetDao budgetSousProjetDao) {
        this.budgetSousProjetDao = budgetSousProjetDao;
    }

   

    @Override
    public List<BudgetSousProjet> findByBudgetFaculteAnnee(int annee) {
        return budgetSousProjetDao.findByBudgetFaculteAnnee(annee);
    }

    @Override
    public int creerBudgetSousProjet(BudgetSousProjet budgetSousProjet) {
           BudgetFaculte bf = budgetFaculteService.findByAnnee(budgetSousProjet.getBudgetFaculte().getAnnee());
           BudgetSousProjet bsp = findByReferenceSousProjetAndBudgetFaculteAnnee (budgetSousProjet.getReferenceSousProjet(), budgetSousProjet.getBudgetFaculte().getAnnee());
        if (bf == null) {
            return -1;
        }
        else if (bsp != null){
            return  -2;
        }else {
//        List<BudgetSousProjet> budgetSousProjets = findByBudgetFaculteAnnee(budgetSousProjet.getBudgetFaculte().getAnnee());
//        for (BudgetSousProjet bsp : budgetSousProjets) {
//             if (bsp.getReferenceSousProjet().equals(budgetSousProjet.getReferenceSousProjet())) {
//            return -2;
//        }
//        }

        bf.setDetaillesBudget(bf.getDetaillesBudget());
        double restEstimatif = bf.getDetaillesBudget().getReliquatEstimatif() - budgetSousProjet.getDetaillesBudget().getCreditOuvertEstimatif();
        double restReel = bf.getDetaillesBudget().getReliquatReel() - budgetSousProjet.getDetaillesBudget().getCreditOuvertReel();
        if (restEstimatif < 0) {
            return -3;
        } else if (restReel < 0) {
            return -4;
        } else {
            bsp = new BudgetSousProjet();
            bsp.setDetaillesBudget(budgetSousProjet.getDetaillesBudget());
            bsp.setReferenceSousProjet(budgetSousProjet.getReferenceSousProjet());
            bsp.getDetaillesBudget().setReliquatEstimatif(budgetSousProjet.getDetaillesBudget().getCreditOuvertEstimatif());
            bsp.getDetaillesBudget().setCreditOuvertEstimatif(budgetSousProjet.getDetaillesBudget().getCreditOuvertEstimatif());
            bsp.getDetaillesBudget().setReliquatReel(budgetSousProjet.getDetaillesBudget().getCreditOuvertReel());
            bsp.getDetaillesBudget().setCreditOuvertReel(budgetSousProjet.getDetaillesBudget().getCreditOuvertReel());
            bsp.getDetaillesBudget().setEngagePaye(budgetSousProjet.getDetaillesBudget().getEngagePaye());
            bsp.getDetaillesBudget().setEngageNonPaye(budgetSousProjet.getDetaillesBudget().getEngageNonPaye());
            bsp.setBudgetFaculte(bf);
            bf.getDetaillesBudget().setReliquatEstimatif(restEstimatif);
            bf.getDetaillesBudget().setReliquatReel(restReel);
            budgetFaculteService.updateReliquatBf(bf);
            budgetSousProjetDao.save(bsp);
            return 1;
        }
        }
    }

    @Override
    public void updateReliquatBsp(BudgetSousProjet budgetSousProjet) {
        budgetSousProjetDao.save(budgetSousProjet);
    }

    @Override
    public void deleteBudgetFaculte(int annee) {
        List<BudgetSousProjet> budgetSousProjets = findByBudgetFaculteAnnee(annee);
        for (BudgetSousProjet budgetSousProjet : budgetSousProjets) {
            budgetEntiteAdministratifService.deleteBudgetSousProjet(budgetSousProjet.getReferenceSousProjet(), annee);
        }
        BudgetFaculte budgetFaculte = budgetFaculteService.findByAnnee(annee);
        budgetFaculteService.deleteBudgetFaculte(budgetFaculte);
    }

    @Override
    public void deleteBudgetSousProjet(BudgetSousProjet budgetSousProjet) {
        budgetSousProjetDao.delete(budgetSousProjet);
    }

    @Override
    public BudgetSousProjet findByReferenceSousProjetAndBudgetFaculteAnnee (String referenceSousProjet, int annee) {
       return budgetSousProjetDao.findByReferenceSousProjetAndBudgetFaculteAnnee (referenceSousProjet, annee);
    }
}
