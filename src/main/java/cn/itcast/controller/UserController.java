package cn.itcast.controller;

import cn.itcast.domain.ResultInfo;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/findone")
    public @ResponseBody User finduser(HttpSession session, HttpServletResponse response) {
        User user = (User) session.getAttribute("user");

//        response.setContentType("application/json;charset=utf-8");
        return  user;
    }
    @RequestMapping("/regist")
    public @ResponseBody ResultInfo regist(User user,String check,HttpSession session){
        ResultInfo info = new ResultInfo();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        System.out.println(checkcode_server+" "+check+" "+check.equalsIgnoreCase(checkcode_server));
        if(check==null||!check.equalsIgnoreCase(checkcode_server)){
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            return  info;
        }
        boolean success = userService.register(user);
        if(success){
            info.setFlag(true);
            info.setData(user);

        }else{
            info.setFlag(false);
            info.setErrorMsg("用户名已存在");
        }

        return info;
    }

    @RequestMapping("/active")
    public void active(String code,HttpServletResponse response,HttpServletRequest request) throws IOException {

        if (code!=null&&code!=""){
//            UserService service = new UserServiceImpl();
            boolean flag = userService.active(code);
            String msg= null;
            if (flag){
                msg = "激活成功请<a href = '"+request.getContextPath()+"/login.html'>登陆</a>";

            }else {
                msg = "激活失败，请联系管理员" ;
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }

    }
    @RequestMapping("/login")
    public @ResponseBody ResultInfo login(String username,String password,String check,HttpSession session){
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        ResultInfo info = new ResultInfo();

        if (checkcode_server==null||!checkcode_server.equalsIgnoreCase(check)){
            info.setFlag(false);
            info.setErrorMsg("验证码错误");

            return info;
        }

        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        User user = userService.login(u);
        if (user==null){
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }else if ("N".equals(user.getStatus())){
            info.setFlag(false);
            info.setErrorMsg("请先激活邮箱");
        }else {
            session.setAttribute("user",user);
            info.setFlag(true);
        }
        return info;
    }
    @RequestMapping("/exit")
    public String exit(HttpSession session){
       session.removeAttribute("user");
       return "login";
    }

}
