package com.vktechnology.naagu.dao.login;

import com.vktechnology.naagu.models.login.Users;



public interface LoginDao {
	Users findByUserName(String username);
}
