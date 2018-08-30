/**
 * 
 */
package com.hb.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hb.model.SignUp;
import com.hb.service.inter.ISignUpService;
import com.hb.util.ResponseBean;

/**
 * All rights reserved by RealSoftInc 2017
 * 
 * @author Mazaharul Haque
 * 
 */

@RestController
@RequestMapping("/signup")
public class SignUpController {
	/**
	 * 
	 */
	private static final Logger LOG = Logger.getLogger(SignUpController.class);
	/**
	 * 
	 */
	@Autowired
	private ISignUpService signUpService;
	/**
	 * 
	 */
	private ResponseBean responseBean=new ResponseBean();

	/**
	 *
	 * Login Service
	 * 
	 */
	@PostMapping("/login")
	public ResponseEntity<ResponseBean> validateUserWithToken(
			@RequestParam(name = "emailOrMobileNumber", required = true) String emailOrMobileNumber,
			@RequestParam(name = "password", required = true) String password,
			@RequestParam(name = "token", required = true) String token) {
		LOG.info("Controller method : validateUser");

		SignUp validationData = null;
		if (emailOrMobileNumber.contains("@")) {
			LOG.info("Use tries to log in by email address++++++");
			validationData = signUpService.validateByEmailWithToken(emailOrMobileNumber, password, token);
		} else {
			LOG.info("Use tries to log in by mobile number");
			validationData = signUpService.validateByMobileNumberWithToken(emailOrMobileNumber, password, token);
		}

		if (validationData != null) {
			LOG.debug("validationData is " + validationData);
			LOG.debug("Http response is HttpStatus.OK");
			responseBean.setStatusCode(1);
			responseBean.setMessage("Success");
			responseBean.setErrMessage("");
			responseBean.setResult(validationData);
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} else {
			LOG.debug("validationData is " + validationData);
			LOG.debug("Http response is HttpStatus.NO_CONTENT");
			responseBean.setStatusCode(2);
			responseBean.setMessage("Failure");
			responseBean.setErrMessage("HttpStatus.BAD_REQUEST");
			responseBean.setResult(validationData);
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.NO_CONTENT);
		}
	}

	/**
	 * 
	 * 
	 * Check if user exists.
	 * 
	 * @param emailOrPassword
	 * 
	 */
	@PostMapping("/exist")
	public ResponseEntity<ResponseBean> userExists(
			@RequestParam(name = "emailOrMobileNumber", required = true) String emailOrMobileNumber) {
		LOG.info("Controller method : userExists");
		SignUp validationData = null;
		if (emailOrMobileNumber.contains("@")) {
			LOG.info("Use tries to check if uer exists by email address");
			validationData = signUpService.checkUserExistsByEmail(emailOrMobileNumber);
		} else {
			LOG.info("Use tries to check if user exists by mobile number");
			validationData = signUpService.checkUserExistsByMobileNumber(emailOrMobileNumber);
		}

		if (validationData != null) {
			LOG.debug("user exists :- " + validationData);
			LOG.debug("Http response is HttpStatus.OK");
			responseBean.setStatusCode(1);
			responseBean.setMessage("Success");
			responseBean.setErrMessage("");
			responseBean.setResult(validationData);
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} else {
			LOG.debug("User does not exists : -  " + validationData);
			LOG.debug("Http response is HttpStatus.NO_CONTENT");
			responseBean.setStatusCode(2);
			responseBean.setMessage("Failure");
			responseBean.setErrMessage("HttpStatus.BAD_REQUEST");
			responseBean.setResult(validationData);
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.NO_CONTENT);
		}
	}

	/**
	 * 
	 */
	@PostMapping("save")
	public ResponseEntity<ResponseBean> signUpUser(final SignUp signUp) {
		LOG.info("Controller method : signUpUser");
		SignUp __signUp = signUpService.signUpUser(signUp);
		if (__signUp != null) {
			LOG.debug("Http response is HttpStatus.OK");
			responseBean.setStatusCode(1);
			responseBean.setMessage("Success");
			responseBean.setErrMessage("");
			responseBean.setResult(__signUp);
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} else {
			LOG.debug("Http response is HttpStatus.BAD_REQUEST");
			
			responseBean.setStatusCode(2);
			responseBean.setMessage("Failure");
			responseBean.setErrMessage("HttpStatus.BAD_REQUEST");
			responseBean.setResult(__signUp);
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * 
	 */
	@PostMapping("update")
	public ResponseEntity<ResponseBean> updateSignUpUser(final SignUp signUp) {
		LOG.info("Controller method : updateSignUpUser");
		SignUp __signUp = signUpService.updateSignUpUser(signUp);
		if (__signUp != null) {
			LOG.debug("Http response is HttpStatus.OK");
			responseBean.setStatusCode(1);
			responseBean.setMessage("Success");
			responseBean.setErrMessage("");
			responseBean.setResult(__signUp);
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} else {
			LOG.debug("Http response is HttpStatus.BAD_REQUEST");
			responseBean.setStatusCode(2);
			responseBean.setMessage("Failure");
			responseBean.setErrMessage("HttpStatus.BAD_REQUEST");
			responseBean.setResult(__signUp);
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
		}
	}
}
