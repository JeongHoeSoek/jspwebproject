package ch10;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.beanutils.BeanUtils;
@WebServlet("/news.nhn")
@MultipartConfig(maxFileSize = 1024 * 1024 * 2, location = "c:/Temp/img")
public class NewsController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NewsDAO dao;
    private ServletContext ctx;

    private final String START_PAGE = "/menu8.jsp";  

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dao = new NewsDAO();
        ctx = getServletContext();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");

        dao = new NewsDAO();

        Method m;
        String view = null;

        if (action == null) {
            action = "listNews";
        }

        try {
            m = this.getClass().getMethod(action, HttpServletRequest.class);
            view = (String) m.invoke(this, request);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            ctx.log("요청 action 없음!!");
            request.setAttribute("error", "action 파라미터가 잘못 되었습니다!!");
            view = START_PAGE;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (view != null) {
            if (view.startsWith("redirect:/")) {
                String rview = view.substring("redirect:/".length());
                response.sendRedirect(rview);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher(view);
                if (dispatcher != null) {
                    dispatcher.forward(request, response);
                } else {
                    ctx.log("dispatcher가 null입니다. 확인해 주세요: " + view);
                    request.setAttribute("error", "서버 내부 오류가 발생했습니다.");
                    RequestDispatcher errorDispatcher = request.getRequestDispatcher(START_PAGE);
                    errorDispatcher.forward(request, response);
                }
            }
        } else {
            ctx.log("view가 null입니다. 확인해 주세요: " + action);
            request.setAttribute("error", "서버 내부 오류가 발생했습니다.");
            RequestDispatcher errorDispatcher = request.getRequestDispatcher(START_PAGE);
            errorDispatcher.forward(request, response);
        }
    }

    public String addNews(HttpServletRequest request) {
        News n = new News();
        try {
            Part part = request.getPart("file");
            String fileName = getFilename(part);

            if (fileName != null && !fileName.isEmpty()) {
                part.write(fileName);
            }
            BeanUtils.populate(n, request.getParameterMap());
            n.setImg("/img/" + fileName);
            dao.addNews(n);
        } catch (Exception e) {
            e.printStackTrace();
            ctx.log("뉴스 추가 과정에서 문제 발생!!");
            request.setAttribute("error", "뉴스가 정상적으로 등록되지 않았습니다!!");
            return listNews(request);
        }
        return "redirect:/news.nhn?action=listNews";
    }

    public String deleteNews(HttpServletRequest request) {
        int aid = Integer.parseInt(request.getParameter("aid"));
        try {
            dao.delNews(aid);
        } catch (SQLException e) {
            e.printStackTrace();
            ctx.log("뉴스 삭제 과정에서 문제 발생!!");
            request.setAttribute("error", "뉴스가 정상적으로 삭제되지 않았습니다!!");
            return listNews(request);
        }
        return "redirect:/news.nhn?action=listNews";
    }

    public String listNews(HttpServletRequest request) {
        List<News> list;
        try {
            list = dao.getAll();
            request.setAttribute("newslist", list);
        } catch (Exception e) {
            e.printStackTrace();
            ctx.log("뉴스 목록 생성 과정에서 문제 발생!!");
            request.setAttribute("error", "뉴스 목록이 정상적으로 처리되지 않았습니다!!");
        }
        return "/menu8.jsp";  // 절대 경로로 수정
    }

    public String getNews(HttpServletRequest request) {
        int aid = Integer.parseInt(request.getParameter("aid"));
        try {
            News n = dao.getNews(aid);
            request.setAttribute("news", n);
        } catch (SQLException e) {
            e.printStackTrace();
            ctx.log("뉴스를 가져오는 과정에서 문제 발생!!");
            request.setAttribute("error", "뉴스를 정상적으로 가져오지 못했습니다!!");
        }
        return "/newsView.jsp";  // 절대 경로로 수정
    }

    public String updateNewsForm(HttpServletRequest request) {
        int aid = Integer.parseInt(request.getParameter("aid"));
        try {
            News n = dao.getNews(aid);
            request.setAttribute("news", n);
        } catch (SQLException e) {
            e.printStackTrace();
            ctx.log("뉴스를 가져오는 과정에서 문제 발생!!");
            request.setAttribute("error", "뉴스를 정상적으로 가져오지 못했습니다!!");
        }
        return "/newsUpdate.jsp";  // 절대 경로로 수정
    }

    public String updateNews(HttpServletRequest request) {
        int aid = Integer.parseInt(request.getParameter("aid"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        try {
            dao.updateNews(aid, title, content);
            News n = dao.getNews(aid);
            request.setAttribute("news", n);
        } catch (SQLException e) {
            e.printStackTrace();
            ctx.log("뉴스를 가져오는 과정에서 문제 발생!!");
            request.setAttribute("error", "뉴스를 정상적으로 가져오지 못했습니다!!");
        }
        return "redirect:/news.nhn?action=getNews&aid=" + aid;
    }

    private String getFilename(Part part) {
        String fileName = null;
        String header = part.getHeader("content-disposition");
        System.out.println("Header => " + header);

        int start = header.indexOf("filename=");
        fileName = header.substring(start + 10, header.length() - 1);
        ctx.log("파일명:" + fileName);
        return fileName;
    }
}
