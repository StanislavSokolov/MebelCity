import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/telegram")
public class TelegramServlet extends HttpServlet {

    boolean start = false;
    TelegramBotsApi botsApi;
    Bot bot;
    String messangeToSend = "";
    long idClient = 0;

    ArrayList<Product> arrayListBeforePreparation;
    ArrayList<Product> arrayList;
    ArrayList<String> arrayListQuantity;
    ArrayList<String> arrayListTotal;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html");

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        arrayList = new ArrayList<>();
        arrayListBeforePreparation = new ArrayList<>();
        arrayListQuantity = new ArrayList<>();
        arrayListTotal = new ArrayList<>();

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        arrayListBeforePreparation = SQL.getArrayList("SELECT * FROM mebelcitycatalog");

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        int costItems = 0;
        int countProduct = 0;

        Cookie[] cookies = httpServletRequest.getCookies();

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

        if (!start) {
            try {
                botsApi = new TelegramBotsApi(DefaultBotSession.class);
                bot = new Bot("botName", "BotToken");
                botsApi.registerBot(bot);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            start = true;
        }

        String function = httpServletRequest.getParameter("function");

        if (function.equals("order")) {
            String name = httpServletRequest.getParameter("name");
            String surname = httpServletRequest.getParameter("surname");
            String address = httpServletRequest.getParameter("address");
            String phone = httpServletRequest.getParameter("phone");
            String email = httpServletRequest.getParameter("email");
            String message = httpServletRequest.getParameter("message");

            messangeToSend =
                    "РАЗМЕЩЕНИЕ ЗАКАЗА"
                            + "\n"
                            + "Клиент: "
                            + name
                            + " "
                            + surname
                            + "\n"
                            + "Адрес доставки: "
                            + address
                            + "\n"
                            + "Телефон: "
                            + phone
                            + "\n"
                            + "E-mail: "
                            + email
                            + "\n"
                            + "Сообщение: "
                            + message
                            + "\n"
                            + "\n";

            for (Product product : arrayList) {
                countProduct = countProduct + 1;
                messangeToSend = messangeToSend
                        + countProduct
                        + ". Наименование: "
                        + product.getName()
                        + "\n"
                        + "Каталожный номер: "
                        + product.getId()
                        + "\n"
                        + "Цена: "
                        + product.getPrice()
                        + "\n"
                        + "Количество: "
                        + product.getQuantity()
                        + "\n"
                        + "Стоимость: "
                        + product.getTotal()
                        + "\n"
                        + "\n";
                SQL.setValue("UPDATE mebelcitycatalog SET ratingOrder = ratingOrder + " + product.getQuantity() + " WHERE Id = " + product.getId());
                System.out.println("UPDATE mebelcitycatalog SET ratingOrder = ratingOrder + " + product.getQuantity() + " WHERE Id = " + product.getId());
            }

            messangeToSend = messangeToSend
                    + "ИТОГО: " + costItems + " руб."
                    + "\n"
                    + "\n";
            bot.setAnswer(idClient, messangeToSend, messangeToSend);
        } else {
            String name = httpServletRequest.getParameter("name");
            String phone = httpServletRequest.getParameter("phone");
            String email = httpServletRequest.getParameter("email");
            String message = httpServletRequest.getParameter("message");
            messangeToSend =
                    "ФОРМА ОБРАТНОЙ СВЯЗИ"
                            + "\n"
                            + "Клиент: "
                            + name
                            + "\n"
                            + "E-mail: "
                            + email
                            + "\n"
                            + "Телефон: "
                            + phone
                            + "\n"
                            + "Сообщение: "
                            + message
                            + "\n"
                            + "\n";
            bot.setAnswer(idClient, messangeToSend, messangeToSend);

        }
    }
}

