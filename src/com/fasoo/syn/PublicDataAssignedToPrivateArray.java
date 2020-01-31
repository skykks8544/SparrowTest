package com.fasoo.syn;

class Element {
    public String s;
    public String[] sa;
}

public class PublicDataAssignedToPrivateArray {
    private String[] userRoles;
    private String[][] userDRoles;

    public String[] publicRoles;

    public void setUserRoles(String[] userRoles, Element e) {
        String k = userRoles[0];

        this.userRoles = publicRoles; // Violation
        this.userRoles[0] = k; // Violation

        this.userRoles = userRoles; // Violation
        this.userRoles[0] = k; // Violation

        this.userRoles = e.sa; // Violation
        this.userRoles[0] = e.s; // Violation
        this.userRoles[1] = e.sa[0]; // Violation

        this.userRoles = (String[])new String[userRoles.length];
        this.userRoles = userRoles; // NOT violation since the array is safe
        this.userRoles[0] = userRoles[0]; // NOT violation since the array is safe

        this.userDRoles[0][1] = userRoles[1]; // Violation
        this.userDRoles[0] = userRoles; // Violation

        this.userDRoles = new String[5][2];
        this.userDRoles[0][1] = userRoles[1]; // NOT violation since the array is safe
        this.userDRoles[0] = userRoles; // NOT violation since the array is safe
    }
}
