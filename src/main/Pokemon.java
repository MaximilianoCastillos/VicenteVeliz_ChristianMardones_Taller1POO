package main;

public class Pokemon {

	private String nombrePokemon;
	private String habitat;
	private double aparicion;
	private int vida;
	private int ataque;
	private int defensa;
	private int ataqueEspecial;
	private int defensaEspecial;
	private int velocidad;
	private String tipo;
	private String estado;

	public Pokemon(String nombrePokemon, String habitat, double aparicion, int vida, int ataque, int defensa,int ataqueEspecial, int defensaEspecial, int velocidad, String tipo) {
				this.nombrePokemon = nombrePokemon;
				this.habitat = habitat;
				this.aparicion = aparicion;
				this.vida = vida;
				this.ataque = ataque;
				this.defensa = defensa;
				this.ataqueEspecial = ataqueEspecial;
				this.defensaEspecial = defensaEspecial;
				this.velocidad = velocidad;
				this.tipo = tipo;
	}

}
