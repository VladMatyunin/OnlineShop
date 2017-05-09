package ru.kpfu.itis.config;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by vladislav on 12.02.17.
 */
public class SessionConfig implements HttpSessionListener {
    @Override
    //TODO add constant
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setMaxInactiveInterval(60*24*3);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
