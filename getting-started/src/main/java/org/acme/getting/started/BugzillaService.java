package org.acme.getting.started;

import javax.enterprise.context.ApplicationScoped;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfig;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

@ApplicationScoped
public class BugzillaService {

    public Map<String, Object> getIssue(String trackerId) {
        Map<String, Object> loginDetails = new HashMap<>();
        String username = System.getProperty("username");
        System.err.println("login with username: " + username);
        loginDetails.put("Bugzilla_login", username);
        String password = System.getProperty("password");
        loginDetails.put("Bugzilla_password", password);

        Map<String, Object> params = new HashMap<>(loginDetails);
        params.put(BugzillaConstants.RESULT_INCLUDE_FIELDS, BugzillaConstants.RESULT_FIELDS);
        params.put(BugzillaConstants.ISSUE_IDS, trackerId);
        params.put(BugzillaConstants.RESULT_PERMISSIVE_SEARCH, true);

        Map<String, ?> resultMap = executeRequest(XMLRPC.RPC_STRUCT, BugzillaConstants.METHOD_GET_BUG, params);
        Object[] bugs = (Object[]) resultMap.get(BugzillaConstants.RESULT_BUGS);
            @SuppressWarnings("unchecked")
            Map<String, Object> results = (Map<String, Object>) bugs[0];
            return results;
    }

    private <T> T executeRequest(final XMLRPC<T> type, String method, Object... params) {
        try {
            return type.cast(getRpcClient().execute(method, params));
        } catch (XmlRpcException e) {
            throw new RuntimeException(e); // TODO improve exception handling
        }
    }

    private XmlRpcClient getRpcClient() {
        String apiURL = "https://bugzilla.redhat.com/" + "xmlrpc.cgi";
        XmlRpcClient rpcClient;
        rpcClient = new XmlRpcClient();

        try {
            URL url = new URL(apiURL);
            rpcClient.setConfig(getClientConfig(url));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return rpcClient;
    }

    private XmlRpcClientConfig getClientConfig(URL apiURL) {
        XmlRpcClientConfigImpl config;
        config = new XmlRpcClientConfigImpl();
        config.setServerURL(apiURL);
        return config;
    }

    private static class XMLRPC<T> {
        static final XMLRPC<Object[]> RPC_ARRAY = new XMLRPC<>(Object[].class);
        static final XMLRPC<Map<String, Object>> RPC_STRUCT = new XMLRPC<>(Map.class);

        final Class<T> cls;

        @SuppressWarnings("unchecked") XMLRPC(final Class<?> cls) {
            this.cls = (Class<T>) cls;
        }

        T cast(final Object obj) {
            return cls.cast(obj);
        }

        static <T> T cast(final XMLRPC<T> type, Object obj) {
            return type.cast(obj);
        }

        static <T> Iterable<T> iterable(final XMLRPC<T> type, final Collection<Object> c) {
            final Iterator<Object> it = c.iterator();
            return () -> new Iterator<T>() {
                @Override
                public boolean hasNext() {
                    return it.hasNext();
                }

                @Override
                public T next() {
                    return type.cast(it.next());
                }

                @Override
                public void remove() {
                    it.remove();
                }
            };
        }

        static <T> Iterable<T> iterable(final XMLRPC<T> type, final Object[] array) {
            final Iterator<Object> it = Arrays.asList(array).iterator();
            return () -> new Iterator<T>() {
                @Override
                public boolean hasNext() {
                    return it.hasNext();
                }

                @Override
                public T next() {
                    return type.cast(it.next());
                }

                @Override
                public void remove() {
                    it.remove();
                }
            };
        }
    }
}
