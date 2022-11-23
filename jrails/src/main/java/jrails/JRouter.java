package jrails;
import java.util.*;
import java.lang.reflect.*;
import java.lang.*;
import java.util.Map;

public class JRouter {
    private HashMap<String, String> routes = new HashMap<>();

    public void addRoute(String verb, String path, Class clazz, String method) {
        String key = verb + path;
        String value = clazz.getName() + "#" + method;
        routes.put(key, value);
    }

    // Returns "clazz#method" corresponding to verb+URN
    // Null if no such route
    public String getRoute(String verb, String path) {
        String key = verb + path;
        String route = routes.get(key);

        if (route != null) {
            String[] splits = route.split("\\.");
            int len = splits.length;
            return splits[len - 1];
        } else {
            return route;
        }
    }

    // Call the appropriate controller method and
    // return the result
    public Html route(String verb, String path, Map<String, String> params) {
        String key = verb + path;
        String route = routes.get(key);

        try {
            String[] arr = route.split("#");
            System.out.println(arr[0] + " is the class name");

            Class<?> c = Class.forName(arr[0]);
            Constructor<?> cons = c.getConstructor();
            Object instance = cons.newInstance();

            Method meth = c.getMethod(arr[1], Map.class);
            instance = meth.invoke(instance, params);
            Html html = (Html) instance;
            return html;
        } catch (Exception e) {
            throw new RuntimeException();
        }


    }
}
