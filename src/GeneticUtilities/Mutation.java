package GeneticUtilities;

import Chromosome.Chromosome;
import City.City;

import java.util.ArrayList;
import java.util.Random;

public class Mutation {

    public static ArrayList<Chromosome> mutate(ArrayList<Chromosome> chromosomeArrayList, double mutationRate, Random generator) {
        int rate = (int)(10 - (10*mutationRate));
        City temp;

        for (Chromosome chromosome : chromosomeArrayList) {
            if (generator.nextInt(10)+1 > rate) {
                int random1 = generator.nextInt(chromosome.size-1);
                int random2 = generator.nextInt(chromosome.size-1);
                temp = chromosome.get(random1);
                chromosome.add(random1, chromosome.get(random2));
                chromosome.add(random2, temp);
                chromosome.calculateFitness();
            }
        }
        return chromosomeArrayList;
    }

}
