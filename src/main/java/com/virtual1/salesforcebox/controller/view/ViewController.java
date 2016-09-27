package com.virtual1.salesforcebox.controller.view;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import javax.portlet.RenderRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;

/**
 * The web controller which handles theme switcher portlet requests in the VIEW mode
 *
 * @author Mikhail Tkachenko
 */
@Controller
@RequestMapping(value = "VIEW")
public class ViewController extends BaseController {

    @RenderMapping
    public ModelAndView render(RenderRequest request, ModelMap map) throws SystemException, PortalException, IOException {
        return new ModelAndView("salesforce-box/view", map);
    }

    @ExceptionHandler({Exception.class})
    public void handleApplicationError(Exception e, ResourceResponse response) throws IOException {

    }

}