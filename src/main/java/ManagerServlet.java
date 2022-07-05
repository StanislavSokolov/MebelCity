import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/manager")
public class ManagerServlet extends HttpServlet {

    ArrayList<Product> arrayListBeforePreparation;
    ArrayList<Product> arrayList;

    private boolean checkAuthorization = false;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html");

        arrayList = new ArrayList<>();
        arrayListBeforePreparation = new ArrayList<>();

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        SQL.createBD();

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        boolean authorizationStatus = false;
        String quantity = "0";
        Cookie[] cookies = httpServletRequest.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("quantity")) {
                authorizationStatus = true;
                quantity = cookie.getValue();
            }
        }

        if (!authorizationStatus) {
            Cookie cookie1 = new Cookie("quantity", quantity);
            cookie1.setMaxAge(60*60*24*90);
            httpServletResponse.addCookie(cookie1);
        }

        httpServletRequest.setAttribute("quantity", quantity);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//        for (Cookie cookie : cookies) {
//            if (cookie.getName().equals("authorized")) {
//                if (cookie.getValue().equals("true")) {
//                    checkAuthorization = true;
//                } else checkAuthorization = false;
//            } else checkAuthorization = false;
//        }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//        if (checkAuthorization) {
//            httpServletRequest.getRequestDispatcher("jsp").forward(httpServletRequest, httpServletResponse);
//        } else httpServletRequest.getRequestDispatcher("manager.jsp").forward(httpServletRequest, httpServletResponse);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        arrayListBeforePreparation = SQL.getArrayList("SELECT * FROM mebelcitycatalog");

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        arrayListBeforePreparation.sort((o1, o2) -> o2.getRating() - o1.getRating());

        for (int i = 0; i < arrayListBeforePreparation.size(); i++) {
            arrayList.add(arrayListBeforePreparation.get(i));
        }



        httpServletRequest.setAttribute("arrayList", arrayList);
        getServletContext().getRequestDispatcher("/manager.jsp").forward(httpServletRequest, httpServletResponse);


    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        httpServletRequest.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html");

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Cookie[] cookies = httpServletRequest.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("authorized")) {
                if (cookie.getValue().equals("true")) {
                    checkAuthorization = true;
                } else checkAuthorization = false;
            } else checkAuthorization = false;
        }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        String login = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");

        if (checkAuthorization) {
//            httpServletRequest.getRequestDispatcher("database.jsp").forward(httpServletRequest, httpServletResponse);
        } else {
            if (login.equals("1234") && password.equals("1234")) {
                Cookie cookie = new Cookie("authorized", "true");
                cookie.setMaxAge(60*60*24);
                httpServletResponse.addCookie(cookie);
//                httpServletRequest.getRequestDispatcher("database.jsp").forward(httpServletRequest, httpServletResponse);
            } else httpServletRequest.getRequestDispatcher("manager.jsp").forward(httpServletRequest, httpServletResponse);
        }


    }
}

