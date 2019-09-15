package com.recruiters.recruiterssupportbackEnd.controller.http;

import com.recruiters.recruiterssupportbackEnd.controller.exceptions.UnauthorizedException;
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
import org.springframework.http.HttpHeaders;

/**
 *
 * @author jhanuar Sanchez
 */
public class ResponseUtils {

    public static HttpHeaders generateTokenHeader(UserEntity userEntity) throws UnauthorizedException {
        HttpHeaders headers = new HttpHeaders();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date actualdate = new Date();//fecha de creacion de token
        dateFormat.format(actualdate);
        String Authorization = null;

        if (userEntity instanceof Person) // si es un reclutador o postulante
        {
            Person optPerson = (Person) userEntity;
            Authorization = optPerson.getEmail() + "," + optPerson.getType() + "," + dateFormat.format(actualdate);

            Signer signer = HMACSigner.newSHA256Signer("Jasaroestaenlacasatrasnochandohaciendoesto");
            JWT jwt = new JWT().setIssuer("www.acme.com")
                    .setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC))
                    .setSubject(Authorization)
                    .setExpiration(ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(60));
            String encodedJWT = JWT.getEncoder().encode(jwt, signer);
            headers.add("Authorization", encodedJWT);

        } else //si es una company
        {
            if (userEntity instanceof Company) {// si es una company
                Company optPerson = (Company) userEntity;
                Authorization = optPerson.getEmail() + "," + optPerson.getType() + "," + dateFormat.format(actualdate);

                Signer signer = HMACSigner.newSHA256Signer("Jasaroestaenlacasatrasnochandohaciendoesto");
                JWT jwt = new JWT().setIssuer("www.acme.com")
                        .setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC))
                        .setSubject(Authorization)
                        .setExpiration(ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(60));
                String encodedJWT = JWT.getEncoder().encode(jwt, signer);
                headers.add("Authorization", encodedJWT);
            } else {

                if (true) {//si es un reclutador
                    Person optPerson = (Person) userEntity;
                    Authorization = optPerson.getEmail() + "," + optPerson.getType() + "," + dateFormat.format(actualdate);

                    Signer signer = HMACSigner.newSHA256Signer("Jasaroestaenlacasatrasnochandohaciendoesto");
                    JWT jwt = new JWT().setIssuer("www.acme.com")
                            .setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC))
                            .setSubject(Authorization)
                            .setExpiration(ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(60));
                    String encodedJWT = JWT.getEncoder().encode(jwt, signer);
                    headers.add("Authorization", encodedJWT);
                } else {
                    throw new UnauthorizedException("empty fields");
                }
                ;
            }
        }
        headers.add("Access-Control-Allow-Methods", "POST,GET");
        headers.add("Access-Control-Allow-Headers", "Content-Type,Authorization");
        headers.add("Access-Control-Expose-Headers", "Authorization");
        return headers;
    }

    public static ArrayList<String> Validation(String encodedJWT) throws UnauthorizedException {
        ArrayList<String> validtype = new ArrayList<>();// arreglo de 2 posiciones que tiene 0,1 si  no ha caducado el token y el Type
        try {
            Verifier verifier = HMACVerifier.newVerifier("Jasaroestaenlacasatrasnochandohaciendoesto");

            JWT jwt = JWT.getDecoder().decode(encodedJWT, verifier);
            String[] parts = jwt.subject.split(",");//separar el optPerson.getEmail() + "," + optPerson.getType() + "," + actualdate;
            String Authorizationdate = parts[2];//guargar la fecha
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date actualdate = new Date();
            dateFormat.format(actualdate); // fecha actual
            dateFormat.parse(Authorizationdate);//fecha creacion token
            Date dateplus1 = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateFormat.parse(Authorizationdate));
            cal.add(Calendar.DATE, 1);//sumar 1 dia a la fecha de creacion de token
            dateplus1 = cal.getTime();
            if (actualdate.before(dateplus1)) {//pregunto si la fecha actual esta antes de la fecha token
                validtype.add("1"); //no se vencio
            } else {
                validtype.add("0");//Se VENCIO
            }
            validtype.add(parts[1]);// meter el type que se supondria que vendria en el body pero dudo */
        } catch (Exception e) {
            throw new UnauthorizedException("Validation Problem");
        }

        return validtype;
    }

}
