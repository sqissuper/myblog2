package services;

import Dao.ArticleInfoDao;
import models.UserInfo;
import utils.ResultUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet("/addart")
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int succ = -1;//操作失败与否
        String msg = "";//信息说明
        //从前端获取参数
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        //调用数据库
        HttpSession session = req.getSession(false);
        if(session != null && session.getAttribute("info") != null) {
            UserInfo userInfo = (UserInfo) session.getAttribute("info");
            ArticleInfoDao articleInfoDao = new ArticleInfoDao();
            try {
                succ = articleInfoDao.add(title,content, userInfo.getId());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            msg = "请先登录";
        }
        //返回前端
        HashMap<String,Object> result = new HashMap<>();
        result.put("succ",succ);
        result.put("msg",msg);
        ResultUtils.write(resp,result);
    }
}
