package com.goldensky.vip.middleware;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Observable;

import io.reactivex.Observer;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/28 14:19
 * 包名： com.goldensky.framework.middleware
 * 类说明：
 */
public class NetRequestChain {

    public static class ObserverInvocationHandler implements InvocationHandler {
        private Observer source;

        public ObserverInvocationHandler(Observer observer) {
            source = observer;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            method.invoke(source, args);
            return null;
        }
    }

    public void aa() {

        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        };

        Class<?> proxy = Proxy.getProxyClass(NetRequestChain.class.getClassLoader(), Observer.class);
        try {
            Observer observer = (Observer) proxy.getConstructor(InvocationHandler.class).newInstance(invocationHandler);



        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}
