package com.sujatanaik.LMSLocalLibrary.controller;

import com.sujatanaik.LMSLocalLibrary.database.dao.UserDAO;
import com.sujatanaik.LMSLocalLibrary.database.dao.UserRoleDAO;
import com.sujatanaik.LMSLocalLibrary.database.entity.User;
import com.sujatanaik.LMSLocalLibrary.database.entity.UserRole;
import com.sujatanaik.LMSLocalLibrary.formbean.PatronRegisterFormBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class LoginController {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private UserRoleDAO userRoleDao;

    /**
     * This method handles the localhost:8080/login/userlogin.
     * This is the user login page of the library management system.
     *
     * NOT USED ANYMORE
     */

    @RequestMapping(value="/login/login/{msg}", method = RequestMethod.GET)
    public ModelAndView login(@PathVariable("msg") String msg) throws Exception {
        ModelAndView response = new ModelAndView();
        log.info("In LoginController - login1() " + msg);
        response.addObject("message", msg);
        response.setViewName("/login/userlogin");
        return response;
    }

    @RequestMapping(value="/login/userlogin", method = RequestMethod.GET)
    public ModelAndView login() throws Exception {
        ModelAndView response = new ModelAndView();
        log.info("In LoginController - login2()");
        response.setViewName("login/userlogin");
        return response;
    }

    /**
     * This method handles the localhost:8080/login/userregistration.
     * This is the user registration page of the library management system.
     */

    // TODO What happens if someone is logged in and tries to access the registration page?
    @RequestMapping(value="/login/userregistration", method= RequestMethod.GET)
    public ModelAndView registration() throws Exception {
        ModelAndView response = new ModelAndView();

        log.info("In LoginController - registration()");

        response.setViewName("login/userregistration");

        PatronRegisterFormBean form = new PatronRegisterFormBean();
        response.addObject("form", form);

        return response;
    }

    /**
     * This method handles the localhost:8080/login/userregistrationsubmit.
     * This is the processing of the submit of the user registration page of the library management system.
     */
    @RequestMapping (value = "login/userregistrationsubmit", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView registrationSubmit(@Valid PatronRegisterFormBean form, BindingResult bindingResult) throws Exception {

        ModelAndView response = new ModelAndView();

        log.info("In LoginController - registrationSubmit()");

        if (bindingResult.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();

            for (ObjectError error: bindingResult.getAllErrors()) {
                errorMessages.add(error.getDefaultMessage());
                log.info(((FieldError) error).getField() + " " + error.getDefaultMessage());
            }

            response.addObject("form", form);
            response.addObject("bindingResult", bindingResult);
            response.addObject("errorMessages", errorMessages);

            response.setViewName("login/userregistration");
            return response;
        }

        // try to load the user from db using incoming id
        User user = userDao.findById(form.getId());

        // now check if the user is null, it means user isn't in the db. We then create a user.
        if (user == null) {
            user = new User();
        }

        user.setEmail(form.getEmail());
        user.setFirstName(form.getFname());
        user.setLastName(form.getLname());
        user.setAddressLine1(form.getAline1());
        user.setAddressLine2(form.getAline2());
        user.setCity(form.getCity());
        user.setState(form.getState());
        user.setZip(form.getZip());
        user.setPhone(form.getPhone());
        user.setGender(User.UserGender.valueOf(form.getGender()));

        if (form.getNews() == null) {
            user.setNews(User.UserNews.NO);
        }
        else {
            user.setNews((User.UserNews.valueOf(form.getNews())));
        }

        // encrypt the password from the form and then save it
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(form.getPassword()));

        user.setStatus(User.UserStatus.ACTIVE);

        // add a row for this user to the users table
        userDao.save(user);

        // also add a row for this user in the user_roles table
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setUserRole("USER");

        userRoleDao.save(userRole);

        // form(HTML) -> form bean(form object above) -> database(user object above)

        log.info(form.toString());

        // if a new user is registering, redirect to /login/login
        // if an admin is registering a new user, redirect to /admin/adminuser

        // using authentication information, to check if any one is logged in
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User loggedInUser = userDao.findByEmail(currentPrincipalName);

        if (loggedInUser == null) {
            // new user just registered
            response.setViewName("redirect:/login/login");
            return response;
        }
        else {
            boolean isAdmin = userRoleDao.existsUserRoleByUserIdAndUserRoleEquals(loggedInUser.getId(), "ADMIN");
            if (isAdmin) {
                // admin registered a new user
                response.setViewName("redirect:/admin/adminuser");
                return response;
            }
            else {
                // ? is this path taken at all?
                response.setViewName("redirect:/login/login");
                return response;
            }
        }
    }
}
