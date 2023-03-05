package com.ugotfilm.login.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ugotfilm.login.dto.UserDTO;
import com.ugotfilm.login.repository.IndexRepository;

//@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class IndexController {

	@Autowired
	private BCryptPasswordEncoder encodePassword;

	@Autowired
	private IndexRepository UserRepository;

	public IndexController() {

	}

	// 회원 가입
	@PostMapping("/join")
	public String join(@RequestBody UserDTO user) {
		System.out.println(user.getPassword());
		user.setPassword(encodePassword.encode(user.getPassword()));
		UserRepository.saveUser(user);
		return "회원가입 완료";
	}

	// 회원 정보 가져오기
	@PostMapping("/update")
	public UserDTO userInfo(UserDTO user) throws Exception {
		System.out.println("정보 수정(유저코드) : " + user.getUsercode());

		user = UserRepository.userInfo(user);
//		user.setPassword("test");
		System.out.println("가져온 유저 정보 : " + user.toString());
		return user;
	}

	@PutMapping(value = "/update")
	public void updateProMethod(@RequestBody UserDTO dto) throws Exception {
		System.out.println("수정 시작");
		System.out.println(dto.getUsercode());
		System.out.println(dto.getNickname());
		UserRepository.updateProcess(dto);
	}

	// 비밀번호 수정
	@PutMapping(value = "/pwmodify")
	public void pwmodifyMethod(@RequestBody UserDTO dto) throws Exception {
		System.out.println("비밀번호 수정 시작");
		System.out.println(dto.getUsercode());
		System.out.println(dto.getPassword());
		dto.setPassword(encodePassword.encode(dto.getPassword()));
		UserRepository.pwUpdateProcess(dto);
	}

	// 탈퇴(유저의 권한을 수정하는 형태)
	@PutMapping(value = "/leave")
	public void leaveProMethod(UserDTO dto) throws Exception {
		System.out.println(dto.getUsercode());
		System.out.println(dto.getNickname());
		UserRepository.leaveProcess(dto);
	}

	// 아이디 중복 체크
	@PostMapping("/idck")
	public int idCheck(UserDTO user) throws Exception {
		System.out.println("id체크 : " + user.getUsername());
		return UserRepository.idck(user);
	}

	// 닉네임 중복 체크
	@PostMapping("/nicknameck")
	public int nicknameCheck(UserDTO user) throws Exception {
		// 같은 값이 있을때 유저코드가 같으면 ok
		// 같은 값이 있을때 유저코드가 다르면 수정불가

		System.out.println("id체크 : " + user.getNickname());
		System.out.println("usercode : " + user.getUsercode());

		int res = UserRepository.nicknameck(user);

		System.out.println("res : " + res);
		if (res != 0) {
			int same = UserRepository.sameUsercode(user);
			if (same == 1) {
				System.out.println("닉네임이 존재 유저코드가 같음");
				return 0;
			} else {
				System.out.println(same);
				System.out.println("닉네임이 존재하나 유저코드가 다름");
				return 1;
			}

		} else {
			System.out.println("닉네임 없음");
			return UserRepository.nicknameck(user);
		}

	}
}
