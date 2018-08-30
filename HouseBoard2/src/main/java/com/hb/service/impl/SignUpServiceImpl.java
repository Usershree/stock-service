/**
 * 
 */
package com.hb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hb.dao.inter.SignUpDAO;
import com.hb.model.SignUp;
import com.hb.service.inter.ISignUpService;

/**
 * All rights reserved by RealSoftInc 2017
 * @author Mazaharul Haque
 * 
 */

@Service
@Scope("singleton")
public class SignUpServiceImpl implements ISignUpService {
	
	/**
	 * 
	 */
	@Autowired
	private SignUpDAO signUpDAO;
	/**
	 * 
	 */

	@Override
	public SignUp checkUserExistsByMobileNumber(String mobileNumber) {
		 SignUp AllSignedUpData= signUpDAO.checkUserExistByMobileNumber(mobileNumber);
			return AllSignedUpData;
	}

	/* (non-Javadoc)
	 * @see com.hb.service.ISignUpService#checkUserExistsByEmail(java.lang.String)
	 */
	@Override
	public SignUp checkUserExistsByEmail(String email) {
		 SignUp AllSignedUpData= signUpDAO.checkUserExistByEmail(email);
			return AllSignedUpData;
	}


	/* (non-Javadoc)
	 * @see com.hb.service.ISignUpService#validateByEmailWithToken(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public SignUp validateByEmailWithToken(String email, String password, String token) {
		 SignUp __SignUp= signUpDAO.validateByEmailWithTocken(email, password, token);
			return __SignUp;
	}

	/* (non-Javadoc)
	 * @see com.hb.service.ISignUpService#validateByMobileNumberWithToken(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public SignUp validateByMobileNumberWithToken(String mobileNumber, String password, String token) {
		SignUp __SignUp= signUpDAO.validateByMobileNumberWithTocken(mobileNumber, password, token);
		return __SignUp;
	}

	/* (non-Javadoc)
	 * @see com.hb.service.ISignUpService#signUpUser(com.hb.model.SignUp)
	 */
	@Override
	public SignUp signUpUser(SignUp signUp) {
		SignUp __SignUp=signUpDAO.signUpUser(signUp);
		return __SignUp;
	}

	/* (non-Javadoc)
	 * @see com.hb.service.ISignUpService#updateSignUpUser(com.hb.model.SignUp)
	 */
	@Override
	public SignUp updateSignUpUser(SignUp signUp) {
		SignUp __SignUp=signUpDAO.updateUserDetail(signUp);
		return __SignUp;
	}


}
