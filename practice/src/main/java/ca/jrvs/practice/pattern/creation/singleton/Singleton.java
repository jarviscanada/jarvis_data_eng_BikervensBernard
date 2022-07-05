package ca.jrvs.practice.pattern.creation.singleton;

/**
 * Ensure that a class has only one instance, while providing a global access point to this instance.
 * i.e. a single database object shared by different parts of the program.*/
public final class Singleton {

    //A static field containing its only instance
    private static Singleton instance = null;
    private static String db_connectionString = "https://localhost";
    //default private constructor, to prevent other objects from using the new operator with the Singleton class.
    private Singleton() {}
    //A static factory method for obtaining the instance
    public static synchronized Singleton getsynchInstance() {
        if (instance == null) {instance = new Singleton();}
        return instance;
    }
    public static String getDbConnectionString() {return db_connectionString;}
    public static void setDbConnectionString(String s) {db_connectionString = s;}

    public static void main(String[] args) {

        Singleton classSingleton1 = Singleton.getsynchInstance();
        Singleton classSingleton2 = Singleton.getsynchInstance();

        System.out.println(classSingleton1.getDbConnectionString());
        classSingleton2.setDbConnectionString("twitter.com");
        System.out.println("\n" +"If you see the same value, then singleton was reused (yay!)" + "\n" +
                "If you see different values, then 2 singletons were created (booo!!)" + "\nRESULT:");
        System.out.println(classSingleton1.getDbConnectionString());
        System.out.println(classSingleton2.getDbConnectionString());
    }
}

