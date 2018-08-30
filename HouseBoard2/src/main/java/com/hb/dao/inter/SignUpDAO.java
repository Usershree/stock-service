/**
 * 
 */
package com.hb.dao.inter;


import com.hb.model.SignUp;

/**
 * All rights reserved by RealSoftInc 2017
 * @author Mazaharul Haque
 * 
 */
public interface SignUpDAO {
	/**
	 * 
	 */
	public SignUp checkUserExistByEmail(final String email);
	/**
	 * 
	 */
	public SignUp checkUserExistByMobileNumber(final String mobileNumber);
	/**
	 * 
	 */
	public SignUp validateByEmailWithTocken(final String email,final String password,final String tocken);
	/**
	 * 
	 */
	public SignUp validateByMobileNumberWithTocken(final String mobileNumber,final String password,final String tocken);
	/**
	 * 
	 */
	public SignUp signUpUser(final SignUp signUp);
	/**
	 * 
	 */
	public SignUp updateUserDetail(final SignUp signUp);
}
