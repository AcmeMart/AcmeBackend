package com.ACME.Student.marketplace.serviceImpl;

import com.ACME.Student.marketplace.POJO.User;
import com.ACME.Student.marketplace.constants.AcmeConstants;
import com.ACME.Student.marketplace.dao.UserDao;
import com.ACME.Student.marketplace.service.UserService;
import com.ACME.Student.marketplace.utils.AcmeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signup{}", requestMap);
        try {
            if (validateSignUpMap(requestMap)) {
                User user = userDao.findByEmailId(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    userDao.save(getUserFromMap(requestMap));
                    return AcmeUtils.getResponseEntity("Successfully Registered.", HttpStatus.OK);
                } else {
                    return AcmeUtils.getResponseEntity("Email already exists", HttpStatus.BAD_REQUEST);
                }
            }
            else {
                return AcmeUtils.getResponseEntity(AcmeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return AcmeUtils.getResponseEntity(AcmeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateSignUpMap(Map<String, String> requestMap){
        if (requestMap.containsKey("firstname") && requestMap.containsKey("email")
                && requestMap.containsKey("lastname") && requestMap.containsKey("phonenumber")
                && requestMap.containsKey("password")) {
            return true;
        }
        return false;
    }

    private User getUserFromMap(Map<String, String> requestMap) {
        User user = new User();
        user.setFirstname(requestMap.get("firstname"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setLastname(requestMap.get("lastname"));
        user.setPhonenumber(Long.parseLong(requestMap.get("phonenumber")));
        user.setRole(requestMap.get("role"));
        user.setStatus(requestMap.get("status"));
        return user;

    }

}
