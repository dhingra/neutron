package com.rd.neutron.quickfix.spring;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import quickfix.*;

/**
 * Wrapper around the @{link quickfix.SocketAcceptor} to be used for creation via Spring
 * that adds the @{link InitializingBean} and @{link DisposableBean} behaviour. 
 * @author
 * @version
 * @since
 */

public class SocketAcceptor extends quickfix.SocketAcceptor implements InitializingBean, DisposableBean {

    public SocketAcceptor(Application application, MessageStoreFactory messageStoreFactory,
                          SessionSettings settings, LogFactory logFactory, MessageFactory messageFactory) throws ConfigError {
        super(application, messageStoreFactory, settings, logFactory, messageFactory);
    }

    public SocketAcceptor(SessionFactory sessionFactory, SessionSettings settings) throws ConfigError
    {
	super(sessionFactory, settings);
    }

    public void afterPropertiesSet() throws Exception {
    	start();
    }

    public void destroy() throws Exception {
        stop(true);
    }
}
