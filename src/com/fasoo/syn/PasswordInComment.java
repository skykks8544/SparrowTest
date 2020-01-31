package com.fasoo.syn;

public class PasswordInComment {

    /* password : a!2@3b4a */
    /* 비밀번호 : a!2@3b4a */
    /* password : a!2@3b4 */
    /* password : a12b3cd5 */

    /* password : 1 */
    public PasswordInComment(){

        // This is a password : a!2@3b4a
        // This is not a password : aa !2@3 b4a
        /* password : a1#cd5 */
        /* password : a1d5 */
        /* password : a#5 */
    }
}
