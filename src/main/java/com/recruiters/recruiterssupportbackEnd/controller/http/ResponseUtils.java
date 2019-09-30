package com.recruiters.recruiterssupportbackEnd.controller.http;

import com.recruiters.recruiterssupportbackEnd.model.entities.Company;
import com.recruiters.recruiterssupportbackEnd.model.entities.Person;
import com.recruiters.recruiterssupportbackEnd.model.entities.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 *
 * @author jhanuar Sanchez
 */
public class ResponseUtils {

    public static HttpHeaders getJWTToken(UserEntity userEntity) {
        HttpHeaders headers = new HttpHeaders();
        String Authorization = null;

        String secretKey = "Jasaroestaenlacasatrasnochandohaciendoesto";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");
        if (userEntity instanceof Person) // si es un reclutador o postulante
        {
            Person optPerson = (Person) userEntity;
            Authorization = optPerson.getEmail() + "," + optPerson.getType();

            String token = Jwts
                    .builder()
                    .setId("softtekJWT")
                    .setSubject(Authorization)
                    .claim("authorities",
                            grantedAuthorities.stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toList()))
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 12600000))//  funcionar por 4 horas en milisegundos
                    .signWith(SignatureAlgorithm.HS512,
                            secretKey.getBytes()).compact();

            Authorization = "Bearer " + token;
            headers.add("Authorization", Authorization);
        } else {
            if (userEntity instanceof Company) {// si es una company
                Company optPerson = (Company) userEntity;
                Authorization = optPerson.getEmail() + ",COMPANY,";
                String token = Jwts
                        .builder()
                        .setId("softtekJWT")
                        .setSubject(Authorization)
                        .claim("authorities",
                                grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis() + 12600000))//  funcionar por 4 horas en milisegundos
                        .signWith(SignatureAlgorithm.HS512,
                                secretKey.getBytes()).compact();

                Authorization = "Bearer " + token;
                headers.add("Authorization", Authorization);
            } else {
                //throw new UnauthorizedException("empty fields");
            }
        }

        headers.add("Access-Control-Allow-Methods", "POST,GET");
        headers.add("Access-Control-Allow-Headers", "Content-Type,Authorization");
        headers.add("Access-Control-Expose-Headers", "Authorization");
        return headers;
    }
}
