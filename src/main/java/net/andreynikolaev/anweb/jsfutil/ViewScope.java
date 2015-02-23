/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.jsfutil;

import java.util.Map;

import javax.faces.context.FacesContext;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class ViewScope implements Scope {
	

	@Override
	@SuppressWarnings("rawtypes")
	public Object get(final String name, final ObjectFactory objectFactory) {
		final Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
                if (viewMap.containsKey(name)) {
			Object bean = viewMap.get(name);			

			// restore a transient autowired beans after re-serialization bean
			WebApplicationContext webAppContext = ContextLoader.getCurrentWebApplicationContext();
			AutowireCapableBeanFactory autowireFactory = webAppContext.getAutowireCapableBeanFactory();
			
			if (webAppContext.containsBean(name)) {
				
				// Reconfigure resored bean instance.
				bean = autowireFactory.configureBean(bean, name);
			}
			// end restore
			
			return bean;
		} else {
			final Object object = objectFactory.getObject();
			viewMap.put(name, object);

			return object;
		}
	}

	@Override
	public String getConversationId() {
		return null;
	}

	@Override
	public void registerDestructionCallback(final String name, final Runnable callback) {
		// Not supported
	}

	@Override
	public Object remove(final String name) {
		return FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove(name);
	}

	@Override
	public Object resolveContextualObject(final String key) {
		return null;
	}
}
