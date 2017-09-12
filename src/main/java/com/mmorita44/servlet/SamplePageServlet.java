package com.mmorita44.servlet;

import com.mmorita44.ao.PersonAOService;
import lombok.val;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Sample Page Servlet
 *
 * @author Masato Morita
 * @since 1.0.0
 */
public class SamplePageServlet extends HttpServlet
{
    @Autowired
    private PersonAOService personAOService;

    /**
     * Activate the Autowired annotation
     */
    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        val template = new StringWriter();
        val content = new VelocityContext();

        // Render a template with Velocity
        Velocity.getTemplate("src/main/resources/templates/sample.vm", "UTF-8").merge(content, template);

        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().print(template);
    }
}
