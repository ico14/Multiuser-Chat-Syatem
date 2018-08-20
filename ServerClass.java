
import database.Databasse;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class ServerClass extends UnicastRemoteObject implements ServerInterface
{
    ServerClass()throws RemoteException
    {
        super();
    }
    Vector<String> v1=new Vector<String>();
    Vector<ClientInterface> v2=new Vector<ClientInterface>();
    @Override
    public void registerUser(ClientInterface ci,
            String name) throws RemoteException {
        v1.add(name);
        v2.add(ci);
        for(int i=0;i<v2.size();i++)
        {
            ClientInterface c=v2.get(i);
            c.refreshList(v1);
        }
        
    }
    @Override
    public void removeUser(ClientInterface ci,String name) throws RemoteException
    {
        v1.remove(name);
        v2.remove(ci);
        for(int i=0;i<v2.size();i++)
        {
            ClientInterface c=v2.get(i);
            c.refreshList(v1);
        }
        
    }
    public static void main(String[] args) {
        try{
        ServerClass ser=new ServerClass();
        Naming.rebind("chat", ser);
        System.out.println("Server Registered!!!");
        }catch(Exception e)
        {}
    }

    @Override
    public void sendMessage(String msg) throws RemoteException 
    {
        for(int i=0;i<v2.size();i++)
        {
            ClientInterface c=v2.get(i);
            c.refreshArea(msg);
        }
    }
    Statement s;
    Connection c;
    @Override
    public void storeindatabase(String fname,String lname,String email,String pass) throws RemoteException
    {
        try
            {
                
              s=Databasse.makeConnection();
              s.executeUpdate("insert into signup(fname,lname,email,password) values('"+fname+"','"+lname+"','"+email+"','"+pass+"')");
              System.out.println("Values saved");
               Databasse.closeConnecton();
                
            }
            catch(Exception e){
               System.out.println(e);
               
            }
        
                    
    }
    @Override
    public boolean existemail(String email)
    {
        int flag=0;
         try
            {
              s=Databasse.makeConnection();
               ResultSet rs=s.executeQuery("select * from signup where email='"+email+"'");
              if(rs.next()){
                flag=1;
                
              }
              else
              {
                flag=0;
              }
              
            }
            catch(Exception e){
               System.out.println(e);
            }
           
            Databasse.closeConnecton();
            if(flag==1)
                return true;
            else
                return false;
    }
    
    @Override
    public String getpassword(String email,String col) throws RemoteException
    {
        String str="";
            try
            {
              s=Databasse.makeConnection();
               ResultSet rs=s.executeQuery("select * from signup where email='"+email+"'");
               if(rs.next())
               {
                   if(rs.getString("email").equals(email))
                   {
                       str=rs.getString(col);
                   }
               }
              
               System.out.println(str);
            }
            catch(Exception e){
               System.out.println(e);
            }
           
            Databasse.closeConnecton();
            return str;
            
    }
}
