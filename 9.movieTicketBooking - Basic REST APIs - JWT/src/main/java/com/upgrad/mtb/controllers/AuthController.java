package com.upgrad.mtb.controllers;


import com.upgrad.mtb.beans.Customer;
import com.upgrad.mtb.dto.CustomerDTO;
import com.upgrad.mtb.dto.LoginDTO;
import com.upgrad.mtb.dto.RefreshTokenRequest;
import com.upgrad.mtb.exceptions.CustomException;
import com.upgrad.mtb.exceptions.CustomerDetailsNotFoundException;
import com.upgrad.mtb.security.jwt.JwtTokenProvider;
import com.upgrad.mtb.services.CustomerServiceImpl;
import com.upgrad.mtb.utils.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    CustomerServiceImpl customerService;

    @RequestMapping(method = RequestMethod.POST, value = "/access-tokens")
    @ResponseBody
    public ResponseEntity signIn(@RequestBody LoginDTO data) {
        try {

            String username = data.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            String token = jwtTokenProvider.createToken(username);

            String refreshToken = customerService.getRefreshTokenForUser(username);
            if (refreshToken == null) {
                refreshToken = jwtTokenProvider.createRefreshToken(username);
                customerService.addRefreshToken(refreshToken, customerService.getCustomerDetailsByUsername(username));
                customerService.updateAccessTokenUserMap("", token, customerService.getCustomerDetailsByUsername(username));
                customerService.addToken(token);

                customerService.updateRefreshTokenAccessTokenMap(refreshToken, token);
            } else {
                //Remove the current access token
                customerService.updateAccessTokenUserMap(customerService.getCurrentAccessTokenFromRefreshToken(refreshToken), token,
                        null);
                customerService.removeTokenIfPresent(customerService.getCurrentAccessTokenFromRefreshToken(refreshToken));
                customerService.updateRefreshTokenAccessTokenMap(refreshToken, token);
                customerService.addToken(token);
            }
            Map<String, String> model = new HashMap<>();
            model.put("jwt", token);
            model.put("refresh_token", refreshToken);
            return ResponseEntity.ok(model);
        } catch (AuthenticationException | CustomerDetailsNotFoundException e) {

            throw new BadCredentialsException("Invalid username/password supplied");
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


    @RequestMapping(method = RequestMethod.POST, value = "/users")
    @ResponseBody
    public ResponseEntity signUp(@RequestBody CustomerDTO data) throws CustomException {
        try {

            Map<String, String> model = new HashMap<>();
            //TODO Validation on the SignUpRequest
            String username = data.getUsername();
            String password = data.getPassword();
            if(StringUtils.isEmpty(username) || !ValidatorUtil.isValid(username)|| StringUtils.isEmpty(password)){
                model.put("Error", "Email id is invalid/ Password is empty");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(model);
            }
            String firstName = data.getFirstName();


            String refreshToken = jwtTokenProvider.createRefreshToken(username);
            String token = jwtTokenProvider.createToken(username);
            //add customer data


            model.put("jwt", token);
            model.put("refresh_token", refreshToken);
            return ResponseEntity.status(HttpStatus.CREATED).body(model);
        } catch (Exception e) {
            throw new CustomException("Username " + data.getUsername() + " already registered", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


    @RequestMapping(method = RequestMethod.GET, value = "/me")
    @ResponseBody
    public ResponseEntity getUserDetails(@RequestHeader(value = "X-Access-Token") String accessToken) {

        Customer customer = customerService.getUserFromAccessToken(accessToken.trim());
        Map<String, String> model = new HashMap<>();
        model.put("username", customer.getUsername());
        model.put("name", customer.getFirstName() + " " + customer.getLastName());
        return ResponseEntity.ok(model);
    }
}
