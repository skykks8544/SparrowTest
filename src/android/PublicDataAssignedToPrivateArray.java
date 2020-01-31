package android;

public class PublicDataAssignedToPrivateArray {
	private String[] userRoles = { "abc", "def", "ghi" };

	public void unsafe(String[] userRoles) {
		this.userRoles = userRoles;         /* Bug */
	}

    public void safe(String[] userRoles){
        this.userRoles = new String[userRoles.length];
        for(int i = 0; i < userRoles.length; i++){
            this.userRoles[i] = userRoles[i];
        }
    }
}