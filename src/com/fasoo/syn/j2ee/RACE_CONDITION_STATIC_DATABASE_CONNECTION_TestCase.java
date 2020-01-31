package com.fasoo.syn.j2ee;

import java.sql.Connection;

public class RACE_CONDITION_STATIC_DATABASE_CONNECTION_TestCase {
    public static Connection con; /* Bug */
}