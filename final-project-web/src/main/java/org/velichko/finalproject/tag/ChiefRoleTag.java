package org.velichko.finalproject.tag;

import jakarta.servlet.jsp.tagext.TagSupport;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.type.UserRole;

/**
 * @author Ivan Velichko
 *
 * The type Chief role tag.
 */
public class ChiefRoleTag extends TagSupport {
    private static final String USER_PARAM = "user";

    @Override
    public int doStartTag() {
        User user = (User) pageContext.getSession().getAttribute(USER_PARAM);
        if (user != null && user.getRole() == UserRole.CHIEF) {
            return EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }
    }
}
