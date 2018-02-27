/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author izacc
 */
public class Peticion {
    
    private int identificador;
    private int tiempo;
    
    public Peticion(int x)
    {
        this.identificador = x;
        generarAleatorio();
    }

    private void generarAleatorio()
    {
        this.tiempo = ThreadLocalRandom.current().nextInt(1, 6);
    }

    public int getIdentificador() {
        return identificador;
    }

    public int getTiempo() {
        return tiempo;
    }

    @Override
    public String toString() {
        return "Peticion{" + "identificador=" + identificador + ", tiempo=" + tiempo + '}';
    }
       
}
