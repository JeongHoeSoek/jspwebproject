package ch09;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

@WebServlet("/professorControl")
public class ProfessorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProfessorDAO dao;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new ProfessorDAO();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		String view = "";

		if(action == null) {
			getServletContext().getRequestDispatcher("/professorControl?action=list").forward(request, response);
		} else {
			switch(action) {
				case "list": view = list(request, response); break;
				case "insert": view = insert(request, response); break;
				case "edit": view = edit(request, response); break;
				case "update": view = update(request, response); break;
				case "delete": view = delete(request, response); break;
			}
			getServletContext().getRequestDispatcher("/ch09/"+view).forward(request, response);
		}
	}

	public String list(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("professors", dao.getAll());
		return "../professorinfo.jsp";
	}

	public String insert(HttpServletRequest request, HttpServletResponse response) {
		Professor p = new Professor();
		try {
			BeanUtils.populate(p, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		dao.insert(p);
		return list(request, response);
	}

	public String edit(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		Professor p = dao.get(id);
		request.setAttribute("professor", p);
		return "../editProfessor.jsp";
	}

	public String update(HttpServletRequest request, HttpServletResponse response) {
		Professor p = new Professor();
		try {
			BeanUtils.populate(p, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		dao.update(p);
		return list(request, response);
	}

	public String delete(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		dao.delete(id);
		return list(request, response);
	}
}
