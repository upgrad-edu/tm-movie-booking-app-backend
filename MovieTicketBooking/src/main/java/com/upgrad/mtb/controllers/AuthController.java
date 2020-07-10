package com.upgrad.mtb.controllers;


import com.upgrad.mtb.beans.Customer;
import com.upgrad.mtb.dto.CustomerDTO;
import com.upgrad.mtb.dto.LoginDTO;
import com.upgrad.mtb.dto.RefreshTokenRequest;
import com.upgrad.mtb.dto.ResetPasswordDTO;
import com.upgrad.mtb.exceptions.APIException;
import com.upgrad.mtb.exceptions.CustomException;
import com.upgrad.mtb.exceptions.CustomerDetailsNotFoundException;
import com.upgrad.mtb.exceptions.BadCredentialsException;
import com.upgrad.mtb.security.jwt.JwtTokenProvider;
import com.upgrad.mtb.services.CustomerServiceImpl;
import com.upgrad.mtb.utils.DTOEntityConverter;
import com.upgrad.mtb.utils.EntityDTOConverter;
import com.upgrad.mtb.validator.CustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@CrossOrigin
@Controller
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    CustomerServiceImpl customerService;
    @Autowired
    CustomerValidator customerValidator;
    @Autowired
    EntityDTOConverter entityDTOConverter;
    @Autowired
    DTOEntityConverter dtoEntityConverter;


    @RequestMapping(method = RequestMethod.POST, value = "/users")
    @ResponseBody
    public ResponseEntity signUp(@RequestBody CustomerDTO customerDTO) throws CustomException {
        try {
            customerValidator.validateCustomer(customerDTO);
            Map<String, String> model = new HashMap<>();
            String username = customerDTO.getUsername();
            String password = customerDTO.getPassword();
            if(StringUtils.isEmpty(username) ||  StringUtils.isEmpty(password)){
                model.put("Error", "Username is invalid/ Password is empty");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(model);
            }
            String refreshToken = jwtTokenProvider.createRefreshToken(username);
            String token = jwtTokenProvider.createToken(username);
            customerDTO.setJwtToken(token);
            customerDTO.setRefreshToken(refreshToken);
            Customer newCustomer = dtoEntityConverter.convertToCustomerEntity(customerDTO);
            Customer savedCustomer = customerService.acceptCustomerDetails(newCustomer);
            CustomerDTO savedCustomerDTO = entityDTOConverter.convertToCustomerDTO(savedCustomer);
            customerService.addToken(token);
            customerService.addRefreshToken(token,savedCustomer);
            customerService.updateRefreshTokenAccessTokenMap(refreshToken,token);
            customerService.updateAccessTokenUserMap("",token,savedCustomer);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomerDTO);
        } catch (Exception e) {
            throw new CustomException("Username " + customerDTO.getUsername() + " already registered", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


    @RequestMapping(method = RequestMethod.GET, value = "/me")
    @ResponseBody
    public ResponseEntity getUserDetails(@RequestHeader(value = "X-ACCESS-TOKEN") String accessToken) {
        Customer customer = customerService.getUserFromAccessToken(accessToken.trim());
        Map<String, String> model = new HashMap<>();
        model.put("username", customer.getUsername());
        model.put("name", customer.getFirstName() + " " + customer.getLastName());
        return ResponseEntity.ok(model);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/access-tokens")
    @ResponseBody
    public ResponseEntity signIn(@RequestBody LoginDTO loginDTO) throws APIException, CustomerDetailsNotFoundException,BadCredentialsException, CustomException {
        System.out.println("Print statement here _____________________________");
        try {
            customerValidator.validateuserLogin(loginDTO);
            Map<String, String> model = new HashMap<>();
            String username = loginDTO.getUsername();
            String password = loginDTO.getPassword();
            if(StringUtils.isEmpty(username) ||  StringUtils.isEmpty(password)){
                model.put("Error", "Username is invalid/ Password is empty");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(model);
            }
            Customer savedCustomer = customerService.getCustomerDetailsByUsername(username);
            if(!savedCustomer.getPassword().equals(password)){
                throw new BadCredentialsException("Invalid username/password");
            }
            String refreshToken = jwtTokenProvider.createRefreshToken(username);
            String token = jwtTokenProvider.createToken(username);
            String oldToken = savedCustomer.getJwtToken();
            savedCustomer.setJwtToken(token);
            savedCustomer.setRefreshToken(refreshToken);
            CustomerDTO savedCustomerDTO = entityDTOConverter.convertToCustomerDTO(savedCustomer);
            customerService.addToken(token);
            customerService.addRefreshToken(token,savedCustomer);
            customerService.updateRefreshTokenAccessTokenMap(refreshToken,token);
            customerService.updateAccessTokenUserMap(oldToken,token,savedCustomer);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomerDTO);
        } catch (Exception e) {
            throw new CustomException("Username " + loginDTO.getUsername() + "not registered/password incorrect", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/access-tokens")
    @ResponseBody
    public ResponseEntity signOut(@RequestBody RefreshTokenRequest data) throws CustomException {
        try {
            String refreshToken = data.getRefresh_token();
            String accessToken = customerService.getCurrentAccessTokenFromRefreshToken(refreshToken);
            customerService.removeUserFromAccessTokenMap(customerService.getCurrentAccessTokenFromRefreshToken(refreshToken));
            customerService.removeRefreshTokenAccessTokenMap(refreshToken);
            customerService.removeRefreshToken(refreshToken);
            customerService.removeTokenIfPresent(accessToken);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (AuthenticationException e) {
            throw new CustomException("Refresh token not valid", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/resetPassword")
    @ResponseBody
    public ResponseEntity resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) throws CustomException {
        try {
            customerValidator.validateResetPassword(resetPasswordDTO);
            Map<String, String> model = new HashMap<>();
            String username = resetPasswordDTO.getUsername();
            String oldPassword = resetPasswordDTO.getOldPassword();
            if(StringUtils.isEmpty(username) ||  StringUtils.isEmpty(oldPassword)){
                model.put("Error", "Username is invalid/ Password is empty");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(model);
            }
            Customer savedCustomer = customerService.getCustomerDetailsByUsername(username);
            if(!savedCustomer.getPassword().equals(oldPassword)){
                throw new BadCredentialsException("Invalid username/password");
            }
            String refreshToken = jwtTokenProvider.createRefreshToken(username);
            String token = jwtTokenProvider.createToken(username);
            String oldToken = savedCustomer.getJwtToken();
            savedCustomer.setJwtToken(token);
            savedCustomer.setRefreshToken(refreshToken);
            savedCustomer.setPassword(resetPasswordDTO.getNewPassword());
            savedCustomer = customerService.updateCustomerDetails(savedCustomer.getId(),savedCustomer);
            CustomerDTO savedCustomerDTO = entityDTOConverter.convertToCustomerDTO(savedCustomer);
            customerService.addToken(token);
            customerService.addRefreshToken(token,savedCustomer);
            customerService.updateRefreshTokenAccessTokenMap(refreshToken,token);
            customerService.updateAccessTokenUserMap(oldToken,token,savedCustomer);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomerDTO);
        } catch (Exception e) {
            throw new CustomException("Username :" + resetPasswordDTO.getUsername() + "Invalid UserName/Password", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


    @RequestMapping(method = RequestMethod.POST, value = "/access-tokens/refresh")
    @ResponseBody
    public ResponseEntity refreshToken(@RequestBody RefreshTokenRequest data) throws CustomException {
        try {
            String refreshToken = data.getRefresh_token();
            if (customerService.getUserfromRefreshToken(refreshToken) == null) {
                throw new CustomException("Refresh token not valid", HttpStatus.UNPROCESSABLE_ENTITY);
            }
            String token = jwtTokenProvider.createToken(customerService.getUserfromRefreshToken(refreshToken).getUsername());
            customerService.updateAccessTokenUserMap(customerService.getCurrentAccessTokenFromRefreshToken(refreshToken), token,
                    null);
            customerService.removeTokenIfPresent(customerService.getCurrentAccessTokenFromRefreshToken(refreshToken));
            customerService.updateRefreshTokenAccessTokenMap(refreshToken, token);
            customerService.addToken(token);

            Map<String, String> model = new HashMap<>();
            model.put("jwt", token);
            return ResponseEntity.ok(model);
        } catch (AuthenticationException | CustomException e) {

            throw new CustomException("Refresh token not valid", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
