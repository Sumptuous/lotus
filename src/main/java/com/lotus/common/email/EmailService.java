package com.lotus.common.email;

import java.util.HashMap;

/**
 * @author wyy.
 */
public interface EmailService {
    int sendEmail(String username,String emailName,String subject,String template,HashMap<String,Object> content);
}
