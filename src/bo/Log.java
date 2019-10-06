package bo;

public interface Log {

    public static final String FILE_LOG = "./resources/log.csv";
    public abstract void addLog(String str);

}
