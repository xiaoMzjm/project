package com.zjm;

/**
 * @author:�ھ�
 * @date:2017/8/15 23:34
 */
public class MyServlet extends HttpServlet {

    @Override
    public void execute(Request request, Response response) {
        response.setResponse("from mySevlet : " + request.getCommand());
    }
}
