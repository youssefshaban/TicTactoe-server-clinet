/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

/**
 *
 * @author John
 */
final class Environment {
    public static final String MYSQL_DB_HOST = System.getenv("MYSQL_DB_HOST");
    public static final String MYSQL_DB_PORT = System.getenv("MYSQL_DB_PORT");
    public static final String MYSQL_DB_NAME = System.getenv("MYSQL_DB_NAME");
    public static final String MYSQL_DB_USERNAME = System.getenv("MYSQL_DB_USERNAME");
    public static final String MYSQL_DB_PASSWORD = System.getenv("MYSQL_DB_PASSWORD");

    private Environment() {}
}
