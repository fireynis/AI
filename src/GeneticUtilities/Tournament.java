package GeneticUtilities;

import Chromosome.Chromosome;

import java.util.ArrayList;
import java.util.Random;

public class Tournament {

    public static ArrayList<Chromosome> tournament(ArrayList<Chromosome> chromosomeArrayList, int k, Random generator) {
        ArrayList<Chromosome> newChromosomes = new ArrayList<>();
        ArrayList<Chromosome> tournament = new ArrayList<>();
        Chromosome bestChromosome;

        while (newChromosomes.size() != chromosomeArrayList.size()) {
            tournament.clear();
            for (int i = 0; i < k; i++) {
                tournament.add(chromosomeArrayList.get(generator.nextInt(chromosomeArrayList.size()-1)));
            }
            bestChromosome = tournament.get(0);
            for (Chromosome chromosome : tournament) {
                if (chromosome.fitness < bestChromosome.fitness) {
                    bestChromosome = chromosome;
                }
            }
            newChromosomes.add(bestChromosome);
        }
        return newChromosomes;
    }
}
