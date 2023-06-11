package org.dog.server.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @Author: Odin
 * @Date: 2023/6/3 23:09
 * @Description:
 */
public class RepeatableReadRequest extends HttpServletRequestWrapper {

    public RepeatableReadRequest(HttpServletRequest request) {
        super(request);
    }
}
