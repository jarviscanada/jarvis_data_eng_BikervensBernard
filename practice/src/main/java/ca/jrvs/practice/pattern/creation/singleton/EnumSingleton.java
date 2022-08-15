package ca.jrvs.practice.pattern.creation.singleton;

public enum EnumSingleton {

    INSTANCE();

    private static String db_connectionString = "https://localhost";

    private EnumSingleton() {}

    public EnumSingleton getInstance() {return INSTANCE;}
    public static String getDbConnectionString() {return db_connectionString;}
    public static void setDbConnectionString(String s) {db_connectionString = s;}
    public static void main(String[] args) {
        EnumSingleton enumSingleton1 = EnumSingleton.INSTANCE.getInstance();
        EnumSingleton enumSingleton2 = EnumSingleton.INSTANCE.getInstance();

        System.out.println(enumSingleton1.getDbConnectionString());
        enumSingleton2.setDbConnectionString("New enum info");

        System.out.println("\n" +"If you see the same value, then singleton was reused (yay!)" + "\n" +
                "If you see different values, then 2 singletons were created (booo!!)" + "\nRESULT:");
        System.out.println(enumSingleton1.getDbConnectionString());
        System.out.println(enumSingleton2.getDbConnectionString());
    }
}
