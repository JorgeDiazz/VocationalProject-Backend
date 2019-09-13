package com.recruiters.recruiterssupportbackEnd.controller.http;

import com.recruiters.recruiterssupportbackEnd.model.entities.Company;
import com.recruiters.recruiterssupportbackEnd.model.entities.Person;
import com.recruiters.recruiterssupportbackEnd.model.entities.UserEntity;
import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.http.HttpHeaders;

/**
 *
 * @author jhanuar Sanchez
 */
public class ResponseUtils {

    public static HttpHeaders generateTokenHeader(UserEntity userEntity) {
        HttpHeaders headers = new HttpHeaders();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date actualdate = new Date();//fecha de creacion de token
        dateFormat.format(actualdate);
        String token = null;
        // si es un reclutador
        try {
            Person optPerson = (Person) userEntity;
            token = optPerson.getEmail() + "." + optPerson.getType() + "." + actualdate;

            Signer signer = HMACSigner.newSHA256Signer("Jasaroestaenlacasatrasnochandohaciendoesto");
            JWT jwt = new JWT().setIssuer("www.acme.com")
                    .setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC))
                    .setSubject(token)
                    .setExpiration(ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(60));
            String encodedJWT = JWT.getEncoder().encode(jwt, signer);
            headers.add("token", encodedJWT);
        } catch (Exception e) {
            System.out.println("no era person");
        }

        //si es una company
        try {
            Company optPerson = (Company) userEntity;
            token = optPerson.getEmail() + "." + optPerson.getType() + "." + actualdate;

            Signer signer = HMACSigner.newSHA256Signer("Jasaroestaenlacasatrasnochandohaciendoesto");
            JWT jwt = new JWT().setIssuer("www.acme.com")
                    .setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC))
                    .setSubject(token)
                    .setExpiration(ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(60));
            String encodedJWT = JWT.getEncoder().encode(jwt, signer);
            headers.add("token", encodedJWT);
        } catch (Exception e) {
            System.out.println("no era company");
        }
        headers.add("Access-Control-Allow-Methods", "POST,GET");
        headers.add("Access-Control-Allow-Headers", "Content-Type,Authorization");
        headers.add("Access-Control-Expose-Headers", "token");
        return headers;
    }

    public static List<String> Validation(String encodedJWT) {
        ArrayList<String> validtype= new ArrayList<>();// arreglo de 2 posiciones que tiene 0,1 si  no ha caducado el token y el Type
        try {
            Verifier verifier = HMACVerifier.newVerifier("Jasaroestaenlacasatrasnochandohaciendoesto");

            JWT jwt = JWT.getDecoder().decode(encodedJWT, verifier);

            String[] parts = jwt.subject.split(".");//separar el optPerson.getEmail() + "." + optPerson.getType() + "." + actualdate;
            String tokendate=parts[2];
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date actualdate = new Date();
            dateFormat.format(actualdate); // fecha actual
            dateFormat.parse(tokendate);//fecha creacion token
            Date dateplus1 = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateFormat.parse(tokendate));
            cal.add(Calendar.DATE, 1);//sumar 1 dia a la fecha de creacion de token
            dateplus1 = cal.getTime();
            if (actualdate.before(dateplus1)) {//pregunto si la fecha actual esta antes de la fecha token
                validtype.add("1");//Se VENCIO 
            } else {
                validtype.add("0");//NO SE VENCIO
            }
          validtype.add(parts[1]);// meter el type que se supondria que vendria en el body pero dudo 
        } catch (Exception e) {
            System.out.println("esta wea no sirvio");
        }
        
        return validtype;
    }

}
