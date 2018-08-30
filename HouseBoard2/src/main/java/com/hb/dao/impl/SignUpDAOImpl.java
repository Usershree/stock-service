/**
 * 
 */
package com.hb.dao.impl;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.hb.dao.inter.SignUpDAO;
import com.hb.model.SignUp;

/**
 * All rights reserved by RealSoftInc 2017
 * 
 * @author Mazaharul Haque
 * 
 */

@Repository
@Scope("singleton")
@Transactional
public class SignUpDAOImpl implements SignUpDAO {
	/*
	 * @Autowired private SessionFactory sessionFactory;
	 */
	@Autowired(required = true)
	private HibernateTemplate hibernateTemplate;
	/**
	 * 
	 */
	private Session sess = null;
	/**
	 * 
	 */
	private static final Logger LOG = Logger.getLogger(SignUpDAOImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hb.dao.SignUpDAO#checkUserExistByEmail(java.lang.String)
	 */
	@Override
	public SignUp checkUserExistByEmail(String email) {
		try {
			LOG.info("Opening session for validate in checkUserExistByEmail class.");
			sess = hibernateTemplate.getSessionFactory().openSession();
			LOG.info("Creating criteria.");
			final Criteria c = sess.createCriteria(SignUp.class);

			LOG.info("Mobile Number entered is :- " + email);
			c.add(Restrictions.eq("email", email.trim()));
			LOG.info("Getting unique record from SignUp bean.");
			final SignUp signUp = (SignUp) c.uniqueResult();

			LOG.debug("Unique bean returned is " + signUp);
			return signUp;
		} catch (Exception e) {
			LOG.error("Exception occured in checkUserExistByEmail method. Reason is ", e);
			return null;
		} finally {
			if (sess != null)
				sess.close();

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hb.dao.SignUpDAO#checkUserExistByMobileNumber(java.lang.String)
	 */
	@Override
	public SignUp checkUserExistByMobileNumber(String mobileNumber) {
		try {
			LOG.info("Opening session for validate in checkUserExistByMobileNumber class.");
			sess = hibernateTemplate.getSessionFactory().openSession();
			LOG.info("Creating criteria.");
			final Criteria c = sess.createCriteria(SignUp.class);

			LOG.info("Mobile Number entered is :- " + mobileNumber);
			c.add(Restrictions.eq("mobileNumber", Long.valueOf(mobileNumber.trim())));
			LOG.info("Getting unique record from SignUp bean.");
			final SignUp signUp = (SignUp) c.uniqueResult();

			LOG.debug("Unique bean returned is " + signUp);
			return signUp;
		} catch (Exception e) {
			LOG.error("Exception occured in checkUserExistByMobileNumber method. Reason is ", e);
			return null;
		} finally {
			if (sess != null)
				sess.close();

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hb.dao.SignUpDAO#validateByEmailWithTocken(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public SignUp validateByEmailWithTocken(String email, String password, String tocken) {

		try {
			LOG.info("Opening session for validate in validateByEmail class.");
			sess = hibernateTemplate.getSessionFactory().openSession();
			LOG.info("Creating criteria.");
			final Criteria c = sess.createCriteria(SignUp.class);

			LOG.info("UserName entered is :- " + email);
			LOG.info("Password entered is :- " + password);
			c.add(Restrictions.eq("email", email.trim()));
			c.add(Restrictions.eq("password", password));
			LOG.info("Getting unique record from SignUp bean.");
			final SignUp signUp = (SignUp) c.uniqueResult();
			if (signUp != null) {
				String tocken_recieved_from_table = signUp.getDeviceTocken();
				if (!tocken_recieved_from_table.equals(tocken)) {
					LOG.info("SingnUp Id is :- " + signUp.getId());

					LOG.info("Setting device tocken string.");
					final SignUp signUp_for_device_tocken = hibernateTemplate.get(SignUp.class, signUp.getId());
					signUp_for_device_tocken.setDeviceTocken(tocken);

					// sess = hibernateTemplate.getSessionFactory().openSession();
					final Transaction tx = sess.beginTransaction();

					LOG.info("Updating signUp row for device tocken.");
					hibernateTemplate.update(signUp_for_device_tocken);
					tx.commit();

					return signUp_for_device_tocken;
				}
			}
			LOG.debug("Unique bean returned is " + signUp);
			return signUp;
		} catch (Exception e) {
			LOG.error("Exception occured in validateByEmail method. Reason is ", e);
			return null;
		} finally {
			if (sess != null)
				sess.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hb.dao.SignUpDAO#validateByMobileNumberWithTocken(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public SignUp validateByMobileNumberWithTocken(String mobileNumber, String password, String tocken) {

		try {
			LOG.info("Opening session for validate in validateByMobileNumber class.");
			sess = hibernateTemplate.getSessionFactory().openSession();
			LOG.info("Creating criteria.");
			final Criteria c = sess.createCriteria(SignUp.class);

			LOG.info("Mobile Number entered is :- " + mobileNumber);
			LOG.info("Password entered is :- " + password.trim());
			c.add(Restrictions.eq("mobileNumber", Long.valueOf(mobileNumber.trim())));
			c.add(Restrictions.eq("password", password));
			LOG.info("Getting unique record from SignUp bean.");
			final SignUp signUp = (SignUp) c.uniqueResult();

			if (signUp != null) {
				String tocken_recieved_from_table = signUp.getDeviceTocken();
				if (!tocken_recieved_from_table.equals(tocken)) {
					LOG.info("SingnUp Id is :- " + signUp.getId());

					LOG.info("Setting device tocken string.");
					final SignUp signUp_for_device_tocken = hibernateTemplate.get(SignUp.class, signUp.getId());
					signUp_for_device_tocken.setDeviceTocken(tocken);

					// sess = hibernateTemplate.getSessionFactory().openSession();
					final Transaction tx = sess.beginTransaction();

					LOG.info("Updating signUp row for device tocken.");
					hibernateTemplate.update(signUp_for_device_tocken);
					tx.commit();

					return signUp_for_device_tocken;
				}
			}
			LOG.debug("Unique bean returned is " + signUp);
			return signUp;
		} catch (Exception e) {
			LOG.error("Exception occured in validateByMobileNumber method. Reason is ", e);
			return null;
		} finally {
			if (sess != null)
				sess.close();

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hb.dao.SignUpDAO#signUpUser(com.hb.model.SignUp)
	 */
	@Override
	public SignUp signUpUser(SignUp signUp) {
		try {
			LOG.info("" + signUp.getClass() + "." + signUp);
			LOG.info("Opening hibernate session for signUpUser.");
			sess = hibernateTemplate.getSessionFactory().openSession();
			final Transaction tx = sess.beginTransaction();
			LOG.info("Inserting signUp details hibernateTemplate.save(signUp)");
			hibernateTemplate.save(signUp);

			tx.commit();

			LOG.debug("SignUp inserted is :- " + signUp);
			return signUp;
		} catch (Exception e) {
			LOG.error("", e);
			return null;
		} finally {
			sess.close();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hb.dao.SignUpDAO#updateUserDetail(com.hb.model.SignUp)
	 */
	@Override
	public SignUp updateUserDetail(SignUp signUp) {
		try {
			LOG.info("" + signUp.getClass() + "." + signUp);
			LOG.info("Opening hibernate session for updateUserDetail.");
			sess = hibernateTemplate.getSessionFactory().openSession();
			final Transaction tx = sess.beginTransaction();
			LOG.info("updating signUp details hibernateTemplate.update(signUp)");
			hibernateTemplate.update(signUp);

			tx.commit();

			LOG.debug("SignUp updated is :- " + signUp);
			return signUp;
		} catch (Exception e) {
			LOG.error("", e);
			return null;
		} finally {
			sess.close();
		}
	}

}
