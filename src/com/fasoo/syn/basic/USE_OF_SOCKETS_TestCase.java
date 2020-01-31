package com.fasoo.syn.basic;

import javax.ejb.EJBHome;
import javax.ejb.EJBObject;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.Properties;

/*
    USE_OF_SOCKETS Test case
 */
public class USE_OF_SOCKETS_TestCase {
    public static void main(String args[]) throws Exception{
        ServerSocket serverSocket = new ServerSocket(1122);
        Socket clientSocket = serverSocket.accept();
        serverSocket.accept();
        new ServerSocket(1122).accept();

        Properties p = new Properties();
        p.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        p.put(Context.PROVIDER_URL, "t3://localhost:7001");

        Context ctx = new InitialContext(p);

        Object h = ctx.lookup("HelloWorldBean");
        HelloWorldHome home = (HelloWorldHome)PortableRemoteObject.narrow(h, HelloWorldHome.class);
        HelloWorld helloWorld = home.create("lee yong hoi");
        System.out.println(helloWorld.helloWorld());
    }
}

class HelloWorldBean implements SessionBean{
    private SessionContext ctx;
    private String name;

    public void setSessionContext(SessionContext c){
        ctx = c;
    }

    public String helloWorld(){
        try{
            ServerSocket serverSocket = new ServerSocket(1122);
            Socket clientSocket = serverSocket.accept();		/* Bug */
            serverSocket.accept();										/* Bug */
            new ServerSocket(1122).accept();						/* Bug */
        }catch(IOException ioe){}

        return name + "님! Hello World";
    }

    public void ejbCreate(String name){
        this.name = name;
    }

    public void ejbRemove(){}

    public void ejbActivate(){}

    public void ejbPassivate(){}
}

interface HelloWorldHome extends EJBHome{
    public HelloWorld create(String name) throws RemoteException;
}

interface HelloWorld extends EJBObject{
    public String helloWorld() throws RemoteException;
}
