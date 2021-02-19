package tool.autocos.function.mail;

public interface IMail {
    void creat(String email, String pass);
    boolean getToken(String email, String pass);
    void getIdActive();
    void activeCOS();
}
