
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;

public interface ServerInterface 
        extends Remote
{
    void registerUser(ClientInterface ci,
            String name)
            throws RemoteException;
    void sendMessage(String msg)throws RemoteException;
    void storeindatabase(String fname,String lname,String email,String pass) throws RemoteException;
    boolean existemail(String email) throws RemoteException;
    String getpassword(String email,String col) throws RemoteException;
    void removeUser(ClientInterface ci,String name) throws RemoteException;
    
}
