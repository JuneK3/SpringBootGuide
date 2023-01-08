package com.rootlab.ch13.service;


import com.rootlab.ch13.data.dto.SignInResultDto;
import com.rootlab.ch13.data.dto.SignUpResultDto;

public interface UserService {

	SignUpResultDto signUp(String id, String password, String name, String role);

	SignInResultDto signIn(String id, String password) throws RuntimeException;

}
