/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.budgetapi.domain.model.service.impl;

import com.faculte.budgetapi.domain.bean.BudgetEntiteAdministratif;
import com.faculte.budgetapi.domain.bean.BudgetFaculte;
import com.faculte.budgetapi.domain.bean.BudgetSousProjet;
import com.faculte.budgetapi.domain.model.dao.BudgetEntiteAdministratifDao;
import com.faculte.budgetapi.domain.model.service.BudgetCompteBudgitaireService;
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
public class BudgetEntiteAdministratifServiceImpl implements BudgetEntiteAdministratifService {

    @Autowired
    private BudgetEntiteAdministratifService budgetEntiteAdministratifService;
    @Autowired
    private BudgetEntiteAdministratifDao budgetEntiteAdministratifDao;
    @Autowired
    private BudgetCompteBudgitaireService budgetCompteBudgitaireService;
    @Autowired
    private BudgetSousProjetService budgetSousProjetService;
    @Autowired
    private BudgetFaculteService budgetFaculteService;

    public BudgetSousProjetService getBudgetSousProjetService() {
        return budgetSousProjetService;
    }

    public void setBudgetSousProjetService(BudgetSousProjetService budgetSousProjetService) {
        this.budgetSousProjetService = budgetSousProjetService;
    }

    public void setBudgetCompteBudgitaireService(BudgetCompteBudgitaireService budgetCompteBudgitaireService) {
        this.budgetCompteBudgitaireService = budgetCompteBudgitaireService;
    }

    public void setBudgetEntiteAdministratifService(BudgetEntiteAdministratifService budgetEntiteAdministratifService) {
        this.budgetEntiteAdministratifService = budgetEntiteAdministratifService;
    }

    public void setBudgetEntiteAdministratifDao(BudgetEntiteAdministratifDao budgetEntiteAdministratifDao) {
        this.budgetEntiteAdministratifDao = budgetEntiteAdministratifDao;
    }

    @Override
    public BudgetEntiteAdministratif findByReferenceEntiteAdministratifAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(String referenceEntiteAdministratif, String referenceSousProjet, int annee) {
        return budgetEntiteAdministratifDao.findByReferenceEntiteAdministratifAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(referenceEntiteAdministratif, referenceSousProjet, annee);
    }

    @Override
 public List<BudgetEntiteAdministratif> findByBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(String referenceSousProjet , int annee)
{
        return budgetEntiteAdministratifDao.findByBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee( referenceSousProjet ,annee);
    }

//    @Override
//    public int creerBudgetEntiteAdministratif(BudgetEntiteAdministratif budgetEntiteAdministratif) {
//        BudgetFaculte bf = budgetFaculteService.findByAnnee(budgetEntiteAdministratif.getBudgetSousProjet().getBudgetFaculte().getAnnee());
//        if (bf == null) {
//            return -1;
//        }
//        int res = 0;
//        List<BudgetSousProjet> budgetSousProjets = budgetSousProjetService.findByBudgetFaculteAnnee(budgetEntiteAdministratif.getBudgetSousProjet().getBudgetFaculte().getAnnee());
//       // List<BudgetEntiteAdministratif> administratifs = findByBudgetSousProjetReferenceSousProjet(budgetEntiteAdministratif.getBudgetSousProjet().getReferenceSousProjet());
//        for (BudgetSousProjet bsp : budgetSousProjets) {
//            if (bsp.getReferenceSousProjet().equals(budgetEntiteAdministratif.getBudgetSousProjet().getReferenceSousProjet())) {
//                for (BudgetEntiteAdministratif bea : bsp.getBudgetEntiteAdmins()) {
//                    if (bea.getReferenceEntiteAdministratif().equals(budgetEntiteAdministratif.getReferenceEntiteAdministratif())) {
//                        return -2;
//                    }
//                }
//                bsp.setDetaillesBudget(bsp.getDetaillesBudget());
//                double restEstimatif = bsp.getDetaillesBudget().getReliquatEstimatif() - budgetEntiteAdministratif.getDetaillesBudget().getCreditOuvertEstimatif();
//                double restReel = bsp.getDetaillesBudget().getReliquatReel() - budgetEntiteAdministratif.getDetaillesBudget().getCreditOuvertReel();
//                 if (restEstimatif < 0) {
//                    res = -4;
//                } else if (restReel < 0) {
//                    res = -5;
//                } else {
//                    BudgetEntiteAdministratif bea = new BudgetEntiteAdministratif();
//                    bea.setDetaillesBudget(budgetEntiteAdministratif.getDetaillesBudget());
//                    bea.setReferenceEntiteAdministratif(budgetEntiteAdministratif.getReferenceEntiteAdministratif());
//                    bea.getDetaillesBudget().setReliquatEstimatif(budgetEntiteAdministratif.getDetaillesBudget().getCreditOuvertEstimatif());
//                    bea.getDetaillesBudget().setCreditOuvertEstimatif(budgetEntiteAdministratif.getDetaillesBudget().getCreditOuvertEstimatif());
//                    bea.getDetaillesBudget().setReliquatReel(budgetEntiteAdministratif.getDetaillesBudget().getCreditOuvertReel());
//                    bea.getDetaillesBudget().setCreditOuvertReel(budgetEntiteAdministratif.getDetaillesBudget().getCreditOuvertReel());
//                    bea.getDetaillesBudget().setEngagePaye(budgetEntiteAdministratif.getDetaillesBudget().getEngagePaye());
//                    bea.getDetaillesBudget().setEngageNonPaye(budgetEntiteAdministratif.getDetaillesBudget().getEngageNonPaye());
//                    bea.setBudgetSousProjet(bsp);
//                    bsp.getDetaillesBudget().setReliquatEstimatif(restEstimatif);
//                    bsp.getDetaillesBudget().setReliquatReel(restReel);
//                    budgetSousProjetService.updateReliquatBsp(bsp);
//                    budgetEntiteAdministratifDao.save(bea);
//                    res = 1;
//                }
//            }
//        }
//        return res;
//
//    }
    @Override
    public void updateReliquatBea(BudgetEntiteAdministratif budgetEntiteAdministratif
    ) {
        budgetEntiteAdministratifDao.save(budgetEntiteAdministratif);
    }

