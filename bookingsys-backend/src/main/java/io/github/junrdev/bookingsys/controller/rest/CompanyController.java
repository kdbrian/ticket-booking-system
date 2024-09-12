package io.github.junrdev.bookingsys.controller.rest;

import io.github.junrdev.bookingsys.model.Company;
import io.github.junrdev.bookingsys.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService service;

    @Autowired
    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @PostMapping("/new")
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        return ResponseEntity.ok(service.saveCompany(company));
    }

    @GetMapping("/")
    public ResponseEntity<List<Company>> getCompanies() {
        return ResponseEntity.ok(service.getAllCompanies());
    }

    @GetMapping("/{company_id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable(name = "company_id") String id) {
        var company = service.getCompanyById(id);
        return company.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/update/{company_id}")
    public ResponseEntity<Company> updateCompanyById(@PathVariable(name = "company_id") String id, @RequestBody Company company) {
        return ResponseEntity.ok(service.updateCompany(id, company));
    }

    @PatchMapping("/{company_id}/deactivate")
    public void deactivateCompanyById(@PathVariable(name = "company_id") String id, @RequestBody Company company) {
        service.deactivateAccount(id, company);
    }

    @DeleteMapping("/delete/{company_id}")
    public void deleteCompanyById(@PathVariable(name = "company_id") String id) {
        service.deleteCompany(id);
    }

}
