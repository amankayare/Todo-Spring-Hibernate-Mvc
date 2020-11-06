package com.todo.app.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.todo.app.dto.Todo;
import com.todo.app.dto.User;
import com.todo.app.service.TodoService;
import com.todo.app.service.UserService;
import com.todo.app.util.ProfileValidator;
import com.todo.app.util.SignupValidator;
import com.todo.app.util.UserValidator;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private TodoService todoService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private SignupValidator signupValidator;

	@Autowired
	private ProfileValidator profileValidator;

	@Autowired
	private MailSender mailSender;

	@RequestMapping(value = "/userLogin.app")
	public String loginRendering(ModelMap map) {
		System.out.println("mapping userLogin");

		map.put("user", new User());
		return "login";
	}

	@RequestMapping(value = "/doLogin.app")
	public String loginProcess(User user, BindingResult result, ModelMap map, HttpSession session) {

		/*
		 * userValidator.validate(user, result);
		 * 
		 * if (result.hasErrors()) { System.out.println("has ERROR"); return "login"; }
		 */
		boolean flag = userService.loginUser(user);
		if (flag) {
			System.out.println("user found");
			List<User> userList = userService.findUserByEmail(user);

			session.setAttribute("user", userList.get(0));

			List<Todo> todoList = todoService.findTodoByUserId(userList.get(0));
			session.setAttribute("todoList", todoList);
			System.out.println("TODOLIST:" + todoList);
			return "dashboard";

		} else {
			System.out.println("USER  NOT FOUND!! ");
			map.addAttribute("userNotExist", "Bad Credentials !!!");
			return "login";
		}
	}

	@RequestMapping(value = "/userSignup.app")
	public String regiserRendering(ModelMap map) {
		map.put("user", new User());
		System.out.println("mapping user signup");
		return "signup";
	}

	@RequestMapping(value = "/doSignup.app")
	public String regiserProcess(ModelMap map, User user, BindingResult result, HttpSession session) {
		System.out.println("mapping doRegister");

		signupValidator.validate(user, result);

		if (result.hasErrors()) {
			System.out.println("has ERROR");
			return "signup";
		}

		List<User> list = userService.findUserByEmail(user);

		if (list.isEmpty()) {
			userService.saveUser(user);
			return "index";

		} else {
			map.addAttribute("UserExist", "user already Exist");
			return "signup";
		}
	}

	@RequestMapping(value = "/logout.app")
	public String logOutProcess(HttpSession session, HttpServletResponse response) {

		session.removeAttribute("user");
		session.invalidate();
		response.setHeader("Cache-Control", "no-cache");
		return "index";

	}

	@RequestMapping(value = "/forgetPassword.app")
	public String forgetPassword(HttpSession session, HttpServletResponse response) {

		return "dashboard";

	}

	@RequestMapping(value = "/forgotPassword.app", method = RequestMethod.POST)
	public String forgotPassword(@RequestParam(value = "email", required = true) String email, ModelMap map) {

		String pass = userService.forgotPassword(email);
		String msg = "you are not registered";
		if (pass != null) {
			System.out.println("pass!=null");
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("cdacmumbai3@gmail.com");
			message.setTo(email);
			message.setSubject("Your password");
			message.setText("Dear user your password for TODO-List Application is:" + pass);
			// sending message
			mailSender.send(message);
			msg = "check the mail for password";
			map.put("user", new User());
			map.addAttribute("msg", msg);

			return "forget";
		} else {
			System.out.println("pass=null");

			map.addAttribute("msg", msg);
			return "forget";
		}
	}

	@RequestMapping(value = "/backToDashboard.app")
	public String backToDashboard(HttpSession session, HttpServletResponse response) {

		User user = (User) session.getAttribute("user");
		return "dashboard";

	}

	@RequestMapping(value = "/userProfile.app")
	public String profileRendering(ModelMap map, HttpSession session) {
		System.out.println("mapping userProfile render");

		User user = (User) session.getAttribute("user");
		List<User> list = userService.findUserByEmail(user);

		map.put("user", list.get(0));
		return "profile";
	}

	@RequestMapping(value = "/doProfileUpdate.app")
	public String updateProfile(ModelMap map, User user, BindingResult result, HttpSession session) {
		System.out.println("mapping userProfile update");
		profileValidator.validate(user, result);

		if (result.hasErrors()) {
			System.out.println("has ERROR");
			return "profile";
		}

		/*
		 * List<User> emailExistList = userService.findUserByEmail(user);
		 * 
		 * if (!emailExistList.isEmpty()) { map.addAttribute("already",
		 * "Email Id already Exist"); return "profile"; }
		 * 
		 */
		User sessionUser = (User) session.getAttribute("user");
		user.setUserId(sessionUser.getUserId());
		userService.modifyUser(user);

		session.setAttribute("user", user);
		return "profile";
	}

	@RequestMapping(value = "/upload_file.app", method = RequestMethod.POST)
	public String uploadFile(@RequestParam("file") MultipartFile file, HttpSession session, HttpServletRequest request,
			ModelMap map) {
		String fileName = "0";

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				fileName = file.getOriginalFilename();
				fileName = fileName.substring(fileName.lastIndexOf("."));

				User user = (User) session.getAttribute("user");

				// extention taken
				fileName = user.getUserId() + fileName;

				// Creating the directory to store file
				String rootPath = request.getServletContext().getRealPath("/");
				File dir = new File(rootPath + File.separator + "images");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server

				String filePath = dir.getAbsolutePath() + File.separator + fileName;

				System.out.println("Server File Location= " + filePath);

				File serverFile = new File(filePath);

				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				userService.uploadImage(fileName, user.getUserId());

				user.setProfilePic(fileName);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "dashboard";
	}
}