package TSP;

import Chromosome.Chromosome;
import City.City;
import City.ReadCities;
import GeneticUtilities.Crossover;
import GeneticUtilities.Mutation;
import GeneticUtilities.Tournament;

import java.io.*;
import java.util.*;

public class TSP {

    public TSP(long seed, int popSize, int k, int maxGen, String crossOver, double crossOverRate, double mutationRate, int runs) {
        Random generator = new Random(seed);
        ArrayList<Chromosome> chromosomeArray = new ArrayList<>();
        ArrayList<City> cities = new ArrayList<>();
        ArrayList<City> temp;
        double bestFitnessGeneration = Double.MAX_VALUE;
        double averageFitnessGeneration = 0.0;
        double totalAverageBestFitness = 0.0;
        double totalAverageFitness = 0.0;
        Chromosome bestChromosome;
        PrintWriter printer = null;

        try {
            printer = new PrintWriter(crossOver+"-"+crossOverRate+"-"+mutationRate+"-"+System.nanoTime()+".csv", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        assert printer != null;
        printer.println("Seed,Pop Size,K-Value,Max Generations,Cross Over Type,Cross Over Rate,Mutation Rate");
        printer.println(seed+","+popSize+","+k+","+maxGen+","+crossOver+","+crossOverRate+","+mutationRate);
        printer.println();

        ReadCities input = new ReadCities();
        try {
            cities = input.readFile("");
        } catch (IOException e1) {
            e1.printStackTrace();
            System.exit(1);
        }

        for (int l = 0; l < runs; l++) {
            chromosomeArray.clear();

            for (int i = 0; i < popSize; i++) {
                temp = (ArrayList<City>) cities.clone();
                Collections.shuffle(temp, generator);
                chromosomeArray.add(new Chromosome(temp));
            }

            printer.println("Generation,Best Fitness,Average Fitness");

            bestChromosome = chromosomeArray.get(0);

            for (int i = 0; i < maxGen; i++) {
                averageFitnessGeneration = 0.0;
                bestFitnessGeneration = Double.MAX_VALUE;
                //Do the tournament selection
                chromosomeArray = Tournament.tournament(chromosomeArray, k, generator);
                //Do the crossover
                chromosomeArray = Crossover.uniformCrossover(chromosomeArray, crossOverRate, generator);
                //Do the mutation
                chromosomeArray = Mutation.mutate(chromosomeArray, mutationRate, generator);

                for (Chromosome chromosome : chromosomeArray) {
                    if (chromosome.fitness < bestChromosome.fitness) {
                        bestChromosome = chromosome;
                    }
                    if (chromosome.fitness < bestFitnessGeneration) {
                        bestFitnessGeneration = chromosome.fitness;
                    }
                    averageFitnessGeneration += chromosome.fitness;
                }
                averageFitnessGeneration = averageFitnessGeneration/chromosomeArray.size();
                printer.println((i+1)+","+bestFitnessGeneration+","+averageFitnessGeneration);
            }
            printer.println();
            printer.println("Run Best Fitness,Chromosome");
            printer.println(bestChromosome.fitness+","+bestChromosome.print());
            printer.println();

            totalAverageBestFitness += bestFitnessGeneration;
            totalAverageFitness += averageFitnessGeneration;
        }

        totalAverageBestFitness = totalAverageBestFitness/(runs);
        totalAverageFitness = totalAverageFitness/(runs);

        printer.println("Total Average Best Fitness,Total Average Fitness");
        printer.println(totalAverageBestFitness+","+totalAverageFitness);
        printer.close();
    }
}
