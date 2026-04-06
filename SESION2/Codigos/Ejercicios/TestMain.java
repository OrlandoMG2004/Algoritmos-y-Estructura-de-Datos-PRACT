package Ejercicios;

import java.util.ArrayList;


// EJER 9
// 1. Interfaz
interface Cargable {
    double getConsumoVatios();
    int getNivelBateria();
    void cargar(int unidades);
}

// 2. Clase Smartphone
class Smartphone implements Cargable {
    private String modelo;
    private double consumoVatios;
    private int nivelBateria;

    public Smartphone(String modelo, double consumoVatios) {
        this.modelo = modelo;
        this.consumoVatios = consumoVatios;
        this.nivelBateria = 0;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public double getConsumoVatios() {
        return consumoVatios;
    }

    public void setConsumoVatios(double consumoVatios) {
        this.consumoVatios = consumoVatios;
    }

    @Override
    public int getNivelBateria() {
        return nivelBateria;
    }

    public void setNivelBateria(int nivelBateria) {
        this.nivelBateria = nivelBateria;
    }

    @Override
    public void cargar(int unidades) {
        nivelBateria += unidades;
        if (nivelBateria > 100) {
            nivelBateria = 100;
        }
    }

    @Override
    public String toString() {
        return modelo;
    }
}

// 2. Clase Laptop
class Laptop implements Cargable {
    private String marca;
    private double consumoVatios;
    private int nivelBateria;

    public Laptop(String marca, double consumoVatios) {
        this.marca = marca;
        this.consumoVatios = consumoVatios;
        this.nivelBateria = 0;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public double getConsumoVatios() {
        return consumoVatios;
    }

    public void setConsumoVatios(double consumoVatios) {
        this.consumoVatios = consumoVatios;
    }

    @Override
    public int getNivelBateria() {
        return nivelBateria;
    }

    public void setNivelBateria(int nivelBateria) {
        this.nivelBateria = nivelBateria;
    }

    @Override
    public void cargar(int unidades) {
        nivelBateria += unidades;
        if (nivelBateria > 100) {
            nivelBateria = 100;
        }
    }

    @Override
    public String toString() {
        return marca;
    }
}

// 3. Clase genérica restringida
class PowerStation<T extends Cargable> {
    private ArrayList<T> dispositivos;

    public PowerStation() {
        dispositivos = new ArrayList<>();
    }

    public void conectar(T dispositivo) {
        dispositivos.add(dispositivo);
    }

    public double calcularConsumoTotal() {
        double total = 0;
        for (T dispositivo : dispositivos) {
            total += dispositivo.getConsumoVatios();
        }
        return total;
    }

    public int buscarDispositivo(String nombre) {
        for (int i = 0; i < dispositivos.size(); i++) {
            if (dispositivos.get(i).toString().equalsIgnoreCase(nombre)) {
                return i;
            }
        }
        return -1;
    }

    public void mostrarReporte() {
        for (int i = 0; i < dispositivos.size(); i++) {
            T dispositivo = dispositivos.get(i);
            System.out.println(
                "Posición: " + i +
                " | Dispositivo: " + dispositivo +
                " | Consumo: " + dispositivo.getConsumoVatios() + " W" +
                " | Batería: " + dispositivo.getNivelBateria() + "%"
            );
        }
    }
}

// 4. Clase principal
public class TestMain {
    public static void main(String[] args) {
        PowerStation<Smartphone> zonaCelulares = new PowerStation<>();

        Smartphone s1 = new Smartphone("iPhone 15", 20.0);
        Smartphone s2 = new Smartphone("Galaxy S24", 25.0);

        zonaCelulares.conectar(s1);
        zonaCelulares.conectar(s2);

        s1.cargar(30);
        s2.cargar(50);

        zonaCelulares.mostrarReporte();
        System.out.println("Consumo total: " + zonaCelulares.calcularConsumoTotal() + " W");
        System.out.println("Posición de Galaxy S24: " + zonaCelulares.buscarDispositivo("Galaxy S24"));

        System.out.println();

        PowerStation<Laptop> zonaLaptops = new PowerStation<>();

        Laptop l1 = new Laptop("Lenovo", 65.0);
        Laptop l2 = new Laptop("HP", 70.0);

        zonaLaptops.conectar(l1);
        zonaLaptops.conectar(l2);

        l1.cargar(40);
        l2.cargar(60);

        zonaLaptops.mostrarReporte();
        System.out.println("Consumo total: " + zonaLaptops.calcularConsumoTotal() + " W");
        System.out.println("Posición de HP: " + zonaLaptops.buscarDispositivo("HP"));
    }
}