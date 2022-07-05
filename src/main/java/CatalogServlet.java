import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// обрабатываем параметры категории каталога и сортировки в методе catalog

@WebServlet("/catalog")
public class CatalogServlet extends HttpServlet {

    ArrayList<Product> arrayList;

    int directionOfSorting = 0;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html");
        String category = httpServletRequest.getParameter("category");
        String sorting = httpServletRequest.getParameter("sorting");
        arrayList = new ArrayList<>();

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        SQL.setValue("UPDATE mebelcityclick SET click = click + 1 WHERE name = 'mebelcity'");

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        if (category.equals("kitchen")) SQL.setValue("UPDATE mebelcityclick SET click = click + 1 WHERE name = 'Кухня'");
        if (category.equals("livingroom")) SQL.setValue("UPDATE mebelcityclick SET click = click + 1 WHERE name = 'Гостиная'");
        if (category.equals("children")) SQL.setValue("UPDATE mebelcityclick SET click = click + 1 WHERE name = 'Детская'");
        if (category.equals("bedroom")) SQL.setValue("UPDATE mebelcityclick SET click = click + 1 WHERE name = 'Спальная'");
        if (category.equals("dressingroom")) SQL.setValue("UPDATE mebelcityclick SET click = click + 1 WHERE name = 'Гардеробная'");
        if (category.equals("bathroom")) SQL.setValue("UPDATE mebelcityclick SET click = click + 1 WHERE name = 'Ванная'");
        if (category.equals("upholsteredfurniture")) SQL.setValue("UPDATE mebelcityclick SET click = click + 1 WHERE name = 'Мягкая мебель'");
        if (category.equals("officefurniture")) SQL.setValue("UPDATE mebelcityclick SET click = click + 1 WHERE name = 'Офисная мебель'");
        if (category.equals("gardenfurniture")) SQL.setValue("UPDATE mebelcityclick SET click = click + 1 WHERE name = 'Садовая мебель'");
        if (category.equals("pets")) SQL.setValue("UPDATE mebelcityclick SET click = click + 1 WHERE name = 'Мебель для животных'");

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

        arrayList = SQL.getArrayList("SELECT * FROM mebelcitycatalog WHERE category LIKE '" + category + "'");

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        if (sorting.equals("price") & (directionOfSorting == 0)) {
            directionOfSorting = 1;
            sorting = "maxprice";
            arrayList.sort((o1, o2) -> o1.getPrice() - o2.getPrice());
        }
        if (sorting.equals("price") & (directionOfSorting == 1)) {
            directionOfSorting = 0;
            sorting = "minprice";
            arrayList.sort((o1, o2) -> o2.getPrice() - o1.getPrice());
        }
        if (sorting.equals("rating") & (directionOfSorting == 0)) {
            directionOfSorting = 1;
            sorting = "maxrating";
            arrayList.sort((o1, o2) -> o1.getRating() - o2.getRating());
        }
        if (sorting.equals("rating") & (directionOfSorting == 1)) {
            directionOfSorting = 0;
            sorting = "minrating";
            arrayList.sort((o1, o2) -> o2.getRating() - o1.getRating());
        }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//        if (pageNotFound) {
        httpServletRequest.setAttribute("arrayList", arrayList);
        httpServletRequest.setAttribute("sorting", sorting);
        httpServletRequest.setAttribute("category", category);
        getServletContext().getRequestDispatcher("/catalog.jsp").forward(httpServletRequest, httpServletResponse);
//        } else {
//            getServletContext().getRequestDispatcher("/notfound.jsp").forward(httpServletRequest, httpServletResponse);
//        }
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
    }
}


