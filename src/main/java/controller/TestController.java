package controller;

import domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.TestService;

import java.util.List;

@Controller
public class TestController {
    @Autowired
    private TestService testService;

    @ResponseBody
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String find(){
        List<Users> temp = testService.getUsers();
        for(Users user: temp){
            System.out.println("id : " + user.getId());
            System.out.println("account : " + user.getAccount());
            System.out.println("password : " + user.getPassword());
            System.out.println("nickName : " +  user.getNick_name());
            System.out.println("---------------------------");
        }
        return "clear";
    }
}
