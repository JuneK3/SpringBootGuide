package com.rootlab.ch13.service.impl;

import com.rootlab.ch13.config.common.CommonResponse;
import com.rootlab.ch13.config.security.JwtTokenProvider;
import com.rootlab.ch13.data.dto.SignInResultDto;
import com.rootlab.ch13.data.dto.SignUpResultDto;
import com.rootlab.ch13.data.entity.User;
import com.rootlab.ch13.data.repository.UserRepository;
import com.rootlab.ch13.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

	private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	public final UserRepository userRepository;
	public final JwtTokenProvider jwtTokenProvider;
	public final PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.jwtTokenProvider = jwtTokenProvider;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public SignUpResultDto signUp(String id, String password, String name, String role) {
		log.info("[getSignUpResult] 회원 가입 정보 전달");
		User user;
		if (role.equalsIgnoreCase("admin")) {
			user = User.builder()
					.uid(id)
					.name(name)
					.password(passwordEncoder.encode(password))
					.roles(Collections.singletonList("ROLE_ADMIN"))
					.build();
		} else {
			user = User.builder()
					.uid(id)
					.name(name)
					.password(passwordEncoder.encode(password))
					.roles(Collections.singletonList("ROLE_USER"))
					.build();
		}

		User savedUser = userRepository.save(user);
		SignUpResultDto signUpResultDto = new SignInResultDto();
		log.info("[getSignUpResult] userEntity 값이 들어왔는지 확인 후 결과값 주입");

		if (!savedUser.getName().isEmpty()) {
			log.info("[getSignUpResult] 정상 처리 완료");
			setSuccessResult(signUpResultDto);
		} else {
			log.info("[getSignUpResult] 실패 처리 완료");
			setFailResult(signUpResultDto);
		}
		return signUpResultDto;
	}

	@Override
	public SignInResultDto signIn(String id, String password) throws RuntimeException {
		log.info("[getSignInResult] signDataHandler 로 회원 정보 요청");
		User user = userRepository.findByUid(id);
		log.info("[getSignInResult] Id : {}", id);

		log.info("[getSignInResult] 패스워드 비교 수행");
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new RuntimeException();
		}
		log.info("[getSignInResult] 패스워드 일치");

		log.info("[getSignInResult] SignInResultDto 객체 생성");
		SignInResultDto signInResultDto = SignInResultDto.builder()
				.token(jwtTokenProvider.createToken(String.valueOf(user.getUid()), user.getRoles()))
				.build();

		log.info("[getSignInResult] SignInResultDto 객체에 값 주입");
		setSuccessResult(signInResultDto);
		return signInResultDto;
	}

	// 결과 모델에 api 요청 성공 데이터를 세팅해주는 메소드
	private void setSuccessResult(SignUpResultDto result) {
		result.setSuccess(true);
		result.setCode(CommonResponse.SUCCESS.getCode());
		result.setMsg(CommonResponse.SUCCESS.getMsg());
	}

	// 결과 모델에 api 요청 실패 데이터를 세팅해주는 메소드
	private void setFailResult(SignUpResultDto result) {
		result.setSuccess(false);
		result.setCode(CommonResponse.FAIL.getCode());
		result.setMsg(CommonResponse.FAIL.getMsg());
	}
}
