package it.gestionearticoli.model;

public class Categoria {
	private Long id;
	private String descrizione;
	
	public Categoria() {}

	public Categoria(String descrizione) {
		super();
		this.descrizione = descrizione;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		return "Categoria [id: " + id + ", descrizione: " + descrizione + "]";
	}
	
	
}
