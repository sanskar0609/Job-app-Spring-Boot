package com.example.job.app.company.impl;

import com.example.job.app.company.Company;
import com.example.job.app.company.CompanyRepository;
import com.example.job.app.company.CompanyService;
import com.example.job.app.job.Job;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateComapny(Company company, Long id) {
        Optional<Company> companyOptional=companyRepository.findById(id);

        if(companyOptional.isPresent()){
            Company companyToUpdate=companyOptional.get();

            companyToUpdate.setDescription(company.getDescription());
            companyToUpdate.setName(company.getName());
            companyToUpdate.setJobs(company.getJobs());
            companyRepository.save(companyToUpdate);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        if(companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }else {
        return false;}
    }

    @Override
    public Company getComapanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }


}
