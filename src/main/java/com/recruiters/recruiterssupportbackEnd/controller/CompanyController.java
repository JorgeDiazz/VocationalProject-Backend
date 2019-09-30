package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.controller.exceptions.ExpectationFailedException;
import com.recruiters.recruiterssupportbackEnd.controller.exceptions.UnauthorizedException;
import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import com.recruiters.recruiterssupportbackEnd.controller.request_entities.CreateCompanyRequest;
import com.recruiters.recruiterssupportbackEnd.model.entities.Company;
import com.recruiters.recruiterssupportbackEnd.model.entities.UserEntity;
import com.recruiters.recruiterssupportbackEnd.repository.CompanyRepository;
import static java.lang.Integer.TYPE;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jhanu
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @PostMapping("/")
    public ResponseEntity<UserEntity> createCompany(@RequestBody CreateCompanyRequest newCompany) throws Throwable {

        String nit = newCompany.getNit();
        String email = newCompany.getEmail();

        Optional<Company> optCompany = companyRepository.findByNit(nit);

        if (optCompany.isPresent()) { // Si existe envia mensaje de Error
            throw new UnauthorizedException("company already exist");
        } else {

            Optional<Company> optCompany2 = companyRepository.findByEmail(email); //Busqueda por correo - No pueden existir 2 personas con el mismo correo

            if (optCompany2.isPresent()) { // Si existe envia mensaje de Error
                throw new UnauthorizedException("email in use");
            } else {
                Company company = new Company();
                company.setNit(newCompany.getNit());
                company.setName(newCompany.getName());
                company.setAddress("KRA JIJI");
                company.setPhone(newCompany.getPhone());
                company.setImage(null);
                company.setEmail(newCompany.getEmail());
                company.setPassword("12345");

                try {
                    companyRepository.save(company);
                } catch (Exception e) {
                    throw new ExpectationFailedException("company data is incorrect");
                }

                return HttpResponseEntity.getOKStatus(company);
            }
        }
    }

}
