
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface 
        extends Remote
{
    void registerUser(ClientInterface ci,
            String name)
            throws RemoteException;
    void sendMessage(String msg)throws RemoteException;

    public void storeindatabase(String s1, String s2, String s3, String s4)throws RemoteException;

    public boolean existemail(String text)throws RemoteException;

    public String getpassword(String email)throws RemoteException;

    public String getpassword(String email, String password)throws RemoteException;

    public String getpassword(String email, int col)throws RemoteException;
    void removeUser(ClientInterface ci,String name) throws RemoteException;
}
