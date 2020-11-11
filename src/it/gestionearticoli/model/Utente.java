package it.gestionearticoli.model;

public class Utente {
	private Long id;
	private String nome;
	private String cognome;
	private String codice_fiscale;
	private String username;
	private String password;
	private String ruolo;
	
	public Utente(){}
	
	public Utente(String nome, String cognome, String codice_fiscale, String username, String password, String ruolo) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codice_fiscale = codice_fiscale;
		this.username = username;
		this.password = password;
		this.ruolo = ruolo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodice_fiscale() {
		return codice_fiscale;
	}

	public void setCodice_fiscale(String codice_fiscale) {
		this.codice_fiscale = codice_fiscale;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	@Override
	public String toString() {
		return "Utente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", codice_fiscale=" + codice_fiscale
				+ ", username=" + username + ", password=" + password + ", ruolo=" + ruolo + "]";
	}
	
	
}
