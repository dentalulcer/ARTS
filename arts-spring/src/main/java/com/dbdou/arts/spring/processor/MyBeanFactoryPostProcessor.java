package com.dbdou.arts.spring.processor;

import com.dbdou.arts.spring.bean.TargetBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * Created by dentalulcer
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        GenericBeanDefinition myBean = (GenericBeanDefinition) beanFactory.getBeanDefinition("sourceBean");
        myBean.setBeanClass(TargetBean.class);

    }

}
