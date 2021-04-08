package services;

import Dao.UserFormDao;
import models.UserInfo;
import utils.ResultUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int succ = -1;
        String msg = "";
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(username != null && !username.equals("") && password!= null && !password.equals("")) {
            UserFormDao formDao = new UserFormDao();

            try {
                UserInfo info = formDao.getUser(username,password);
                if(info.getId() > 0) {
                    succ = 1;
                    HttpSession session = request.getSession();
                    session.setAttribute("info",info);
                } else {
                    succ = 0;
                    msg = "用户名或密码错误！";
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            msg = "非法请求，参数不完整";
        }

        HashMap<String, Object> result = new HashMap<>();
        result.put("succ", succ);
        result.put("msg", msg);
        ResultUtils.write(response, result);
    }
}
