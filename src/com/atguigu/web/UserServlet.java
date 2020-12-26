package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;


public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 获取请求的参数 username
        String username = req.getParameter("username");
        // 调用 userService.existsUsername();
        boolean existsUsername = userService.existsUsername(username);
        // 把返回的结果封装成为 map 对象
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername", existsUsername);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //1、销毁 Session 中用户登录的信息（或者销毁 Session）
        req.getSession().invalidate();
        //2、重定向到首页
        resp.sendRedirect(req.getContextPath());
    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 调用 userService.login()登录处理业务
        User loginUser = userService.login(new User(null, username, password, null));

        // 如果等于 null,说明登录 失败!
        if(loginUser == null){
            // 把错误信息，和回显的表单项信息，保存到Request域中
            req.setAttribute("msg", "用户或密码错误！");
            req.setAttribute("username", username);
            // 跳回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            // 登录 成功
            // 保存用户登录的信息到Session域中
            req.getSession().setAttribute("user", loginUser);
            // 跳到成功页面 login_success.
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        // 获取 Session 中的验证码
        String token = (String)req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 删除 Session 中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        // 获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        /*
        BeanUtils 工具类，它可以一次性的把所有请求的参数注入到 JavaBean 中。
        BeanUtils 工具类，经常用于把 Map 中的值注入到 JavaBean 中，或者是对象属性值的拷贝操作。
         */
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        // 检查验证码是否可用
        if(token != null && token.equalsIgnoreCase(code)){
            // 检查用户名是否可用
            if(userService.existsUsername(username)){
                // 把回显信息，保存到Request域中
                req.setAttribute("msg", "用户名已存在!");
                req.setAttribute("username", username);
                req.setAttribute("email", email);
                // 不可用
                System.out.println("用户名[\"" + username + "\"]已存在!");
                // 跳回注册界面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            }else{
                // 可用
                // 调用 Service 保存到数据库
                userService.registUser(new User(null, username, password, email)); 
                // 跳到注册成功页面 regist_success.htm
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        }else{
            // 把回显信息，保存到Request域中
            req.setAttribute("msg", "验证码错误!");
            req.setAttribute("username", username);
            req.setAttribute("email", email);

            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

}
