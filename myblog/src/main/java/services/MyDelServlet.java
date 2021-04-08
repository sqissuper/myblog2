package services;

import Dao.ArticleInfoDao;
import utils.ResultUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet("/mydel")
public class MyDelServlet extends HelloServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int succ = -1;//操作失败与否
        String msg = "";//信息说明
        //从前端获取参数
        int id = Integer.parseInt(req.getParameter("id"));
        //调用数据库
        ArticleInfoDao articleInfoDao = new ArticleInfoDao();
        try {
            succ = articleInfoDao.del(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //返回前端
        HashMap<String,Object> result = new HashMap<>();
        result.put("succ",succ);
        result.put("msg",msg);
        ResultUtils.write(resp,result);
    }

}
//小强是小狗