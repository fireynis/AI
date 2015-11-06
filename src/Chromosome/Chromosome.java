package Chromosome;

import City.City;

import java.util.ArrayList;

public class Chromosome {

    ArrayList<City> cities;
    public double fitness;
    public int size;

    public Chromosome(ArrayList<City> cities) {
        this.cities = cities;
        this.calculateFitness();
        this.size = this.cities.size();
    }

    public Chromosome(int size) {
        this.cities = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            this.cities.add(null);
        }
        this.fitness = Integer.MAX_VALUE;
        this.size = size;
    }

    public double calculateFitness() {
        double fitness = 0.0;
        City[] cities = new City[this.cities.size()];
        this.cities.toArray(cities);

        for (int i = 0; i < cities.length; i++) {
            if (i < cities.length - 1 && cities[i] != null) {
                fitness += cities[i].distance(cities[i + 1]);
            }
        }
        fitness += cities[cities.length - 1].distance(cities[0]);
        this.fitness = fitness;
        return fitness;
    }

    public boolean exists(City city) {
        for (City tempCity : this.cities) {
            if (tempCity != null && city.getIndex() == tempCity.getIndex()) {
                return true;
            }
        }
        return false;
    }

    public City get(int index) {
        return this.cities.get(index);
    }

    public void add(int index, City city) {
        this.cities.set(index, city);
        this.size = this.cities.size();
    }

    public String print() {
        String chromosome = "";

        for (City city :
                this.cities) {
            chromosome += city.getIndex()+" ";
        }
        return chromosome.trim();
    }
}
