package filter;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import managedbean.LoginBean;

@WebFilter(filterName = "CheckLoggedIn", urlPatterns =
{
    "/*"
})
public class CheckLoggedIn implements Filter
{
    private FilterConfig filterConfig = null;
    
    @Inject
    LoginBean user;

    public CheckLoggedIn()
    {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain)
            throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String loginURI = request.getContextPath() + "/faces/Login_UI.xhtml";
        String registerURI = request.getContextPath() + "/faces/Register_UI.xhtml";
        String sharedCssURI = request.getContextPath() + "/faces/javax.faces.resource/shared.css";
        String cssURI = request.getContextPath() + "/faces/javax.faces.resource/notLoggedIn.css";
        String logoURI = request.getContextPath() + "/faces/resources/img/logo.png";
        String bgImgURI = request.getContextPath() + "/faces/resources/css/batthern.png";

        boolean loggedIn = user.credentialsAreOK();
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        boolean registerRequest = request.getRequestURI().equals(registerURI);
        boolean cssRequest = request.getRequestURI().equals(cssURI);
        boolean logoRequest = request.getRequestURI().equals(logoURI);
        boolean sharedCssRequest = request.getRequestURI().equals(sharedCssURI);
        boolean bgImgRequest = request.getRequestURI().equals(bgImgURI);

        if (loggedIn || loginRequest || registerRequest || cssRequest || logoRequest || sharedCssRequest || bgImgRequest)
        {
            chain.doFilter(request, response);
        }
        else
        {
            response.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy()
    {
        this.filterConfig = null;
    }

    @Override
    public void init(FilterConfig filterConfig)
    {
        this.filterConfig = filterConfig;
    }

    public void log(String msg)
    {
        filterConfig.getServletContext().log(msg);
    }
}
