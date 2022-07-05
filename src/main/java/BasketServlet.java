import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/basket")
public class BasketServlet extends HttpServlet {

    public int count = 0;

    ArrayList<Product> arrayListBeforePreparation;
    ArrayList<Product> arrayList;
    ArrayList<String> arrayListQuantity;
    ArrayList<String> arrayListTotal;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        httpServletRequest.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html");

        arrayList = new ArrayList<>();
        arrayListBeforePreparation = new ArrayList<>();
        arrayListQuantity = new ArrayList<>();
        arrayListTotal = new ArrayList<>();

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

        SQL.setValue("UPDATE mebelcityclick SET click = click + 1 WHERE name = 'basket'");

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        arrayListBeforePreparation = SQL.getArrayList("SELECT * FROM mebelcitycatalog");

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        int costItems = 0;

        for (Cookie cookie : cookies) {
            for (Product product : arrayListBeforePreparation) {
                if (cookie.getName().equals(String.valueOf(product.getId()))) {
                    product.setQuantity(cookie.getValue());
                    product.setTotal(String.valueOf(Integer.parseInt(cookie.getValue()) * product.getPrice()));
                    arrayList.add(product);
                    costItems = costItems + Integer.parseInt(cookie.getValue()) * product.getPrice();
                }
            }
        }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        httpServletRequest.setAttribute("costItems", String.valueOf(costItems));
        httpServletRequest.setAttribute("costShipping", "0");
        httpServletRequest.setAttribute("costWork", "0");
        httpServletRequest.setAttribute("costTotal", String.valueOf(costItems));

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        httpServletRequest.setAttribute("arrayList", arrayList);
        httpServletRequest.setAttribute("arrayListQuantity", arrayListQuantity);
        httpServletRequest.setAttribute("arrayListTotal", arrayListTotal);
        httpServletRequest.getRequestDispatcher("basket.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        httpServletRequest.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html");

        String id = httpServletRequest.getParameter("id");

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        SQL.setValue("UPDATE mebelcitycatalog SET ratingBusket = ratingBusket + 1 WHERE Id = " + id);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        boolean checkForDuplication = false;
        String quantity = "1";
        Cookie[] cookies = httpServletRequest.getCookies();

        Cookie cookie1 = null;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(id)) {
                checkForDuplication = true;
                cookie1 = new Cookie(id, String.valueOf(Integer.parseInt(cookie.getValue())+1));
            }
            if (cookie.getName().equals("quantity")) {
                quantity = cookie.getValue();
            }
        }

        if (!checkForDuplication) {
            cookie1 = new Cookie(id, "1");
            Cookie cookie2 = new Cookie("quantity", String.valueOf(Integer.parseInt(quantity)+1));
            quantity = cookie2.getValue();
            cookie2.setMaxAge(60*60*24*90);
            httpServletResponse.addCookie(cookie2);
        }

        cookie1.setMaxAge(60*60*24*90);
        httpServletResponse.addCookie(cookie1);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        httpServletResponse.setHeader("Cache-Control", "no-cache");
        httpServletResponse.setContentType("text/html");
        httpServletResponse.getWriter().write(quantity);
    }
}

