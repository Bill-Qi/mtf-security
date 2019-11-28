/**
 * 
 */
package com.mtf.web.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;
import com.mtf.dto.User;
import com.mtf.dto.UserQueryCondition;

/**
 * @author Bill
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping
	public User create(@Valid @RequestBody User user) {
		logger.info(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));

//        if (errors.hasErrors()) {
//            errors.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
//        }

		return user.setId("1");
	}

	@PutMapping("/{id:\\d+}")
	public User update(@Valid @RequestBody User user, BindingResult errors) {
		logger.info(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));

		if (errors.hasErrors()) {
			errors.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
		}

		return user;
	}

	@GetMapping
	@JsonView(User.UserSimpleView.class)
	public List<User> query(User user, @PageableDefault(size = 10, page = 0) Pageable pageable) {

		// 通过反射方法，打印查询参数对象
		logger.info(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));
		logger.info(ReflectionToStringBuilder.toString(pageable, ToStringStyle.MULTI_LINE_STYLE));

		ArrayList<User> users = new ArrayList<>();
		users.add(new User().setUsername("user1").setPassword("1"));
		users.add(new User().setUsername("user2").setPassword("2"));
		users.add(new User().setUsername("user3").setPassword("3"));
		return users;
	}

	@GetMapping("/{id:\\d+}")
	@JsonView(User.UserDetailView.class)
	public User getInfo(@PathVariable String id) {
//        throw new UserNotExistException(id);

		logger.info("getInfo user_id = {}", id);
		return new User().setUsername("user1").setPassword("1");

	}

	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable String id) {
		logger.info("delete user_id = {}", id);
	}

	@GetMapping("/me")
	public Object getCurrentUser(Authentication authentication) {
		return authentication;
	}
//		有三种情况
//    @GetMapping("/me")
//    public Object getCurrentUser(@AuthenticationPrincipal UserDetails user) {
//        return user;
//    }

	@GetMapping("/me/details")
	public Object getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
		return userDetails;
	}

}
