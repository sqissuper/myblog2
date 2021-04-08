package services;

import Dao.ArticleInfoDao;
import models.ArticleInfo;
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
import java.util.List;

public class MyArtListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int succ = -1;
        String msg = "";
        List<ArticleInfo> list = null;

        HttpSession session = request.getSession(false);
        if(session == null) {
            msg = "请先登录！";
        } else {
            UserInfo info = (UserInfo) session.getAttribute("info");

            int uid = info.getId();
            ArticleInfoDao infoDao = new ArticleInfoDao();
            try {
                list = infoDao.getListByUid(uid);
                succ = 1;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        HashMap<String, Object> result = new HashMap<>();
        result.put("succ", succ);
        result.put("msg", msg);
        result.put("list", list);
        ResultUtils.write(response, result);
    }
}
