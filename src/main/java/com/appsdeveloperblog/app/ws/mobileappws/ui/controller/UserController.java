package com.appsdeveloperblog.app.ws.mobileappws.ui.controller;

import com.appsdeveloperblog.app.ws.exceptions.UserServiceException;
import com.appsdeveloperblog.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.response.UserRest;
import com.appsdeveloperblog.app.ws.userservice.UserService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("users")//http://localhost:8080/users
public class UserController {

    Map<String,UserRest> users;
    @Autowired
    UserService userService;
    @GetMapping()
    public String getUser(@RequestParam(value="page",defaultValue = "5")int page,
                          @RequestParam(value="limit")int limit,
                          @RequestParam(value="sort",required=false) String sort){
        return "get user was called page= "+page+" limit = "+limit;
    }
    @GetMapping(path="/{userId}" , produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> getUser(@PathVariable String userId){

       // if(true) throw new UserServiceException("A user service exception is thrown");
        if(users.containsKey(userId)){
            return new ResponseEntity<>(users.get(userId),HttpStatus.OK);
        }
        else{
            return new  ResponseEntity<>( HttpStatus.NO_CONTENT);
        }

    }

    @PostMapping(
            consumes =
                    {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
            produces =
                    {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails){

        UserRest returnValue=userService.createUser(userDetails);

        return new ResponseEntity<UserRest>(returnValue,HttpStatus.OK) ;
    }

    @PutMapping(path="/{userId}",consumes =
            {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
            produces =
                    {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> updateUser(@PathVariable String userId, @RequestBody UpdateUserDetailsRequestModel userDetails){
        UserRest storedUserDetails=users.get(userId);
        storedUserDetails.setFirstName(userDetails.getFirstName());
        storedUserDetails.setLastName(userDetails.getLastName());
        users.put(userId,storedUserDetails);
        return new ResponseEntity<UserRest>(storedUserDetails,HttpStatus.OK);
        //return storedUserDetails;
    }
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id){
        users.remove(id);
        return ResponseEntity.noContent().build();

    }
}
