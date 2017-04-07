package fr.ebiz.cdb.webapp.rest;

import fr.ebiz.cdb.core.Company;
import fr.ebiz.cdb.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Bertrand Pestre on 07/04/17.
 */
@RestController
@RequestMapping("api/company")
public class CompanyRestController {

    @Autowired
    private ICompanyService companyService;

    @GetMapping
    public List<Company> getCompanies() {
        return companyService.fetchAll();
    }

}
