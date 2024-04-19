package uz.muhammadtrying.run_out_of_names.config;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uz.muhammadtrying.run_out_of_names.entity.Student;
import uz.muhammadtrying.run_out_of_names.repos.StudentRepo;

import java.io.IOException;
import java.util.*;
@WebFilter(filterName = "checkFilterLogin", urlPatterns = "/*")
public class MyFilter implements Filter {
    List<String> urls = new ArrayList<>(List.of(
            "/student/servlet",
            "/",
            "/index.jsp",
            "/login.jsp",
            "/auth/login"
    ));

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        StudentRepo studentRepo = new StudentRepo();

        String requestURI = req.getRequestURI();
        if (urls.contains(requestURI)) {
            filterChain.doFilter(req, servletResponse);
            return;
        }


        HttpSession session = req.getSession();
        Object object = session.getAttribute("currentUser");

        if (object == null) {
            Optional<Cookie> cookieOptional =
                    Arrays.stream(req.getCookies()).
                            filter(cookie -> cookie.getName().equals("currentUser")).findFirst();

            if (cookieOptional.isPresent()) {
                System.out.println("Cookie found");
                Cookie cookie = cookieOptional.get();
                Integer userId = Integer.parseInt(cookie.getValue());
                Student student = studentRepo.findById(userId);

                session.setAttribute("currentUser", student);
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
        System.out.println("XAtolik");
        resp.sendRedirect("/404");
//        filterChain.doFilter(req, servletResponse);

    }
}
