package org.velichko.finalproject.tag;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;
import org.velichko.finalproject.logic.entity.User;

public class AnonymousRoleTag extends TagSupport {
    private static final String USER_PARAM = "user";

    @Override
    public int doStartTag() throws JspException {
        User user = (User) pageContext.getSession().getAttribute(USER_PARAM);
        if (user == null) {
            return EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }
    }
}
