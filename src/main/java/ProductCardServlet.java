import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/productcard")
public class ProductCardServlet extends HttpServlet {

    ArrayList<Product> arrayList;
    Product productCard;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        httpServletRequest.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html");
        int id = Integer.parseInt(httpServletRequest.getParameter("id"));
        arrayList = new ArrayList<>();

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

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        SQL.setValue("UPDATE mebelcityclick SET click = click + 1 WHERE name = 'productcard'");

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        SQL.setValue("UPDATE mebelcitycatalog SET rating = rating + 1 WHERE Id = " + id);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        arrayList = SQL.getArrayList("SELECT * FROM mebelcitycatalog WHERE Id LIKE " + id);
        productCard = arrayList.get(0);


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//        for(Product product : arrayListBeforePreparation) {
//            if(product.getId() == id) {
//                productCard = product;
//            }
//        }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        httpServletRequest.setAttribute("productcard", productCard);
        httpServletRequest.getRequestDispatcher("productcard.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
    }
}

