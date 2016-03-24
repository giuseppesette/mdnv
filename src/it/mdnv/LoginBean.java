package it.mdnv;

public class LoginBean {
	private String name;
    private String password;
    private Boolean logged;

    

    public Boolean getLogged() {
		return logged;
	}


	public void setLogged(final Boolean logged) {
		this.logged = logged;
	}


	public String getName ()
    {
        return name;
    }


    public void setName (final String name)
    {
        this.name = name;
    }


    public String getPassword ()
    {
        return password;
    }


    public void setPassword (final String password)
    {
        this.password = password;
    }
}// end class
