/**
 * 
 */
package com.hb.service.inter;

import com.hb.model.SignUp;

/**
 * All rights reserved by RealSoftInc 2017
 * @author Mazaharul Haque
 * 
 */
public interface ISignUpService {
	/**
	 * 
	 */
	public SignUp validateByEmailWithToken(final String email, final String password,final String token);
	/**
	 * 
	 */
	public SignUp validateByMobileNumberWithToken(final String mobileNumber,final String password,final String token);
	/**
	 * 
	 */
	public SignUp checkUserExistsByMobileNumber(final String mobileNumber);
	/**
	 * 
	 */
	public SignUp checkUserExistsByEmail(final String email);
	/**
	 * 
	 */
	public SignUp signUpUser(final SignUp signUp);
	/**
	 * 
	 */
	public SignUp updateSignUpUser(final SignUp signUp);
}
