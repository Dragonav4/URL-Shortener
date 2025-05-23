//package org.example.s31722tpo10.Constraints;
//
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//
//public class HttpsUrlValidator implements ConstraintValidator<HttpsUrl.HttpsURL, String> {
//    @Override
//    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
//
//        if(s == null) return true;
//        try {
//            URL url = new URL(s);
//            return "https".equals(url.getProtocol());
//        } catch (MalformedURLException e) {
//            return false;
//        }
//    }
//}
