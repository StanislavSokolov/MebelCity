import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    ArrayList<Product> arrayListBeforePreparation;
    ArrayList<Product> arrayList;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html");
        String name = httpServletRequest.getParameter("name");
        arrayList = new ArrayList<>();
        arrayListBeforePreparation = new ArrayList<>();

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

        SQL.setValue("INSERT mebelcitysearch(request) VALUES ('" + name + "');");

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        SQL.setValue("UPDATE mebelcityclick SET click = click + 1 WHERE name = 'search'");

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        arrayListBeforePreparation = SQL.getArrayList("SELECT * FROM mebelcitycatalog");

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        for(Product product : arrayListBeforePreparation) {
            if (product.getName().equals(name)) {
                if (arrayList.isEmpty()){
                    arrayList.add(product);
                } else {
                    boolean check = false;
                    for(Product product1: arrayList) {
                        if (product1.getId() == (product.getId())) {
                            check = true;
                        }
                    }
                    if (!check) arrayList.add(product);
                }
            }

//            if (product.getTeg1().equals(name)) {
//                if (arrayList.isEmpty()){
//                    arrayList.add(product);
//                } else {
//                    boolean check = false;
//                    for(Product product1: arrayList) {
//                        if (product1.getId().equals(product.getId())) {
//                            check = true;
//                        }
//                    }
//                    if (!check) arrayList.add(product);
//                }
//            }
//
//            if (product.getTeg2().equals(name)) {
//                if (arrayList.isEmpty()){
//                    arrayList.add(product);
//                } else {
//                    boolean check = false;
//                    for(Product product1: arrayList) {
//                        if (product1.getId().equals(product.getId())) {
//                            check = true;
//                        }
//                    }
//                    if (!check) arrayList.add(product);
//                }
//            }
//
//            if (product.getTeg3().equals(name)) {
//                if (arrayList.isEmpty()){
//                    arrayList.add(product);
//                } else {
//                    boolean check = false;
//                    for(Product product1: arrayList) {
//                        if (product1.getId().equals(product.getId())) {
//                            check = true;
//                        }
//                    }
//                    if (!check) arrayList.add(product);
//                }
//            }
        }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        httpServletRequest.setAttribute("arrayList", arrayList);
        httpServletRequest.getRequestDispatcher("search.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
    }
}
