package com.ACME.Student.marketplace.restImpl;

import com.ACME.Student.marketplace.constants.AcmeConstants;
import com.ACME.Student.marketplace.rest.UserRest;
import com.ACME.Student.marketplace.service.UserService;
import com.ACME.Student.marketplace.utils.AcmeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserRestImpl implements UserRest {

    @Autowired
    UserService userService;
    @Override
    //Expecting a response entity of type String
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            return userService.signUp(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
         return AcmeUtils.getResponseEntity(AcmeConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
