/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balancecargas;


import GUI.ventana;
import java.util.ArrayList;
import modelos.Peticion;

/**
 *
 * @author izacc
 */
public class BalanceCargas extends Thread {

    public String nombre ="";
    private ArrayList<Peticion> Nodo;
    private ArrayList<Peticion> ListaTotal;
    public int tam;
    private double tiempoInicial;
    private ventana frame;

    public BalanceCargas(String nombre)
    {
        this.Nodo = new ArrayList<>();
        this.nombre = nombre;
    }
    public BalanceCargas(String nombre, int tam, ArrayList<Peticion> lista, double tiempoInicial,ArrayList<Peticion> ListaTotal)
    {
        this.Nodo = lista;
        this.nombre = nombre;
        this.tam = tam;
        this.ListaTotal = ListaTotal;
        this.tiempoInicial = tiempoInicial;
        
    }
    public void generarLista(int tam)
    {
        ventana frame = new ventana(this.nombre,tam);
        for(int x = 0 ; x < tam ; x++)
        {
            this.Nodo.add(new Peticion(x));
            frame.mostrarTabla(x, Nodo.get(x));
        }
    }
    
    public void run()
    {
        frame = new ventana(this.nombre,this.Nodo.size());
        System.out.println("El Balance de Cargas Comienza a procesar El " 
					+ this.nombre + " en el tiempo : " 
					+ (System.currentTimeMillis() - this.tiempoInicial) / 1000 
					+ " seg");
        int y=1;
        
        for (int i = 0; i < this.Nodo.size(); i++) 
        {                           
                if(this.Nodo.size()>=1)
                {
                    this.esperarXsegundos(Nodo.get(i).getTiempo()); 
                    frame.mostrarTabla(y,Nodo.get(i));
                    System.out.println("Procesado la peticion  " + (y++)
                        + " del Identificador " + this.Nodo.get(i).getIdentificador() + " Del "+""+this.nombre+" ->Tiempo: " 
                        + (System.currentTimeMillis() - this.tiempoInicial) / 1000 
                        + "seg");

                    this.Nodo.remove(i);                   
                    if(this.ListaTotal.size()>0)
                    {
                        this.Nodo.add(this.ListaTotal.get(i));
                        this.ListaTotal.remove(i);
                        
                        i=-1;                       
                    }

                    System.out.println("Queda en la lista: "+this.ListaTotal.size());
                    i=-1;
                }
           
	}
        
        System.out.println("----El balance de Cargas HA TERMINADO DE PROCESAR " 
						+ this.nombre + " EN EL TIEMPO: " 
						+ (System.currentTimeMillis() - this.tiempoInicial) / 1000 
						+ "seg----");

        System.out.println("Tamaño del nodo final : "+this.Nodo.size());
        System.out.println("Tamaño de la lista : "+this.ListaTotal.size());
        
        String mensaje = "El balance de Cargas \nHA TERMINADO DE PROCESAR\nEN EL TIEMPO: " 
						+ (System.currentTimeMillis() - this.tiempoInicial) / 1000 
						+ "seg";
        frame.terminar(mensaje);
        
    }
    
    private void esperarXsegundos(int segundos) 
    {
	try 
        {
            Thread.sleep(segundos * 1000);
	} catch (InterruptedException ex) 
        {
            Thread.currentThread().interrupt();
	}
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Peticion> getLista() {
        return Nodo;
    }

    public void setLista(ArrayList<Peticion> Lista) {
        this.Nodo = Lista;
    }

    public int getTam() {
        return tam;
    }

    public void setTam(int tam) {
        this.tam = tam;
    }

    public ArrayList<Peticion> getNodo() {
        return Nodo;
    }

    public void setNodo(ArrayList<Peticion> Nodo) {
        this.Nodo = Nodo;
    }

    public ArrayList<Peticion> getListaTotal() {
        return ListaTotal;
    }

    public void setListaTotal(ArrayList<Peticion> ListaTotal) {
        this.ListaTotal = ListaTotal;
    }

    public double getTiempoInicial() {
        return tiempoInicial;
    }

    public void setTiempoInicial(double tiempoInicial) {
        this.tiempoInicial = tiempoInicial;
    }
    
}
