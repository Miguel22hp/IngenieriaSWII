package main;

import java.util.ArrayList;
import java.util.List;

public class Arrendatario
{
    @Override
    public String toString() {
        return "Arrendatario{" +
                "dni='" + dni + '\'' +
                ", edad=" + edad +
                ", sexo=" + sexo +
                ", aval='" + aval + '\'' +
                ", alquileres=" + alquileres +
                '}';
    }

    String dni;
    int edad;
    char sexo;
    String aval;
    List<Integer> alquileres;
    public Arrendatario(String dni, int edad, char sexo, String aval)
	{
        this.dni = dni;
        this.edad = edad;
        this.sexo = sexo;
        this.aval = aval;
        alquileres = new ArrayList<>();
    }

    public String getDni() {
        return dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getAval() {
        return aval;
    }

    public void setAval(String aval) {
        this.aval = aval;
    }

    public List<Integer> getAlquileres() {
        return alquileres;
    }

    public void setAlquileres(List<Integer> alquileres) {
        this.alquileres = alquileres;
    }
}