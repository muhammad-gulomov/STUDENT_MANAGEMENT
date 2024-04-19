package uz.muhammadtrying.run_out_of_names.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "log out servlet", value = "/log/out")
public class LogOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().invalidate();

        String currentUserCookieName = "currentUser";
        Cookie currentUserCookie = Arrays.stream(req.getCookies())
                .filter(cookie -> cookie.getName().equals(currentUserCookieName))
                .findFirst().orElse(null);

        if (currentUserCookie != null) {
            currentUserCookie.setMaxAge(0);
            currentUserCookie.setPath("/");
            currentUserCookie.setSecure(false);
            resp.addCookie(currentUserCookie);
        }
        resp.sendRedirect("/");
    }
}
