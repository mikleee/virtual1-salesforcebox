package com.virtual1.salesforcebox.controller.view;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

/**
 * Contains the common functionality for all web controllers
 *
 * @author Mikhail Tkachenko
 */
@Controller
public abstract class BaseController {
    private Logger logger = Logger.getLogger(getClass());

}