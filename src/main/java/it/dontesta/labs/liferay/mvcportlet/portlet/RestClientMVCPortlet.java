package it.dontesta.labs.liferay.mvcportlet.portlet;

import it.dontesta.labs.liferay.mvcportlet.constants.RestClientMVCPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;

/**
 * @author amusarra
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=category.sample",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=Sample Rest Client MVC Portlet",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.name=" + RestClientMVCPortletKeys.RestClientMVC,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class RestClientMVCPortlet extends MVCPortlet {

    String baseurl = "https://api.github.com/users/amusarra";

    @Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {

        Response response = WebClient.create(baseurl)
                .path("repos")
                .accept(MediaType.APPLICATION_JSON)
                .get();


        renderRequest.setAttribute("response", response.readEntity(String.class));


        super.render(renderRequest, renderResponse);
    }
}
