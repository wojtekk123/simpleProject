package pl.codeconcept.e2d.service;

public interface SecureService  {
    String findLoggerInUsername ();
    void autoLogin (String username, String password);
}