    @Override
    public void deleteBudgetSousProjet (String referenceSousProjet , int annee) {
        List<BudgetEntiteAdministratif> budgetEntiteAdministratifs = findByBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(referenceSousProjet, annee);
        for (BudgetEntiteAdministratif budgetEntiteAdministratif : budgetEntiteAdministratifs) {
            budgetCompteBudgitaireService.deleteBudgetEntiteAdministratif(budgetEntiteAdministratif.getReferenceEntiteAdministratif(), referenceSousProjet, annee);
        }
       BudgetSousProjet budgetSousProjet = budgetSousProjetService.findByReferenceSousProjetAndBudgetFaculteAnnee(referenceSousProjet, annee);
        budgetSousProjetService.deleteBudgetSousProjet(budgetSousProjet);
    }

    @Override
    public void deleteBudgetEntiteAdministratif(BudgetEntiteAdministratif budgetEntiteAdministratif
    ) {
        budgetEntiteAdministratifDao.delete(budgetEntiteAdministratif);
    }
    @Override
    public int creerBudgetEntiteAdministratif(BudgetEntiteAdministratif budgetEntiteAdministratif) {
        BudgetFaculte bf = budgetFaculteService.findByAnnee(budgetEntiteAdministratif.getBudgetSousProjet().getBudgetFaculte().getAnnee());
        BudgetSousProjet bsp = budgetSousProjetService.findByReferenceSousProjetAndBudgetFaculteAnnee(budgetEntiteAdministratif.getBudgetSousProjet().getReferenceSousProjet(), budgetEntiteAdministratif.getBudgetSousProjet().getBudgetFaculte().getAnnee());
        BudgetEntiteAdministratif bea = findByReferenceEntiteAdministratifAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(budgetEntiteAdministratif.getReferenceEntiteAdministratif(), budgetEntiteAdministratif.getBudgetSousProjet().getReferenceSousProjet(), budgetEntiteAdministratif.getBudgetSousProjet().getBudgetFaculte().getAnnee());
        if (bf == null) {
            return -1;
        } else if (bsp == null) {
            return -2;
        } else if (bea != null) {
            return -3;
        }else {
                     bsp.setDetaillesBudget(bsp.getDetaillesBudget());
                double restEstimatif = bsp.getDetaillesBudget().getReliquatEstimatif() - budgetEntiteAdministratif.getDetaillesBudget().getCreditOuvertEstimatif();
                double restReel = bsp.getDetaillesBudget().getReliquatReel() - budgetEntiteAdministratif.getDetaillesBudget().getCreditOuvertReel();
                 if (restEstimatif < 0) {
                    return -4;
                } else if (restReel < 0) {
                   return -5;
                } else {
                    bea = new BudgetEntiteAdministratif();
                    bea.setDetaillesBudget(budgetEntiteAdministratif.getDetaillesBudget());
                    bea.setReferenceEntiteAdministratif(budgetEntiteAdministratif.getReferenceEntiteAdministratif());
                    bea.getDetaillesBudget().setReliquatEstimatif(budgetEntiteAdministratif.getDetaillesBudget().getCreditOuvertEstimatif());
                    bea.getDetaillesBudget().setCreditOuvertEstimatif(budgetEntiteAdministratif.getDetaillesBudget().getCreditOuvertEstimatif());
                    bea.getDetaillesBudget().setReliquatReel(budgetEntiteAdministratif.getDetaillesBudget().getCreditOuvertReel());
                    bea.getDetaillesBudget().setCreditOuvertReel(budgetEntiteAdministratif.getDetaillesBudget().getCreditOuvertReel());
                    bea.getDetaillesBudget().setEngagePaye(budgetEntiteAdministratif.getDetaillesBudget().getEngagePaye());
                    bea.getDetaillesBudget().setEngageNonPaye(budgetEntiteAdministratif.getDetaillesBudget().getEngageNonPaye());
                    bea.setBudgetSousProjet(bsp);
                    bsp.getDetaillesBudget().setReliquatEstimatif(restEstimatif);
                    bsp.getDetaillesBudget().setReliquatReel(restReel);
                    budgetSousProjetService.updateReliquatBsp(bsp);
                    budgetEntiteAdministratifDao.save(bea);
                     return 1;
                }
            }
        }

    }